-- Création des types ENUM
CREATE TYPE collectivity_status AS ENUM ('PENDING', 'ACTIVE', 'SUSPENDED');
CREATE TYPE member_status AS ENUM ('ACTIVE', 'INACTIVE', 'SUSPENDED');
CREATE TYPE payment_type AS ENUM ('MONTHLY', 'ANNUAL', 'EXCEPTIONAL');
CREATE TYPE account_type AS ENUM ('CASH', 'BANK', 'MOBILE_MONEY');
CREATE TYPE activity_type AS ENUM ('MANDATORY', 'EXCEPTIONAL');

-- Table des collectivités
CREATE TABLE collectivities (
                                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                name VARCHAR(100) NOT NULL,
                                city VARCHAR(100) NOT NULL,
                                specialty VARCHAR(100),
                                creation_date DATE NOT NULL,
                                validated_by_federation BOOLEAN DEFAULT FALSE,
                                status collectivity_status DEFAULT 'PENDING',
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table des rôles
CREATE TABLE roles (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       name VARCHAR(50) NOT NULL UNIQUE,
                       description TEXT
);

-- Insertion des rôles par défaut
INSERT INTO roles (name, description) VALUES
                                          ('PRESIDENT', 'Président de la collectivité'),
                                          ('TRESORIER', 'Trésorier de la collectivité'),
                                          ('SECRETAIRE', 'Secrétaire de la collectivité'),
                                          ('VICE_PRESIDENT', 'Vice-président'),
                                          ('CONSEILLER', 'Conseiller');

-- Table des membres
CREATE TABLE members (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         first_name VARCHAR(50) NOT NULL,
                         last_name VARCHAR(50) NOT NULL,
                         birth_date DATE NOT NULL,
                         email VARCHAR(100) UNIQUE,
                         phone VARCHAR(20) UNIQUE NOT NULL,
                         address TEXT,
                         collectivity_id UUID REFERENCES collectivities(id),
                         sponsor_id UUID REFERENCES members(id),
                         join_date DATE,
                         status member_status DEFAULT 'INACTIVE',
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table des mandats
CREATE TABLE mandates (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          member_id UUID NOT NULL REFERENCES members(id),
                          role_id UUID NOT NULL REFERENCES roles(id),
                          collectivity_id UUID NOT NULL REFERENCES collectivities(id),
                          start_date DATE NOT NULL,
                          end_date DATE,
                          is_active BOOLEAN DEFAULT TRUE,
                          UNIQUE(member_id, role_id, collectivity_id)
);

-- Table des comptes
CREATE TABLE accounts (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          name VARCHAR(100) NOT NULL,
                          type account_type NOT NULL,
                          balance DECIMAL(15,2) DEFAULT 0,
                          collectivity_id UUID REFERENCES collectivities(id),
                          is_active BOOLEAN DEFAULT TRUE,
                          UNIQUE(type, collectivity_id)
);

-- Table des paiements
CREATE TABLE payments (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          member_id UUID NOT NULL REFERENCES members(id),
                          account_id UUID NOT NULL REFERENCES accounts(id),
                          amount DECIMAL(15,2) NOT NULL,
                          type payment_type NOT NULL,
                          payment_date DATE NOT NULL DEFAULT CURRENT_DATE,
                          reference VARCHAR(50),
                          description TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table des activités
CREATE TABLE activities (
                            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                            title VARCHAR(200) NOT NULL,
                            description TEXT,
                            activity_date TIMESTAMP NOT NULL,
                            type activity_type NOT NULL,
                            collectivity_id UUID NOT NULL REFERENCES collectivities(id),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table des présences
CREATE TABLE attendances (
                             id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                             activity_id UUID NOT NULL REFERENCES activities(id),
                             member_id UUID NOT NULL REFERENCES members(id),
                             present BOOLEAN DEFAULT FALSE,
                             justification TEXT,
                             UNIQUE(activity_id, member_id)
);

-- Index pour optimisation
CREATE INDEX idx_members_collectivity ON members(collectivity_id);
CREATE INDEX idx_members_sponsor ON members(sponsor_id);
CREATE INDEX idx_payments_member ON payments(member_id);
CREATE INDEX idx_payments_account ON payments(account_id);
CREATE INDEX idx_mandates_member ON mandates(member_id);
CREATE INDEX idx_activities_collectivity ON activities(collectivity_id);
CREATE INDEX idx_attendances_activity ON attendances(activity_id);



CREATE TYPE sponsorship_relation_type AS ENUM ('FAMILY', 'FRIEND', 'COLLEAGUE', 'NEIGHBOR', 'OTHER');


CREATE TABLE sponsorships (
                              id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                              candidate_id UUID NOT NULL REFERENCES members(id) ON DELETE CASCADE,
                              sponsor_id UUID NOT NULL REFERENCES members(id) ON DELETE CASCADE,
                              relation_type sponsorship_relation_type NOT NULL,
                              relation_details VARCHAR(100),
                              UNIQUE(candidate_id, sponsor_id)
);


ALTER TABLE collectivities ADD COLUMN annual_fee_amount DECIMAL(15,2) DEFAULT 0;


CREATE INDEX idx_sponsorships_candidate ON sponsorships(candidate_id);
CREATE INDEX idx_sponsorships_sponsor ON sponsorships(sponsor_id);
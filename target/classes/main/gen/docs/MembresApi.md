# MembresApi

All URIs are relative to *http://localhost:8080/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createMember**](MembresApi.md#createMember) | **POST** /members | Créer un membre (candidat) |
| [**joinMember**](MembresApi.md#joinMember) | **POST** /members/{id}/join | Faire adhérer un membre (validation des conditions) |
| [**leaveMember**](MembresApi.md#leaveMember) | **POST** /members/{id}/leave | Faire quitter un membre |
| [**transferMember**](MembresApi.md#transferMember) | **POST** /members/{id}/transfer | Transférer un membre vers une autre collectivité |


<a id="createMember"></a>
# **createMember**
> MemberResponse createMember(memberRequest)

Créer un membre (candidat)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MembresApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    MembresApi apiInstance = new MembresApi(defaultClient);
    MemberRequest memberRequest = new MemberRequest(); // MemberRequest | 
    try {
      MemberResponse result = apiInstance.createMember(memberRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembresApi#createMember");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **memberRequest** | [**MemberRequest**](MemberRequest.md)|  | |

### Return type

[**MemberResponse**](MemberResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Membre créé avec succès |  -  |
| **400** | Données invalides |  -  |

<a id="joinMember"></a>
# **joinMember**
> MemberResponse joinMember(id)

Faire adhérer un membre (validation des conditions)

Nouvelles conditions (21/04/2026) : - Être parrainé par au moins deux membres confirmés (≥ 90 jours d&#39;ancienneté et actifs) - Le nombre de parrains issus de la collectivité cible doit être ≥ au nombre de parrains d&#39;autres collectivités - Paiement des frais d&#39;adhésion (50 000 MGA) + cotisation annuelle de la collectivité - Informations personnelles complètes 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MembresApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    MembresApi apiInstance = new MembresApi(defaultClient);
    UUID id = UUID.randomUUID(); // UUID | 
    try {
      MemberResponse result = apiInstance.joinMember(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembresApi#joinMember");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **UUID**|  | |

### Return type

[**MemberResponse**](MemberResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Adhésion réussie |  -  |
| **400** | Conditions non remplies |  -  |

<a id="leaveMember"></a>
# **leaveMember**
> MemberResponse leaveMember(id)

Faire quitter un membre

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MembresApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    MembresApi apiInstance = new MembresApi(defaultClient);
    UUID id = UUID.randomUUID(); // UUID | 
    try {
      MemberResponse result = apiInstance.leaveMember(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembresApi#leaveMember");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **UUID**|  | |

### Return type

[**MemberResponse**](MemberResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Départ enregistré |  -  |

<a id="transferMember"></a>
# **transferMember**
> MemberResponse transferMember(id, transferMemberRequest)

Transférer un membre vers une autre collectivité

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MembresApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    MembresApi apiInstance = new MembresApi(defaultClient);
    UUID id = UUID.randomUUID(); // UUID | 
    TransferMemberRequest transferMemberRequest = new TransferMemberRequest(); // TransferMemberRequest | 
    try {
      MemberResponse result = apiInstance.transferMember(id, transferMemberRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembresApi#transferMember");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **UUID**|  | |
| **transferMemberRequest** | [**TransferMemberRequest**](TransferMemberRequest.md)|  | |

### Return type

[**MemberResponse**](MemberResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Transfert réussi |  -  |
| **400** | Erreur lors du transfert |  -  |


package com.federation.dto;

import lombok.Data;

@Data
public class CollectivityStructureDto {
    private MemberDto president;
    private MemberDto vicePresident;
    private MemberDto treasurer;
    private MemberDto secretary;
}
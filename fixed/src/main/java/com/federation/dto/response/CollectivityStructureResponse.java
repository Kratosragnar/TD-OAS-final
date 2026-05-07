package com.federation.dto.response;

import lombok.Data;

@Data
public class CollectivityStructureResponse {
    private MemberResponse president;
    private MemberResponse vicePresident;
    private MemberResponse treasurer;
    private MemberResponse secretary;
}
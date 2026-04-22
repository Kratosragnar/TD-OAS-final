# StatistiquesApi

All URIs are relative to *http://localhost:8080/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getCollectivityStats**](StatistiquesApi.md#getCollectivityStats) | **GET** /statistics/collectivities/{id} | Statistiques d&#39;une collectivité |
| [**getMemberStats**](StatistiquesApi.md#getMemberStats) | **GET** /statistics/members/{id} | Statistiques d&#39;un membre |


<a id="getCollectivityStats"></a>
# **getCollectivityStats**
> CollectivityStats getCollectivityStats(id)

Statistiques d&#39;une collectivité

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StatistiquesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    StatistiquesApi apiInstance = new StatistiquesApi(defaultClient);
    UUID id = UUID.randomUUID(); // UUID | 
    try {
      CollectivityStats result = apiInstance.getCollectivityStats(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatistiquesApi#getCollectivityStats");
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

[**CollectivityStats**](CollectivityStats.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Statistiques de la collectivité |  -  |

<a id="getMemberStats"></a>
# **getMemberStats**
> MemberStats getMemberStats(id)

Statistiques d&#39;un membre

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StatistiquesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    StatistiquesApi apiInstance = new StatistiquesApi(defaultClient);
    UUID id = UUID.randomUUID(); // UUID | 
    try {
      MemberStats result = apiInstance.getMemberStats(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatistiquesApi#getMemberStats");
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

[**MemberStats**](MemberStats.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Statistiques du membre |  -  |


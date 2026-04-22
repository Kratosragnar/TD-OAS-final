# StatistiquesFdralesApi

All URIs are relative to *http://localhost:8080/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getFederationAttendanceRate**](StatistiquesFdralesApi.md#getFederationAttendanceRate) | **GET** /statistics/federation/attendance-rate | Taux d&#39;assiduité global par collectivité sur une période donnée |
| [**getNewMembersCount**](StatistiquesFdralesApi.md#getNewMembersCount) | **GET** /statistics/federation/new-members | Nombre de nouveaux adhérents par collectivité sur une période donnée |
| [**getUpToDateMembersPercentage**](StatistiquesFdralesApi.md#getUpToDateMembersPercentage) | **GET** /statistics/federation/up-to-date-members | Pourcentage de membres à jour de cotisation par collectivité |


<a id="getFederationAttendanceRate"></a>
# **getFederationAttendanceRate**
> List&lt;CollectivityAttendanceRate&gt; getFederationAttendanceRate(startDate, endDate)

Taux d&#39;assiduité global par collectivité sur une période donnée

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StatistiquesFdralesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    StatistiquesFdralesApi apiInstance = new StatistiquesFdralesApi(defaultClient);
    LocalDate startDate = LocalDate.now(); // LocalDate | Date de début (format YYYY-MM-DD)
    LocalDate endDate = LocalDate.now(); // LocalDate | Date de fin (format YYYY-MM-DD)
    try {
      List<CollectivityAttendanceRate> result = apiInstance.getFederationAttendanceRate(startDate, endDate);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatistiquesFdralesApi#getFederationAttendanceRate");
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
| **startDate** | **LocalDate**| Date de début (format YYYY-MM-DD) | |
| **endDate** | **LocalDate**| Date de fin (format YYYY-MM-DD) | |

### Return type

[**List&lt;CollectivityAttendanceRate&gt;**](CollectivityAttendanceRate.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Taux d&#39;assiduité par collectivité |  -  |

<a id="getNewMembersCount"></a>
# **getNewMembersCount**
> List&lt;CollectivityNewMembers&gt; getNewMembersCount(startDate, endDate)

Nombre de nouveaux adhérents par collectivité sur une période donnée

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StatistiquesFdralesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    StatistiquesFdralesApi apiInstance = new StatistiquesFdralesApi(defaultClient);
    LocalDate startDate = LocalDate.now(); // LocalDate | 
    LocalDate endDate = LocalDate.now(); // LocalDate | 
    try {
      List<CollectivityNewMembers> result = apiInstance.getNewMembersCount(startDate, endDate);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatistiquesFdralesApi#getNewMembersCount");
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
| **startDate** | **LocalDate**|  | |
| **endDate** | **LocalDate**|  | |

### Return type

[**List&lt;CollectivityNewMembers&gt;**](CollectivityNewMembers.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Nombre de nouveaux adhérents par collectivité |  -  |

<a id="getUpToDateMembersPercentage"></a>
# **getUpToDateMembersPercentage**
> List&lt;CollectivityPaymentStatus&gt; getUpToDateMembersPercentage(referenceDate)

Pourcentage de membres à jour de cotisation par collectivité

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StatistiquesFdralesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    StatistiquesFdralesApi apiInstance = new StatistiquesFdralesApi(defaultClient);
    LocalDate referenceDate = LocalDate.now(); // LocalDate | Date de référence pour vérifier les cotisations à jour
    try {
      List<CollectivityPaymentStatus> result = apiInstance.getUpToDateMembersPercentage(referenceDate);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatistiquesFdralesApi#getUpToDateMembersPercentage");
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
| **referenceDate** | **LocalDate**| Date de référence pour vérifier les cotisations à jour | |

### Return type

[**List&lt;CollectivityPaymentStatus&gt;**](CollectivityPaymentStatus.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Pourcentage de membres à jour par collectivité |  -  |


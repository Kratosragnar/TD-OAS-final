# PrsencesApi

All URIs are relative to *http://localhost:8080/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createAttendance**](PrsencesApi.md#createAttendance) | **POST** /attendances | Enregistrer une présence |
| [**findAttendancesByActivity**](PrsencesApi.md#findAttendancesByActivity) | **GET** /activities/{activityId}/attendances | Présences d&#39;une activité |


<a id="createAttendance"></a>
# **createAttendance**
> AttendanceResponse createAttendance(attendanceRequest)

Enregistrer une présence

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PrsencesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    PrsencesApi apiInstance = new PrsencesApi(defaultClient);
    AttendanceRequest attendanceRequest = new AttendanceRequest(); // AttendanceRequest | 
    try {
      AttendanceResponse result = apiInstance.createAttendance(attendanceRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PrsencesApi#createAttendance");
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
| **attendanceRequest** | [**AttendanceRequest**](AttendanceRequest.md)|  | |

### Return type

[**AttendanceResponse**](AttendanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Présence enregistrée |  -  |

<a id="findAttendancesByActivity"></a>
# **findAttendancesByActivity**
> List&lt;AttendanceResponse&gt; findAttendancesByActivity(activityId)

Présences d&#39;une activité

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PrsencesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    PrsencesApi apiInstance = new PrsencesApi(defaultClient);
    UUID activityId = UUID.randomUUID(); // UUID | 
    try {
      List<AttendanceResponse> result = apiInstance.findAttendancesByActivity(activityId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PrsencesApi#findAttendancesByActivity");
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
| **activityId** | **UUID**|  | |

### Return type

[**List&lt;AttendanceResponse&gt;**](AttendanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des présences |  -  |


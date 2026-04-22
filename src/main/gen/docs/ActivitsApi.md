# ActivitsApi

All URIs are relative to *http://localhost:8080/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createActivity**](ActivitsApi.md#createActivity) | **POST** /activities | Créer une activité |
| [**findAllActivities**](ActivitsApi.md#findAllActivities) | **GET** /activities | Lister toutes les activités |


<a id="createActivity"></a>
# **createActivity**
> ActivityResponse createActivity(activityRequest)

Créer une activité

Types disponibles : MANDATORY, EXCEPTIONAL

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ActivitsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    ActivitsApi apiInstance = new ActivitsApi(defaultClient);
    ActivityRequest activityRequest = new ActivityRequest(); // ActivityRequest | 
    try {
      ActivityResponse result = apiInstance.createActivity(activityRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ActivitsApi#createActivity");
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
| **activityRequest** | [**ActivityRequest**](ActivityRequest.md)|  | |

### Return type

[**ActivityResponse**](ActivityResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Activité créée |  -  |

<a id="findAllActivities"></a>
# **findAllActivities**
> List&lt;ActivityResponse&gt; findAllActivities(type)

Lister toutes les activités

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ActivitsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    ActivitsApi apiInstance = new ActivitsApi(defaultClient);
    String type = "MANDATORY"; // String | 
    try {
      List<ActivityResponse> result = apiInstance.findAllActivities(type);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ActivitsApi#findAllActivities");
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
| **type** | **String**|  | [optional] [enum: MANDATORY, EXCEPTIONAL] |

### Return type

[**List&lt;ActivityResponse&gt;**](ActivityResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des activités |  -  |


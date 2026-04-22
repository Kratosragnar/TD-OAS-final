# PaiementsApi

All URIs are relative to *http://localhost:8080/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPayment**](PaiementsApi.md#createPayment) | **POST** /payments | Enregistrer un paiement |
| [**findAllPayments**](PaiementsApi.md#findAllPayments) | **GET** /payments | Lister tous les paiements |
| [**findPaymentsByMember**](PaiementsApi.md#findPaymentsByMember) | **GET** /members/{memberId}/payments | Paiements d&#39;un membre |


<a id="createPayment"></a>
# **createPayment**
> PaymentResponse createPayment(paymentRequest)

Enregistrer un paiement

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PaiementsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    PaiementsApi apiInstance = new PaiementsApi(defaultClient);
    PaymentRequest paymentRequest = new PaymentRequest(); // PaymentRequest | 
    try {
      PaymentResponse result = apiInstance.createPayment(paymentRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PaiementsApi#createPayment");
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
| **paymentRequest** | [**PaymentRequest**](PaymentRequest.md)|  | |

### Return type

[**PaymentResponse**](PaymentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Paiement enregistré |  -  |

<a id="findAllPayments"></a>
# **findAllPayments**
> List&lt;PaymentResponse&gt; findAllPayments(type, startDate, endDate)

Lister tous les paiements

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PaiementsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    PaiementsApi apiInstance = new PaiementsApi(defaultClient);
    String type = "MONTHLY"; // String | 
    LocalDate startDate = LocalDate.now(); // LocalDate | 
    LocalDate endDate = LocalDate.now(); // LocalDate | 
    try {
      List<PaymentResponse> result = apiInstance.findAllPayments(type, startDate, endDate);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PaiementsApi#findAllPayments");
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
| **type** | **String**|  | [optional] [enum: MONTHLY, ANNUAL, EXCEPTIONAL] |
| **startDate** | **LocalDate**|  | [optional] |
| **endDate** | **LocalDate**|  | [optional] |

### Return type

[**List&lt;PaymentResponse&gt;**](PaymentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des paiements |  -  |

<a id="findPaymentsByMember"></a>
# **findPaymentsByMember**
> List&lt;PaymentResponse&gt; findPaymentsByMember(memberId)

Paiements d&#39;un membre

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PaiementsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    PaiementsApi apiInstance = new PaiementsApi(defaultClient);
    UUID memberId = UUID.randomUUID(); // UUID | 
    try {
      List<PaymentResponse> result = apiInstance.findPaymentsByMember(memberId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PaiementsApi#findPaymentsByMember");
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
| **memberId** | **UUID**|  | |

### Return type

[**List&lt;PaymentResponse&gt;**](PaymentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des paiements du membre |  -  |


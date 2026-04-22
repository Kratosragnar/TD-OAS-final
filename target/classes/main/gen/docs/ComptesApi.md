# ComptesApi

All URIs are relative to *http://localhost:8080/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createAccount**](ComptesApi.md#createAccount) | **POST** /accounts | Créer un compte |
| [**findAccountById**](ComptesApi.md#findAccountById) | **GET** /accounts/{id} | Obtenir un compte par son ID |
| [**findAllAccounts**](ComptesApi.md#findAllAccounts) | **GET** /accounts | Lister tous les comptes |


<a id="createAccount"></a>
# **createAccount**
> AccountResponse createAccount(accountRequest)

Créer un compte

Types disponibles : CASH, BANK, MOBILE_MONEY Une seule caisse (CASH) autorisée par collectivité. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ComptesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    ComptesApi apiInstance = new ComptesApi(defaultClient);
    AccountRequest accountRequest = new AccountRequest(); // AccountRequest | 
    try {
      AccountResponse result = apiInstance.createAccount(accountRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ComptesApi#createAccount");
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
| **accountRequest** | [**AccountRequest**](AccountRequest.md)|  | |

### Return type

[**AccountResponse**](AccountResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Compte créé |  -  |
| **400** | Erreur (ex: caisse déjà existante) |  -  |

<a id="findAccountById"></a>
# **findAccountById**
> AccountResponse findAccountById(id)

Obtenir un compte par son ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ComptesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    ComptesApi apiInstance = new ComptesApi(defaultClient);
    UUID id = UUID.randomUUID(); // UUID | 
    try {
      AccountResponse result = apiInstance.findAccountById(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ComptesApi#findAccountById");
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

[**AccountResponse**](AccountResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Compte trouvé |  -  |
| **404** | Compte non trouvé |  -  |

<a id="findAllAccounts"></a>
# **findAllAccounts**
> List&lt;AccountResponse&gt; findAllAccounts()

Lister tous les comptes

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ComptesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    ComptesApi apiInstance = new ComptesApi(defaultClient);
    try {
      List<AccountResponse> result = apiInstance.findAllAccounts();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ComptesApi#findAllAccounts");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;AccountResponse&gt;**](AccountResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des comptes |  -  |


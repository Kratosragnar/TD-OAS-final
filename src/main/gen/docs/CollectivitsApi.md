# CollectivitsApi

All URIs are relative to *http://localhost:8080/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createCollectivity**](CollectivitsApi.md#createCollectivity) | **POST** /collectivities | Créer une collectivité |
| [**findAllCollectivities**](CollectivitsApi.md#findAllCollectivities) | **GET** /collectivities | Lister toutes les collectivités |
| [**findCollectivityById**](CollectivitsApi.md#findCollectivityById) | **GET** /collectivities/{id} | Obtenir une collectivité par son ID |
| [**validateCollectivity**](CollectivitsApi.md#validateCollectivity) | **POST** /collectivities/{id}/validate | Valider une collectivité par la fédération |


<a id="createCollectivity"></a>
# **createCollectivity**
> CollectivityResponse createCollectivity(collectivityRequest)

Créer une collectivité

Conditions requises : - Minimum 10 membres fondateurs - Ancienneté ≥ 6 mois pour chaque fondateur - Postes obligatoires remplis (Président, Trésorier, Secrétaire) 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CollectivitsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    CollectivitsApi apiInstance = new CollectivitsApi(defaultClient);
    CollectivityRequest collectivityRequest = new CollectivityRequest(); // CollectivityRequest | 
    try {
      CollectivityResponse result = apiInstance.createCollectivity(collectivityRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectivitsApi#createCollectivity");
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
| **collectivityRequest** | [**CollectivityRequest**](CollectivityRequest.md)|  | |

### Return type

[**CollectivityResponse**](CollectivityResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Collectivité créée avec succès |  -  |
| **400** | Données invalides ou conditions non remplies |  -  |

<a id="findAllCollectivities"></a>
# **findAllCollectivities**
> List&lt;CollectivityResponse&gt; findAllCollectivities(status, city)

Lister toutes les collectivités

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CollectivitsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    CollectivitsApi apiInstance = new CollectivitsApi(defaultClient);
    String status = "PENDING"; // String | Filtrer par statut
    String city = "city_example"; // String | Filtrer par ville
    try {
      List<CollectivityResponse> result = apiInstance.findAllCollectivities(status, city);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectivitsApi#findAllCollectivities");
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
| **status** | **String**| Filtrer par statut | [optional] [enum: PENDING, ACTIVE, SUSPENDED] |
| **city** | **String**| Filtrer par ville | [optional] |

### Return type

[**List&lt;CollectivityResponse&gt;**](CollectivityResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des collectivités |  -  |

<a id="findCollectivityById"></a>
# **findCollectivityById**
> CollectivityResponse findCollectivityById(id)

Obtenir une collectivité par son ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CollectivitsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    CollectivitsApi apiInstance = new CollectivitsApi(defaultClient);
    UUID id = UUID.randomUUID(); // UUID | ID de la collectivité
    try {
      CollectivityResponse result = apiInstance.findCollectivityById(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectivitsApi#findCollectivityById");
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
| **id** | **UUID**| ID de la collectivité | |

### Return type

[**CollectivityResponse**](CollectivityResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Collectivité trouvée |  -  |
| **404** | Collectivité non trouvée |  -  |

<a id="validateCollectivity"></a>
# **validateCollectivity**
> validateCollectivity(id)

Valider une collectivité par la fédération

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CollectivitsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api/v1");

    CollectivitsApi apiInstance = new CollectivitsApi(defaultClient);
    UUID id = UUID.randomUUID(); // UUID | 
    try {
      apiInstance.validateCollectivity(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectivitsApi#validateCollectivity");
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

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Collectivité validée avec succès |  -  |
| **404** | Collectivité non trouvée |  -  |


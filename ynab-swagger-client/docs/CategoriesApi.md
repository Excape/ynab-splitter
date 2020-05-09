# CategoriesApi

All URIs are relative to *https://api.youneedabudget.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getCategories**](CategoriesApi.md#getCategories) | **GET** /budgets/{budget_id}/categories | List categories
[**getCategoryById**](CategoriesApi.md#getCategoryById) | **GET** /budgets/{budget_id}/categories/{category_id} | Single category
[**getMonthCategoryById**](CategoriesApi.md#getMonthCategoryById) | **GET** /budgets/{budget_id}/months/{month}/categories/{category_id} | Single category for a specific budget month
[**updateMonthCategory**](CategoriesApi.md#updateMonthCategory) | **PATCH** /budgets/{budget_id}/months/{month}/categories/{category_id} | Update a category for a specific month

<a name="getCategories"></a>
# **getCategories**
> CategoriesResponse getCategories(budgetId, lastKnowledgeOfServer)

List categories

Returns all categories grouped by category group.  Amounts (budgeted, activity, balance, etc.) are specific to the current budget month (UTC).

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.CategoriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

CategoriesApi apiInstance = new CategoriesApi();
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
Long lastKnowledgeOfServer = 789L; // Long | The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included.
try {
    CategoriesResponse result = apiInstance.getCategories(budgetId, lastKnowledgeOfServer);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CategoriesApi#getCategories");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |
 **lastKnowledgeOfServer** | **Long**| The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included. | [optional]

### Return type

[**CategoriesResponse**](CategoriesResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getCategoryById"></a>
# **getCategoryById**
> CategoryResponse getCategoryById(budgetId, categoryId)

Single category

Returns a single category.  Amounts (budgeted, activity, balance, etc.) are specific to the current budget month (UTC).

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.CategoriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

CategoriesApi apiInstance = new CategoriesApi();
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
String categoryId = "categoryId_example"; // String | The id of the category
try {
    CategoryResponse result = apiInstance.getCategoryById(budgetId, categoryId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CategoriesApi#getCategoryById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |
 **categoryId** | **String**| The id of the category |

### Return type

[**CategoryResponse**](CategoryResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getMonthCategoryById"></a>
# **getMonthCategoryById**
> CategoryResponse getMonthCategoryById(budgetId, month, categoryId)

Single category for a specific budget month

Returns a single category for a specific budget month.  Amounts (budgeted, activity, balance, etc.) are specific to the current budget month (UTC).

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.CategoriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

CategoriesApi apiInstance = new CategoriesApi();
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
LocalDate month = new LocalDate(); // LocalDate | The budget month in ISO format (e.g. 2016-12-01) (\"current\" can also be used to specify the current calendar month (UTC))
String categoryId = "categoryId_example"; // String | The id of the category
try {
    CategoryResponse result = apiInstance.getMonthCategoryById(budgetId, month, categoryId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CategoriesApi#getMonthCategoryById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |
 **month** | **LocalDate**| The budget month in ISO format (e.g. 2016-12-01) (\&quot;current\&quot; can also be used to specify the current calendar month (UTC)) |
 **categoryId** | **String**| The id of the category |

### Return type

[**CategoryResponse**](CategoryResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="updateMonthCategory"></a>
# **updateMonthCategory**
> SaveCategoryResponse updateMonthCategory(body, budgetId, month, categoryId)

Update a category for a specific month

Update a category for a specific month

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.CategoriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

CategoriesApi apiInstance = new CategoriesApi();
SaveMonthCategoryWrapper body = new SaveMonthCategoryWrapper(); // SaveMonthCategoryWrapper | The category to update
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
LocalDate month = new LocalDate(); // LocalDate | The budget month in ISO format (e.g. 2016-12-01) (\"current\" can also be used to specify the current calendar month (UTC))
String categoryId = "categoryId_example"; // String | The id of the category
try {
    SaveCategoryResponse result = apiInstance.updateMonthCategory(body, budgetId, month, categoryId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CategoriesApi#updateMonthCategory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SaveMonthCategoryWrapper**](SaveMonthCategoryWrapper.md)| The category to update |
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |
 **month** | **LocalDate**| The budget month in ISO format (e.g. 2016-12-01) (\&quot;current\&quot; can also be used to specify the current calendar month (UTC)) |
 **categoryId** | **String**| The id of the category |

### Return type

[**SaveCategoryResponse**](SaveCategoryResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: */*
 - **Accept**: application/json


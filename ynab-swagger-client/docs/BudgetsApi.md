# BudgetsApi

All URIs are relative to *https://api.youneedabudget.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getBudgetById**](BudgetsApi.md#getBudgetById) | **GET** /budgets/{budget_id} | Single budget
[**getBudgetSettingsById**](BudgetsApi.md#getBudgetSettingsById) | **GET** /budgets/{budget_id}/settings | Budget Settings
[**getBudgets**](BudgetsApi.md#getBudgets) | **GET** /budgets | List budgets

<a name="getBudgetById"></a>
# **getBudgetById**
> BudgetDetailResponse getBudgetById(budgetId, lastKnowledgeOfServer)

Single budget

Returns a single budget with all related entities.  This resource is effectively a full budget export.

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.BudgetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

BudgetsApi apiInstance = new BudgetsApi();
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
Long lastKnowledgeOfServer = 789L; // Long | The starting server knowledge.  If provided, only entities that have changed since `last_knowledge_of_server` will be included.
try {
    BudgetDetailResponse result = apiInstance.getBudgetById(budgetId, lastKnowledgeOfServer);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BudgetsApi#getBudgetById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |
 **lastKnowledgeOfServer** | **Long**| The starting server knowledge.  If provided, only entities that have changed since &#x60;last_knowledge_of_server&#x60; will be included. | [optional]

### Return type

[**BudgetDetailResponse**](BudgetDetailResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getBudgetSettingsById"></a>
# **getBudgetSettingsById**
> BudgetSettingsResponse getBudgetSettingsById(budgetId)

Budget Settings

Returns settings for a budget

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.BudgetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

BudgetsApi apiInstance = new BudgetsApi();
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
try {
    BudgetSettingsResponse result = apiInstance.getBudgetSettingsById(budgetId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BudgetsApi#getBudgetSettingsById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |

### Return type

[**BudgetSettingsResponse**](BudgetSettingsResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getBudgets"></a>
# **getBudgets**
> BudgetSummaryResponse getBudgets(includeAccounts)

List budgets

Returns budgets list with summary information

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.BudgetsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

BudgetsApi apiInstance = new BudgetsApi();
Boolean includeAccounts = true; // Boolean | Whether to include the list of budget accounts
try {
    BudgetSummaryResponse result = apiInstance.getBudgets(includeAccounts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BudgetsApi#getBudgets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **includeAccounts** | **Boolean**| Whether to include the list of budget accounts | [optional]

### Return type

[**BudgetSummaryResponse**](BudgetSummaryResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


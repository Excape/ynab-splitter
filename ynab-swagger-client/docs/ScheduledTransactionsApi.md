# ScheduledTransactionsApi

All URIs are relative to *https://api.youneedabudget.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getScheduledTransactionById**](ScheduledTransactionsApi.md#getScheduledTransactionById) | **GET** /budgets/{budget_id}/scheduled_transactions/{scheduled_transaction_id} | Single scheduled transaction
[**getScheduledTransactions**](ScheduledTransactionsApi.md#getScheduledTransactions) | **GET** /budgets/{budget_id}/scheduled_transactions | List scheduled transactions

<a name="getScheduledTransactionById"></a>
# **getScheduledTransactionById**
> ScheduledTransactionResponse getScheduledTransactionById(budgetId, scheduledTransactionId)

Single scheduled transaction

Returns a single scheduled transaction

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.ScheduledTransactionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

ScheduledTransactionsApi apiInstance = new ScheduledTransactionsApi();
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
String scheduledTransactionId = "scheduledTransactionId_example"; // String | The id of the scheduled transaction
try {
    ScheduledTransactionResponse result = apiInstance.getScheduledTransactionById(budgetId, scheduledTransactionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ScheduledTransactionsApi#getScheduledTransactionById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |
 **scheduledTransactionId** | **String**| The id of the scheduled transaction |

### Return type

[**ScheduledTransactionResponse**](ScheduledTransactionResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getScheduledTransactions"></a>
# **getScheduledTransactions**
> ScheduledTransactionsResponse getScheduledTransactions(budgetId, lastKnowledgeOfServer)

List scheduled transactions

Returns all scheduled transactions

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.ScheduledTransactionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

ScheduledTransactionsApi apiInstance = new ScheduledTransactionsApi();
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
Long lastKnowledgeOfServer = 789L; // Long | The starting server knowledge.  If provided, only entities that have changed since `last_knowledge_of_server` will be included.
try {
    ScheduledTransactionsResponse result = apiInstance.getScheduledTransactions(budgetId, lastKnowledgeOfServer);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ScheduledTransactionsApi#getScheduledTransactions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |
 **lastKnowledgeOfServer** | **Long**| The starting server knowledge.  If provided, only entities that have changed since &#x60;last_knowledge_of_server&#x60; will be included. | [optional]

### Return type

[**ScheduledTransactionsResponse**](ScheduledTransactionsResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


# DeprecatedApi

All URIs are relative to *https://api.youneedabudget.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**bulkCreateTransactions**](DeprecatedApi.md#bulkCreateTransactions) | **POST** /budgets/{budget_id}/transactions/bulk | Bulk create transactions

<a name="bulkCreateTransactions"></a>
# **bulkCreateTransactions**
> BulkResponse bulkCreateTransactions(body, budgetId)

Bulk create transactions

Creates multiple transactions.  Although this endpoint is still supported, it is recommended to use &#x27;POST /budgets/{budget_id}/transactions&#x27; to create multiple transactions.

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.DeprecatedApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

DeprecatedApi apiInstance = new DeprecatedApi();
BulkTransactions body = new BulkTransactions(); // BulkTransactions | The list of transactions to create
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
try {
    BulkResponse result = apiInstance.bulkCreateTransactions(body, budgetId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DeprecatedApi#bulkCreateTransactions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**BulkTransactions**](BulkTransactions.md)| The list of transactions to create |
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |

### Return type

[**BulkResponse**](BulkResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: */*
 - **Accept**: application/json


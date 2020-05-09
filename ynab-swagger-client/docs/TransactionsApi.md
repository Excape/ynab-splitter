# TransactionsApi

All URIs are relative to *https://api.youneedabudget.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createTransaction**](TransactionsApi.md#createTransaction) | **POST** /budgets/{budget_id}/transactions | Create a single transaction or multiple transactions
[**getTransactionById**](TransactionsApi.md#getTransactionById) | **GET** /budgets/{budget_id}/transactions/{transaction_id} | Single transaction
[**getTransactions**](TransactionsApi.md#getTransactions) | **GET** /budgets/{budget_id}/transactions | List transactions
[**getTransactionsByAccount**](TransactionsApi.md#getTransactionsByAccount) | **GET** /budgets/{budget_id}/accounts/{account_id}/transactions | List account transactions
[**getTransactionsByCategory**](TransactionsApi.md#getTransactionsByCategory) | **GET** /budgets/{budget_id}/categories/{category_id}/transactions | List category transactions
[**getTransactionsByPayee**](TransactionsApi.md#getTransactionsByPayee) | **GET** /budgets/{budget_id}/payees/{payee_id}/transactions | List payee transactions
[**updateTransaction**](TransactionsApi.md#updateTransaction) | **PUT** /budgets/{budget_id}/transactions/{transaction_id} | Updates an existing transaction
[**updateTransactions**](TransactionsApi.md#updateTransactions) | **PATCH** /budgets/{budget_id}/transactions | Update multiple transactions

<a name="createTransaction"></a>
# **createTransaction**
> SaveTransactionsResponse createTransaction(body, budgetId)

Create a single transaction or multiple transactions

Creates a single transaction or multiple transactions.  If you provide a body containing a &#x27;transaction&#x27; object, a single transaction will be created and if you provide a body containing a &#x27;transactions&#x27; array, multiple transactions will be created.  Scheduled transactions cannot be created on this endpoint.

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.TransactionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

TransactionsApi apiInstance = new TransactionsApi();
SaveTransactionsWrapper body = new SaveTransactionsWrapper(); // SaveTransactionsWrapper | The transaction or transactions to create.  To create a single transaction you can specify a value for the 'transaction' object and to create multiple transactions you can specify an array of 'transactions'.  It is expected that you will only provide a value for one of these objects.
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
try {
    SaveTransactionsResponse result = apiInstance.createTransaction(body, budgetId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TransactionsApi#createTransaction");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SaveTransactionsWrapper**](SaveTransactionsWrapper.md)| The transaction or transactions to create.  To create a single transaction you can specify a value for the &#x27;transaction&#x27; object and to create multiple transactions you can specify an array of &#x27;transactions&#x27;.  It is expected that you will only provide a value for one of these objects. |
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |

### Return type

[**SaveTransactionsResponse**](SaveTransactionsResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: */*
 - **Accept**: application/json

<a name="getTransactionById"></a>
# **getTransactionById**
> TransactionResponse getTransactionById(budgetId, transactionId)

Single transaction

Returns a single transaction

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.TransactionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

TransactionsApi apiInstance = new TransactionsApi();
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
String transactionId = "transactionId_example"; // String | The id of the transaction
try {
    TransactionResponse result = apiInstance.getTransactionById(budgetId, transactionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TransactionsApi#getTransactionById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |
 **transactionId** | **String**| The id of the transaction |

### Return type

[**TransactionResponse**](TransactionResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTransactions"></a>
# **getTransactions**
> TransactionsResponse getTransactions(budgetId, sinceDate, type, lastKnowledgeOfServer)

List transactions

Returns budget transactions

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.TransactionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

TransactionsApi apiInstance = new TransactionsApi();
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
LocalDate sinceDate = new LocalDate(); // LocalDate | If specified, only transactions on or after this date will be included.  The date should be ISO formatted (e.g. 2016-12-30).
String type = "type_example"; // String | If specified, only transactions of the specified type will be included. 'uncategorized' and 'unapproved' are currently supported.
Long lastKnowledgeOfServer = 789L; // Long | The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included.
try {
    TransactionsResponse result = apiInstance.getTransactions(budgetId, sinceDate, type, lastKnowledgeOfServer);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TransactionsApi#getTransactions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |
 **sinceDate** | **LocalDate**| If specified, only transactions on or after this date will be included.  The date should be ISO formatted (e.g. 2016-12-30). | [optional]
 **type** | **String**| If specified, only transactions of the specified type will be included. &#x27;uncategorized&#x27; and &#x27;unapproved&#x27; are currently supported. | [optional] [enum: uncategorized, unapproved]
 **lastKnowledgeOfServer** | **Long**| The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included. | [optional]

### Return type

[**TransactionsResponse**](TransactionsResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTransactionsByAccount"></a>
# **getTransactionsByAccount**
> TransactionsResponse getTransactionsByAccount(budgetId, accountId, sinceDate, type, lastKnowledgeOfServer)

List account transactions

Returns all transactions for a specified account

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.TransactionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

TransactionsApi apiInstance = new TransactionsApi();
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
String accountId = "accountId_example"; // String | The id of the account
LocalDate sinceDate = new LocalDate(); // LocalDate | If specified, only transactions on or after this date will be included.  The date should be ISO formatted (e.g. 2016-12-30).
String type = "type_example"; // String | If specified, only transactions of the specified type will be included. 'uncategorized' and 'unapproved' are currently supported.
Long lastKnowledgeOfServer = 789L; // Long | The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included.
try {
    TransactionsResponse result = apiInstance.getTransactionsByAccount(budgetId, accountId, sinceDate, type, lastKnowledgeOfServer);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TransactionsApi#getTransactionsByAccount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |
 **accountId** | **String**| The id of the account |
 **sinceDate** | **LocalDate**| If specified, only transactions on or after this date will be included.  The date should be ISO formatted (e.g. 2016-12-30). | [optional]
 **type** | **String**| If specified, only transactions of the specified type will be included. &#x27;uncategorized&#x27; and &#x27;unapproved&#x27; are currently supported. | [optional] [enum: uncategorized, unapproved]
 **lastKnowledgeOfServer** | **Long**| The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included. | [optional]

### Return type

[**TransactionsResponse**](TransactionsResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTransactionsByCategory"></a>
# **getTransactionsByCategory**
> HybridTransactionsResponse getTransactionsByCategory(budgetId, categoryId, sinceDate, type, lastKnowledgeOfServer)

List category transactions

Returns all transactions for a specified category

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.TransactionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

TransactionsApi apiInstance = new TransactionsApi();
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
String categoryId = "categoryId_example"; // String | The id of the category
LocalDate sinceDate = new LocalDate(); // LocalDate | If specified, only transactions on or after this date will be included.  The date should be ISO formatted (e.g. 2016-12-30).
String type = "type_example"; // String | If specified, only transactions of the specified type will be included. 'uncategorized' and 'unapproved' are currently supported.
Long lastKnowledgeOfServer = 789L; // Long | The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included.
try {
    HybridTransactionsResponse result = apiInstance.getTransactionsByCategory(budgetId, categoryId, sinceDate, type, lastKnowledgeOfServer);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TransactionsApi#getTransactionsByCategory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |
 **categoryId** | **String**| The id of the category |
 **sinceDate** | **LocalDate**| If specified, only transactions on or after this date will be included.  The date should be ISO formatted (e.g. 2016-12-30). | [optional]
 **type** | **String**| If specified, only transactions of the specified type will be included. &#x27;uncategorized&#x27; and &#x27;unapproved&#x27; are currently supported. | [optional] [enum: uncategorized, unapproved]
 **lastKnowledgeOfServer** | **Long**| The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included. | [optional]

### Return type

[**HybridTransactionsResponse**](HybridTransactionsResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTransactionsByPayee"></a>
# **getTransactionsByPayee**
> HybridTransactionsResponse getTransactionsByPayee(budgetId, payeeId, sinceDate, type, lastKnowledgeOfServer)

List payee transactions

Returns all transactions for a specified payee

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.TransactionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

TransactionsApi apiInstance = new TransactionsApi();
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
String payeeId = "payeeId_example"; // String | The id of the payee
LocalDate sinceDate = new LocalDate(); // LocalDate | If specified, only transactions on or after this date will be included.  The date should be ISO formatted (e.g. 2016-12-30).
String type = "type_example"; // String | If specified, only transactions of the specified type will be included. 'uncategorized' and 'unapproved' are currently supported.
Long lastKnowledgeOfServer = 789L; // Long | The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included.
try {
    HybridTransactionsResponse result = apiInstance.getTransactionsByPayee(budgetId, payeeId, sinceDate, type, lastKnowledgeOfServer);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TransactionsApi#getTransactionsByPayee");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |
 **payeeId** | **String**| The id of the payee |
 **sinceDate** | **LocalDate**| If specified, only transactions on or after this date will be included.  The date should be ISO formatted (e.g. 2016-12-30). | [optional]
 **type** | **String**| If specified, only transactions of the specified type will be included. &#x27;uncategorized&#x27; and &#x27;unapproved&#x27; are currently supported. | [optional] [enum: uncategorized, unapproved]
 **lastKnowledgeOfServer** | **Long**| The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included. | [optional]

### Return type

[**HybridTransactionsResponse**](HybridTransactionsResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="updateTransaction"></a>
# **updateTransaction**
> TransactionResponse updateTransaction(body, budgetId, transactionId)

Updates an existing transaction

Updates a transaction

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.TransactionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

TransactionsApi apiInstance = new TransactionsApi();
SaveTransactionWrapper body = new SaveTransactionWrapper(); // SaveTransactionWrapper | The transaction to update
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
String transactionId = "transactionId_example"; // String | The id of the transaction
try {
    TransactionResponse result = apiInstance.updateTransaction(body, budgetId, transactionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TransactionsApi#updateTransaction");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SaveTransactionWrapper**](SaveTransactionWrapper.md)| The transaction to update |
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |
 **transactionId** | **String**| The id of the transaction |

### Return type

[**TransactionResponse**](TransactionResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: */*
 - **Accept**: application/json

<a name="updateTransactions"></a>
# **updateTransactions**
> SaveTransactionsResponse updateTransactions(body, budgetId)

Update multiple transactions

Updates multiple transactions, by &#x27;id&#x27; or &#x27;import_id&#x27;.

### Example
```java
// Import classes:
//import ch.excape.ynabclient.invoker.ApiClient;
//import ch.excape.ynabclient.invoker.ApiException;
//import ch.excape.ynabclient.invoker.Configuration;
//import ch.excape.ynabclient.invoker.auth.*;
//import ch.excape.ynabclient.api.TransactionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearer
ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
bearer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearer.setApiKeyPrefix("Token");

TransactionsApi apiInstance = new TransactionsApi();
UpdateTransactionsWrapper body = new UpdateTransactionsWrapper(); // UpdateTransactionsWrapper | The transactions to update. Each transaction must have either an 'id' or 'import_id' specified. If 'id' is specified as null an 'import_id' value can be provided which will allow transaction(s) to be updated by their import_id. If an id is specified, it will always be used for lookup.
String budgetId = "budgetId_example"; // String | The id of the budget (\"last-used\" can be used to specify the last used budget and \"default\" can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
try {
    SaveTransactionsResponse result = apiInstance.updateTransactions(body, budgetId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TransactionsApi#updateTransactions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UpdateTransactionsWrapper**](UpdateTransactionsWrapper.md)| The transactions to update. Each transaction must have either an &#x27;id&#x27; or &#x27;import_id&#x27; specified. If &#x27;id&#x27; is specified as null an &#x27;import_id&#x27; value can be provided which will allow transaction(s) to be updated by their import_id. If an id is specified, it will always be used for lookup. |
 **budgetId** | **String**| The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget) |

### Return type

[**SaveTransactionsResponse**](SaveTransactionsResponse.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

 - **Content-Type**: */*
 - **Accept**: application/json


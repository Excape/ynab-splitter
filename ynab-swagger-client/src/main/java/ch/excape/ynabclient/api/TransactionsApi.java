package ch.excape.ynabclient.api;

import ch.excape.ynabclient.invoker.ApiClient;

import ch.excape.ynabclient.model.ErrorResponse;
import ch.excape.ynabclient.model.HybridTransactionsResponse;
import org.threeten.bp.LocalDate;
import ch.excape.ynabclient.model.SaveTransactionWrapper;
import ch.excape.ynabclient.model.SaveTransactionsResponse;
import ch.excape.ynabclient.model.SaveTransactionsWrapper;
import ch.excape.ynabclient.model.TransactionResponse;
import ch.excape.ynabclient.model.TransactionsResponse;
import ch.excape.ynabclient.model.UpdateTransactionsWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-09-07T14:51:52.476+02:00[Europe/Zurich]")@Component("ch.excape.ynabclient.api.TransactionsApi")
public class TransactionsApi {
    private ApiClient apiClient;

    public TransactionsApi() {
        this(new ApiClient());
    }

    @Autowired
    public TransactionsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create a single transaction or multiple transactions
     * Creates a single transaction or multiple transactions.  If you provide a body containing a &#x27;transaction&#x27; object, a single transaction will be created and if you provide a body containing a &#x27;transactions&#x27; array, multiple transactions will be created.  Scheduled transactions cannot be created on this endpoint.
     * <p><b>201</b> - The transaction or transactions were successfully created
     * <p><b>400</b> - The request could not be understood due to malformed syntax or validation error(s).
     * <p><b>409</b> - A transaction on the same account with the same import_id already exists.
     * @param body The transaction or transactions to create.  To create a single transaction you can specify a value for the &#x27;transaction&#x27; object and to create multiple transactions you can specify an array of &#x27;transactions&#x27;.  It is expected that you will only provide a value for one of these objects.
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @return SaveTransactionsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SaveTransactionsResponse createTransaction(SaveTransactionsWrapper body, String budgetId) throws RestClientException {
        Object postBody = body;
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling createTransaction");
        }
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling createTransaction");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/transactions").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "*/*"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "bearer" };

        ParameterizedTypeReference<SaveTransactionsResponse> returnType = new ParameterizedTypeReference<SaveTransactionsResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Single transaction
     * Returns a single transaction
     * <p><b>200</b> - The requested transaction
     * <p><b>404</b> - The transaction was not found
     * <p><b>0</b> - An error occurred
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @param transactionId The id of the transaction
     * @return TransactionResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TransactionResponse getTransactionById(String budgetId, String transactionId) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getTransactionById");
        }
        // verify the required parameter 'transactionId' is set
        if (transactionId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'transactionId' when calling getTransactionById");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        uriVariables.put("transaction_id", transactionId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/transactions/{transaction_id}").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "bearer" };

        ParameterizedTypeReference<TransactionResponse> returnType = new ParameterizedTypeReference<TransactionResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * List transactions
     * Returns budget transactions
     * <p><b>200</b> - The list of requested transactions
     * <p><b>400</b> - An error occurred
     * <p><b>404</b> - No transactions were found
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @param sinceDate If specified, only transactions on or after this date will be included.  The date should be ISO formatted (e.g. 2016-12-30).
     * @param type If specified, only transactions of the specified type will be included. &#x27;uncategorized&#x27; and &#x27;unapproved&#x27; are currently supported.
     * @param lastKnowledgeOfServer The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included.
     * @return TransactionsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TransactionsResponse getTransactions(String budgetId, LocalDate sinceDate, String type, Long lastKnowledgeOfServer) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getTransactions");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/transactions").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "since_date", sinceDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "last_knowledge_of_server", lastKnowledgeOfServer));

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "bearer" };

        ParameterizedTypeReference<TransactionsResponse> returnType = new ParameterizedTypeReference<TransactionsResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * List account transactions
     * Returns all transactions for a specified account
     * <p><b>200</b> - The list of requested transactions
     * <p><b>404</b> - No transactions were found
     * <p><b>0</b> - An error occurred
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @param accountId The id of the account
     * @param sinceDate If specified, only transactions on or after this date will be included.  The date should be ISO formatted (e.g. 2016-12-30).
     * @param type If specified, only transactions of the specified type will be included. &#x27;uncategorized&#x27; and &#x27;unapproved&#x27; are currently supported.
     * @param lastKnowledgeOfServer The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included.
     * @return TransactionsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TransactionsResponse getTransactionsByAccount(String budgetId, String accountId, LocalDate sinceDate, String type, Long lastKnowledgeOfServer) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getTransactionsByAccount");
        }
        // verify the required parameter 'accountId' is set
        if (accountId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'accountId' when calling getTransactionsByAccount");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        uriVariables.put("account_id", accountId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/accounts/{account_id}/transactions").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "since_date", sinceDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "last_knowledge_of_server", lastKnowledgeOfServer));

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "bearer" };

        ParameterizedTypeReference<TransactionsResponse> returnType = new ParameterizedTypeReference<TransactionsResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * List category transactions
     * Returns all transactions for a specified category
     * <p><b>200</b> - The list of requested transactions
     * <p><b>404</b> - No transactions were found
     * <p><b>0</b> - An error occurred
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @param categoryId The id of the category
     * @param sinceDate If specified, only transactions on or after this date will be included.  The date should be ISO formatted (e.g. 2016-12-30).
     * @param type If specified, only transactions of the specified type will be included. &#x27;uncategorized&#x27; and &#x27;unapproved&#x27; are currently supported.
     * @param lastKnowledgeOfServer The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included.
     * @return HybridTransactionsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HybridTransactionsResponse getTransactionsByCategory(String budgetId, String categoryId, LocalDate sinceDate, String type, Long lastKnowledgeOfServer) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getTransactionsByCategory");
        }
        // verify the required parameter 'categoryId' is set
        if (categoryId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'categoryId' when calling getTransactionsByCategory");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        uriVariables.put("category_id", categoryId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/categories/{category_id}/transactions").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "since_date", sinceDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "last_knowledge_of_server", lastKnowledgeOfServer));

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "bearer" };

        ParameterizedTypeReference<HybridTransactionsResponse> returnType = new ParameterizedTypeReference<HybridTransactionsResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * List payee transactions
     * Returns all transactions for a specified payee
     * <p><b>200</b> - The list of requested transactions
     * <p><b>404</b> - No transactions were found
     * <p><b>0</b> - An error occurred
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @param payeeId The id of the payee
     * @param sinceDate If specified, only transactions on or after this date will be included.  The date should be ISO formatted (e.g. 2016-12-30).
     * @param type If specified, only transactions of the specified type will be included. &#x27;uncategorized&#x27; and &#x27;unapproved&#x27; are currently supported.
     * @param lastKnowledgeOfServer The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included.
     * @return HybridTransactionsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public HybridTransactionsResponse getTransactionsByPayee(String budgetId, String payeeId, LocalDate sinceDate, String type, Long lastKnowledgeOfServer) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getTransactionsByPayee");
        }
        // verify the required parameter 'payeeId' is set
        if (payeeId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'payeeId' when calling getTransactionsByPayee");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        uriVariables.put("payee_id", payeeId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/payees/{payee_id}/transactions").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "since_date", sinceDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "last_knowledge_of_server", lastKnowledgeOfServer));

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "bearer" };

        ParameterizedTypeReference<HybridTransactionsResponse> returnType = new ParameterizedTypeReference<HybridTransactionsResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Updates an existing transaction
     * Updates a transaction
     * <p><b>200</b> - The transaction was successfully updated
     * <p><b>400</b> - The request could not be understood due to malformed syntax or validation error(s)
     * @param body The transaction to update
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @param transactionId The id of the transaction
     * @return TransactionResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TransactionResponse updateTransaction(SaveTransactionWrapper body, String budgetId, String transactionId) throws RestClientException {
        Object postBody = body;
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling updateTransaction");
        }
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling updateTransaction");
        }
        // verify the required parameter 'transactionId' is set
        if (transactionId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'transactionId' when calling updateTransaction");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        uriVariables.put("transaction_id", transactionId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/transactions/{transaction_id}").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "bearer" };

        ParameterizedTypeReference<TransactionResponse> returnType = new ParameterizedTypeReference<TransactionResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update multiple transactions
     * Updates multiple transactions, by &#x27;id&#x27; or &#x27;import_id&#x27;.
     * <p><b>209</b> - The transactions were successfully updated
     * <p><b>400</b> - The request could not be understood due to malformed syntax or validation error(s).
     * @param body The transactions to update. Each transaction must have either an &#x27;id&#x27; or &#x27;import_id&#x27; specified. If &#x27;id&#x27; is specified as null an &#x27;import_id&#x27; value can be provided which will allow transaction(s) to be updated by their import_id. If an id is specified, it will always be used for lookup.
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @return SaveTransactionsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public SaveTransactionsResponse updateTransactions(UpdateTransactionsWrapper body, String budgetId) throws RestClientException {
        Object postBody = body;
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling updateTransactions");
        }
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling updateTransactions");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/transactions").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "*/*"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "bearer" };

        ParameterizedTypeReference<SaveTransactionsResponse> returnType = new ParameterizedTypeReference<SaveTransactionsResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.PATCH, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}

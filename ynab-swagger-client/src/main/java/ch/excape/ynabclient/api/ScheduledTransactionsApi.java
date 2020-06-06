package ch.excape.ynabclient.api;

import ch.excape.ynabclient.invoker.ApiClient;

import ch.excape.ynabclient.model.ErrorResponse;
import ch.excape.ynabclient.model.ScheduledTransactionResponse;
import ch.excape.ynabclient.model.ScheduledTransactionsResponse;

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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-06-06T11:46:19.985416+02:00[Europe/Zurich]")@Component("ch.excape.ynabclient.api.ScheduledTransactionsApi")
public class ScheduledTransactionsApi {
    private ApiClient apiClient;

    public ScheduledTransactionsApi() {
        this(new ApiClient());
    }

    @Autowired
    public ScheduledTransactionsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Single scheduled transaction
     * Returns a single scheduled transaction
     * <p><b>200</b> - The requested Scheduled Transaction
     * <p><b>404</b> - The scheduled transaction was not found
     * <p><b>0</b> - An error occurred
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @param scheduledTransactionId The id of the scheduled transaction
     * @return ScheduledTransactionResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ScheduledTransactionResponse getScheduledTransactionById(String budgetId, String scheduledTransactionId) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getScheduledTransactionById");
        }
        // verify the required parameter 'scheduledTransactionId' is set
        if (scheduledTransactionId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'scheduledTransactionId' when calling getScheduledTransactionById");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        uriVariables.put("scheduled_transaction_id", scheduledTransactionId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/scheduled_transactions/{scheduled_transaction_id}").buildAndExpand(uriVariables).toUriString();
        
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

        ParameterizedTypeReference<ScheduledTransactionResponse> returnType = new ParameterizedTypeReference<ScheduledTransactionResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * List scheduled transactions
     * Returns all scheduled transactions
     * <p><b>200</b> - The list of requested scheduled transactions
     * <p><b>404</b> - No scheduled transactions were found
     * <p><b>0</b> - An error occurred
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @param lastKnowledgeOfServer The starting server knowledge.  If provided, only entities that have changed since &#x60;last_knowledge_of_server&#x60; will be included.
     * @return ScheduledTransactionsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ScheduledTransactionsResponse getScheduledTransactions(String budgetId, Long lastKnowledgeOfServer) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getScheduledTransactions");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/scheduled_transactions").buildAndExpand(uriVariables).toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "last_knowledge_of_server", lastKnowledgeOfServer));

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "bearer" };

        ParameterizedTypeReference<ScheduledTransactionsResponse> returnType = new ParameterizedTypeReference<ScheduledTransactionsResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}

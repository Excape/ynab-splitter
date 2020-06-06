package ch.excape.ynabclient.api;

import ch.excape.ynabclient.invoker.ApiClient;

import ch.excape.ynabclient.model.BudgetDetailResponse;
import ch.excape.ynabclient.model.BudgetSettingsResponse;
import ch.excape.ynabclient.model.BudgetSummaryResponse;
import ch.excape.ynabclient.model.ErrorResponse;

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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-06-06T11:46:19.985416+02:00[Europe/Zurich]")@Component("ch.excape.ynabclient.api.BudgetsApi")
public class BudgetsApi {
    private ApiClient apiClient;

    public BudgetsApi() {
        this(new ApiClient());
    }

    @Autowired
    public BudgetsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Single budget
     * Returns a single budget with all related entities.  This resource is effectively a full budget export.
     * <p><b>200</b> - The requested budget
     * <p><b>404</b> - The specified budget was not found
     * <p><b>0</b> - An error occurred
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @param lastKnowledgeOfServer The starting server knowledge.  If provided, only entities that have changed since &#x60;last_knowledge_of_server&#x60; will be included.
     * @return BudgetDetailResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BudgetDetailResponse getBudgetById(String budgetId, Long lastKnowledgeOfServer) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getBudgetById");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}").buildAndExpand(uriVariables).toUriString();
        
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

        ParameterizedTypeReference<BudgetDetailResponse> returnType = new ParameterizedTypeReference<BudgetDetailResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Budget Settings
     * Returns settings for a budget
     * <p><b>200</b> - The requested budget settings
     * <p><b>404</b> - The specified Budget was not found
     * <p><b>0</b> - An error occurred
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @return BudgetSettingsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BudgetSettingsResponse getBudgetSettingsById(String budgetId) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getBudgetSettingsById");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/settings").buildAndExpand(uriVariables).toUriString();
        
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

        ParameterizedTypeReference<BudgetSettingsResponse> returnType = new ParameterizedTypeReference<BudgetSettingsResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * List budgets
     * Returns budgets list with summary information
     * <p><b>200</b> - The list of budgets
     * <p><b>404</b> - No budgets were found
     * <p><b>0</b> - An error occurred
     * @param includeAccounts Whether to include the list of budget accounts
     * @return BudgetSummaryResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BudgetSummaryResponse getBudgets(Boolean includeAccounts) throws RestClientException {
        Object postBody = null;
        String path = UriComponentsBuilder.fromPath("/budgets").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "include_accounts", includeAccounts));

        final String[] accepts = { 
            "application/json"
         };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "bearer" };

        ParameterizedTypeReference<BudgetSummaryResponse> returnType = new ParameterizedTypeReference<BudgetSummaryResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}

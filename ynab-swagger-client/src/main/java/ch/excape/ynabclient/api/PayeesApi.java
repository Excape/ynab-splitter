package ch.excape.ynabclient.api;

import ch.excape.ynabclient.invoker.ApiClient;

import ch.excape.ynabclient.model.ErrorResponse;
import ch.excape.ynabclient.model.PayeeResponse;
import ch.excape.ynabclient.model.PayeesResponse;

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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-09-07T14:51:52.476+02:00[Europe/Zurich]")@Component("ch.excape.ynabclient.api.PayeesApi")
public class PayeesApi {
    private ApiClient apiClient;

    public PayeesApi() {
        this(new ApiClient());
    }

    @Autowired
    public PayeesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Single payee
     * Returns single payee
     * <p><b>200</b> - The requested payee
     * <p><b>404</b> - The payee was not found
     * <p><b>0</b> - An error occurred
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @param payeeId The id of the payee
     * @return PayeeResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PayeeResponse getPayeeById(String budgetId, String payeeId) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getPayeeById");
        }
        // verify the required parameter 'payeeId' is set
        if (payeeId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'payeeId' when calling getPayeeById");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        uriVariables.put("payee_id", payeeId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/payees/{payee_id}").buildAndExpand(uriVariables).toUriString();
        
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

        ParameterizedTypeReference<PayeeResponse> returnType = new ParameterizedTypeReference<PayeeResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * List payees
     * Returns all payees
     * <p><b>200</b> - The requested list of payees
     * <p><b>404</b> - No payees were found
     * <p><b>0</b> - An error occurred
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @param lastKnowledgeOfServer The starting server knowledge.  If provided, only entities that have changed since last_knowledge_of_server will be included.
     * @return PayeesResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PayeesResponse getPayees(String budgetId, Long lastKnowledgeOfServer) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getPayees");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/payees").buildAndExpand(uriVariables).toUriString();
        
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

        ParameterizedTypeReference<PayeesResponse> returnType = new ParameterizedTypeReference<PayeesResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}

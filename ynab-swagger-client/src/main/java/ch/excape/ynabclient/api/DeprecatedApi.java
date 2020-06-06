package ch.excape.ynabclient.api;

import ch.excape.ynabclient.invoker.ApiClient;

import ch.excape.ynabclient.model.BulkResponse;
import ch.excape.ynabclient.model.BulkTransactions;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-06-06T11:46:19.985416+02:00[Europe/Zurich]")@Component("ch.excape.ynabclient.api.DeprecatedApi")
public class DeprecatedApi {
    private ApiClient apiClient;

    public DeprecatedApi() {
        this(new ApiClient());
    }

    @Autowired
    public DeprecatedApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Bulk create transactions
     * Creates multiple transactions.  Although this endpoint is still supported, it is recommended to use &#x27;POST /budgets/{budget_id}/transactions&#x27; to create multiple transactions.
     * <p><b>201</b> - The bulk request was processed successfully
     * <p><b>400</b> - The request could not be understood due to malformed syntax or validation error(s)
     * @param body The list of transactions to create
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @return BulkResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public BulkResponse bulkCreateTransactions(BulkTransactions body, String budgetId) throws RestClientException {
        Object postBody = body;
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling bulkCreateTransactions");
        }
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling bulkCreateTransactions");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/transactions/bulk").buildAndExpand(uriVariables).toUriString();
        
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

        ParameterizedTypeReference<BulkResponse> returnType = new ParameterizedTypeReference<BulkResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}

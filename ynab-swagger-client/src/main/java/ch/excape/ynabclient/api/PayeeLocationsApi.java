package ch.excape.ynabclient.api;

import ch.excape.ynabclient.invoker.ApiClient;

import ch.excape.ynabclient.model.ErrorResponse;
import ch.excape.ynabclient.model.PayeeLocationResponse;
import ch.excape.ynabclient.model.PayeeLocationsResponse;

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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-09-07T14:51:52.476+02:00[Europe/Zurich]")@Component("ch.excape.ynabclient.api.PayeeLocationsApi")
public class PayeeLocationsApi {
    private ApiClient apiClient;

    public PayeeLocationsApi() {
        this(new ApiClient());
    }

    @Autowired
    public PayeeLocationsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Single payee location
     * Returns a single payee location
     * <p><b>200</b> - The payee location
     * <p><b>404</b> - The payee location was not found
     * <p><b>0</b> - An error occurred
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @param payeeLocationId id of payee location
     * @return PayeeLocationResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PayeeLocationResponse getPayeeLocationById(String budgetId, String payeeLocationId) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getPayeeLocationById");
        }
        // verify the required parameter 'payeeLocationId' is set
        if (payeeLocationId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'payeeLocationId' when calling getPayeeLocationById");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        uriVariables.put("payee_location_id", payeeLocationId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/payee_locations/{payee_location_id}").buildAndExpand(uriVariables).toUriString();
        
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

        ParameterizedTypeReference<PayeeLocationResponse> returnType = new ParameterizedTypeReference<PayeeLocationResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * List payee locations
     * Returns all payee locations
     * <p><b>200</b> - The list of payee locations
     * <p><b>404</b> - No payees locations were found
     * <p><b>0</b> - An error occurred
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @return PayeeLocationsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PayeeLocationsResponse getPayeeLocations(String budgetId) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getPayeeLocations");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/payee_locations").buildAndExpand(uriVariables).toUriString();
        
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

        ParameterizedTypeReference<PayeeLocationsResponse> returnType = new ParameterizedTypeReference<PayeeLocationsResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * List locations for a payee
     * Returns all payee locations for the specified payee
     * <p><b>200</b> - The list of requested payee locations
     * <p><b>404</b> - No payees locations were found
     * <p><b>0</b> - An error occurred
     * @param budgetId The id of the budget (\&quot;last-used\&quot; can be used to specify the last used budget and \&quot;default\&quot; can be used if default budget selection is enabled (see: https://api.youneedabudget.com/#oauth-default-budget)
     * @param payeeId id of payee
     * @return PayeeLocationsResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PayeeLocationsResponse getPayeeLocationsByPayee(String budgetId, String payeeId) throws RestClientException {
        Object postBody = null;
        // verify the required parameter 'budgetId' is set
        if (budgetId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'budgetId' when calling getPayeeLocationsByPayee");
        }
        // verify the required parameter 'payeeId' is set
        if (payeeId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'payeeId' when calling getPayeeLocationsByPayee");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("budget_id", budgetId);
        uriVariables.put("payee_id", payeeId);
        String path = UriComponentsBuilder.fromPath("/budgets/{budget_id}/payees/{payee_id}/payee_locations").buildAndExpand(uriVariables).toUriString();
        
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

        ParameterizedTypeReference<PayeeLocationsResponse> returnType = new ParameterizedTypeReference<PayeeLocationsResponse>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}

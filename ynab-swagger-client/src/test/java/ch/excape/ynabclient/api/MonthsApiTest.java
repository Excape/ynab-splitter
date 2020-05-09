/*
 * YNAB API Endpoints
 * Our API uses a REST based design, leverages the JSON data format, and relies upon HTTPS for transport. We respond with meaningful HTTP response codes and if an error occurs, we include error details in the response body.  API Documentation is at https://api.youneedabudget.com
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package ch.excape.ynabclient.api;

import ch.excape.ynabclient.model.ErrorResponse;
import org.threeten.bp.LocalDate;
import ch.excape.ynabclient.model.MonthDetailResponse;
import ch.excape.ynabclient.model.MonthSummariesResponse;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for MonthsApi
 */
@Ignore
public class MonthsApiTest {

    private final MonthsApi api = new MonthsApi();

    /**
     * Single budget month
     *
     * Returns a single budget month
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getBudgetMonthTest() {
        String budgetId = null;
        LocalDate month = null;
        MonthDetailResponse response = api.getBudgetMonth(budgetId, month);

        // TODO: test validations
    }
    /**
     * List budget months
     *
     * Returns all budget months
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getBudgetMonthsTest() {
        String budgetId = null;
        Long lastKnowledgeOfServer = null;
        MonthSummariesResponse response = api.getBudgetMonths(budgetId, lastKnowledgeOfServer);

        // TODO: test validations
    }
}
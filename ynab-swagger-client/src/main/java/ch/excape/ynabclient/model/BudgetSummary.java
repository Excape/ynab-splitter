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

package ch.excape.ynabclient.model;

import java.util.Objects;
import java.util.Arrays;
import ch.excape.ynabclient.model.Account;
import ch.excape.ynabclient.model.CurrencyFormat;
import ch.excape.ynabclient.model.DateFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
/**
 * BudgetSummary
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-06-06T11:46:19.985416+02:00[Europe/Zurich]")
public class BudgetSummary {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("last_modified_on")
  private OffsetDateTime lastModifiedOn = null;

  @JsonProperty("first_month")
  private LocalDate firstMonth = null;

  @JsonProperty("last_month")
  private LocalDate lastMonth = null;

  @JsonProperty("date_format")
  private DateFormat dateFormat = null;

  @JsonProperty("currency_format")
  private CurrencyFormat currencyFormat = null;

  @JsonProperty("accounts")
  private List<Account> accounts = null;

  public BudgetSummary id(UUID id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @Schema(required = true, description = "")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public BudgetSummary name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @Schema(required = true, description = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BudgetSummary lastModifiedOn(OffsetDateTime lastModifiedOn) {
    this.lastModifiedOn = lastModifiedOn;
    return this;
  }

   /**
   * The last time any changes were made to the budget from either a web or mobile client
   * @return lastModifiedOn
  **/
  @Schema(description = "The last time any changes were made to the budget from either a web or mobile client")
  public OffsetDateTime getLastModifiedOn() {
    return lastModifiedOn;
  }

  public void setLastModifiedOn(OffsetDateTime lastModifiedOn) {
    this.lastModifiedOn = lastModifiedOn;
  }

  public BudgetSummary firstMonth(LocalDate firstMonth) {
    this.firstMonth = firstMonth;
    return this;
  }

   /**
   * The earliest budget month
   * @return firstMonth
  **/
  @Schema(description = "The earliest budget month")
  public LocalDate getFirstMonth() {
    return firstMonth;
  }

  public void setFirstMonth(LocalDate firstMonth) {
    this.firstMonth = firstMonth;
  }

  public BudgetSummary lastMonth(LocalDate lastMonth) {
    this.lastMonth = lastMonth;
    return this;
  }

   /**
   * The latest budget month
   * @return lastMonth
  **/
  @Schema(description = "The latest budget month")
  public LocalDate getLastMonth() {
    return lastMonth;
  }

  public void setLastMonth(LocalDate lastMonth) {
    this.lastMonth = lastMonth;
  }

  public BudgetSummary dateFormat(DateFormat dateFormat) {
    this.dateFormat = dateFormat;
    return this;
  }

   /**
   * Get dateFormat
   * @return dateFormat
  **/
  @Schema(description = "")
  public DateFormat getDateFormat() {
    return dateFormat;
  }

  public void setDateFormat(DateFormat dateFormat) {
    this.dateFormat = dateFormat;
  }

  public BudgetSummary currencyFormat(CurrencyFormat currencyFormat) {
    this.currencyFormat = currencyFormat;
    return this;
  }

   /**
   * Get currencyFormat
   * @return currencyFormat
  **/
  @Schema(description = "")
  public CurrencyFormat getCurrencyFormat() {
    return currencyFormat;
  }

  public void setCurrencyFormat(CurrencyFormat currencyFormat) {
    this.currencyFormat = currencyFormat;
  }

  public BudgetSummary accounts(List<Account> accounts) {
    this.accounts = accounts;
    return this;
  }

  public BudgetSummary addAccountsItem(Account accountsItem) {
    if (this.accounts == null) {
      this.accounts = new ArrayList<Account>();
    }
    this.accounts.add(accountsItem);
    return this;
  }

   /**
   * The budget accounts (only included if &#x60;include_accounts&#x3D;true&#x60; specified as query parameter)
   * @return accounts
  **/
  @Schema(description = "The budget accounts (only included if `include_accounts=true` specified as query parameter)")
  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BudgetSummary budgetSummary = (BudgetSummary) o;
    return Objects.equals(this.id, budgetSummary.id) &&
        Objects.equals(this.name, budgetSummary.name) &&
        Objects.equals(this.lastModifiedOn, budgetSummary.lastModifiedOn) &&
        Objects.equals(this.firstMonth, budgetSummary.firstMonth) &&
        Objects.equals(this.lastMonth, budgetSummary.lastMonth) &&
        Objects.equals(this.dateFormat, budgetSummary.dateFormat) &&
        Objects.equals(this.currencyFormat, budgetSummary.currencyFormat) &&
        Objects.equals(this.accounts, budgetSummary.accounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastModifiedOn, firstMonth, lastMonth, dateFormat, currencyFormat, accounts);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BudgetSummary {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    lastModifiedOn: ").append(toIndentedString(lastModifiedOn)).append("\n");
    sb.append("    firstMonth: ").append(toIndentedString(firstMonth)).append("\n");
    sb.append("    lastMonth: ").append(toIndentedString(lastMonth)).append("\n");
    sb.append("    dateFormat: ").append(toIndentedString(dateFormat)).append("\n");
    sb.append("    currencyFormat: ").append(toIndentedString(currencyFormat)).append("\n");
    sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

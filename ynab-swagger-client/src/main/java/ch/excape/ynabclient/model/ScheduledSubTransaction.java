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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
/**
 * ScheduledSubTransaction
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-06-06T11:46:19.985416+02:00[Europe/Zurich]")
public class ScheduledSubTransaction {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("scheduled_transaction_id")
  private UUID scheduledTransactionId = null;

  @JsonProperty("amount")
  private Long amount = null;

  @JsonProperty("memo")
  private String memo = null;

  @JsonProperty("payee_id")
  private UUID payeeId = null;

  @JsonProperty("category_id")
  private UUID categoryId = null;

  @JsonProperty("transfer_account_id")
  private UUID transferAccountId = null;

  @JsonProperty("deleted")
  private Boolean deleted = null;

  public ScheduledSubTransaction id(UUID id) {
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

  public ScheduledSubTransaction scheduledTransactionId(UUID scheduledTransactionId) {
    this.scheduledTransactionId = scheduledTransactionId;
    return this;
  }

   /**
   * Get scheduledTransactionId
   * @return scheduledTransactionId
  **/
  @Schema(required = true, description = "")
  public UUID getScheduledTransactionId() {
    return scheduledTransactionId;
  }

  public void setScheduledTransactionId(UUID scheduledTransactionId) {
    this.scheduledTransactionId = scheduledTransactionId;
  }

  public ScheduledSubTransaction amount(Long amount) {
    this.amount = amount;
    return this;
  }

   /**
   * The scheduled subtransaction amount in milliunits format
   * @return amount
  **/
  @Schema(required = true, description = "The scheduled subtransaction amount in milliunits format")
  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public ScheduledSubTransaction memo(String memo) {
    this.memo = memo;
    return this;
  }

   /**
   * Get memo
   * @return memo
  **/
  @Schema(description = "")
  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public ScheduledSubTransaction payeeId(UUID payeeId) {
    this.payeeId = payeeId;
    return this;
  }

   /**
   * Get payeeId
   * @return payeeId
  **/
  @Schema(description = "")
  public UUID getPayeeId() {
    return payeeId;
  }

  public void setPayeeId(UUID payeeId) {
    this.payeeId = payeeId;
  }

  public ScheduledSubTransaction categoryId(UUID categoryId) {
    this.categoryId = categoryId;
    return this;
  }

   /**
   * Get categoryId
   * @return categoryId
  **/
  @Schema(description = "")
  public UUID getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(UUID categoryId) {
    this.categoryId = categoryId;
  }

  public ScheduledSubTransaction transferAccountId(UUID transferAccountId) {
    this.transferAccountId = transferAccountId;
    return this;
  }

   /**
   * If a transfer, the account_id which the scheduled subtransaction transfers to
   * @return transferAccountId
  **/
  @Schema(description = "If a transfer, the account_id which the scheduled subtransaction transfers to")
  public UUID getTransferAccountId() {
    return transferAccountId;
  }

  public void setTransferAccountId(UUID transferAccountId) {
    this.transferAccountId = transferAccountId;
  }

  public ScheduledSubTransaction deleted(Boolean deleted) {
    this.deleted = deleted;
    return this;
  }

   /**
   * Whether or not the scheduled subtransaction has been deleted.  Deleted scheduled subtransactions will only be included in delta requests.
   * @return deleted
  **/
  @Schema(required = true, description = "Whether or not the scheduled subtransaction has been deleted.  Deleted scheduled subtransactions will only be included in delta requests.")
  public Boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScheduledSubTransaction scheduledSubTransaction = (ScheduledSubTransaction) o;
    return Objects.equals(this.id, scheduledSubTransaction.id) &&
        Objects.equals(this.scheduledTransactionId, scheduledSubTransaction.scheduledTransactionId) &&
        Objects.equals(this.amount, scheduledSubTransaction.amount) &&
        Objects.equals(this.memo, scheduledSubTransaction.memo) &&
        Objects.equals(this.payeeId, scheduledSubTransaction.payeeId) &&
        Objects.equals(this.categoryId, scheduledSubTransaction.categoryId) &&
        Objects.equals(this.transferAccountId, scheduledSubTransaction.transferAccountId) &&
        Objects.equals(this.deleted, scheduledSubTransaction.deleted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, scheduledTransactionId, amount, memo, payeeId, categoryId, transferAccountId, deleted);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScheduledSubTransaction {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    scheduledTransactionId: ").append(toIndentedString(scheduledTransactionId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    memo: ").append(toIndentedString(memo)).append("\n");
    sb.append("    payeeId: ").append(toIndentedString(payeeId)).append("\n");
    sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
    sb.append("    transferAccountId: ").append(toIndentedString(transferAccountId)).append("\n");
    sb.append("    deleted: ").append(toIndentedString(deleted)).append("\n");
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

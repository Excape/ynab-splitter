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
 * SubTransaction
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-09-07T14:51:52.476+02:00[Europe/Zurich]")
public class SubTransaction {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("transaction_id")
  private String transactionId = null;

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

  public SubTransaction id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @Schema(required = true, description = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SubTransaction transactionId(String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

   /**
   * Get transactionId
   * @return transactionId
  **/
  @Schema(required = true, description = "")
  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public SubTransaction amount(Long amount) {
    this.amount = amount;
    return this;
  }

   /**
   * The subtransaction amount in milliunits format
   * @return amount
  **/
  @Schema(required = true, description = "The subtransaction amount in milliunits format")
  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public SubTransaction memo(String memo) {
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

  public SubTransaction payeeId(UUID payeeId) {
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

  public SubTransaction categoryId(UUID categoryId) {
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

  public SubTransaction transferAccountId(UUID transferAccountId) {
    this.transferAccountId = transferAccountId;
    return this;
  }

   /**
   * If a transfer, the account_id which the subtransaction transfers to
   * @return transferAccountId
  **/
  @Schema(description = "If a transfer, the account_id which the subtransaction transfers to")
  public UUID getTransferAccountId() {
    return transferAccountId;
  }

  public void setTransferAccountId(UUID transferAccountId) {
    this.transferAccountId = transferAccountId;
  }

  public SubTransaction deleted(Boolean deleted) {
    this.deleted = deleted;
    return this;
  }

   /**
   * Whether or not the subtransaction has been deleted.  Deleted subtransactions will only be included in delta requests.
   * @return deleted
  **/
  @Schema(required = true, description = "Whether or not the subtransaction has been deleted.  Deleted subtransactions will only be included in delta requests.")
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
    SubTransaction subTransaction = (SubTransaction) o;
    return Objects.equals(this.id, subTransaction.id) &&
        Objects.equals(this.transactionId, subTransaction.transactionId) &&
        Objects.equals(this.amount, subTransaction.amount) &&
        Objects.equals(this.memo, subTransaction.memo) &&
        Objects.equals(this.payeeId, subTransaction.payeeId) &&
        Objects.equals(this.categoryId, subTransaction.categoryId) &&
        Objects.equals(this.transferAccountId, subTransaction.transferAccountId) &&
        Objects.equals(this.deleted, subTransaction.deleted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, transactionId, amount, memo, payeeId, categoryId, transferAccountId, deleted);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubTransaction {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
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
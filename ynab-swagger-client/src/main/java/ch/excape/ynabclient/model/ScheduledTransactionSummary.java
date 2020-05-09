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
import org.threeten.bp.LocalDate;
/**
 * ScheduledTransactionSummary
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-09-07T14:51:52.476+02:00[Europe/Zurich]")
public class ScheduledTransactionSummary {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("date_first")
  private LocalDate dateFirst = null;

  @JsonProperty("date_next")
  private LocalDate dateNext = null;

  /**
   * Gets or Sets frequency
   */
  public enum FrequencyEnum {
    NEVER("never"),
    DAILY("daily"),
    WEEKLY("weekly"),
    EVERYOTHERWEEK("everyOtherWeek"),
    TWICEAMONTH("twiceAMonth"),
    EVERY4WEEKS("every4Weeks"),
    MONTHLY("monthly"),
    EVERYOTHERMONTH("everyOtherMonth"),
    EVERY3MONTHS("every3Months"),
    EVERY4MONTHS("every4Months"),
    TWICEAYEAR("twiceAYear"),
    YEARLY("yearly"),
    EVERYOTHERYEAR("everyOtherYear");

    private String value;

    FrequencyEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static FrequencyEnum fromValue(String text) {
      for (FrequencyEnum b : FrequencyEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

  }  @JsonProperty("frequency")
  private FrequencyEnum frequency = null;

  @JsonProperty("amount")
  private Long amount = null;

  @JsonProperty("memo")
  private String memo = null;

  /**
   * The scheduled transaction flag
   */
  public enum FlagColorEnum {
    RED("red"),
    ORANGE("orange"),
    YELLOW("yellow"),
    GREEN("green"),
    BLUE("blue"),
    PURPLE("purple");

    private String value;

    FlagColorEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static FlagColorEnum fromValue(String text) {
      for (FlagColorEnum b : FlagColorEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

  }  @JsonProperty("flag_color")
  private FlagColorEnum flagColor = null;

  @JsonProperty("account_id")
  private UUID accountId = null;

  @JsonProperty("payee_id")
  private UUID payeeId = null;

  @JsonProperty("category_id")
  private UUID categoryId = null;

  @JsonProperty("transfer_account_id")
  private UUID transferAccountId = null;

  @JsonProperty("deleted")
  private Boolean deleted = null;

  public ScheduledTransactionSummary id(UUID id) {
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

  public ScheduledTransactionSummary dateFirst(LocalDate dateFirst) {
    this.dateFirst = dateFirst;
    return this;
  }

   /**
   * The first date for which the Scheduled Transaction was scheduled.
   * @return dateFirst
  **/
  @Schema(required = true, description = "The first date for which the Scheduled Transaction was scheduled.")
  public LocalDate getDateFirst() {
    return dateFirst;
  }

  public void setDateFirst(LocalDate dateFirst) {
    this.dateFirst = dateFirst;
  }

  public ScheduledTransactionSummary dateNext(LocalDate dateNext) {
    this.dateNext = dateNext;
    return this;
  }

   /**
   * The next date for which the Scheduled Transaction is scheduled.
   * @return dateNext
  **/
  @Schema(required = true, description = "The next date for which the Scheduled Transaction is scheduled.")
  public LocalDate getDateNext() {
    return dateNext;
  }

  public void setDateNext(LocalDate dateNext) {
    this.dateNext = dateNext;
  }

  public ScheduledTransactionSummary frequency(FrequencyEnum frequency) {
    this.frequency = frequency;
    return this;
  }

   /**
   * Get frequency
   * @return frequency
  **/
  @Schema(required = true, description = "")
  public FrequencyEnum getFrequency() {
    return frequency;
  }

  public void setFrequency(FrequencyEnum frequency) {
    this.frequency = frequency;
  }

  public ScheduledTransactionSummary amount(Long amount) {
    this.amount = amount;
    return this;
  }

   /**
   * The scheduled transaction amount in milliunits format
   * @return amount
  **/
  @Schema(required = true, description = "The scheduled transaction amount in milliunits format")
  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public ScheduledTransactionSummary memo(String memo) {
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

  public ScheduledTransactionSummary flagColor(FlagColorEnum flagColor) {
    this.flagColor = flagColor;
    return this;
  }

   /**
   * The scheduled transaction flag
   * @return flagColor
  **/
  @Schema(description = "The scheduled transaction flag")
  public FlagColorEnum getFlagColor() {
    return flagColor;
  }

  public void setFlagColor(FlagColorEnum flagColor) {
    this.flagColor = flagColor;
  }

  public ScheduledTransactionSummary accountId(UUID accountId) {
    this.accountId = accountId;
    return this;
  }

   /**
   * Get accountId
   * @return accountId
  **/
  @Schema(required = true, description = "")
  public UUID getAccountId() {
    return accountId;
  }

  public void setAccountId(UUID accountId) {
    this.accountId = accountId;
  }

  public ScheduledTransactionSummary payeeId(UUID payeeId) {
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

  public ScheduledTransactionSummary categoryId(UUID categoryId) {
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

  public ScheduledTransactionSummary transferAccountId(UUID transferAccountId) {
    this.transferAccountId = transferAccountId;
    return this;
  }

   /**
   * If a transfer, the account_id which the scheduled transaction transfers to
   * @return transferAccountId
  **/
  @Schema(description = "If a transfer, the account_id which the scheduled transaction transfers to")
  public UUID getTransferAccountId() {
    return transferAccountId;
  }

  public void setTransferAccountId(UUID transferAccountId) {
    this.transferAccountId = transferAccountId;
  }

  public ScheduledTransactionSummary deleted(Boolean deleted) {
    this.deleted = deleted;
    return this;
  }

   /**
   * Whether or not the scheduled transaction has been deleted.  Deleted scheduled transactions will only be included in delta requests.
   * @return deleted
  **/
  @Schema(required = true, description = "Whether or not the scheduled transaction has been deleted.  Deleted scheduled transactions will only be included in delta requests.")
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
    ScheduledTransactionSummary scheduledTransactionSummary = (ScheduledTransactionSummary) o;
    return Objects.equals(this.id, scheduledTransactionSummary.id) &&
        Objects.equals(this.dateFirst, scheduledTransactionSummary.dateFirst) &&
        Objects.equals(this.dateNext, scheduledTransactionSummary.dateNext) &&
        Objects.equals(this.frequency, scheduledTransactionSummary.frequency) &&
        Objects.equals(this.amount, scheduledTransactionSummary.amount) &&
        Objects.equals(this.memo, scheduledTransactionSummary.memo) &&
        Objects.equals(this.flagColor, scheduledTransactionSummary.flagColor) &&
        Objects.equals(this.accountId, scheduledTransactionSummary.accountId) &&
        Objects.equals(this.payeeId, scheduledTransactionSummary.payeeId) &&
        Objects.equals(this.categoryId, scheduledTransactionSummary.categoryId) &&
        Objects.equals(this.transferAccountId, scheduledTransactionSummary.transferAccountId) &&
        Objects.equals(this.deleted, scheduledTransactionSummary.deleted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dateFirst, dateNext, frequency, amount, memo, flagColor, accountId, payeeId, categoryId, transferAccountId, deleted);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScheduledTransactionSummary {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dateFirst: ").append(toIndentedString(dateFirst)).append("\n");
    sb.append("    dateNext: ").append(toIndentedString(dateNext)).append("\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    memo: ").append(toIndentedString(memo)).append("\n");
    sb.append("    flagColor: ").append(toIndentedString(flagColor)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
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
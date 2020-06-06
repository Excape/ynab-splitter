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
 * Category
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-06-06T11:46:19.985416+02:00[Europe/Zurich]")
public class Category {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("category_group_id")
  private UUID categoryGroupId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("hidden")
  private Boolean hidden = null;

  @JsonProperty("original_category_group_id")
  private UUID originalCategoryGroupId = null;

  @JsonProperty("note")
  private String note = null;

  @JsonProperty("budgeted")
  private Long budgeted = null;

  @JsonProperty("activity")
  private Long activity = null;

  @JsonProperty("balance")
  private Long balance = null;

  /**
   * The type of goal, if the category has a goal (TB&#x3D;&#x27;Target Category Balance&#x27;, TBD&#x3D;&#x27;Target Category Balance by Date&#x27;, MF&#x3D;&#x27;Monthly Funding&#x27;, NEED&#x3D;&#x27;Plan Your Spending&#x27;)
   */
  public enum GoalTypeEnum {
    TB("TB"),
    TBD("TBD"),
    MF("MF"),
    NEED("NEED");

    private String value;

    GoalTypeEnum(String value) {
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
    public static GoalTypeEnum fromValue(String text) {
      for (GoalTypeEnum b : GoalTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

  }  @JsonProperty("goal_type")
  private GoalTypeEnum goalType = null;

  @JsonProperty("goal_creation_month")
  private LocalDate goalCreationMonth = null;

  @JsonProperty("goal_target")
  private Long goalTarget = null;

  @JsonProperty("goal_target_month")
  private LocalDate goalTargetMonth = null;

  @JsonProperty("goal_percentage_complete")
  private Integer goalPercentageComplete = null;

  @JsonProperty("deleted")
  private Boolean deleted = null;

  public Category id(UUID id) {
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

  public Category categoryGroupId(UUID categoryGroupId) {
    this.categoryGroupId = categoryGroupId;
    return this;
  }

   /**
   * Get categoryGroupId
   * @return categoryGroupId
  **/
  @Schema(required = true, description = "")
  public UUID getCategoryGroupId() {
    return categoryGroupId;
  }

  public void setCategoryGroupId(UUID categoryGroupId) {
    this.categoryGroupId = categoryGroupId;
  }

  public Category name(String name) {
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

  public Category hidden(Boolean hidden) {
    this.hidden = hidden;
    return this;
  }

   /**
   * Whether or not the category is hidden
   * @return hidden
  **/
  @Schema(required = true, description = "Whether or not the category is hidden")
  public Boolean isHidden() {
    return hidden;
  }

  public void setHidden(Boolean hidden) {
    this.hidden = hidden;
  }

  public Category originalCategoryGroupId(UUID originalCategoryGroupId) {
    this.originalCategoryGroupId = originalCategoryGroupId;
    return this;
  }

   /**
   * If category is hidden this is the id of the category group it originally belonged to before it was hidden.
   * @return originalCategoryGroupId
  **/
  @Schema(description = "If category is hidden this is the id of the category group it originally belonged to before it was hidden.")
  public UUID getOriginalCategoryGroupId() {
    return originalCategoryGroupId;
  }

  public void setOriginalCategoryGroupId(UUID originalCategoryGroupId) {
    this.originalCategoryGroupId = originalCategoryGroupId;
  }

  public Category note(String note) {
    this.note = note;
    return this;
  }

   /**
   * Get note
   * @return note
  **/
  @Schema(description = "")
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Category budgeted(Long budgeted) {
    this.budgeted = budgeted;
    return this;
  }

   /**
   * Budgeted amount in milliunits format
   * @return budgeted
  **/
  @Schema(required = true, description = "Budgeted amount in milliunits format")
  public Long getBudgeted() {
    return budgeted;
  }

  public void setBudgeted(Long budgeted) {
    this.budgeted = budgeted;
  }

  public Category activity(Long activity) {
    this.activity = activity;
    return this;
  }

   /**
   * Activity amount in milliunits format
   * @return activity
  **/
  @Schema(required = true, description = "Activity amount in milliunits format")
  public Long getActivity() {
    return activity;
  }

  public void setActivity(Long activity) {
    this.activity = activity;
  }

  public Category balance(Long balance) {
    this.balance = balance;
    return this;
  }

   /**
   * Balance in milliunits format
   * @return balance
  **/
  @Schema(required = true, description = "Balance in milliunits format")
  public Long getBalance() {
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

  public Category goalType(GoalTypeEnum goalType) {
    this.goalType = goalType;
    return this;
  }

   /**
   * The type of goal, if the category has a goal (TB&#x3D;&#x27;Target Category Balance&#x27;, TBD&#x3D;&#x27;Target Category Balance by Date&#x27;, MF&#x3D;&#x27;Monthly Funding&#x27;, NEED&#x3D;&#x27;Plan Your Spending&#x27;)
   * @return goalType
  **/
  @Schema(description = "The type of goal, if the category has a goal (TB='Target Category Balance', TBD='Target Category Balance by Date', MF='Monthly Funding', NEED='Plan Your Spending')")
  public GoalTypeEnum getGoalType() {
    return goalType;
  }

  public void setGoalType(GoalTypeEnum goalType) {
    this.goalType = goalType;
  }

  public Category goalCreationMonth(LocalDate goalCreationMonth) {
    this.goalCreationMonth = goalCreationMonth;
    return this;
  }

   /**
   * The month a goal was created
   * @return goalCreationMonth
  **/
  @Schema(description = "The month a goal was created")
  public LocalDate getGoalCreationMonth() {
    return goalCreationMonth;
  }

  public void setGoalCreationMonth(LocalDate goalCreationMonth) {
    this.goalCreationMonth = goalCreationMonth;
  }

  public Category goalTarget(Long goalTarget) {
    this.goalTarget = goalTarget;
    return this;
  }

   /**
   * The goal target amount in milliunits
   * @return goalTarget
  **/
  @Schema(description = "The goal target amount in milliunits")
  public Long getGoalTarget() {
    return goalTarget;
  }

  public void setGoalTarget(Long goalTarget) {
    this.goalTarget = goalTarget;
  }

  public Category goalTargetMonth(LocalDate goalTargetMonth) {
    this.goalTargetMonth = goalTargetMonth;
    return this;
  }

   /**
   * The target month for the goal to be completed.  Only some goal types specify this date.
   * @return goalTargetMonth
  **/
  @Schema(description = "The target month for the goal to be completed.  Only some goal types specify this date.")
  public LocalDate getGoalTargetMonth() {
    return goalTargetMonth;
  }

  public void setGoalTargetMonth(LocalDate goalTargetMonth) {
    this.goalTargetMonth = goalTargetMonth;
  }

  public Category goalPercentageComplete(Integer goalPercentageComplete) {
    this.goalPercentageComplete = goalPercentageComplete;
    return this;
  }

   /**
   * The percentage completion of the goal
   * @return goalPercentageComplete
  **/
  @Schema(description = "The percentage completion of the goal")
  public Integer getGoalPercentageComplete() {
    return goalPercentageComplete;
  }

  public void setGoalPercentageComplete(Integer goalPercentageComplete) {
    this.goalPercentageComplete = goalPercentageComplete;
  }

  public Category deleted(Boolean deleted) {
    this.deleted = deleted;
    return this;
  }

   /**
   * Whether or not the category has been deleted.  Deleted categories will only be included in delta requests.
   * @return deleted
  **/
  @Schema(required = true, description = "Whether or not the category has been deleted.  Deleted categories will only be included in delta requests.")
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
    Category category = (Category) o;
    return Objects.equals(this.id, category.id) &&
        Objects.equals(this.categoryGroupId, category.categoryGroupId) &&
        Objects.equals(this.name, category.name) &&
        Objects.equals(this.hidden, category.hidden) &&
        Objects.equals(this.originalCategoryGroupId, category.originalCategoryGroupId) &&
        Objects.equals(this.note, category.note) &&
        Objects.equals(this.budgeted, category.budgeted) &&
        Objects.equals(this.activity, category.activity) &&
        Objects.equals(this.balance, category.balance) &&
        Objects.equals(this.goalType, category.goalType) &&
        Objects.equals(this.goalCreationMonth, category.goalCreationMonth) &&
        Objects.equals(this.goalTarget, category.goalTarget) &&
        Objects.equals(this.goalTargetMonth, category.goalTargetMonth) &&
        Objects.equals(this.goalPercentageComplete, category.goalPercentageComplete) &&
        Objects.equals(this.deleted, category.deleted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, categoryGroupId, name, hidden, originalCategoryGroupId, note, budgeted, activity, balance, goalType, goalCreationMonth, goalTarget, goalTargetMonth, goalPercentageComplete, deleted);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Category {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    categoryGroupId: ").append(toIndentedString(categoryGroupId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    hidden: ").append(toIndentedString(hidden)).append("\n");
    sb.append("    originalCategoryGroupId: ").append(toIndentedString(originalCategoryGroupId)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    budgeted: ").append(toIndentedString(budgeted)).append("\n");
    sb.append("    activity: ").append(toIndentedString(activity)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    goalType: ").append(toIndentedString(goalType)).append("\n");
    sb.append("    goalCreationMonth: ").append(toIndentedString(goalCreationMonth)).append("\n");
    sb.append("    goalTarget: ").append(toIndentedString(goalTarget)).append("\n");
    sb.append("    goalTargetMonth: ").append(toIndentedString(goalTargetMonth)).append("\n");
    sb.append("    goalPercentageComplete: ").append(toIndentedString(goalPercentageComplete)).append("\n");
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

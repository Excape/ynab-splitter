# Category

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  | 
**categoryGroupId** | [**UUID**](UUID.md) |  | 
**name** | **String** |  | 
**hidden** | **Boolean** | Whether or not the category is hidden | 
**originalCategoryGroupId** | [**UUID**](UUID.md) | If category is hidden this is the id of the category group it originally belonged to before it was hidden. |  [optional]
**note** | **String** |  |  [optional]
**budgeted** | **Long** | Budgeted amount in milliunits format | 
**activity** | **Long** | Activity amount in milliunits format | 
**balance** | **Long** | Balance in milliunits format | 
**goalType** | [**GoalTypeEnum**](#GoalTypeEnum) | The type of goal, if the category has a goal (TB&#x3D;&#x27;Target Category Balance&#x27;, TBD&#x3D;&#x27;Target Category Balance by Date&#x27;, MF&#x3D;&#x27;Monthly Funding&#x27;, NEED&#x3D;&#x27;Plan Your Spending&#x27;) |  [optional]
**goalCreationMonth** | [**LocalDate**](LocalDate.md) | The month a goal was created |  [optional]
**goalTarget** | **Long** | The goal target amount in milliunits |  [optional]
**goalTargetMonth** | [**LocalDate**](LocalDate.md) | The target month for the goal to be completed.  Only some goal types specify this date. |  [optional]
**goalPercentageComplete** | **Integer** | The percentage completion of the goal |  [optional]
**deleted** | **Boolean** | Whether or not the category has been deleted.  Deleted categories will only be included in delta requests. | 

<a name="GoalTypeEnum"></a>
## Enum: GoalTypeEnum
Name | Value
---- | -----
TB | &quot;TB&quot;
TBD | &quot;TBD&quot;
MF | &quot;MF&quot;
NEED | &quot;NEED&quot;

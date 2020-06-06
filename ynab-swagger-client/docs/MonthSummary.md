# MonthSummary

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**month** | [**LocalDate**](LocalDate.md) |  | 
**note** | **String** |  |  [optional]
**income** | **Long** | The total amount of transactions categorized to &#x27;Inflow: To be Budgeted&#x27; in the month | 
**budgeted** | **Long** | The total amount budgeted in the month | 
**activity** | **Long** | The total amount of transactions in the month, excluding those categorized to &#x27;Inflow: To be Budgeted&#x27; | 
**toBeBudgeted** | **Long** | The available amount for &#x27;To be Budgeted&#x27; | 
**ageOfMoney** | **Integer** | The Age of Money as of the month |  [optional]
**deleted** | **Boolean** | Whether or not the month has been deleted.  Deleted months will only be included in delta requests. | 

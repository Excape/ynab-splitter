# BudgetSummary

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  | 
**name** | **String** |  | 
**lastModifiedOn** | [**OffsetDateTime**](OffsetDateTime.md) | The last time any changes were made to the budget from either a web or mobile client |  [optional]
**firstMonth** | [**LocalDate**](LocalDate.md) | The earliest budget month |  [optional]
**lastMonth** | [**LocalDate**](LocalDate.md) | The latest budget month |  [optional]
**dateFormat** | [**DateFormat**](DateFormat.md) |  |  [optional]
**currencyFormat** | [**CurrencyFormat**](CurrencyFormat.md) |  |  [optional]
**accounts** | [**List&lt;Account&gt;**](Account.md) | The budget accounts (only included if &#x60;include_accounts&#x3D;true&#x60; specified as query parameter) |  [optional]

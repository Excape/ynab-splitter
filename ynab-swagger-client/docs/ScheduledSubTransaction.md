# ScheduledSubTransaction

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  | 
**scheduledTransactionId** | [**UUID**](UUID.md) |  | 
**amount** | **Long** | The scheduled subtransaction amount in milliunits format | 
**memo** | **String** |  |  [optional]
**payeeId** | [**UUID**](UUID.md) |  |  [optional]
**categoryId** | [**UUID**](UUID.md) |  |  [optional]
**transferAccountId** | [**UUID**](UUID.md) | If a transfer, the account_id which the scheduled subtransaction transfers to |  [optional]
**deleted** | **Boolean** | Whether or not the scheduled subtransaction has been deleted.  Deleted scheduled subtransactions will only be included in delta requests. | 

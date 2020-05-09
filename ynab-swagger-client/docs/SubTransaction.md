# SubTransaction

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** |  | 
**transactionId** | **String** |  | 
**amount** | **Long** | The subtransaction amount in milliunits format | 
**memo** | **String** |  |  [optional]
**payeeId** | [**UUID**](UUID.md) |  |  [optional]
**categoryId** | [**UUID**](UUID.md) |  |  [optional]
**transferAccountId** | [**UUID**](UUID.md) | If a transfer, the account_id which the subtransaction transfers to |  [optional]
**deleted** | **Boolean** | Whether or not the subtransaction has been deleted.  Deleted subtransactions will only be included in delta requests. | 

# HybridTransaction

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | [**TypeEnum**](#TypeEnum) | Whether the hybrid transaction represents a regular transaction or a subtransaction | 
**parentTransactionId** | **String** | For subtransaction types, this is the id of the parent transaction.  For transaction types, this id will be always be null. |  [optional]
**accountName** | **String** |  | 
**payeeName** | **String** |  |  [optional]
**categoryName** | **String** |  |  [optional]

<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
TRANSACTION | &quot;transaction&quot;
SUBTRANSACTION | &quot;subtransaction&quot;

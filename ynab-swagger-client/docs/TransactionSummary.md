# TransactionSummary

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** |  | 
**date** | [**LocalDate**](LocalDate.md) | The transaction date in ISO format (e.g. 2016-12-01) | 
**amount** | **Long** | The transaction amount in milliunits format | 
**memo** | **String** |  |  [optional]
**cleared** | [**ClearedEnum**](#ClearedEnum) | The cleared status of the transaction | 
**approved** | **Boolean** | Whether or not the transaction is approved | 
**flagColor** | [**FlagColorEnum**](#FlagColorEnum) | The transaction flag |  [optional]
**accountId** | [**UUID**](UUID.md) |  | 
**payeeId** | [**UUID**](UUID.md) |  |  [optional]
**categoryId** | [**UUID**](UUID.md) |  |  [optional]
**transferAccountId** | [**UUID**](UUID.md) | If a transfer transaction, the account to which it transfers |  [optional]
**transferTransactionId** | **String** | If a transfer transaction, the id of transaction on the other side of the transfer |  [optional]
**matchedTransactionId** | **String** | If transaction is matched, the id of the matched transaction |  [optional]
**importId** | **String** | If the Transaction was imported, this field is a unique (by account) import identifier.  If this transaction was imported through File Based Import or Direct Import and not through the API, the import_id will have the format: &#x27;YNAB:[milliunit_amount]:[iso_date]:[occurrence]&#x27;.  For example, a transaction dated 2015-12-30 in the amount of -$294.23 USD would have an import_id of &#x27;YNAB:-294230:2015-12-30:1&#x27;.  If a second transaction on the same account was imported and had the same date and same amount, its import_id would be &#x27;YNAB:-294230:2015-12-30:2&#x27;. |  [optional]
**deleted** | **Boolean** | Whether or not the transaction has been deleted.  Deleted transactions will only be included in delta requests. | 

<a name="ClearedEnum"></a>
## Enum: ClearedEnum
Name | Value
---- | -----
CLEARED | &quot;cleared&quot;
UNCLEARED | &quot;uncleared&quot;
RECONCILED | &quot;reconciled&quot;

<a name="FlagColorEnum"></a>
## Enum: FlagColorEnum
Name | Value
---- | -----
RED | &quot;red&quot;
ORANGE | &quot;orange&quot;
YELLOW | &quot;yellow&quot;
GREEN | &quot;green&quot;
BLUE | &quot;blue&quot;
PURPLE | &quot;purple&quot;

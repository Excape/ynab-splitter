# SaveTransaction

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**accountId** | [**UUID**](UUID.md) |  | 
**date** | [**LocalDate**](LocalDate.md) | The transaction date in ISO format (e.g. 2016-12-01).  Future dates (scheduled transactions) are not permitted.  Split transaction dates cannot be changed and if a different date is supplied it will be ignored. | 
**amount** | **Long** | The transaction amount in milliunits format.  Split transaction amounts cannot be changed and if a different amount is supplied it will be ignored. | 
**payeeId** | [**UUID**](UUID.md) | The payee for the transaction.  To create a transfer between two accounts, use the account transfer payee pointing to the target account.  Account transfer payees are specified as tranfer_payee_id on the account resource. |  [optional]
**payeeName** | **String** | The payee name.  If a payee_name value is provided and payee_id has a null value, the payee_name value will be used to resolve the payee by either (1) a matching payee rename rule (only if import_id is also specified) or (2) a payee with the same name or (3) creation of a new payee. |  [optional]
**categoryId** | [**UUID**](UUID.md) | The category for the transaction.  Split and Credit Card Payment categories are not permitted and will be ignored if supplied.  If an existing transaction has a Split category it cannot be changed. |  [optional]
**memo** | **String** |  |  [optional]
**cleared** | [**ClearedEnum**](#ClearedEnum) | The cleared status of the transaction |  [optional]
**approved** | **Boolean** | Whether or not the transaction is approved.  If not supplied, transaction will be unapproved by default. |  [optional]
**flagColor** | [**FlagColorEnum**](#FlagColorEnum) | The transaction flag |  [optional]
**importId** | **String** | If specified, the new transaction will be assigned this import_id and considered \&quot;imported\&quot;.  We will also attempt to match this imported transaction to an existing \&quot;user-entered\&quot; transation on the same account, with the same amount, and with a date +/-10 days from the imported transaction date.&lt;br&gt;&lt;br&gt;Transactions imported through File Based Import or Direct Import (not through the API) are assigned an import_id in the format: &#x27;YNAB:[milliunit_amount]:[iso_date]:[occurrence]&#x27;. For example, a transaction dated 2015-12-30 in the amount of -$294.23 USD would have an import_id of &#x27;YNAB:-294230:2015-12-30:1&#x27;.  If a second transaction on the same account was imported and had the same date and same amount, its import_id would be &#x27;YNAB:-294230:2015-12-30:2&#x27;.  Using a consistent format will prevent duplicates through Direct Import and File Based Import.&lt;br&gt;&lt;br&gt;If import_id is omitted or specified as null, the transaction will be treated as a \&quot;user-entered\&quot; transaction. As such, it will be eligible to be matched against transactions later being imported (via DI, FBI, or API). |  [optional]

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

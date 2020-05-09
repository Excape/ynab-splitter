# Account

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  | 
**name** | **String** |  | 
**type** | [**TypeEnum**](#TypeEnum) | The type of account. Note: payPal, merchantAccount, investmentAccount, and mortgage types have been deprecated and will be removed in the future. | 
**onBudget** | **Boolean** | Whether this account is on budget or not | 
**closed** | **Boolean** | Whether this account is closed or not | 
**note** | **String** |  |  [optional]
**balance** | **Long** | The current balance of the account in milliunits format | 
**clearedBalance** | **Long** | The current cleared balance of the account in milliunits format | 
**unclearedBalance** | **Long** | The current uncleared balance of the account in milliunits format | 
**transferPayeeId** | [**UUID**](UUID.md) | The payee id which should be used when transferring to this account | 
**deleted** | **Boolean** | Whether or not the account has been deleted.  Deleted accounts will only be included in delta requests. | 

<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
CHECKING | &quot;checking&quot;
SAVINGS | &quot;savings&quot;
CASH | &quot;cash&quot;
CREDITCARD | &quot;creditCard&quot;
LINEOFCREDIT | &quot;lineOfCredit&quot;
OTHERASSET | &quot;otherAsset&quot;
OTHERLIABILITY | &quot;otherLiability&quot;
PAYPAL | &quot;payPal&quot;
MERCHANTACCOUNT | &quot;merchantAccount&quot;
INVESTMENTACCOUNT | &quot;investmentAccount&quot;
MORTGAGE | &quot;mortgage&quot;

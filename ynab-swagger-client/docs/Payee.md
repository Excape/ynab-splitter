# Payee

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  | 
**name** | **String** |  | 
**transferAccountId** | **String** | If a transfer payee, the account_id to which this payee transfers to |  [optional]
**deleted** | **Boolean** | Whether or not the payee has been deleted.  Deleted payees will only be included in delta requests. | 

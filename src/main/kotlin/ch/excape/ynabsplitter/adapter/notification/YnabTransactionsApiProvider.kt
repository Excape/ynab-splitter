package ch.excape.ynabsplitter.adapter.notification

import ch.excape.ynabclient.api.TransactionsApi

interface YnabTransactionsApiProvider {
    /**
     * Provide a TransactionsApi for YNAB with the access token of the provided
     * user id
     * @throws AccessTokenNotAvailableException
     */
    fun provideTransactionsApi(userId: String): TransactionsApi
}

class AccessTokenNotAvailableException(message: String?) : Exception(message) {
}

package ch.excape.ynabsplitter.service

import ch.excape.ynabclient.model.TransactionDetail
import ch.excape.ynabsplitter.domain.Transaction

fun TransactionDetail.toTransaction() = Transaction(id, date, amount, memo, isApproved, payeeName)
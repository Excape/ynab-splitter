package ch.excape.ynabclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class TransactionsDetailWrapper {

    @JsonProperty("transactions")
    private List<TransactionDetail> transactions;

    public TransactionsDetailWrapper transactions(List<TransactionDetail> transactions) {
        this.transactions = transactions;
        return this;
    }

    public List<TransactionDetail> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDetail> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionsDetailWrapper that = (TransactionsDetailWrapper) o;
        return Objects.equals(getTransactions(), that.getTransactions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactions());
    }

    @Override
    public String toString() {
        return "TransactionDetailWrapper{" +
                "transactions=" + transactions +
                '}';
    }
}

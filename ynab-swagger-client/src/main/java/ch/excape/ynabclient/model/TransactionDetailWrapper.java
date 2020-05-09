package ch.excape.ynabclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class TransactionDetailWrapper {

    @JsonProperty("transaction")
    private TransactionDetail transaction;

    public TransactionDetailWrapper transactions(TransactionDetail transaction) {
        this.transaction = transaction;
        return this;
    }

    public TransactionDetail getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionDetail transaction) {
        this.transaction = transaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDetailWrapper that = (TransactionDetailWrapper) o;
        return Objects.equals(getTransaction(), that.getTransaction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransaction());
    }

    @Override
    public String toString() {
        return "TransactionDetailWrapper{" +
                "transaction=" + transaction +
                '}';
    }
}

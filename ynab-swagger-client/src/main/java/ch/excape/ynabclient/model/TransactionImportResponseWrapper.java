package ch.excape.ynabclient.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionImportResponseWrapper {

    @JsonProperty("transaction_ids")
    private List<String> transactionIds;

    public TransactionImportResponseWrapper transactions(List<String> transactionIds) {
        this.transactionIds = transactionIds;
        return this;
    }

    public List<String> getTransactionIds() {
        return transactionIds;
    }

    public void setTransactionIds(List<String> transactionIds) {
        this.transactionIds = transactionIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionImportResponseWrapper that = (TransactionImportResponseWrapper) o;
        return Objects.equals(getTransactionIds(), that.getTransactionIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionIds());
    }

    @Override
    public String toString() {
        return "TransactionImportResponseWrapper{" +
                "transactionIds=" + transactionIds +
                '}';
    }
}

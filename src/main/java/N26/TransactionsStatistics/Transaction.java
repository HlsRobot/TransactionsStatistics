package N26.TransactionsStatistics;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public class Transaction {

    private BigDecimal amount;

    private DateTime timestamp;

    public Transaction(final BigDecimal amount, final DateTime timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Transaction() { }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }
}

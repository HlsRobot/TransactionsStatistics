package N26.TransactionsStatistics;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionsStatisticsService {

    private static final int SECONDS_ALLOWED = 30;

    private static List<Transaction> transactionList = new ArrayList<>();

    public HttpStatus addNewTransaction(final TransactionDto transaction) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        final DateTime transactionTime = formatter.parseDateTime(transaction.getTimestamp());

        //TODO use the transactionTime instead of new DateTime
        final int timeDifference = this.calculateSecondsDifferenceWithNow(new DateTime(DateTimeZone.UTC));
        if (timeDifference >= SECONDS_ALLOWED) {
            return HttpStatus.NO_CONTENT;
        } else if (timeDifference < 0) {
            return HttpStatus.UNPROCESSABLE_ENTITY;
        } else {
            //TODO use the transactionTime instead of new DateTime
            transactionList.add(new Transaction(transaction.getAmount(), new DateTime(DateTimeZone.UTC)));
        }
        return HttpStatus.OK;
    }

    public StatisticsDto getStatics() {
        final List<BigDecimal> processList = transactionList
                .stream()
                .filter(transaction -> this.calculateSecondsDifferenceWithNow(transaction.getTimestamp()) <= SECONDS_ALLOWED)
                .map(Transaction::getAmount)
                .collect(Collectors.toList());
        final BigDecimal sum = this.calculateSum(processList);
        if (!processList.isEmpty()) {
            return new StatisticsDto.Builder()
                    .sum(this.fixAmountValue(sum))
                    .average(this.fixAmountValue(this.calculateAverage(sum, processList.size())))
                    .maximum(this.fixAmountValue(this.calculateMax(processList)))
                    .minimum(this.fixAmountValue(this.calculateMin(processList)))
                    .count(processList.size())
                    .build();
        }
        return new StatisticsDto.Builder()
                .sum(BigDecimal.ZERO)
                .average(BigDecimal.ZERO)
                .maximum(BigDecimal.ZERO)
                .minimum(BigDecimal.ZERO)
                .count(0)
                .build();
    }

    public void deleteTransactions() {
        transactionList = new ArrayList<>();
    }

    private BigDecimal calculateSum(final List<BigDecimal> transactionList) {
        return transactionList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateAverage(final BigDecimal sum, final int length) {
        return sum.divide(new BigDecimal(length), RoundingMode.HALF_UP);
    }

    private BigDecimal calculateMax(final List<BigDecimal> transactionList) {
        Optional<BigDecimal> max = transactionList.stream()
                .max(BigDecimal::compareTo);
        return max.orElse(null);
    }

    private BigDecimal calculateMin(final List<BigDecimal> transactionList) {
        Optional<BigDecimal> max = transactionList.stream()
                .min(BigDecimal::compareTo);
        return max.orElse(null);
    }

    private int calculateSecondsDifferenceWithNow(final DateTime dateTime) {
        final int test = Seconds.secondsBetween(dateTime, new DateTime(DateTimeZone.UTC)).getSeconds();
        return Seconds.secondsBetween(dateTime, new DateTime(DateTimeZone.UTC)).getSeconds();
    }

    private BigDecimal fixAmountValue(final BigDecimal amount) {
        return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}

package N26.TransactionsStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class StatisticsRestController {

    private TransactionsStatisticsService transactionsStatisticsService;

    @Autowired
    public StatisticsRestController(final TransactionsStatisticsService transactionsStatisticsService) {
        this.transactionsStatisticsService = transactionsStatisticsService;
    }

    @RequestMapping(path = "/transactions", method = RequestMethod.POST)
    public ResponseEntity<String> addTransaction(@RequestBody final TransactionDto transaction) {

        return new ResponseEntity<>(this.transactionsStatisticsService.addNewTransaction(transaction));
    }

    @RequestMapping(path = "/statistics", method = RequestMethod.GET)
    public ResponseEntity<StatisticsDto> getStatistics() {
        return new ResponseEntity<>(this.transactionsStatisticsService.getStatics(), HttpStatus.OK);
    }

    @RequestMapping(path = "/transactions", method = RequestMethod.DELETE)
    public ResponseEntity deleteTransactions() {
        this.transactionsStatisticsService.deleteTransactions();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}

package N26.TransactionsStatistics;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionDto {

	@JsonProperty("amount")
	private BigDecimal amount;
	
	@JsonProperty("timestamp")
	private String timestamp;
	
	private TransactionDto(final BigDecimal amount, final String timestamp) {
		this.amount = amount;
		this.timestamp = timestamp;
	}
	
	public TransactionDto() { }

	public BigDecimal getAmount() {
		return amount;
	}

	public String getTimestamp() {
		return timestamp;
	}
	
	public static class Builder {
		private BigDecimal amount;
		private String timestamp;
		
		public Builder amount(final BigDecimal amount) {
			this.amount = amount;
			return this;
		}
		
		public Builder timestamp(final String timestamp) {
			this.timestamp = timestamp;
			return this;
		}
		
		public TransactionDto build() {
			return new TransactionDto(this.amount, this.timestamp);
		}
	}
}

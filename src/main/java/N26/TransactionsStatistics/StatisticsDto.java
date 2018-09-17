package N26.TransactionsStatistics;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatisticsDto {
	
	@JsonProperty("sum")
	private BigDecimal sum;
	
	@JsonProperty("avg")
	private BigDecimal average;
	
	@JsonProperty("max")
	private BigDecimal maximum;
	
	@JsonProperty("min")
	private BigDecimal minimum;
	
	@JsonProperty("count")
	private Long count;
	
	private StatisticsDto(final BigDecimal sum, final BigDecimal average, final BigDecimal maximum, final BigDecimal minimum, final Long count) {
		this.sum = sum;
		this.average = average;
		this.maximum = maximum;
		this.minimum = minimum;
		this.count = count;
	}
	
	public StatisticsDto() {}

	public BigDecimal getSum() {
		return sum;
	}

	public BigDecimal getAverage() {
		return average;
	}

	public BigDecimal getMaximum() {
		return maximum;
	}

	public BigDecimal getMinimum() {
		return minimum;
	}

	public Long getCount() {
		return count;
	}
	
	public static class Builder {
		private BigDecimal sum;
		private BigDecimal average;
		private BigDecimal maximum;
		private BigDecimal minimum;
		private long count;
		
		public Builder sum(final BigDecimal sum) {
			this.sum = sum;
			return this;
		}
		
		public Builder average(final BigDecimal average) {
			this.average = average;
			return this;
		}

		public Builder maximum(final BigDecimal maximum) {
			this.maximum = maximum;
			return this;
		}

		public Builder minimum(final BigDecimal minimum) {
			this.minimum = minimum;
			return this;
		}

		public Builder count(final long count) {
			this.count = count;
			return this;
		}
		
		public StatisticsDto build() {
			return new StatisticsDto(this.sum, this.average, this.maximum, this.minimum, this.count);
		}


	}

}

package fr.spring;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.integration.annotation.Aggregator;

public class MyAggregator {
	protected static final Logger log = Logger.getLogger(MyAggregator.class);
	@Aggregator
	public String aggregate(List<String> bits) {
		log.info("**************************************************aggregate****************************************");
		StringBuilder sb = new StringBuilder();
		for (String bit : bits) {
			sb.append(bit).append(",");
		}
		// remove final comma, if any
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}
		if (sb.length() < 1) {
			return null;
		}
		return sb.toString();
	}
}

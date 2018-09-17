package fr.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.integration.annotation.Splitter;

public class CommaDelimitedSplitter {
	@Splitter
	public List<String> split(String input) {
		String[] splits = input.split(",");
		List<String> list = new ArrayList<String>();
		for (String split : splits) {
			String trimmed = split.trim();
			if (trimmed.length() > 0) {
				list.add(trimmed);
			}
		}
		return list;
	}
}

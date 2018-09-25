package fr.spring.batch.custom.reader;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

public class CustomItemWriter implements ItemWriter<Personne>{
	private static Logger logger = Logger.getLogger(CustomItemWriter.class);
	@Override
	public void write(List<? extends Personne> arg0) throws Exception {
		logger.info(arg0.toString());
	}

}

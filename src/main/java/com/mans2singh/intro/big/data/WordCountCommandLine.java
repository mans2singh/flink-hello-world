package com.mans2singh.intro.big.data;

import java.util.Arrays;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordCountCommandLine {

	private static final String WORD_SEPARATOR = " ";
	
	protected final static Logger LOGGER = LoggerFactory.getLogger(WordCountCommandLine.class);
	
	public static void main(String[] args) throws Exception {

		LOGGER.info("Arguments were " + Arrays.toString(args));
		
		if ( args.length != 1 ) {
			LOGGER.error("Please enter the sentence");
			return;
		}
		
		// Create an environment
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		// Create a data set
		DataSet<String> words = env.fromElements( args[0].split(WORD_SEPARATOR) );

		// Process the data set for word count
		DataSet<Tuple2<String, Integer>> counts = words.map(new WordMapper())
				.groupBy(0)
				.sum(1);

		// Print the count
		counts.print();
		
	}

}

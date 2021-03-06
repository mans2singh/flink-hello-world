package com.mans2singh.intro.big.data.batch;

import java.util.Arrays;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mans2singh.intro.big.data.WordMapper;

/**
 * This application reads a sentence from command line and counts the words in it 
 * 
 * @author mans2singh
 *
 */
public class WordCountCommandLine {

	/**
	 * Word separator
	 */
	private static final String WORD_SEPARATOR = " ";
	
	/**
	 * Logger for the class
	 */
	protected final static Logger LOGGER = LoggerFactory.getLogger(WordCountCommandLine.class);
	
	/**
	 * Driver class 
	 * @param args sentence
	 * 
	 * @throws Exception
	 */
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

		// Define the output an execute
		counts.print();
		
	}

}

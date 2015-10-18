package com.mans2singh.intro.big.data.batch;

import java.util.Arrays;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This application shows how to extract distinct words from a line
 * 
 * @author msingh2
 *
 */
public class WordDistinctCommandLine {

	/**
	 * The word separator
	 */
	private static final String WORD_SEPARATOR = " ";
	
	/**
	 * Logger for the class
	 */
	protected final static Logger LOGGER = LoggerFactory.getLogger(WordDistinctCommandLine.class);
	
	/**
	 * Driver for the distinct word application
	 * @param args a line
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		LOGGER.info("Arguments were " + Arrays.toString(args));
		
		if ( args.length != 1 ) {
			LOGGER.error("Please enter a sentence");
			return;
		}
		
		// Create an environment
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		// Create a data set
		DataSet<String> words = env.fromElements( args[0].split(WORD_SEPARATOR) );

		DataSet<Tuple1<String>> wordTuple = words.map(new MapFunction<String,Tuple1<String>>() {

			/**
			 * serial version for the class
			 */
			private static final long serialVersionUID = -6263509467340844252L;

			/**
			 * Map a word to a tuple
			 * @param word
			 * @return tuple with the word
			 */
			@Override
			public Tuple1<String> map(String word) throws Exception {
				return new Tuple1<String>(word);
			}});
		
		// Process the data set for word count
		DataSet<Tuple1<String>> distinct = wordTuple.distinct();

		// Print the count
		distinct.print();
		
	}

}

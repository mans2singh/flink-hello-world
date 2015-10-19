package com.mans2singh.intro.big.data.join;

import java.util.Arrays;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.JoinOperator.DefaultJoin;
import org.apache.flink.api.java.tuple.Tuple2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mans2singh.intro.big.data.WordMapper;
import com.mans2singh.intro.big.data.WordSplitter;

/**
 * This example show how to join word counts from two files
 * 
 * @author mans2singh
 */
public class WordJoinBatch {

	/** Logger for class */
	protected final static Logger LOGGER = LoggerFactory.getLogger(WordJoinBatch.class);
	
	/**
	 * Driver class
	 * @param args two files to be joined
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		LOGGER.info("Arguments were " + Arrays.toString(args));
		
		if ( args.length != 2 ) {
			LOGGER.error("Please enter 2 filenames");
			return;
		}
		
		// Create an environment
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		// Create a data set
		DataSet<String> firstWords = env.readTextFile(args[0]);
		DataSet<String> secondWords = env.readTextFile(args[1]);
				
		// Process the data set for word count
		DataSet<Tuple2<String, Integer>> firstCounts = firstWords.flatMap(new WordSplitter()).map(new WordMapper())
				.groupBy(0)
				.sum(1);

		DataSet<Tuple2<String, Integer>> secondCounts = secondWords.flatMap(new WordSplitter()).map(new WordMapper())
				.groupBy(0)
				.sum(1);

		// Join the two data sets
		DefaultJoin<Tuple2<String, Integer>, Tuple2<String, Integer>> joinResult = 
				firstCounts.join(secondCounts).where(0).equalTo(0);
		
		// Print the count
		joinResult.print();
		
	}

}

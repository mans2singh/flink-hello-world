package com.mans2singh.intro.big.data.batch;

import java.util.Arrays;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.JoinOperator.DefaultJoin;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This application shows how to get average of rating counts from movie lens data set
 * based on movie lens data 
 * @author mans2singh
 *
 */
public class MovieLensAverage {

	/**
	 * Logger for the class
	 */
	protected final static Logger LOGGER = LoggerFactory.getLogger(MovieLensAverage.class);
	
	/**
	 * Driver for averaging the rating count
	 * @param args a line
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		LOGGER.info("Arguments were " + Arrays.toString(args));
		
		if ( args.length != 2 ) {
			LOGGER.error("Please enter a u.data and u.item file path");
			return;
		}
		
		// Create an environment
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		// Create a data set (movie ratings)
		DataSet<String> lines = env.readTextFile( args[0] );

		// Create a data set (movie info)
		DataSet<Tuple2<String, String>> itemInfo = env.readTextFile( args[1] ).map(
				new MapFunction<String,Tuple2<String, String>>() {

			/**
		     * 
			 */
			private static final long serialVersionUID = -6672090286342561268L;

			/**
			 * Map a line to a tuple of movie id and it's title
			 * @param input
			 * @return tuple movie id and name
			 */
			@Override
			public Tuple2<String, String> map(String line) throws Exception {
				LOGGER.debug("item line is {}",line);
				String [] tokens = line.split("\\|");
				LOGGER.debug("Item tokens are {}", (Object[])tokens);
				return new Tuple2<String,String>(tokens[0], tokens[1]);
			}});

		DataSet<Tuple3<String, Integer,Integer>> movieIdAndRating = 
				lines.map(new MapFunction<String,Tuple3<String, Integer,Integer>>() {

			/**
			 * serial version for the class
			 */
			private static final long serialVersionUID = -6263509467340844452L;

			/**
			 * Map a line to id, rating and it's id
			 * @param word
			 * @return tuple movie id, rating and count (1)
			 */
			@Override
			public Tuple3<String, Integer,Integer> map(String line) throws Exception {
				LOGGER.debug("line is {}",line);
				String [] tokens = line.split("\t");
				LOGGER.debug("Tokens are {}", (Object[]) tokens);
				return new Tuple3<String,Integer,Integer>(tokens[1], Integer.parseInt(tokens[2]),1);
			}});
		
		// Process the data to get sum of rating and count by id
		// input tuple is id, rating, 1 
		// result is id (group by), sum of rating, and count of elements
		// in the group
		DataSet<Tuple3<String, Integer,Integer>> movieIdAndRatingSum = movieIdAndRating.groupBy(0).sum(1).andSum(2);

		DefaultJoin<Tuple3<String, Integer, Integer>, Tuple2<String, String>> results = 
				movieIdAndRatingSum.join(itemInfo).where(0).equalTo(0);
		
		// Print the count
		results.print();
		
	}

}

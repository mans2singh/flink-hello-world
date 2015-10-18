package com.mans2singh.intro.big.data;

import java.util.Arrays;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This streaming application reads data from a socket and counts the words in it
 * 
 * @author msingh2
 *
 */
public class WordCountSocketStream {

	/** Logger */
	protected final static Logger LOGGER = LoggerFactory.getLogger(WordCountSocketStream.class);
	
	/**
	 * The driver class
	 * @param args requires socket and port
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		LOGGER.info("Arguments were " + Arrays.toString(args));
		
		if ( args.length != 2 ) {
			LOGGER.error("Please enter the host and port to listen");
			return;
		}
		
		// set up the execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// get input data
		DataStream<String> text = env.socketTextStream(args[0], Integer.parseInt(args[1]));

		// split each line into words
		DataStream<String> textWords = text.flatMap(new WordSplitter());

		// map the word into tuple with count
		DataStream<Tuple2<String, Integer>> result = textWords.map(new WordMapper()).
				// group the tuples by first attribute (0) ie, word
				groupBy(0).
				// Add the number of counts (attribute 1)
				sum(1);
		
		result.print();

		// execute program
		env.execute("Streaming WordCount");
				 		
	}

}

package com.mans2singh.intro.big.data;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;


public class WordMapper implements MapFunction<String, Tuple2<String,Integer>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2253987075061995793L;

	@Override
	public Tuple2<String, Integer> map(String word) throws Exception {
		return new Tuple2<String,Integer>(word,1);
	}

	
}

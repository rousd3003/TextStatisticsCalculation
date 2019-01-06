package com.betvictor.task.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;


public class TextStatistics{
	
	private ArrayList<String> paragraphsList;
	private HashMap<String,Integer> wordsCounterMap;
	private ArrayList<Integer> paragraphsSizeList;
	private ArrayList<Long> paragraphsProcessingTimeList;
	public Long totalProcessingTime;
	
	
	public TextStatistics() {
		this.paragraphsList = new ArrayList<String>();
		this.wordsCounterMap = new HashMap<String,Integer>();
		this.paragraphsSizeList = new ArrayList<Integer>();
		this.paragraphsProcessingTimeList = new ArrayList<Long>();
	}

	public void proccessTextPerRequest(String bodyText) throws Exception {
		if (bodyText != null) {
			ObjectMapper mapper =new ObjectMapper();
			Map<String,String> jsonMap = mapper.readValue(bodyText, Map.class);
			String text = jsonMap.get("text_out");
			if (text!=null && !text.isEmpty())
				extractParragraphs(text);
			else {
				throw new Exception("Bad response from Random Text Service");
			}		
		}else{
			throw new Exception("Bad body response from Random Text Service");
		}
	}

	private void computeWordsFromParragraph(String lineText){

		String[] wordsList = lineText.split("\\s+");
		for(String word: wordsList) {
			String formatWord = word.toLowerCase();
			wordsCounterMap.put(formatWord, wordsCounterMap.containsKey(formatWord)? wordsCounterMap.get(formatWord)+1 : 1);
		}
	}


	private void extractParragraphs(String text){
		
		String[] parragraphsPerRequest = text.trim().split("</p>");
		for(int i=0;i<parragraphsPerRequest.length;i++)
		{
			long startProcessingParagraphTime = System.nanoTime();
			String parragraph=parragraphsPerRequest[i].trim().replaceAll("<p>|</p>|\\.", "");
			computeWordsFromParragraph(parragraph);
			extractParagraphSize(parragraph);
			paragraphsList.add(parragraph);
			long endProcessingParagraphTime = System.nanoTime();
			paragraphsProcessingTimeList.add(Long.valueOf(endProcessingParagraphTime - startProcessingParagraphTime));
			
		}
	}

	private void extractParagraphSize(String parragraph) {
		paragraphsSizeList.add(parragraph.length());
		
	}
			
	public String getMostFrequentWord() {
		if (wordsCounterMap!=null && !wordsCounterMap.isEmpty()) {
			int maxRepeated=Collections.max(wordsCounterMap.values());
			for (Map.Entry<String, Integer> wordEntry: wordsCounterMap.entrySet()) {
				if (maxRepeated == wordEntry.getValue().intValue()) {
					return wordEntry.getKey();
				}
			}
		}
		return null;
		
	}
	public Double calculateParragraphsSizeAverage() {
		if (paragraphsSizeList!=null && !paragraphsSizeList.isEmpty())
			return paragraphsSizeList.stream().mapToInt(val -> val).average().getAsDouble();
		return null;
	}

	public Double calculateParragraphProcessingTime() {
		if (paragraphsProcessingTimeList ==null ||paragraphsProcessingTimeList.isEmpty())
			return null;	
		return paragraphsProcessingTimeList.stream().mapToLong(value ->value).average().getAsDouble();
	}
	
	public void setTotalProcessingTime(Long totalProcessingTime) {
		this.totalProcessingTime = totalProcessingTime;
	}
	
	public Long getTotalProcessingTime(){
		return this.totalProcessingTime;
	}
}

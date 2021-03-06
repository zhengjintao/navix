package com.navix.doc.server.commons;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 人员开门队列
 */
public class DocMessageCache {
	
	private Queue<String> queue = new LinkedList<>();
	private static final int maxNum = 10000;
	private static final DocMessageCache cache = new DocMessageCache();
	
	
	public static DocMessageCache getInstance(){
		return cache;
	}
	
	public synchronized boolean add(String userId, String docId, String docMessageId) {
		String key = userId+docId+docMessageId;
		
		if(queue.contains(key)){
			return false;
		}
		
		if(queue.size() >= maxNum){
			queue.poll();
		}
		
		queue.add(key);
		return true;
	}
}

package com.tcaulk.motonav.util;

public class Util {

	public static String arrayToList(Object[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < arr.length; i++) {
			sb.append(arr[i] + (i < arr.length - 1 ? "," : ""));
		}
		
		return sb.toString();
	}
}

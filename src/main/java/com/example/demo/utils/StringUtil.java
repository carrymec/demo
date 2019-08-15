package com.example.demo.utils;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

	// 1,2,3
	public static List<Integer> splitToListInt(String str) {
		List<String> stringList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
		return stringList.stream().map(Integer::parseInt).collect(Collectors.toList());
	}

	public static List<String> splitToListString(String str) {
		List<String> stringList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
		return stringList.stream().map(strItem -> strItem).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		String a = "a,b,v,d,t,h,";
		List<String> s = splitToListString(a);
		String f1 = s.get(0);
		System.out.println(f1);
		s.remove(0);
		String symbol = StringUtils.join(s.toArray(), ",");
		System.out.println(symbol);
	}

}

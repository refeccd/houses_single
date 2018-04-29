package com.liaozan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@SpringBootApplication
@EnableAsync
@EnableSwagger2
public class WebApplication {

	public static void main (String[] args) {
		SpringApplication.run(WebApplication.class, args);
		List<Map<String, Object>> list = new ArrayList<>(); 
		HashMap hashMap = new HashMap();
		hashMap.put("a", 1);
		hashMap.put("c", 3);
		list.add(hashMap);
		HashMap hashMap1 = new HashMap();
		hashMap1.put("a", 11);
		hashMap1.put("b", 2);
		list.add(hashMap1);
		HashMap hashMap2 = new HashMap();
		hashMap2.put("a", 111);
		hashMap2.put("c", 3);
		list.add(hashMap2);
		HashMap hashMap3 = new HashMap();
		hashMap3.put("b", 22);
		list.add(hashMap3);
		HashMap hashMap4 = new HashMap();
		hashMap4.put("a", 1111);
		hashMap4.put("b", 222);
		list.add(hashMap4);
		System.out.println(list);
		sort(list);
	}

	public static void sort(List<Map<String, Object>> list){
		Map<String,List<Object>> listMap = new HashMap<>();
		for (Map<String, Object> map : list) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if(!listMap.containsKey(entry.getKey())){
					List<Object> temp = new ArrayList<>();
					temp.add(entry.getValue());
					listMap.put(entry.getKey(),temp);
				}else {
					List<Object> objectList = listMap.get(entry.getKey());
					objectList.add(entry.getValue());
				}
			}
		}
		listMap.forEach((k,v)-> v.sort((o1, o2) -> (int)o2 - (int)o1));
		System.out.println(listMap);
	}

}

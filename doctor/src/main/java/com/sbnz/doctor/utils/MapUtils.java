package com.sbnz.doctor.utils;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Nikola
 *
 */
public class MapUtils {

	public static MapRetVal getMostLikelyCode(HashMap<String, Double> mapa) {
		double max = 0.0;
		String code = "";
		if (mapa.size() > 0) {
			for (String key : mapa.keySet()) {
				if (mapa.get(key) > max) {
					max = mapa.get(key);
					code = key;
				}
			}
		}
		
		
		HashSet<String> nekiSet = new HashSet<>();
		
		
		
		
		
		
		
		MapRetVal retVal = new MapRetVal(code, max);
		return retVal;
	}
}

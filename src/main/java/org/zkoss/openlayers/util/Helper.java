/* JSONHelper.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 20, 2012 10:44:02 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jumperchen
 *
 */
public class Helper {
	@SuppressWarnings("unchecked")
	public static Map toMap(Object[]... pairs) {
		Map map = new HashMap();
		for (Object[] pair : pairs) {
			map.put(pair[0], pair[1]);
		}
		return map;
	}
	public static Object[] pair(String key, Object value) {
		return new Object[]{key, value};
	}
	
	/**
	 * Merges the map with the new value and returns a new map.
	 */
	@SuppressWarnings("unchecked")
	public static Map mergeMap(Map map, Object[]... pairs) {
		if (map == null) {
			map = new HashMap();
		}
		Map newMap = new HashMap(map);
		for (Object[] pair : pairs) {
			newMap.put(pair[0], pair[1]);
		}
		return newMap;
	}
}

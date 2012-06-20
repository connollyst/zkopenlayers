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
}

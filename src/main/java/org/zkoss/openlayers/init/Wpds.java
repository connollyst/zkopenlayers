/* Wpds.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 19, 2012 2:03:36 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.init;

import org.zkoss.json.JSONValue;

/**
 * @author jumperchen
 *
 */
public class Wpds {
	public static String outOLListJavaScript() {
		final StringBuffer sb = new StringBuffer(4096)
		.append("openlayers.configs = {");
		Configuration config = Configuration.getInstance();
		sb.append("layers: ").append(JSONValue.toJSONString(config.getLayers()))
			.append("};");
		return sb.toString();
	}
}

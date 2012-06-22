/* Control.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 20, 2012 10:24:45 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.control;

import java.util.Map;

import org.zkoss.openlayers.OLWidget;

/**
 * @author jumperchen
 *
 */
public abstract class Control extends OLWidget {
	private Map _options;
	public Control(Map options) {
		_options = options;
	}
	protected Map getOptions() {
		return _options;
	}
	
	public String toJSONString() {
		return toJSONFun(getNativeClass(),
				mergeMap(getOptions(), "uuid", getUuid()));
	}
}

/* Strategy.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 22, 2012 6:16:07 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers.strategy;

import java.util.Map;

import org.zkoss.openlayers.OLWidget;

/**
 * @author jumperchen
 * 
 */
public abstract class Strategy extends OLWidget {
	private Map _options;

	public Strategy(Map options) {
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

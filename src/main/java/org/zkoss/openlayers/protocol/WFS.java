/* WFS.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 22, 2012 6:13:29 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.protocol;

import java.util.Map;

import org.zkoss.openlayers.OLWidget;

/**
 * @author jumperchen
 *
 */
public class WFS extends OLWidget {
	private Map _options;
	public WFS(Map options) {
		_options = options;
	}
	@Override
	public String toJSONString() {
		return toJSONFun(getNativeClass(), _options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Protocol.WFS";
	}

}

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
import org.zkoss.openlayers.util.Function;

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
	protected String getNativeClass() {
		return "OpenLayers.Protocol.WFS";
	}
	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _options);
	}

}

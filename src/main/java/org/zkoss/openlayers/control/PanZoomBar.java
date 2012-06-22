/* PanZoomBar.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 6:53:04 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.control;

import java.util.Map;

/**
 * @author jumperchen
 *
 */
public class PanZoomBar extends PanZoom {
	public PanZoomBar() {
		super(null);
	}

	public PanZoomBar(Map options) {
		super(options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Control.PanZoomBar";
	}
}

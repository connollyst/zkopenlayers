/* PanZoom.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 6:52:12 PM , Created by jumperchen
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
public class PanZoom extends Control {

	public PanZoom() {
		super(null);
	}

	public PanZoom(Map options) {
		super(options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Control.PanZoom";
	}

}

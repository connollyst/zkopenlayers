/* Navigation.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 6:50:11 PM , Created by jumperchen
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
public class Navigation extends Control {
	public Navigation() {
		super(null);
	}
	public Navigation(Map options) {
		super(options);
	}
	
	public void enableZoomWheel() {
		clientUpdate("enableZoomWheel");
	}
	
	public void disableZoomWheel() {
		clientUpdate("disableZoomWheel");		
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Control.Navigation";
	}

}

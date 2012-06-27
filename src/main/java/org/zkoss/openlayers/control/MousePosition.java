/* MousePosition.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 26, 2012 3:53:26 PM , Created by jumperchen
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
public class MousePosition extends Control {

	public MousePosition(Map options) {
		super(options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Control.MousePosition";
	}

}

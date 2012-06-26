/* OverviewMap.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 25, 2012 11:38:18 AM , Created by jumperchen
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
public class OverviewMap extends Control {

	public OverviewMap(Map options) {
		super(options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Control.OverviewMap";
	}

}

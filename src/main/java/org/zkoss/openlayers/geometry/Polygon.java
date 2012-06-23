/* Polygon.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 23, 2012 10:08:28 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers.geometry;

import java.util.List;

/**
 * @author jumperchen
 * 
 */
public class Polygon extends Collection {

	public Polygon(List<? extends LinearRing> components) {
		super(components);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Geometry.Polygon";
	}

}

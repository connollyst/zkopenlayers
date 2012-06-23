/* Curve.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 23, 2012 8:49:31 PM , Created by jumperchen
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
public class Curve extends MultiPoint {

	public Curve(List<? extends Point> components) {
		super(components);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Geometry.Curve";
	}

}

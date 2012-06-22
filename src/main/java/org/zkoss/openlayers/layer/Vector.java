/* Vector.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 22, 2012 5:30:55 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.layer;

import java.util.Map;

/**
 * @author jumperchen
 *
 */
public class Vector extends Layer {

	
	public Vector(String name, Map options) {
		super(name, options);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.Vector";
	}

}

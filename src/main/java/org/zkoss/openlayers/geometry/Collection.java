/* Collection.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 23, 2012 8:41:30 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.geometry;

import java.util.List;

import org.zkoss.openlayers.base.Projection;
import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 *
 */
public class Collection extends Geometry {
	private List<? extends Geometry> _components;
	public Collection(List<? extends Geometry> components) {
		_components = components;
	}

	public Collection transform(Projection source, Projection dest) {
		getNativeObject().invoke("transform", source, dest);
		return this;
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Geometry.Collection";
	}
	
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _components);
	}

}

/* Projection.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 20, 2012 3:22:33 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.base;

import org.zkoss.openlayers.OLWidget;

/**
 * @author jumperchen
 *
 */
public class Projection extends OLWidget {

	private String _projCode;
	public Projection(String projCode) {
		_projCode = projCode;
	}
	@Override
	public String toJSONString() {
		return "new " + getNativeClass() + "('" + this._projCode + "')";
	}

	/* (non-Javadoc)
	 * @see org.zkoss.openlayers.OLWidget#getNativeClass()
	 */
	@Override
	protected String getNativeClass() {
		// TODO Auto-generated method stub
		return "OpenLayers.Projection";
	}

}

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

import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 *
 */
public class Projection extends OLBase {

	private String _projCode;
	public Projection(String projCode) {
		_projCode = projCode;
	}
	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), this._projCode);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Projection";
	}

}

/* WMTS.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 3:38:55 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.layer;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.openlayers.util.Helper;

/**
 * @author jumperchen
 *
 */
public class WMTS extends Layer {
	
	public WMTS(Map options) {
		super(null, options);
	}
	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.WMTS";
	}

	public void mergeNewParams(Map params) {
		if (_options == null)
			_options = new HashMap();
		_options.putAll(params);
		clientUpdate("mergeNewParams", params);
	}
	@Override
	public String toJSONString() {
		return toJSONFun(getNativeClass(),
				mergeMap(getOptions(), "uuid", getUuid()));
	}
}

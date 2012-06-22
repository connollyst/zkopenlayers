/* Bing.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 22, 2012 4:52:22 PM , Created by jumperchen
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
public class Bing extends XYZ {

	public Bing(Map options) {
		super(null, null, options);
	}
	
	@Override
	public String toJSONString() {
		return toJSONFun(getNativeClass(), mergeMap(getOptions(), "uuid", getUuid()));
	}
	
	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.Bing";
	}

}

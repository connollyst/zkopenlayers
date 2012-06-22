/* OSM.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 3:30:29 PM , Created by jumperchen
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
public class OSM extends XYZ {
	public OSM() {
		this(null, null, null);
	}
	public OSM(String name) {
		this(name, null, null);
	}
	public OSM(String name, String url, Map options) {
		super(name, url, options);
	}

	public OSM(Map options) {
		this(null, null, options);
	}

	public String toJSONString() {
		return toJSONFun(getNativeClass(),
				getName(), getURL(),
				mergeMap(getOptions(), "uuid", getUuid()));
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.OSM";
	}

}

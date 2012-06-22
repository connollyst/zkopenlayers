/* WMS.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 11:52:40 AM , Created by jumperchen
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
public class WMS extends Layer {
	private String _url;
	public WMS(String name, String url, Map options) {
		super(name, options);
		_url = url;
	}

	public String getURL() {
		return _url;
	}
	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.WMS";
	}
	
	public void mergeNewParams(Map params) {
		if (_options == null)
			_options = new HashMap();
		_options.putAll(params);
		if (_map != null)
			_map.clientUpdate(this, "mergeNewParams", params);
	}
	@Override
	public String toJSONString() {
		return toJSONFun(getNativeClass(), getName(), getURL(),
				getOptions(), Helper.toMap(Helper.pair("uuid", getUuid())));
	}
}

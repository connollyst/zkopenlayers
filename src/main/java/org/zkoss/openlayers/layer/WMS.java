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

import org.zkoss.openlayers.util.Function;
import static org.zkoss.openlayers.util.Helper.mergeMap;
import static org.zkoss.openlayers.util.Helper.pair;

/**
 * @author jumperchen
 * 
 */
public class WMS extends Layer {
	private String _url;

	private Map _params;

	public WMS(String name, String url, Map params, Map options) {
		super(name, options);
		_url = url;
		_params = params;
	}

	public WMS(String name, String url, Map params) {
		this(name, url, params, null);
	}

	public String getURL() {
		return _url;
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.WMS";
	}

	public void mergeNewParams(Map params) {
		if (_params == null)
			_params = new HashMap();
		_params.putAll(params);
		clientUpdate("mergeNewParams", params);
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), getName(), getURL(), _params,
				mergeMap(getOptions(), pair("uuid", getUuid())));
	}
}

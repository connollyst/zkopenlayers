/* Layer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 19, 2012 12:27:23 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers.init;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONValue;

/**
 * @author jumperchen
 * 
 */
public class Layer implements JSONAware {
	private String _vendor;

	private String _clsnm;

	private Map<String, String> _props;

	public Layer(String vendor, String clsnm) {
		_vendor = vendor;
		_clsnm = clsnm;
		_props = new HashMap<String, String>();
	}

	public String getVendor() {
		return _vendor;
	}

	public String getClassName() {
		return _clsnm;
	}

	public void addProp(String name, String type) {
		_props.put(name, type);
	}

	public String getProp(String name) {
		return _props.get(name);
	}

	public Map<String, String> getProps() {
		return _props;
	}

	public String toJSONString() {
		StringBuffer sb = new StringBuffer();
		sb.append('{').append("vendor: '").append(_vendor)
				.append("\',").append("className:")
				.append(_clsnm).append(',').append("props:")
				.append(JSONValue.toJSONString(_props)).append('}');
		return sb.toString();
	}

	public String toString() {
		return toJSONString();
	}
}

/* OLWidget.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 20, 2012 10:01:35 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers;

import java.util.Map;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONValue;

/**
 * @author jumperchen
 *
 */
public abstract class OLWidget implements JSONAware {
	protected Openlayers _map;
	private String _uuid;
	private static final String ANONYMOUS_ID = "z__ol";
	private static int _anonymousId;
	protected void clientUpdate(String attr, Object value) {
		if (_map != null)
			_map.clientUpdate(this, attr, value);
	}
	
	public String getUuid() {
		if (_uuid == null) {
			_uuid = ANONYMOUS_ID + _anonymousId++;
		}
		return _uuid;
	}
	protected abstract String getNativeClass();
	
	/** package */ void setMap(Openlayers map) {
		if (_map != map) {
			if (_map != null) {
				_map.removeOLWidget(this);
			}
			_map = map;
		}
	}
	protected static String toJSONFun(String codes) {
		StringBuilder sb = new StringBuilder(64);
		sb.append("(function (){ return ").append(codes).append(";})()");
		return sb.toString();
	}
	protected static String toJSONMap(String... pairs) {
		StringBuilder sb = new StringBuilder(64);
		sb.append('{');
		for (String pair : pairs) {
			sb.append(pair).append(',');
		}
		int len = sb.length();
		if (len > 1) {
			sb.delete(len -1, len);
		}
		return sb.append('}').toString();
	}
	protected static String pair(String key, Object value, boolean asString) {
		if (value instanceof String) {
			return asString ? key + ":\"" + value + "\""
						: key + ":" + value;
		}
		return key + ":" + JSONValue.toJSONString(value);
	}
	protected static Map add(Map options, String key, Object val) {
		options.put(key, val);
		return options;
	}
	protected static String pair(String key, Object value) {
		return pair(key, value, true);
	}
	@Override
	public String toString() {
		return toJSONString();
	}
}

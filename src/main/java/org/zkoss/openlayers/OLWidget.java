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

import java.util.HashMap;
import java.util.Map;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONValue;
import org.zkoss.openlayers.util.Helper;

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
	
	/**
	 * Internal use only.
	 */
	public void setMap(Openlayers map) {
		if (_map != map) {
			if (_map != null) {
				_map.removeOLWidget(this);
			}
			_map = map;
		}
	}
	protected static String toJSONFun(String className, Object... arguments) {
		StringBuilder sb = new StringBuilder(64);
		sb.append("(function (){ return new ").append(className)
			.append('(');
		if (arguments.length > 0) {
			for (Object arg : arguments)
				sb.append(JSONValue.toJSONString(arg)).append(',');
			int len = sb.length();
			sb.deleteCharAt(len - 1);
		}
		sb.append(");})()");
		return sb.toString();
	}
	protected static String toJSONExecFun(String initfun, String method, Object... arguments) {
		StringBuilder sb = new StringBuilder(64);
		sb.append("(function (){ var _a = ").append(initfun)
			.append("; _a['").append(method).append("'](");
		if (arguments.length > 0) {
			for (Object arg : arguments)
				sb.append(JSONValue.toJSONString(arg)).append(',');
			int len = sb.length();
			sb.deleteCharAt(len - 1);
		}
		sb.append(");return _a;})()");
		return sb.toString();	
	}
	protected static String toJSONExecFuns(String initfun, String method, Object[] arguments) {
		StringBuilder sb = new StringBuilder(64);
		sb.append("(function (){ var _a = ").append(initfun)
			.append(';');
		if (arguments.length > 0) {
			for (Object arg : arguments)
				sb.append(" _a['").append(method).append("'](").append(JSONValue.toJSONString(arg)).append(");");
		}
		sb.append("return _a;})()");
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
	
	/**
	 * Merges the options with the new value and returns a new map. 
	 */
	@SuppressWarnings("unchecked")
	protected static Map mergeMap(Map options, String key, Object val) {
		if (options == null) {
			return Helper.toMap(Helper.pair(key, val));
		} else {
			Map newOptions = new HashMap();
			newOptions.putAll(options);
			newOptions.put(key, val);
			return newOptions;
		}
	}
	protected static String pair(String key, Object value) {
		return pair(key, value, true);
	}
	@Override
	public String toString() {
		return toJSONString();
	}
}

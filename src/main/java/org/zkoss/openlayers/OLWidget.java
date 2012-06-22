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

	private Map<String, Object[]> _attrQueue;

	protected void clientUpdate(String attr, Object... value) {
		if (_map != null)
			_map.clientUpdate(this, attr, value);
		else {
			if (_attrQueue == null)
				_attrQueue = new HashMap<String, Object[]>();
			_attrQueue.put(attr, value);
		}
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
	protected void setMap(Openlayers map) {
		if (_map != map) {
			final Openlayers oldMap = _map;
			if (_map != null) {
				_map.removeOLWidget(this);
			}
			_map = map;
			if (_map != null) {
				onMapAttached(_map, oldMap);
			} else {
				onMapDetached(oldMap);
			}
		}
	}

	/**
	 * A call back method after map attached.
	 * <p>
	 * Subclass must invoke super.onMapAttached() to flush the client's update
	 * queue.
	 */
	public void onMapAttached(Openlayers newMap, Openlayers oldMap) {
		if (_attrQueue != null) {
			for (String key : _attrQueue.keySet())
				_map.clientUpdate(this, key, _attrQueue.remove(key));
		}
	}

	/**
	 * A call back method after map detached
	 * <p>
	 * Default: does nothing.
	 */
	public void onMapDetached(Openlayers map) {
	}

	protected static String toJSONFun(String className, Object... arguments) {
		StringBuilder sb = new StringBuilder(64);
		sb.append("(function (){ return new ").append(className).append('(');
		if (arguments.length > 0) {
			for (Object arg : arguments)
				sb.append(JSONValue.toJSONString(arg)).append(',');
			int len = sb.length();
			sb.deleteCharAt(len - 1);
		}
		sb.append(");})()");
		return sb.toString();
	}
	protected static String toJSONExecFunWithContent(String initfun, String content) {
		StringBuilder sb = new StringBuilder(64);
		sb.append("(function (){ var _a = ").append(initfun).append("; _a")
				.append(content).append(";return _a;})()");
		return sb.toString();
	}
	protected static String toJSONExecFun(String initfun, String method,
			Object... arguments) {
		StringBuilder sb = new StringBuilder(64);
		sb.append("(function (){ var _a = ").append(initfun).append("; _a['")
				.append(method).append("'](");
		if (arguments.length > 0) {
			for (Object arg : arguments)
				sb.append(JSONValue.toJSONString(arg)).append(',');
			int len = sb.length();
			sb.deleteCharAt(len - 1);
		}
		sb.append(");return _a;})()");
		return sb.toString();
	}

	protected static String toJSONExecFuns(String initfun, String method,
			Object[] arguments) {
		StringBuilder sb = new StringBuilder(64);
		sb.append("(function (){ var _a = ").append(initfun).append(';');
		if (arguments.length > 0) {
			for (Object arg : arguments)
				sb.append(" _a['").append(method).append("'](")
						.append(JSONValue.toJSONString(arg)).append(");");
		}
		sb.append("return _a;})()");
		return sb.toString();
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
	@Override
	public String toString() {
		return toJSONString();
	}
}

/* StyleMap.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 22, 2012 5:32:31 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers.base;

import java.util.Map;

import org.zkoss.json.JSONValue;

/**
 * @author jumperchen
 * 
 */
public class StyleMap extends OLBase {
	private Style _style;

	private Map _options;

	private StringBuilder _buffer;

	public StyleMap(Map options) {
		this(null, options);
	}

	public StyleMap(Style style, Map options) {
		_style = style;
		_options = options;
	}

	public void addUniqueValueRules(String renderIntent, String property,
			Map symbolizers, Map context) {
		if (_buffer == null)
			_buffer = new StringBuilder(64);
		_buffer.append(".addUniqueValueRules(\'")
				.append(renderIntent)
				.append("\',\'")
				.append(property)
				.append("\',")
				.append(symbolizers != null ? JSONValue
						.toJSONString(symbolizers) : "null")
				.append(',')
				.append(context != null ? JSONValue.toJSONString(context)
						: "null").append(')');
	}

	@Override
	public String toJSONString() {
		final String initFun = toJSONFun(getNativeClass(), _style, _options);
		if (_buffer != null)
			return toJSONExecFunWithContent(initFun, _buffer.toString());
		return initFun;
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.StyleMap";
	}

}

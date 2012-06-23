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
import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 * 
 */
public class StyleMap extends OLBase {
	private Style _style;

	private Map _options;

	public StyleMap(Map options) {
		this(null, options);
	}

	public StyleMap(Style style, Map options) {
		_style = style;
		_options = options;
	}

	public void addUniqueValueRules(String renderIntent, String property,
			Map symbolizers, Map context) {
		getNativeObject().invoke("addUniqueValueRules", renderIntent, property,
				symbolizers, context);
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _style, _options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.StyleMap";
	}

}

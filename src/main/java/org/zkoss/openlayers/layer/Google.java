/* Google.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 20, 2012 11:03:17 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers.layer;

import java.util.Map;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONValue;

/**
 * @author jumperchen
 * 
 */
public class Google extends Layer {
	public enum Type implements JSONAware {
		NORMAL, PHYSICAL, HYBRID, SATELLITE;
		@Override
		public String toJSONString() {
			switch (this) {
			case NORMAL:
				return "G_NORMAL_MAP";
			case PHYSICAL:
				return "G_PHYSICAL_MAP";
			case HYBRID:
				return "G_HYBRID_MAP";
			case SATELLITE:
				return "G_SATELLITE_MAP";
			}
			return "";
		}
	}
	public Google(Map options) {
		super(null, options);
	}
	public Google(String name, Map options) {
		super(name, options);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.Google";
	}

	@Override
	public String toJSONString() {
		return toJSONMap(pair("id", getUuid()), pair("name", getName()),
				pair("pkg", getNativeClass(), false),
				pair("options", add(getOptions(), "uuid", getUuid())));
	}
}

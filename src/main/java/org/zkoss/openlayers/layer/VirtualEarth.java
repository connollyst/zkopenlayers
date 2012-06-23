/* VirtualEarth.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 4:07:27 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.layer;

import java.util.Map;

import org.zkoss.json.JSONAware;

/**
 * @author jumperchen
 *
 */
public class VirtualEarth extends Layer {
	public enum Type implements JSONAware {
		AERIAL, BIRDSEYE, BIRDSEYEHYBRID, HYBRID,
		OBLIQUE, ROAD, SHADED;
		@Override
		public String toJSONString() {
			switch (this) {
			case AERIAL:
				return "VEMapStyle.Aerial";
			case BIRDSEYE:
				return "VEMapStyle.Birdseye";
			case BIRDSEYEHYBRID:
				return "VEMapStyle.BirdseyeHybrid";
			case HYBRID:
				return "VEMapStyle.Hybrid";
			case OBLIQUE:
				return "VEMapStyle.Oblique";
			case ROAD:
				return "VEMapStyle.Road";
			case SHADED:
				return "VEMapStyle.Shaded";
			}
			return "";
		}
	}
	public VirtualEarth(String name, Map options) {
		super(name, options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.VirtualEarth";
	}

}

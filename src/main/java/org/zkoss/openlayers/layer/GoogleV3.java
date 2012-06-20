/* GoogleV3.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 20, 2012 12:31:13 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.layer;

import java.util.Map;

import org.zkoss.json.JSONAware;
import org.zkoss.openlayers.base.Projection;

/**
 * @author jumperchen
 *
 */
public class GoogleV3 extends Google {
	public enum Type implements JSONAware {
		ROADMAP, SATELLITE , HYBRID , TERRAIN ;
		@Override
		public String toJSONString() {
			switch (this) {
			case ROADMAP:
				return "google.maps.MapTypeId.ROADMAP";
			case SATELLITE:
				return "google.maps.MapTypeId.SATELLITE";
			case HYBRID:
				return "google.maps.MapTypeId.HYBRID";
			case TERRAIN:
				return "google.maps.MapTypeId.TERRAIN";
			}
			return "";
		}
	}
	public GoogleV3(Map options) {
		super(null, options);
	}
	public GoogleV3(String name, Map options) {
		super(name, options);
	}
	@Override
	public Projection getProjection() {
		return new Projection("EPSG:900913");
	}
}

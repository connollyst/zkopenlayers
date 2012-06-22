/* Layer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 20, 2012 10:24:30 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.layer;

import java.util.Map;

import org.zkoss.openlayers.OLWidget;
import org.zkoss.openlayers.base.Projection;

/**
 * @author jumperchen
 *
 */
public abstract class Layer extends OLWidget {
	private String _name;
	protected Map _options;
	public Layer(String name, Map options) {
		_name = name;
		_options = options;
	}
	protected Map getOptions() {
		return _options;
	}
	public String getName() {
		return _name;
	}
	public void addOptions(Map options, boolean reinitialize) {
		
	}
	public Projection getProjection() {
		return null;
	}

	public String toJSONString() {
		return toJSONFun(getNativeClass(), getName(),
				mergeMap(getOptions(), "uuid", getUuid()));
	}

}

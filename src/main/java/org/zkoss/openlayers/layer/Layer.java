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

import java.util.HashMap;
import java.util.Map;

import org.zkoss.openlayers.OLWidget;
import org.zkoss.openlayers.base.Projection;
import org.zkoss.openlayers.util.Function;

import static org.zkoss.openlayers.util.Helper.mergeMap;
import static org.zkoss.openlayers.util.Helper.pair;
/**
 * @author jumperchen
 *
 */
public abstract class Layer extends OLWidget implements Cloneable {
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
		clientUpdate("addOptions", new Object[] {options, reinitialize});
	}
	public Projection getProjection() {
		return null;
	}

	protected Function newNativeObject() {
		return new Function(getNativeClass(), getName(), mergeMap(getOptions(), pair("uuid",
				getUuid())));
	}
	
	public Object clone() {
		Layer clone = null;
		try {
			clone = (Layer)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
		if (clone._options != null) {
			clone._options = new HashMap(_options);
		}
		clone._map = null;
		return clone;
	}

}

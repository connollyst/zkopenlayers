/* Control.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 20, 2012 10:24:45 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers.control;

import java.util.Map;

import org.zkoss.openlayers.OLWidget;
import org.zkoss.openlayers.util.Function;
import static org.zkoss.openlayers.util.Helper.mergeMap;
import static org.zkoss.openlayers.util.Helper.pair;

/**
 * @author jumperchen
 * 
 */
public abstract class Control extends OLWidget {
	private Map _options;

	public Control(Map options) {
		_options = options;
	}
	public void activate() {
		clientUpdate("activate");
	}
	public void deactivate() {
		clientUpdate("deactivate");
	}
	
	protected Map getOptions() {
		return _options;
	}
	protected Function newNativeObject() {
		return new Function(getNativeClass(), mergeMap(getOptions(),
					pair("uuid", getUuid())));
	}
}

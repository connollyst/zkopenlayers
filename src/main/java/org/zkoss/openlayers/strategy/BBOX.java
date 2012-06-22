/* BBOX.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 22, 2012 6:17:26 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.strategy;

import java.util.Map;

/**
 * @author jumperchen
 *
 */
public class BBOX extends Strategy {
	public BBOX() {
		this(null);
	}
	public BBOX(Map options) {
		super(options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Strategy.BBOX";
	}

}

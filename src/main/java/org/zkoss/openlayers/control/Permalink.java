/* Permalink.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 6:54:09 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.control;

import java.util.Map;

/**
 * @author jumperchen
 *
 */
public class Permalink extends Control {
	public Permalink() {
		this(null);
	}
	public Permalink(Map options) {
		super(options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Control.Permalink";
	}

}

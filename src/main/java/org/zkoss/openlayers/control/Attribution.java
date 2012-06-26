/* Attribution.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 25, 2012 12:18:06 PM , Created by jumperchen
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
public class Attribution extends Control {
	public Attribution() {
		this(null);
	}

	public Attribution(Map options) {
		super(options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Control.Attribution";
	}

}

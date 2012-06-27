/* ArgParser.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 26, 2012 4:07:41 PM , Created by jumperchen
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
public class ArgParser extends Control {

	public ArgParser(Map options) {
		super(options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Control.ArgParser";
	}

}

/* Text.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 5:16:56 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.layer;

import java.util.Map;

/**
 * @author jumperchen
 *
 */
public class Text extends Layer {

	public Text(String name, Map options) {
		super(name, options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.Text";
	}

}

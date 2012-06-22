/* Yahoo.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 11:31:59 AM , Created by jumperchen
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
public class Yahoo extends Layer {
	public Yahoo(String name) {
		super(name, null);
	}
	public Yahoo(String name, Map options) {
		super(name, options);
	}
	
	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.Yahoo";
	}

}

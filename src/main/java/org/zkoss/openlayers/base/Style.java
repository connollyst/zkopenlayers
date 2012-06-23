/* Style.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 22, 2012 5:37:31 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.base;

import java.util.Map;

import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 *
 */
public class Style extends OLBase {
	private Map _style;
	private Map _options;
	public Style(Map style, Map options) {
		_style = style;
		_options = options;
	}
	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _style, _options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Style";
	}

}

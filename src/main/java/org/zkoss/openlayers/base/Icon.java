/* Icon.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 5:21:10 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.base;

import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 *
 */
public class Icon extends OLBase {
	private String _url;
	private Size _size;
	private Pixel _offset;
	public Icon(String url, Size size, Pixel offset) {
		_url = url;
		_size = size;
		_offset = offset;
	}
	/**
	 * Returns a new Icon
	 */
	public Icon clone() {
		return new Icon(_url, _size, _offset);
	}
	
	public void setOpacity(double opacity) {
		getNativeObject().invoke("setOpacity", opacity);
    }


	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _url, _size, _offset);
	}
	
	@Override
	protected String getNativeClass() {
		return "OpenLayers.Icon";
	}

}

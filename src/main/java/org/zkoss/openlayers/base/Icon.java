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

/**
 * @author jumperchen
 *
 */
public class Icon extends OLBase {
	private String _url;
	private Size _size;
	private Pixel _offset;
	private Double _opacity;
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
	public String toJSONString() {
		String initFun = toJSONFun(getNativeClass(), _url, _size, _offset);
		if (_opacity != null) {
			return toJSONExecFun(initFun, "setOpacity", _opacity);
		}
		return initFun;
	}
	
	public void setOpacity(double opacity) {
		_opacity = opacity;
    }

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Icon";
	}

}

/* XYZ.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 22, 2012 5:04:17 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers.layer;

import java.util.Map;

import org.zkoss.openlayers.base.Projection;
import org.zkoss.openlayers.util.Function;
import static org.zkoss.openlayers.util.Helper.mergeMap;
import static org.zkoss.openlayers.util.Helper.pair;

/**
 * @author jumperchen
 * 
 */
public class XYZ extends Layer {

	private String _url;

	public XYZ(String name, String url, Map options) {
		super(name, options);
		_url = url;
	}

	public String getURL() {
		return _url;
	}

	@Override
	public Projection getProjection() {
		return new Projection("EPSG:900913");
	}
	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.XYZ";
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), getName(), getURL(),
				mergeMap(getOptions(), pair("uuid", getUuid())));
	}

}

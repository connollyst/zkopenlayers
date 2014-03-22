package org.zkoss.openlayers.layer;

import static org.zkoss.openlayers.util.Helper.*;

import java.util.Map;

import org.zkoss.openlayers.base.Bounds;
import org.zkoss.openlayers.base.Size;
import org.zkoss.openlayers.util.Function;

/**
 * OpenLayers class, replacing its image.js file, for rendering images
 * 
 * @author Jakub Kudla (kubakudla)
 * 
 */
public class Image extends Layer {

	private String _url;

	private Bounds _extent;

	private Size _size;

	public Image(String name, String url, Bounds extent, Size size, Map options) {
		super(name, options);
		this._url = url;
		this._extent = extent;
		this._size = size;
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.Image";
	}

	public String getURL() {
		return _url;
	}

	public Bounds getExtent() {
		return _extent;
	}

	public Size getSize() {
		return _size;
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), getName(), getURL(), getExtent(),
				getSize(), mergeMap(getOptions(), pair("uuid", getUuid())));
	}

}

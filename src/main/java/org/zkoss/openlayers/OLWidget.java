/* OLWidget.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 20, 2012 10:01:35 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers;

import org.zkoss.json.JSONAware;
import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 * 
 */
public abstract class OLWidget implements JSONAware {
	protected Openlayers _map;

	private String _uuid;
	private Function _native;

	private static final String ANONYMOUS_ID = "z__ol";

	private static volatile int _anonymousId;

	protected void clientUpdate(String attr, Object... args) {
		if (_map != null)
			_map.clientUpdate(this, attr, args);
		else {
			switch (args.length) {
			case 0:
				getNativeObject().invoke(attr);
				break;
			case 1:
				getNativeObject().invoke(attr, args[0]);
				break;
			case 2:
				getNativeObject().invoke(attr, args[0], args[1]);
				break;
			case 3:
				getNativeObject().invoke(attr, args[0], args[1], args[2]);
				break;
			case 4:
				getNativeObject().invoke(attr, args[0], args[1], args[2],
						args[3]);
				break;
			case 5:
				getNativeObject().invoke(attr, args[0], args[1], args[2],
						args[3], args[4]);
				break;
			case 6:
				getNativeObject().invoke(attr, args[0], args[1], args[2],
						args[3], args[4], args[5]);
				break;
			case 7:
				getNativeObject().invoke(attr, args[0], args[1], args[2],
						args[3], args[4], args[5], args[6]);
				break;
			case 8:
				getNativeObject().invoke(attr, args[0], args[1], args[2],
						args[3], args[4], args[5], args[6], args[7]);
				break;
			}
		}
	}

	public String getUuid() {
		if (_uuid == null) {
			_uuid = getNextUuid();
		}
		return _uuid;
	}
	
	protected String getNextUuid() {
		String uuid = ANONYMOUS_ID + _anonymousId++;
		if (_anonymousId == Integer.MAX_VALUE) {
			_anonymousId = 0;
		}
		return uuid;
	}
	
	protected abstract String getNativeClass();
	
	/**
	 * Returns the native Javascript Object, which is according with this OLWidget.
	 * <p>Note: it will return the same native object when invoked multi-times.
	 */
	protected Function getNativeObject() {
		if (_native == null)
			_native = newNativeObject();
		return _native;
	}

	protected abstract Function newNativeObject();
	
	public String toJSONString() {
		return getNativeObject().toJSONString();
	}
	/**
	 * Internal use only.
	 */
	protected void setMap(Openlayers map) {
		if (_map != map) {
			final Openlayers oldMap = _map;
			if (_map != null) {
				_map.removeOLWidget(this);
			}
			_map = map;
			if (_map != null) {
				onMapAttached(_map, oldMap);
			} else {
				onMapDetached(oldMap);
			}
		}
	}

	/**
	 * A call back method after map attached.
	 * <p>
	 * Default: does nothing.
	 */
	public void onMapAttached(Openlayers newMap, Openlayers oldMap) {
	}

	/**
	 * A call back method after map detached
	 * <p>
	 * Default: does nothing.
	 */
	public void onMapDetached(Openlayers map) {
	}
	
	@Override
	public String toString() {
		return toJSONString();
	}
}

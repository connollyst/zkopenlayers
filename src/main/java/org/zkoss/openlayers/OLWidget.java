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

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONValue;
import org.zkoss.openlayers.util.Function;
import org.zkoss.zk.ui.sys.DesktopCtrl;

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

	private static volatile char[] _prefix;

	private static final ArrayList<String> _chars;

	private static ReentrantLock _lock = new ReentrantLock();

	static {
		_chars = new ArrayList<String>(52);
		for (char c = 'a'; c <= 'z'; c++)
			_chars.add(Character.toString(c));
		for (char c = 'A'; c <= 'Z'; c++)
			_chars.add(Character.toString(c));
	}

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
			_uuid = _map != null ? ((DesktopCtrl)_map.getDesktop()).getNextUuid(_map)
					: getNextUuid();
		}
		return _uuid;
	}
	
	protected static String getNextUuid() {
		_lock.lock();

		String uuid = null;
		try {
			uuid = ANONYMOUS_ID
					+ (_prefix != null ? String.valueOf(_prefix) : "")
					+ _anonymousId++;
			if (_anonymousId == Integer.MAX_VALUE) {
				_anonymousId = 0;
				if (_prefix == null)
					_prefix = new char[] { _chars.get(0).charAt(0) };
				else {
					int len = _prefix.length;
					char c = _prefix[len - 1];
					if (c == _chars.get(_chars.size() - 1).charAt(0)) {
						char[] _oldPre = _prefix;
						_prefix = new char[len + 1];
						System.arraycopy(_oldPre, 0, _prefix, 0, len);
						_prefix[len] = _chars.get(0).charAt(0);
					} else _prefix[len - 1] = _chars
							.get(_chars.indexOf(Character
									.toString(_prefix[len - 1]))+1).charAt(0);
				}
			}
		} finally {
			_lock.unlock();
		}
		return uuid;
	}

	protected abstract String getNativeClass();

	/**
	 * Returns the native Javascript Object, which is according with this
	 * OLWidget.
	 * <p>
	 * Note: it will return the same native object when invoked multi-times.
	 */
	protected Function getNativeObject() {
		if (_native == null)
			_native = newNativeObject();
		return _native;
	}

	protected abstract Function newNativeObject();

	public String toJSONString() {
		return getNativeObject().toJSONString(_map);
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

	public Function toClientWidget() {
		if (_map == null)
			throw new UnsupportedOperationException(
					"The OLWidget is not attached to Openlayers!");
		StringBuilder sb = new StringBuilder();
		return new ReturnFunction(sb
				.append("(function () {return openlayers._binds['")
				.append(_map.getUuid()).append("']['").append(getUuid())
				.append("'];})()").toString());

	}

	private static class ReturnFunction extends Function {
		public ReturnFunction(String nativeClass) {
			super(nativeClass);
		}

		public String toJSONString(Openlayers map) {
			StringBuilder sb = new StringBuilder(64);
			sb.append("(function (){ var _a =  ").append(_native).append(';');
			if (!_methodQueue.isEmpty()) {
				for (Method method : _methodQueue)
					sb.append(" _a['")
							.append(method.getName())
							.append("'].apply(_a,")
							.append(JSONValue.toJSONString(method
									.getArguments())).append(");");
				_methodQueue.clear();
			}
			sb.append("return _a;})()");
			return sb.toString();
		}
	}
}

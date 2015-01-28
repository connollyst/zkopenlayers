/* Function.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 23, 2012 4:39:34 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers.util;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONValue;
import org.zkoss.openlayers.Openlayers;

/**
 * A Javascript Object
 * 
 * @author jumperchen
 * @author @author Jakub Kudla (kubakudla)
 */
public class Function implements JSONAware {
	protected String _native;
	protected Object[] _arguments;
	protected List<Method> _methodQueue;

	public Function(String nativeClass, Object... arguments) {
		_native = nativeClass;
		_arguments = arguments;
		_methodQueue = new LinkedList<Method>();
	}

	public Function invoke(String method, Object... arguments) {
		_methodQueue.add(new Method(method, arguments));
		return this;
	}

	public String toJSONString(Openlayers map) {
		StringBuilder sb = new StringBuilder(64);
		sb.append("(function (){ var _a =  new ").append(_native).append('(');
		if (_arguments.length > 0) {
			for (Object arg : _arguments) {
				String str = JSONValue.toJSONString(arg);
				str = fixComplexJson(str);
				sb.append(str).append(',');
			}
			int len = sb.length();
			sb.deleteCharAt(len - 1);
		}
		sb.append(");");
		if (!_methodQueue.isEmpty()) {
			for (Method method : _methodQueue)
				sb.append(" _a['").append(method.getName())
						.append("'].apply(_a,")
						.append(JSONValue.toJSONString(method.getArguments()))
						.append(");");
			_methodQueue.clear();
		}
		if (map != null) {
			sb.append("var map = openlayers._binds['").append(map.getUuid())
					.append("']; if (!map) map = openlayers._binds['")
					.append(map.getUuid()).append("'] = {};")
					.append("map[_a.uuid] = _a;");
		}
		sb.append("return _a;})()");
		return sb.toString();
	}

	private String fixComplexJson(String str) {
		// fix e.g. for {"handlerOptions":["sides","3"],"uuid":"b1HQa"}
		// which needs to be {"handlerOptions":{"sides":"3"},"uuid":"b1HQa"}
		Pattern pattern = Pattern.compile("\\\"handlerOptions\\\":\\[.*\\]");
		Matcher matcher = pattern.matcher(str);
		String wrongStr = "";
		String fixedStr = "";
		while (matcher.find()) {
			wrongStr = matcher.group();
			fixedStr = wrongStr.replace("[", "{");
			fixedStr = fixedStr.replace(",", ":");
			fixedStr = fixedStr.replace("]", "}");
			str = str.replace(wrongStr, fixedStr);
		}
		return str;
	}

	@Override
	public String toJSONString() {
		return toJSONString(null);
	}

	protected static class Method {
		private String _name;
		private Object[] _arguments;

		private Method(String name, Object[] arguments) {
			_name = name;
			_arguments = arguments;
		}

		public String getName() {
			return _name;
		}

		public Object[] getArguments() {
			return _arguments;
		}
	}
}

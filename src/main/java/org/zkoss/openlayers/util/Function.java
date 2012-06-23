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

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONValue;

/**
 * A Javascript Object
 * @author jumperchen
 */
public class Function implements JSONAware {
	private String _native;
	private Object[] _arguments;
	private List<Method> _methodQueue;
	
	public Function(String nativeClass, Object... arguments) {
		_native = nativeClass;
		_arguments = arguments;
		_methodQueue = new LinkedList<Method>();
	}
	public Function invoke(String method, Object... arguments) {
		_methodQueue.add(new Method(method, arguments));
		return this;
	}
	@Override
	public String toJSONString() {
		StringBuilder sb = new StringBuilder(64);
		sb.append("(function (){ var _a =  new ").append(_native).append('(');
		if (_arguments.length > 0) {
			for (Object arg : _arguments)
				sb.append(JSONValue.toJSONString(arg)).append(',');
			int len = sb.length();
			sb.deleteCharAt(len - 1);
		}
		sb.append(");");
		if (!_methodQueue.isEmpty()) {
			for (Method method : _methodQueue)
				sb.append(" _a['").append(method.getName()).append("'].apply(_a,")
						.append(JSONValue.toJSONString(method.getArguments())).append(");");
			_methodQueue.clear();
		}
		sb.append("return _a;})()");
		return sb.toString();
	}
	private static class Method {
		private String _name;
		private Object[] _arguments;
		private Method(String name, Object...  arguments) {
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

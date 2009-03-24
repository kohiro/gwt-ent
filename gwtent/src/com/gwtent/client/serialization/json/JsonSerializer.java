package com.gwtent.client.serialization.json;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.gwtent.client.reflection.ClassType;
import com.gwtent.client.reflection.Constructor;
import com.gwtent.client.reflection.Field;
import com.gwtent.client.reflection.ReflectionUtils;
import com.gwtent.client.reflection.TypeOracle;
import com.gwtent.client.serialization.AbstractDataContractSerializer;
import com.gwtent.client.serialization.DataMember;

public class JsonSerializer extends AbstractDataContractSerializer{
	protected Object deserializeObject(String json, ClassType type){
		JSONValue value = JSONParser.parse(json);
		
		Constructor constructor = type.findConstructor(new String[0]);
		Object result = constructor.newInstance();
		
		if (value instanceof JSONArray){
			if (result instanceof Collection){
				
			}else{
				throw new RuntimeException("JSONArray request a Collection object to contain it.");
			}
		}else if (value instanceof JSONObject){
			
		}
		
		return result;
	}
	
	protected String serializeObject(Object object, ClassType type){
		StringBuilder sb = new StringBuilder();
		
		
  	sb.append(serialize(object, type).toString());
		
		return sb.toString();
	}
	
	private JSONValue serialize(Object object, ClassType type){
		if (object instanceof Map){
			return null;
		}else	if (object instanceof Iterable){
			return serializeIterable((Iterable)object);
		} else {
			return serializePureObject(object, type);
		}
	}
	
	private void deserializeArray(JSONArray array, Collection object){
		for (int i = 0; i < array.size(); i++){
	//		object.add(o);
		}
	}
	
	private Object deserializeObject(JSONObject value){
		return null;
	}
	
	private JSONValue serializeIterable(Iterable objects){
		JSONArray result = new JSONArray();
		int index = 0;
		for (Object obj : objects){
			result.set(index, serialize(obj, TypeOracle.Instance.getClassType(obj.getClass())));
			index++;
		}
		return result;
	}
	
	private JSONValue serializePureObject(Object object, ClassType type){
		JSONObject result = new JSONObject();
		
		for (Field field : ReflectionUtils.getAllFields(type, DataMember.class)){
			Object value = field.getFieldValue(object);
			
			if (value == null){
				result.put(field.getName(), JSONNull.getInstance());
			}else if (value instanceof Boolean){
				result.put(field.getName(), JSONBoolean.getInstance((Boolean)value));
			} else if (value instanceof Number){
				result.put(field.getName(), new JSONNumber(((Number)value).doubleValue()));
			} else{
				result.put(field.getName(), new JSONString(value.toString()));
			}
		}
		
		return result;
	}
}
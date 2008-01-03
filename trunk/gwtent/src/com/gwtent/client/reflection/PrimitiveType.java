package com.gwtent.client.reflection;

import java.util.HashMap;
import java.util.Map;


public class PrimitiveType extends Type {

	  public static final PrimitiveType BOOLEAN = create("boolean", "Z");
	  public static final PrimitiveType BYTE = create("byte", "B");
	  public static final PrimitiveType CHAR = create("char", "C");
	  public static final PrimitiveType DOUBLE = create("double", "D");
	  public static final PrimitiveType FLOAT = create("float", "F");
	  public static final PrimitiveType INT = create("int", "I");
	  public static final PrimitiveType LONG = create("long", "J");
	  public static final PrimitiveType SHORT = create("short", "S");
	  public static final PrimitiveType VOID = create("void", "V");

	  private static Map map;

	  public static PrimitiveType valueOf(String typeName) {
	    return (PrimitiveType)getMap().get(typeName);
	  }

	  private static PrimitiveType create(String name, String jni) {
	    PrimitiveType type = new PrimitiveType(name, jni);
	    Object existing = getMap().put(name, type);
	    assert (existing == null);
	    return type;
	  }

	  private static Map getMap() {
	    if (map == null) {
	      map = new HashMap();
	    }
	    return map;
	  }

	  private final String jni;

	  private final String name;

	  private PrimitiveType(String name, String jni) {
	    this.name = name;
	    this.jni = jni;
	  }

	  public Type getErasedType() {
	    return this;
	  }

	  
	  public String getJNISignature() {
	    return jni;
	  }

	  
	  public String getQualifiedSourceName() {
	    return name;
	  }

	  
	  public String getSimpleSourceName() {
	    return name;
	  }

	  
	  public ArrayType isArray() {
	    // intentional null
	    return null;
	  }

	  
	  public ClassType isClass() {
	    // intentional null
	    return null;
	  }

	  
//	  public JEnumType isEnum() {
//	    return null;
//	  }

	  
//	  public JGenericType isGenericType() {
//	    return null;
//	  }

	  
	  public ClassType isInterface() {
	    // intentional null
	    return null;
	  }

	  
//	  public JParameterizedType isParameterized() {
//	    // intentional null
//	    return null;
//	  }

	  
	  public PrimitiveType isPrimitive() {
	    return this;
	  }

	  
//	  public JRawType isRawType() {
//	    // intentional null
//	    return null;
//	  }

	  
//	  public JWildcardType isWildcard() {
//	    return null;
//	  }

	  
//	  PrimitiveType getSubstitutedType(JParameterizedType parameterizedType) {
//	    return this;
//	  }
}

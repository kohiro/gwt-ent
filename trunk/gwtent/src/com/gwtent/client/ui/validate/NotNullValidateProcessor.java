/*
 * GwtEnt - Gwt ent library.
 * 
 * Copyright (c) 2007, James Luo(JamesLuo.au@gmail.com)
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package com.gwtent.client.ui.validate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NotNullValidateProcessor implements ExpressionProcessor {

	private static NotNullValidateProcessor processor;
	
	public static NotNullValidateProcessor getInstance(){
		if (processor == null)
			processor = new NotNullValidateProcessor();
		
		return processor;
	}

	private final List tests = new ArrayList();
	
	private NotNullValidateProcessor(){
		new ObjectTest();
		new StringTest();
		new DateTest();
	}

	
	public boolean canProcess(String expression) {
		if (expression.equalsIgnoreCase("NotNull"))
			return true;
		else
			return false;
	}

	
	public void AsyncValidate(String expression, Object value, ValidateCallBack callBack) throws ValidateException {
		// TODO Auto-generated method stub
		
	}


	public void SyncValidate(String expression, Object value) throws ValidateException {
		Iterator iterator = tests.iterator();
		while (iterator.hasNext()){
			NotNullTest test = (NotNullTest)iterator.next();
			test.test(value);
		}
	}
	
	
	interface NotNullTest {
		/**
		 * if null return true
		 * 
		 * @param value
		 * @return
		 */
		public void test(Object value) throws ValidateException;
	}

	
	
	/**
	 * not allow abstract class here ?
	 */
	abstract class AutoRegTest implements NotNullTest {
		public AutoRegTest() {
			tests.add(this);
		}

		public abstract void test(Object value) throws ValidateException;
	}

	class ObjectTest extends AutoRegTest implements NotNullTest {

		public void test(Object value) throws ValidateException {
			if (value == null)
				throw new ValidateException("Value not allow null.");
		}
	}

	class StringTest extends AutoRegTest implements NotNullTest {

		public void test(Object value) throws ValidateException {
			if (value instanceof String) {
				if (((String) value).length() <= 0)
					throw new ValidateException("Value not allow null.");
			}
		}
	}

	class DateTest extends AutoRegTest implements NotNullTest {

		public void test(Object value) throws ValidateException {
			if (value instanceof Date) {
				if (!((Date) value).equals(new Date(0)))
					throw new ValidateException("Date not allow null and must > " + new Date(0));
			}
		}

	}



	// TODO Implements more NotNull Test


}
package com.sglj.fbf.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

/**
 * JavaBean反射和自省处理工具类
 * @author guanhongwei
 *
 */
public class BeanUtil {

	/**
	 * 根据属性名来获取属性的值
	 * 
	 * @param object
	 *            需要得到属性值的对象
	 * @param propertyName
	 *            属性名
	 * @return
	 */
	public static Object getProperty(Object object, String propertyName) {
		Assert.notNull(object);
		Assert.hasText(propertyName);

		try {
			Field field = getDeclaredField(object, propertyName);
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			Object result = field.get(object);
			field.setAccessible(accessible);
			return result;
		} catch (NoSuchFieldException nsfe) {
			throw new RuntimeException(nsfe.getMessage());
		} catch (Exception e) {
			throw new InternalError("Runtime Exception impossibility throw");
		}

	}

	/**
	 * 根据属性名和属性值来设置对象的属性
	 * 
	 * @param object
	 *            需要放置属性值的对象
	 * @param propertyName
	 *            属性名
	 * @param newValue
	 *            属性值
	 */
	public static void setProperty(Object object, String propertyName, Object newValue) {
		Assert.notNull(object);
		Assert.hasText(propertyName);

		try {
			Field field = getDeclaredField(object, propertyName);
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			field.set(object, newValue);
			field.setAccessible(accessible);
		} catch (NoSuchFieldException nsfe) {
			throw new RuntimeException(nsfe.getMessage());
		} catch (Exception e) {
			throw new InternalError("Runtime Exception impossibility throw");
		}

	}

	/**
	 * 根据属性名，在当前对象实例的所有Field对象中（包括父类的Field）检索对应的属性值
	 * 
	 * @param object
	 *            对象实例
	 * @param propertyName
	 *            属性名
	 * @return Field 对象
	 * 
	 * @throws NoSuchFieldException
	 */
	public static Field getDeclaredField(Object object, String propertyName) throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);

		return getDeclaredField(object.getClass(), propertyName);
	}

	/**
	 * 根据属性名，在当前Class类实例的所有Field对象中（包括父类的Field）检索对应的属性值
	 * 
	 * @param clazz
	 *            Class类实例
	 * @param propertyName
	 *            属性名
	 * @return Field 对象
	 * 
	 * @throws NoSuchFieldException
	 */
	public static Field getDeclaredField(Class<?> clazz, String propertyName) throws NoSuchFieldException {
		Assert.notNull(clazz);
		Assert.hasText(propertyName);

		for (Class<?> superClass = clazz; superClass != Object.class;) {
			try {
				return superClass.getDeclaredField(propertyName);
			} catch (NoSuchFieldException e) {
				superClass = superClass.getSuperclass();
			}
		}

		throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);

	}

	/**
	 * 获取当前Class类实例中所有的Field对象（包括父类的Field）
	 * 
	 * @param clazz
	 *            Class类实例
	 * @return Field对象类型数组
	 */
	public static Field[] getAllDeclaredFields(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Class<?> superClass = clazz.getSuperclass();
		if (superClass == null) {
			return fields;
		}
		Field[] superFields = getAllDeclaredFields(superClass);
		Field[] destFields = new Field[fields.length + superFields.length];
		System.arraycopy(fields, 0, destFields, 0, fields.length);
		System.arraycopy(superFields, 0, destFields, fields.length, superFields.length);

		return destFields;
	}

	/**
	 * 获取当前Class类实例中所有的Field对象的name属性（包括父类的Field）
	 * 
	 * @param clazz
	 *            Class类实例
	 * @return 由Field对象属性name组成的数组
	 */
	public static String[] getAllDeclaredFieldsName(Class<?> clazz) {
		List<String> fieldsName = new ArrayList<String>();
		Field[] fields = getAllDeclaredFields(clazz);
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			fieldsName.add(field.getName());
		}

		return fieldsName.toArray(new String[] {});
	}

	/**
	 * 获取当前Class类实例中的Field对象的name属性
	 * 
	 * @param clazz
	 *            Class类实例
	 * @return 由Field对象属性name组成的数组
	 */
	public static String[] getDeclaredFieldsName(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();

		int size = fields.length;
		String[] fieldNames = new String[size];
		for (int i = 0; i < size; i++)
			fieldNames[i] = fields[i].getName();

		return fieldNames;
	}

	/**
	 * 获取当前对象所有非空属性的
	 * 
	 * @param object
	 * @return
	 */
	public static Map<String, Object> getAllNotNullProperties(Object object) {
		Map<String, Object> properties = new HashMap<String, Object>();
		Field[] fields = getAllDeclaredFields(object.getClass());

		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].isAccessible()) {
				fields[i].setAccessible(true);
			}

			try {
				Object fieldContent = fields[i].get(object);
				if (fieldContent != null && !Modifier.isFinal(fields[i].getModifiers())) {
					properties.put(fields[i].getName(), fieldContent);
				}
			} catch (Exception e) {
				throw new InternalError("Runtime Exception impossibility throw");
			}

		}

		return properties;
	}

	/**
	 * 把传入源对象的属性值赋给目标对象
	 * 
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 */
	public static void copyProperties(Object source, Object target) {
		Map<String, Object> properties = getAllNotNullProperties(source);
		Field[] fields = getAllDeclaredFields(target.getClass());

		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].isAccessible()) {
				fields[i].setAccessible(true);
			}

			try {
				Object fieldContent = properties.get(fields[i].getName());
				if (fieldContent != null && !Modifier.isFinal(fields[i].getModifiers())) {
					fields[i].set(target, fieldContent);
				}
			} catch (Exception e) {
				throw new InternalError("Runtime Exception impossibility throw");
			}

		}

	}

}

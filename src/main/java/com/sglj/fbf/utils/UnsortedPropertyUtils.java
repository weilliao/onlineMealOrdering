package com.sglj.fbf.utils;

import java.beans.IntrospectionException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;
/**
 * 将对象转换成yaml的过程中，自定义属性生成的顺序
 * <p>Title:  </p>
 * <p>Description:  </p>
 * @author
 * @date
 */
public class UnsortedPropertyUtils extends PropertyUtils {
    @Override
    protected Set<Property> createPropertySet(Class<? extends Object> type, BeanAccess bAccess)
            throws IntrospectionException {
        Set<Property> result = new LinkedHashSet<Property>(getPropertiesMap(type,
                BeanAccess.FIELD).values());
        //result.remove(result.iterator().next());// drop 'listInt' property
        return result;
    }
}
package com.sglj.fbf.utils;

import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;
/**
 * 将对象转换成yaml的过程中，如果对象属性为空则该属性不生成
 * <p>Title:  </p>
 * <p>Description:  </p>
 * @author
 * @date
 */
public class SkipNullRepresenter extends Representer {
    @Override
    protected NodeTuple representJavaBeanProperty(Object javaBean, Property property,
            Object propertyValue, Tag customTag) {
        if (propertyValue == null) {
            return null;
        } else {
            return super
                    .representJavaBeanProperty(javaBean, property, propertyValue, customTag);
        }
    }
}

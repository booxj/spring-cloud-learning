package cloud.zk.client.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * @description:
 * @author: wb
 * @data: 2019/3/3 11:44 PM
 * @see:
 * @since:
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Qualifier
public @interface CustomizedLoadBalanced {
}

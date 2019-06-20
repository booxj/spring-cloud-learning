package cloud.zk.server.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SemaphoreCircuitBreaker {

    int timeout() default -1;

    int value() default 5;
}

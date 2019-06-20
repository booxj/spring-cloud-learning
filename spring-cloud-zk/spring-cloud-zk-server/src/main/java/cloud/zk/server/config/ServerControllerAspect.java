package cloud.zk.server.config;

import cloud.zk.server.annotation.CircuitBreaker;
import cloud.zk.server.annotation.SemaphoreCircuitBreaker;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

@Aspect
@Component
public class ServerControllerAspect {

    private ExecutorService executorService = newFixedThreadPool(20);

    //    @Around("execution(* cloud.zk.server.web.DiscoverController.myHystrixHello(..)) && args(message) && @annotation(circuitBreaker)")
    @Around("@annotation(circuitBreaker) && args(message)")
    public Object methodInTimeout(ProceedingJoinPoint point, String message, CircuitBreaker circuitBreaker) throws Throwable {
        long timeout = circuitBreaker.timeout();
        return doInvoke(point, message, timeout);
    }

    private static Semaphore semaphore;

    //注解+信号灯实现
    @Around("@annotation(semaphoreCircuitBreaker) && args(message)")
    public Object methodInTimeoutAndSemaphore(ProceedingJoinPoint point,
                                          String message,
                                          SemaphoreCircuitBreaker semaphoreCircuitBreaker) throws Throwable {
        int value = semaphoreCircuitBreaker.value();
        if (semaphore == null) {
            semaphore = new Semaphore(value);
        }
        Object returnValue = null;
        try {
            if (semaphore.tryAcquire()) {
                returnValue = point.proceed(new Object[]{message});
                Thread.sleep(1000);
            } else {
                returnValue = "";
            }
        } finally {
            semaphore.release();
        }

        return returnValue;

    }

    private Object doInvoke(ProceedingJoinPoint point, String message, long timeout) throws Throwable {
        Future<Object> future = executorService.submit(() -> {
            Object returnValue = null;
            try {
                returnValue = point.proceed(new Object[]{message});
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return returnValue;
        });

        Object returnValue = null;
        try {
            returnValue = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            throw e;
        }
        return returnValue;
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdown();
    }


}

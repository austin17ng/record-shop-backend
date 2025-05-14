package me.austinng.recordshop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(* me.austinng.recordshop.controller..*(..))")
    public void controllerMethods() {}

    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering method: {}", joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Exiting method: {} with result: {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "controllerMethods()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        log.info("Exception in method: {} {}", joinPoint.getSignature(), e);
    }
}

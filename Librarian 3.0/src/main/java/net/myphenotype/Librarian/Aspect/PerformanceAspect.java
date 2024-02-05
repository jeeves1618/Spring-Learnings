package net.myphenotype.Librarian.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class PerformanceAspect {
    @Around("net.myphenotype.Librarian.Aspect.PointCutExpressions.getDatabaseAccessMetrics()")
    public Object performanceCalc(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String methodName = proceedingJoinPoint.getSignature().toShortString();
        long startTime = System.currentTimeMillis();
        Object returnedData = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("The method, " + methodName + " took " + (endTime - startTime) + " milli seconds to execute");
        return returnedData;
    }
}

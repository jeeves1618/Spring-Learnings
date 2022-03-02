package net.myphenotype.SpringAOP.Aspect;

import lombok.extern.slf4j.Slf4j;
import net.myphenotype.SpringAOP.Entity.BookExpanded;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class GetterAspect {
    @Before("net.myphenotype.SpringAOP.Aspect.PointCutExpressions.beforeGetters()")
    public void beforeGeeting(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("Aspect: Invoking the method " + methodSignature.getMethod() + " with return type " + methodSignature.getReturnType());
        Object[] objects = joinPoint.getArgs();
        for (Object tempObject:objects) {
            if (tempObject instanceof net.myphenotype.SpringAOP.Entity.BookExpanded){
                BookExpanded bookExpanded = (BookExpanded) tempObject;
                log.info("The title of the book is " + bookExpanded.getBookTitle());
            }
        }
    }
}

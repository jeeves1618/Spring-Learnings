package net.myphenotype.SpringAOP.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PointCutExpressions {
    @Pointcut("execution(* net.myphenotype.SpringAOP.Controller.*.*())")
    public void beforeGetters(){}

    @Pointcut("execution(* net.myphenotype.SpringAOP.Controller.*.*(..))")
    public void beforeSetters(){}

    @Pointcut("execution(* net.myphenotype.SpringAOP.DAO.*.*(..))")
    public void getDatabaseAccessMetrics(){}
}

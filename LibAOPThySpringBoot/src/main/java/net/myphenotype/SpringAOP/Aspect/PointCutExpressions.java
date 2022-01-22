package net.myphenotype.SpringAOP.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PointCutExpressions {
    @Pointcut("execution(* net.myphenotype.SpringAOP.Entity.*.get*())")
    public void beforeGetters(){}

    @Pointcut("execution(* net.myphenotype.SpringAOP.*.*.set*(*))")
    public void beforeSetters(){}
}

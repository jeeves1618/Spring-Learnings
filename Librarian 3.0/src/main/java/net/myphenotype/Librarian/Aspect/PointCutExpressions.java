package net.myphenotype.Librarian.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PointCutExpressions {
    @Pointcut("execution(* net.myphenotype.Librarian.Controller.*.*())")
    public void beforeGetters(){}

    @Pointcut("execution(* net.myphenotype.Librarian.Controller.*.*(..))")
    public void beforeSetters(){}

    @Pointcut("execution(* net.myphenotype.Librarian.DAO.*.*(..))")
    public void getDatabaseAccessMetrics(){}
}

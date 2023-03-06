package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {


    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder(){};

    //클래스 이름 패턴 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}

    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}

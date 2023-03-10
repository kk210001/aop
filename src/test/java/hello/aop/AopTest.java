package hello.aop;


import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import hello.aop.order.aop.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
//@Import(AspectV1.class) //스프링 빈 등록
//@Import(AspectV2.class) //포인트컷과 어드바이스 분리
//@Import(AspectV3.class) //여러 포인트컷
//@Import(AspectV4PointCut.class) //포인트컷 외부
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class}) //어드바이스 순서 지정
@Import(AspectV6Advice.class) //어드바이스 순서 지정
public class AopTest {


    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;


    @Test
    void aopInfo(){
        log.info("isAopProxy, orderService", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        Assertions.assertThatThrownBy(() -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);
    }
}

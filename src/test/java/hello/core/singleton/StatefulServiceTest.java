package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread A 사용자 10000원 주문
        int userA = statefulService1.order("userA", 10000);
        // Thread B 사용자 20000원 주문
        int userB = statefulService1.order("userB", 20000);

        // Thread A 사용자 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + userA);

        // 공유되는 필드가 변경되어 값이 틀리게 나온다.
        // 싱글톤 필드는 항상 무상태로 설정해야한다.
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }

    }

}
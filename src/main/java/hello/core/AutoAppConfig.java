package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // componentScan을 지정해주지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작위치가 된다
    // 설정 정보 클래스의 위치를 프로젝트 최상단에 두면 좋다
    // 보통은 @SpringBootApplication 안에 @ComponentScan 이 들어가있다.




}

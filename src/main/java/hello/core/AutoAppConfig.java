package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(

        //default hello.core 부터 시작해서 찾음
        basePackages = "hello.core.member", //찾는 위치 조정
        basePackageClasses = AutoAppConfig.class, //찾는 위치 조정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
//@ComponentScan : @Component 어노테이션에 붙은 클래스 찾아서 자동으로 스프링 빈 등록
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}

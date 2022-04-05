package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component //빈이 자동으로 등록 due to ComponentScan
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * DIP : 역할(인터페이스/추상클래스/클라이언트 코드) 클래스는 구현하는 클래스에 의존적이면 안 된다. // ex benz 유저는 benz 말고 다른 차종을 몰 수 없는 경우
     * OCP : 구현 클래스가 변화/확장하더라도 역할(인터페이스/추상클래스)을 담당하는 클래스에 영향을 주지 않아야 한다. // ex 전기차 유저의 경우 휘발유 차종을 몰 경우 새로 면허를 따야하는 경우
     */

    /**
     * DIP : 추상화에 의존해야지, 구체화에 의존하면 안 된다.
     * OCP : 확장에는 열려있되 변경에는 닫혀있어야 함.
     */

    //DIP 위반 : OrderServiceImpl가 DiscountPolicy 인터페이스 뿐만 아니라 FixDiscountPolicy 구체 클래스도 함께 의존
    //OCP 위반 : FixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl의 소스도 변경해야 함
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    //필드 의존성 주입 : anti code, 유연하게 객체를 바꿀 수 없음( 외부 변경이 불가능해서 테스트 할 수 없음 ). setter 를 통해 정의 해주어야 함
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;
//
//    @Autowired // setter 의존성 주입
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("2. memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired // setter 의존성 주입
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("2. discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }


//    @Autowired //DI, 생성자가 한 개 있을 때는 스프링이 알아서 의존관계 주입 해줌
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도 : 싱글톤 깨지는지 확인
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}

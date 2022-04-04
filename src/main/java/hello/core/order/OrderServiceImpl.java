package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

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

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

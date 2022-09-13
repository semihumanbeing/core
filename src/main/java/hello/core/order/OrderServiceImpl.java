package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;

    // 고정할인율적용 - 그러나 구체화한 코드도 의존해 DIP를 위반하는 문제가생긴다.
    // private final DiscountPolicy discountPolicy = new FixedDiscountPolicy();

    // 유동할인율적용 - 인터페이스에만 의존하도록 코드 변경
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // 생성자 주입: 불변, 필수 의존관계에 사용한다
    // Setter injection 보다 먼저 이루어진다.
    
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원정보 조회
        Member member = memberRepository.findById(memberId);
        // 할인정책을 통해 할인가격 계산
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 최종적으로 할인된 가격 받기
        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
    // 테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

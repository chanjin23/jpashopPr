package hello.core.order;

public interface OrderService {
    //이게 오더 즉, 회원이 주문을해서 아이템 명 가격들을 입력함
    Order creatOrder(Long memberId, String itemName, int itemPrice);
}

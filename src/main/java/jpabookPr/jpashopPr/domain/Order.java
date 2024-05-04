package jpabookPr.jpashopPr.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name ="orders") //관례상 이름을 orders변경
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //롬복을 이용하여 생성자제한을  protected로 제한한다.
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //xToOne은 default가 EAGER즉시로딩으로 되어있기때문에 모든 것을 LAZY로 바꿔야한다.
    @ManyToOne(fetch = LAZY)  //다대일 양방관계
    @JoinColumn(name = "member_id") //member key joinColumn
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)   //cascadALL설정을 하면 전파된 모든 것에 persist 된다.
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")   //주인이 된다.
    private Delivery delivery;

    private LocalDateTime orderDate;    //주문시간

    private OrderStatus status;  //주문상태 [ORDER, CANCEL]

    //==연관관계 메서드=//
    public void setMember(Member member) {
        this.member =member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//  //값을 만들때 set, set,set이렇게 하지말고 생성을 한번할때 한꺼번에 바꾸게끔 한다.
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel(){
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        int totalPrice =0;
        for (OrderItem orderItem : orderItems) {
            totalPrice+=orderItem.getTotalPrice();
        }
        return totalPrice;
//        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();    //람다식은 다음과 같이 표현할 수 있다.
    }
}

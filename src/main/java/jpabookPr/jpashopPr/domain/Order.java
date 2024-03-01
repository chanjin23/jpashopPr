package jpabookPr.jpashopPr.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name ="orders") //관례상 이름을 orders변경
@Getter @Setter
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
}

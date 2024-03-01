package jpabookPr.jpashopPr.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="orders") //관례상 이름을 orders변경
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne  //다대일 양방관계
    @JoinColumn(name = "member_id") //member key joinColumn
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")   //주인이 된다.
    private Delivery delivery;

    private LocalDateTime orderDate;    //주문시간

    private OrderStatus status;  //주문상태 [ORDER, CANCEL]
}

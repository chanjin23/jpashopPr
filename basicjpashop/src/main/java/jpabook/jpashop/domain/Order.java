package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "ORDERS") //규약때문에 어쩔수없이ORDERS로 씀
public class Order {

    @Id @GeneratedValue
    @Column(name ="ORDER_ID")
    private Long id;

    @Column(name ="MEMBER_ID")
    private Long memberId;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)    //enum타입 설정
    private OrderStatus status; //ORDER, CANCEL

}

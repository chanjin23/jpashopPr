package jpabookPr.jpashopPr.domain;

import jakarta.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")    //거울
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)   //ordinal은 0,1,2,3으로 된다. 그래서 string을 써야 순서를 밀리지않는다
    private DeliveryStatus status;  //READY, COMP
}

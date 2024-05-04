package jpabookPr.jpashopPr.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = LAZY)    //거울
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)   //ordinal은 0,1,2,3으로 된다. 그래서 string을 써야 순서를 밀리지않는다
    private DeliveryStatus status;  //READY, COMP
}

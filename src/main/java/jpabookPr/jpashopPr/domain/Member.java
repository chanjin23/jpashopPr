package jpabookPr.jpashopPr.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded   //내장타입
    private Address address;

    @OneToMany(mappedBy = "member") //member에 의해 매핑된 거울일 뿐이다. 읽기전용
    private List<Order> orders = new ArrayList<>();
}

package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id     @GeneratedValue
    @Column(name = "member_id") //PK 컬럼명
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")  //일대다 관계 member에 의해서 값이 변경된다.
    //읽기전용으로 바뀐다.
    private List<Order> orders = new ArrayList<>();
}

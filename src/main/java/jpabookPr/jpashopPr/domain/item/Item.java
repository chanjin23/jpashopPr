package jpabookPr.jpashopPr.domain.item;

import jakarta.persistence.*;
import jpabookPr.jpashopPr.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
//상속 관계 전략을 singletable전략을 쓸거임
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items") //이방식은 실무에서 유용하지 않다.
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    //객체 지향적으로 생각했을때 데이터를 가지고 클래스가 로직을 가지고 있는 것이 좋다.

    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     *stock 감소
     */
    public void removeStock(int quantity){
        int restStock = stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        stockQuantity = restStock;
    }
}

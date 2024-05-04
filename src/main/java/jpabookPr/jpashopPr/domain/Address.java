package jpabookPr.jpashopPr.domain;


import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
//값은 Setter는 열지 않는 것이 좋다. 그래서 생성자로만 열도록 만들어놓은다.
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

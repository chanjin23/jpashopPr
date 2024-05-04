package jpabookPr.jpashopPr.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    //이름은 필수적으로 받는다.
    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;

    private String city;

    private String street;

    private String zipcode;

}

package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter //롬복이 getter setter를 자동으로 생성해준다.
@Setter
@ToString   //toString 자동생성
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdf");

        String name1 = helloLombok.getName();
        System.out.println("name1 = " + name1);
        System.out.println("helloLombok = " + helloLombok);
    }
}

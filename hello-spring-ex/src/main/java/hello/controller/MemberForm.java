package hello.controller;

public class MemberForm {
    private String name;    //이름을 등록할 떄 여기에 값이 들어가게 된다.

    public String getName() {/*getName을 통해서 값을 꺼낸다.*/
        return name;
    }

    public void setName(String name) {/*setname을 할 때 값이 들어가게 된다.*/
        this.name = name;
    }
}

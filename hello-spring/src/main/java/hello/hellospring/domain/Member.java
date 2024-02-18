package hello.hellospring.domain;

public class Member {
    private Long id;
    //여기서 Long은 long의 차이점 long을 boxing한 개체로 값을 포인터한다고 생각하면됨.
    //보통 long은 값이 not null인경우 원시적이므로 long을 쓰는 것이 좋다.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

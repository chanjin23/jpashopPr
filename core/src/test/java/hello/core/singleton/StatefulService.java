package hello.core.singleton;

public class StatefulService {

//    private int price;  // 상태를 유지하는 필드

    public int order(String name, int price){
        System.out.println("name = " + name +  " price = " +  price);
//        this.price =price;
        //이런식으로 값을 넘겨버리면 필드를 공유하기때문에 크나큰 장애가 발생할 수 있다.
        return price;   //공유필드를 없애고 return값을 반환해서 싱글톤의 문제점을 해결하였다.
    }

//    public int getPrice(){
//        return price;
//    }

}

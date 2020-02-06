//package src.com.company;
//
//import calculator.Calculator;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class CalculatorTest_2 {
//    private Calculator cal;
//
//    @Before
//    public void setup(){
//        cal = new Calculator();
//        System.out.println("before");
//    }
//
//    @Test
//    public void add() {
//        assertEquals(9, cal.add(6, 3));
//        System.out.println("add");
//    }
//
//    @Test
//    public void substract(){
//        assertEquals(3, cal.subtract(6, 3));
//        //첫번째 인자 : 기대하는 결과값, 두번째 인자 : 프로덕션 코드의 메소드를 실행한 결과 값
//        System.out.println("substract");
//    }
//
//    @After
//    public void teardown(){
//        System.out.println("teardown");
//    }
//
//
//}

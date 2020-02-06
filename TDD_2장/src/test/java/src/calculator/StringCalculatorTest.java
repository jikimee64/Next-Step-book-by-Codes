package src.calculator;
import calculator.StringCalculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    private StringCalculator cal;

    @Before
    public void setup(){
        cal = new StringCalculator();
    }

    //@Test
    public void add() {
        assertEquals(6, cal.add("1,2,3"));
        System.out.println("add");
    }

   // @Test
    public void 빈문자열_null값_0반환(){
        assertEquals(0, cal.add(null));
    }

//    @Test
//    public void 커스텀구분자(){
//        assertEquals(5, cal.add("//p\n1p5p3"));
//    }

    @After
    public void teardown(){
        System.out.println("teardown");
    }
}

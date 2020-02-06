package calculator;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String text){
        if(text == null || text.equals("")){
            return 0;
        }
        String[] tokens = new String[0];
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
        if(m.find()) {
            String customDelimeter = m.group(1);
            tokens = m.group(2).split(customDelimeter);
        }

        //List<String> tokens = Arrays.asList(text.split(",|:")); //Arrays.asList()의 큰 단점은 remove 및 add를 할 수가 없다.
        List<Integer> numbers = new ArrayList<Integer>();
        int sum = 0;
        for(String i : tokens) {
            numbers.add(Integer.parseInt(i));
        }
        for(Integer s : numbers){
            if( s < 0){
                throw new RuntimeException();
            }
            sum += s;
        }
        return sum;
    }

}

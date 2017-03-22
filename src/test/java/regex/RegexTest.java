package regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangyu on 16/9/27.
 */
public class RegexTest {

    @Test
    public void test(){
        String line = "This order was placed for QT3000! OK?";
        String pattern = "foo*r";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group() );
        } else {
            System.out.println("NO MATCH");
        }
    }
}

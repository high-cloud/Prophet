package ustc.zzy.prophet;

import ustc.zzy.prophet.util.Time;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        assertEquals("1970-01-12 21:46:40",Time.timeStamp2date(1000000000));
    }

}
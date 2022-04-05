package spec.spring.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SumTest {

    @Test
    public void sumTest() {
        Assertions.assertEquals(4, Integer.sum(2, 2));
    }

}

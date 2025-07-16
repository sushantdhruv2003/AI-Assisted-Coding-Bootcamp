
package geneai;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class calcTest {

    int add(int a, int b) {
        return a + b;
    }

    @Test
    void testAddition1() {
        assertEquals(12, add(5, 7));
    }

    @Test
    void testAddition2() {
        assertEquals(25, add(10, 15));
    }

    @Test
    void testAddition3() {
        assertEquals(9, add(4, 5));
    }
}

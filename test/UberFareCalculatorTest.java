
package geneai;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UberFareCalculatorTest {
    
    @Test
    void testMiniNormalHour() {
        UberFareCalculator calc = new UberFareCalculator();
        calc.endRide(10, 12, "Mini"); // 10km, normal hour
        // 10*10*1.0 = 100, min fare 50, so fare = 100
        // No outstation
        // No surge
        // No car multiplier
        // Expected: 100
        assertEquals(100.0, getFare(calc), 0.01);
    }

    @Test
    void testSedanOfficeHour() {
        UberFareCalculator calc = new UberFareCalculator();
        calc.endRide(10, 8, "Sedan"); // 10km, office hour
        // 10*10*1.2*1.5 = 180
        assertEquals(180.0, getFare(calc), 0.01);
    }

    @Test
    void testSUVNightHour() {
        UberFareCalculator calc = new UberFareCalculator();
        calc.endRide(10, 22, "SUV"); // 10km, night hour
        // 10*10*1.5*2.0 = 300
        assertEquals(300.0, getFare(calc), 0.01);
    }

    @Test
    void testMiniOutstation() {
        UberFareCalculator calc = new UberFareCalculator();
        calc.endRide(250, 12, "Mini"); // 250km, normal hour, outstation
        // 250*10*1.0 = 2500 + 500 = 3000
        assertEquals(3000.0, getFare(calc), 0.01);
    }

    @Test
    void testSedanMinimumFare() {
        UberFareCalculator calc = new UberFareCalculator();
        calc.endRide(2, 12, "Sedan"); // 2km, normal hour
        // 2*10*1.5 = 30, but min fare is 50
        assertEquals(50.0, getFare(calc), 0.01);
    }

    // Helper to access private totalFare
    private double getFare(UberFareCalculator calc) {
        try {
            java.lang.reflect.Field f = UberFareCalculator.class.getDeclaredField("totalFare");
            f.setAccessible(true);
            return f.getDouble(calc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

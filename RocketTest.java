import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RocketTest {
    private Rocket rocket;

    @Before
    public void setUp() {
        rocket = new Rocket("explorer-1", 2500);
    }

    @Test
    public void testVerifyReadiness_FuelAt100_ShouldReturnTrue() {
        rocket.setFuelLevel(100);
        assertTrue(rocket.verify_readiness());
    }

    @Test
    public void testVerifyReadiness_FuelBelow100_ShouldReturnFalse() {
        rocket.setFuelLevel(99);
        assertFalse(rocket.verify_readiness());
    }
}

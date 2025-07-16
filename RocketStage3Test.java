package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class RocketStage3Test {
    private RocketStage3 rocket;

    @Before
    public void setUp() {
        rocket = new RocketStage3("explorer-4", 2500, RocketStage3.EngineType.BETA);
    }

    @Test
    public void testLoadItem_AddsCargoMass() {
        rocket.load_item(1000);
        rocket.load_item(500);
        assertEquals(1500, rocket.getTotalCargoMass());
    }

    @Test
    public void testGetLaunchMass_IncludesCargo() {
        rocket.load_item(2000);
        assertEquals(14500, rocket.getLaunchMass()); // 2500 + 10000 + 2000
    }

    @Test
    public void testStartLiftoff_TooHeavyWithCargo_ShouldFail() {
        rocket.setFuelLevel(100);
        rocket.load_item(140000); // Exceeds lift power
        assertFalse(rocket.start_liftoff());
        assertEquals("Failure: Too heavy to lift", rocket.getStatus());
    }

    @Test
    public void testStartLiftoff_SuccessWithCargo() {
        rocket.setFuelLevel(100);
        rocket.load_item(1000);
        assertTrue(rocket.start_liftoff());
        assertEquals("Launched", rocket.getStatus());
        assertTrue(rocket.isLaunched());
    }
}

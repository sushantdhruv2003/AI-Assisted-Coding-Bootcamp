package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RocketStage2Test {
    private RocketStage2 rocketAlpha;
    private RocketStage2 rocketBeta;

    @Before
    public void setUp() {
        rocketAlpha = new RocketStage2("explorer-2", 2500, RocketStage2.EngineType.ALPHA);
        rocketBeta = new RocketStage2("explorer-3", 2500, RocketStage2.EngineType.BETA);
    }

    @Test
    public void testVerifyReadiness_FuelAt100_ShouldReturnTrue() {
        rocketAlpha.setFuelLevel(100);
        assertTrue(rocketAlpha.verify_readiness());
    }

    @Test
    public void testVerifyReadiness_FuelBelow100_ShouldReturnFalse() {
        rocketAlpha.setFuelLevel(99);
        assertFalse(rocketAlpha.verify_readiness());
    }

    @Test
    public void testGetLaunchMass() {
        assertEquals(7500, rocketAlpha.getLaunchMass());
        assertEquals(12500, rocketBeta.getLaunchMass());
    }

    @Test
    public void testStartLiftoff_NotReady_ShouldFail() {
        rocketAlpha.setFuelLevel(50);
        assertFalse(rocketAlpha.start_liftoff());
        assertEquals("Failure: Not ready (fuel)", rocketAlpha.getStatus());
    }

    @Test
    public void testStartLiftoff_TooHeavy_ShouldFail() {
        RocketStage2 heavyRocket = new RocketStage2("heavy", 6000, RocketStage2.EngineType.ALPHA);
        heavyRocket.setFuelLevel(100);
        assertFalse(heavyRocket.start_liftoff());
        assertEquals("Failure: Too heavy to lift", heavyRocket.getStatus());
    }

    @Test
    public void testStartLiftoff_Success() {
        rocketAlpha.setFuelLevel(100);
        assertTrue(rocketAlpha.start_liftoff());
        assertEquals("Launched", rocketAlpha.getStatus());
        assertTrue(rocketAlpha.isLaunched());
    }
}

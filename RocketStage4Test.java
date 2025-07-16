package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RocketStage4Test {
    private RocketStage4 rocket;

    @Before
    public void setUp() {
        rocket = new RocketStage4("explorer-5", 2500, RocketStage4.EngineType.BETA);
    }

    @Test
    public void testMissionSummary_BeforeLaunch() {
        assertEquals("Mission not attempted.", rocket.get_mission_summary());
    }

    @Test
    public void testMissionSummary_SuccessfulLaunch() {
        rocket.setFuelLevel(100);
        assertTrue(rocket.start_liftoff());
        assertEquals("Launch succeeded.", rocket.get_mission_summary());
    }

    @Test
    public void testMissionSummary_FailedLaunch_NotReady() {
        rocket.setFuelLevel(50);
        assertFalse(rocket.start_liftoff());
        assertEquals("Launch failed: Not ready (fuel).", rocket.get_mission_summary());
    }

    @Test
    public void testMissionSummary_FailedLaunch_TooHeavy() {
        rocket.setFuelLevel(100);
        rocket.load_item(200000); // Exceeds lift power
        assertFalse(rocket.start_liftoff());
        assertEquals("Launch failed: Too heavy to lift.", rocket.get_mission_summary());
    }

    @Test
    public void testMissionSummary_RiskyLaunch() {
        rocket.setFuelLevel(100);
        rocket.setRisky(true);
        // Since risky launch is random, just check that the summary is one of the two possible outcomes
        rocket.start_liftoff();
        String summary = rocket.get_mission_summary();
        boolean valid = summary.equals("Risky launch succeeded.") || summary.equals("Risky launch failed.");
        assertTrue(valid);
    }
}

package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlannerTest
{
    private Planner planner;

    @BeforeEach
    void setUp() {
        planner = new Planner();
    }

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void checkCaloriesDemand (ActivityLevel actv)
    {
        // Given
        User testusr = TestConstants.TEST_USER;
        // When
        int newintake = planner.calculateDailyCaloriesDemand(testusr, actv);
        // Then
        assertEquals(newintake, TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(actv));
    }

    @Test
    void checkDailyIntake ()
    {
        // Given
        User testusr = TestConstants.TEST_USER;
        DailyIntake newintake = planner.calculateDailyIntake(testusr);
        // When
        // Then
        assertEquals(newintake.getCalories(), TestConstants.TEST_USER_DAILY_INTAKE.getCalories());
        assertEquals(newintake.getFat(), TestConstants.TEST_USER_DAILY_INTAKE.getFat());
        assertEquals(newintake.getProtein(), TestConstants.TEST_USER_DAILY_INTAKE.getProtein());
        assertEquals(newintake.getCalories(), TestConstants.TEST_USER_DAILY_INTAKE.getCalories());
    }
}

package efs.task.unittests;

import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended()
    {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietNotRecommended()
    {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void shouldReturnIllegalArgumentException_whenHeightIsZero()
    {
        //given
        double weight = 69.5;
        double height = 0;

        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> FitCalculator.isBMICorrect(weight, height));
    }

    @ParameterizedTest(name = "Test {index}: waga={0}")
    @ValueSource(doubles = {94.0, 87.5, 106.6, 89.5})
    void checkTrueValues (double weight)
    {
        // Given
        double height = 1.78;
        // When
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // Then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "Test {index}: waga={0}, wzrost={1}")
    @CsvSource({
            "50.0, 1.65",
            "82.5, 1.9",
            "75.4, 1.8",
            "70, 1.76",
    })
    void checkFalseValues(double weight, double height)
    {
        // Given

        // When
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // Then
        assertFalse(recommended);
    }

    @ParameterizedTest(name = "Test {index}: waga={0}, wzrost={1}")
    @CsvFileSource(resources = "/data.csv", delimiter = ',', numLinesToSkip = 1)
    void checkFalseValues2(double height, double weight)
    {
        // Given

        // When
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // Then
        assertFalse(recommended);
    }

    @Test
    void checkListNotNull()
    {
        // Given
        List<User> test1 = TestConstants.TEST_USERS_LIST;
        // When
        User recommended = FitCalculator.findUserWithTheWorstBMI(test1);

        // Then
        assertEquals(97.3, recommended.getWeight());
        assertEquals(1.79, recommended.getHeight());
    }

    @Test
    void checkListNull()
    {
        // Given
        // When
        User recommended = FitCalculator.findUserWithTheWorstBMI(new ArrayList<>());

        // Then
        assertNull(recommended);
    }

    @Test
    void checkCalculateBMI()
    {
        // Given
        // When

        // Then
        assertArrayEquals(efs.task.unittests.TestConstants.TEST_USERS_BMI_SCORE, FitCalculator.calculateBMIScore(efs.task.unittests.TestConstants.TEST_USERS_LIST));
    }
}
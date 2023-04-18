package com.empik.githubadapter.junit;

import com.empik.githubadapter.empik.dto.Calculations;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculationsTest {

    @MethodSource("calculationsTestParameters")
    @ParameterizedTest
    public void shouldCalculateCorrectValue(int followers,
                                            int publicRepos,
                                            double expectedResult) {
        Calculations calculations = Calculations.of(followers, publicRepos);
        assertEquals(expectedResult, calculations.calculate());
    }

    private static Stream<Arguments> calculationsTestParameters() {
        return Stream.of(
                Arguments.of(0, 1, 0),
                Arguments.of(0, 0, 0),
                Arguments.of(10, 3, 3.0),
                Arguments.of(8952, 8, 0.0067024129)
        );
    }
}

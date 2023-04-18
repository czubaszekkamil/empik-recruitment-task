package com.empik.githubadapter.empik.dto;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor(staticName = "of")
public class Calculations {
    private static final int DEFAULT_SCALE = 10;
    private static final double DEFAULT_RESULT = 0.0;

    private final Integer followers;
    private final Integer publicRepos;

    public double calculate() {
        if (isNotPossibleToCalculate()) {
            return DEFAULT_RESULT;
        }

        BigDecimal result = BigDecimal.valueOf(calculateFromTheFormula());

        return result.setScale(DEFAULT_SCALE, RoundingMode.CEILING).doubleValue();
    }

    private double calculateFromTheFormula() {
        return 6.0 / followers * (2 + publicRepos);
    }

    private boolean isNotPossibleToCalculate() {
        return this.followers == null || this.followers == 0;
    }

}

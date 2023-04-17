package com.empik.githubadapter.empik.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class Calculations {

    private final Integer followers;
    private final Integer publicRepos;

    public Double getResult() {
        if (isNotPossibleToCalculate()) {
            return Double.NaN;
        }

        return 6.0 / followers * (2 + publicRepos);
    }

    private boolean isNotPossibleToCalculate() {
        return this.followers == null || this.followers == 0;
    }

}

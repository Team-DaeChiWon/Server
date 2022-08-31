package com.project.daechiwon.domain.plan.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor @Getter
public class PlanCreationRequest {
    @Size(min = 8, max = 8, message = "yyyyMMdd 형식이여야 합니다")
    private String date;
    private String title;
    private String content;
}

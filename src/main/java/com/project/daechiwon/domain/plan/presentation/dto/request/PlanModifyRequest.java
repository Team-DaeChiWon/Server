package com.project.daechiwon.domain.plan.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Getter
public class PlanModifyRequest {
    private String title;
    private String content;
}

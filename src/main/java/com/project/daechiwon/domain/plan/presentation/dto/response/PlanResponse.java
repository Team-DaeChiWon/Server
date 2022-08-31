package com.project.daechiwon.domain.plan.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor @Builder @Getter
public class PlanResponse {
    private Long id;
    private String title;
    private String content;
}

package com.project.daechiwon.domain.plan.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor @Builder
@Getter
public class PlanGroup {
    private String date;
    private List<PlanResponse> events;
}

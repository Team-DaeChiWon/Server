package com.project.daechiwon.domain.plan.presentation;

import com.project.daechiwon.domain.plan.presentation.dto.request.PlanCreationRequest;
import com.project.daechiwon.domain.plan.presentation.dto.request.PlanModifyRequest;
import com.project.daechiwon.domain.plan.presentation.dto.response.PlanOfMonthResponse;
import com.project.daechiwon.domain.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/plans")
@RestController
public class PlanController {
    private final PlanService planService;

    @GetMapping("/")
    public PlanOfMonthResponse getPlansOf(@RequestParam("year") Integer year, @RequestParam("month") Integer month) {
        return planService.getPlansOf(year, month);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void createPlan(@RequestBody PlanCreationRequest request) {
        planService.createPlan(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{plan-id}")
    public void modifyPlan(@PathVariable("plan-id") Long planId, @RequestBody PlanModifyRequest request) {
        planService.modifyPlan(planId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{plan-id}")
    public void deletePlan(@PathVariable("plan-id") Long planId) {
        planService.deletePlan(planId);
    }

}

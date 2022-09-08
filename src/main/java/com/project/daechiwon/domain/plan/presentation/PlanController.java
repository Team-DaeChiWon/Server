package com.project.daechiwon.domain.plan.presentation;

import com.project.daechiwon.domain.plan.presentation.dto.request.PlanCreationRequest;
import com.project.daechiwon.domain.plan.presentation.dto.request.PlanModifyRequest;
import com.project.daechiwon.domain.plan.presentation.dto.response.PlanOfMonthResponse;
import com.project.daechiwon.domain.plan.service.PlanService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/plans")
@RestController
public class PlanController {
    private final PlanService planService;

    @ApiOperation("교육계획표를 조회합니다")
    @GetMapping("/")
    public PlanOfMonthResponse getPlansOf(@RequestParam("year") Integer year, @RequestParam("month") Integer month) {
        return planService.getPlansOf(year, month);
    }

    @ApiOperation("교육계획표를 작성합니다")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void createPlan(@RequestBody PlanCreationRequest request) {
        planService.createPlan(request);
    }

    @ApiOperation("교육계획표를 수정합니다")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{plan-id}")
    public void modifyPlan(@PathVariable("plan-id") Long planId, @RequestBody PlanModifyRequest request) {
        planService.modifyPlan(planId, request);
    }

    @ApiOperation("교육계획표를 삭제합니다")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{plan-id}")
    public void deletePlan(@PathVariable("plan-id") Long planId) {
        planService.deletePlan(planId);
    }

}

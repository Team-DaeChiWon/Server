package com.project.daechiwon.domain.plan.service;

import com.project.daechiwon.domain.plan.entity.EducationPlan;
import com.project.daechiwon.domain.plan.exception.PlanAccessDeniedException;
import com.project.daechiwon.domain.plan.exception.PlanNotFoundException;
import com.project.daechiwon.domain.plan.presentation.dto.request.PlanCreationRequest;
import com.project.daechiwon.domain.plan.presentation.dto.request.PlanModifyRequest;
import com.project.daechiwon.domain.plan.presentation.dto.response.PlanGroup;
import com.project.daechiwon.domain.plan.presentation.dto.response.PlanOfMonthResponse;
import com.project.daechiwon.domain.plan.presentation.dto.response.PlanResponse;
import com.project.daechiwon.domain.plan.repository.PlanRepository;
import com.project.daechiwon.domain.user.entity.User;
import com.project.daechiwon.domain.user.exception.UserUnauthorizedException;
import com.project.daechiwon.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlanService {
    private final UserFacade userFacade;
    private final PlanRepository planRepository;

    @Transactional(readOnly = true)
    public PlanOfMonthResponse getPlansOf(Integer year, Integer month) {
        LocalDate start = LocalDate.of(year, month, 1);
        List<EducationPlan> plans = planRepository.findAllInMonth(start, start.plusMonths(1));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Map<LocalDate, ArrayList<EducationPlan>> groups = plans.stream().map(it -> it.getDate())
                .distinct()
                .collect(Collectors.toMap((it) -> it, (it) -> new ArrayList<>()));

        plans.forEach(it -> {
            groups.get(it.getDate()).add(it);
        });

        List<PlanGroup> list = groups.entrySet().stream().map((entry) -> PlanGroup.builder()
                        .date(formatter.format(entry.getKey()))
                        .events(entry.getValue().stream().map(item -> PlanResponse.builder()
                                .id(item.getId())
                                .title(item.getTitle())
                                .content(item.getContent())
                                .build()).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        return new PlanOfMonthResponse(list);
    }

    @Transactional
    public void createPlan(PlanCreationRequest request) {
        User user = userFacade.queryUser(true)
                .orElseThrow(UserUnauthorizedException::new);

        EducationPlan plan = EducationPlan.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(user)
                .date(LocalDate.parse(request.getDate(), DateTimeFormatter.ofPattern("yyyyMMdd")))
                .build();

        user.getPlanList().add(plan);
    }

    @Transactional
    public void modifyPlan(Long planId, PlanModifyRequest request) {
        User user = userFacade.queryUser(true)
                .orElseThrow(UserUnauthorizedException::new);

        EducationPlan plan = planRepository.findById(planId)
                .orElseThrow(PlanNotFoundException::new);
        if(!plan.getAuthor().equals(user))
            throw new PlanAccessDeniedException();

        plan.modify(request.getTitle(), request.getContent());
    }

    @Transactional
    public void deletePlan(Long planId) {
        User user = userFacade.queryUser(true)
                .orElseThrow(UserUnauthorizedException::new);

        EducationPlan plan = planRepository.findById(planId)
                .orElseThrow(PlanNotFoundException::new);
        if(!plan.getAuthor().equals(user))
            throw new PlanAccessDeniedException();

        user.getPlanList().remove(plan);
    }
}

package com.project.daechiwon.domain.plan.entity;

import com.project.daechiwon.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Getter @Builder
@Entity
public class EducationPlan {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn
    @ManyToOne
    private User author;

    private String title;

    @Lob
    private String content;

    private LocalDate date;

    public void modify(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

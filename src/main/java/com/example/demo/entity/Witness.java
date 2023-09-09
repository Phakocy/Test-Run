package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="officers")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Witness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "case_management_id")
    private CaseManagement caseManagement;

    public CaseManagement getCaseManagement() {
        return caseManagement;
    }

    public void setCaseManagement(CaseManagement caseManagement) {
        this.caseManagement = caseManagement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

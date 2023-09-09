package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name="officers")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LawEnforcementOfficer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String officerId;

    @OneToMany
    private List<CaseManagement> cases;

}

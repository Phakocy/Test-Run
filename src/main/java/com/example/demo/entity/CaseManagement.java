package com.example.demo.entity;

import com.example.demo.entity.enums.CasePriority;
import com.example.demo.entity.enums.CaseStatus;
import com.example.demo.entity.enums.CaseType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "case_management")
public class CaseManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Case title is required")
    private String caseTitle;

    @Column(columnDefinition = "TEXT")
    private String caseDescription;

    @Enumerated(EnumType.STRING)
    private CaseType caseType;

    @Enumerated(EnumType.STRING)
    private CaseStatus caseStatus;

    @ManyToOne
    @JoinColumn(name = "assigned_officer_id")
    private LawEnforcementOfficer assignedOfficer;

    @Temporal(TemporalType.DATE)
    @PastOrPresent(message = "Date opened must be in the past or present")
    private Date dateOpened;

    @Temporal(TemporalType.DATE)
    private Date dateClosed;

    @Enumerated(EnumType.STRING)
    private CasePriority priority;

    private String location;

    @OneToOne
    private Victim victim;  //Many Victims to One case

    @OneToOne
    private Suspect suspect;

    @OneToMany(mappedBy = "caseManagement")
    private List<Witness> witnesses;

}


//@Entity
//@Table(name="case_management")
//public class CaseManagement {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String caseId; //(To be generated in utils)
//    private String caseNumber; //(To be generated and increment automatically, check Enrollee number in HIMS)
//    private String caseTitle;
//    private String caseDescription;
//    private String caseType;
//    private String caseStatus;
//    private String assignedOfficer; // Foreign key
//    private Date dateopened;
//    private Date dateClosed;
//    private String priority;
//    private String location;
//    private Victim victimInfo;  //(Create a victim class to store victims information)
//    private Suspect suspectInfo;
//    private Witness witnessInfo;
//    private Evidence evidenceInfo;
//    private String caseNote;
//    private Documents attachments;
//    private CourtRecord courtInfo;
//    private Documents legalDoc;
//    private Judges assignedJudges;
////    List <Long> relatedCasesId;
//    List<CaseHistory> caseHistory;
//      private String caseOutcome
//
//}

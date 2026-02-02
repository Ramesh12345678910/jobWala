package com.project.profile_Service.model;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "profileTable")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profileId;
    @Column(nullable = false)
    private String education;
    @Column
    private Integer Experience;
    @Column
    private List<String> skills;
    @Column(nullable = false)
    private Integer userId;

    public ProfileEntity() {
    }

    public ProfileEntity(String education, Integer experience, List<String> skills, Integer userId) {
        this.education = education;
        this.Experience = experience;
        this.skills = skills;
        this.userId = userId;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getExperience() {
        return Experience;
    }

    public void setExperience(Integer experience) {
        Experience = experience;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
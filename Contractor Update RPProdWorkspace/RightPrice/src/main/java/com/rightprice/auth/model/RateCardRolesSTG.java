package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.RP_Rate_Card_Roles_STG")
public class RateCardRolesSTG {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RC_ROLE_ID")
	@ApiModelProperty(notes = "RC Role Id")
	private Integer rcRoleId; 
	
	@Column(name = "RC_ID")
	@ApiModelProperty(notes = "RC Id")
	private Integer rcId;
	
	@Column(name = "RC_MASTER_ROLE_ID")
	@ApiModelProperty(notes = "RC Master Role Id")
	private Integer rcMasterRoleId;
	
	@Column(name = "CLIENT_ROLE")
	@ApiModelProperty(notes = "Client Role")
	private String clientRole;
	
	/*@Column(name = "SKILL_ID")
	@ApiModelProperty(notes = "Skill Id")
	private Integer skillId;
	
	@Column(name = "SKILL_ELEMENT_ID")
	@ApiModelProperty(notes = "Skill Element Id")
	private Integer skillElementId;
	
	@Column(name = "KNOWLEDGE_ID")
	@ApiModelProperty(notes = "Knowledge Id")
	private Integer knowledgeId;*/
	
	@Column(name = "COMMENTS")
	@ApiModelProperty(notes = "Comments")
	private String comments;
	
	@Column(name = "CREATED_BY", updatable = false)
	@ApiModelProperty(notes = "Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable = false)
	@ApiModelProperty(notes = "Created On")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated by")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated On")
	private String updatedOn;
	
	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes = "Country Id")
	private Integer countryId;
	
	@Column(name = "CITY_ID")
	@ApiModelProperty(notes = "City Id")
	private Integer cityId;
	
	@Column(name = "PRACTICE_ID")
	@ApiModelProperty(notes = "Practice Id")
	private Integer practiceId;
	
	@Column(name = "SUB_PRACTICE_ID")
	@ApiModelProperty(notes = "Sub Practice Id")
	private Integer subPracticeId;
	
	@Column(name = "PROFICEINCY_ID")
	@ApiModelProperty(notes = "Proficeincy Id")
	private Integer proficeincyId;
	
	@Column(name = "BAND_ID")
	@ApiModelProperty(notes = "Band Id")
	private Integer bandId;
	
	@Column(name = "GRADE_ID")
	@ApiModelProperty(notes = "Grade Id")
	private Integer gradeId;
	
	@Column(name = "XO_BG_Id")
	@ApiModelProperty(notes = "XO BG Id")
	private Integer xoBGId;
	
	@Column(name = "LOCAL_PERCENTAGE")
	@ApiModelProperty(notes = "Local Percentage")
	private Integer localPercentage;
	
	@Column(name = "DEPUTED_PERCENTAGE")
	@ApiModelProperty(notes = "Deputed Percentage")
	private Integer deputedPercentage;
	
	@Column(name = "ONSITE_CUSTOMER_RATE")
	@ApiModelProperty(notes = "Onsite Customer Rate")
	private Double onsiteCustomerRate;
	
	@Column(name = "ONSITE_USAGE")
	@ApiModelProperty(notes = "Onsite Usage")
	private Integer onsiteUsage;
	
	@Column(name = "OFFSHORE_CUSTOMER_RATE")
	@ApiModelProperty(notes = "Offshore Customer Rate")
	private Double offshoreCustomerRate;
	
	@Column(name = "OFFSHORE_USAGE")
	@ApiModelProperty(notes = "Offshore Usage")	
	private Integer offshoreUsage;
	
	@Column(name = "Transaction_Year")
	@ApiModelProperty(notes = "Transaction Year")
	private Integer transaction_Year;
	
	/*@Column(name = "Year_3_Usage")
	@ApiModelProperty(notes = "Year3 Usage")
	private Integer year3Usage;

	@Column(name = "Year_4_Usage")
	@ApiModelProperty(notes = "Year4 Usage")
	private Integer year4Usage;
	
	@Column(name = "Year_5_Usage")
	@ApiModelProperty(notes = "Year_5_Usage")
	private Integer year5Usage;*/
	
	@Transient
	private String country;

	@Transient
	private String city;
	
	@Transient
	private String Practice;
	
	@Transient
	private String subPractice;
	
	@Transient
	private String Role;
	
	@Transient
	private String proficiencyDesc;
	
	@Transient
	private String band;
	
	@Transient
	private String grade;
	
	@Transient
	private String xOSkills;
	
	@Transient
	private String xOSkillsElement;
	
	@Transient
	private String xOKnowledge;
	
	@Transient
	private String xOBandGrade;
	
	@Transient
	private String xOProficiency;
	
	public Integer getRcRoleId() {
		return rcRoleId;
	}

	public void setRcRoleId(Integer rcRoleId) {
		this.rcRoleId = rcRoleId;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public Integer getRcMasterRoleId() {
		return rcMasterRoleId;
	}

	public void setRcMasterRoleId(Integer rcMasterRoleId) {
		this.rcMasterRoleId = rcMasterRoleId;
	}

	public String getClientRole() {
		return clientRole;
	}

	public void setClientRole(String clientRole) {
		this.clientRole = clientRole;
	}

	/*public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public Integer getSkillElementId() {
		return skillElementId;
	}

	public void setSkillElementId(Integer skillElementId) {
		this.skillElementId = skillElementId;
	}

	public Integer getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}*/

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getPracticeId() {
		return practiceId;
	}

	public void setPracticeId(Integer practiceId) {
		this.practiceId = practiceId;
	}

	public Integer getSubPracticeId() {
		return subPracticeId;
	}

	public void setSubPracticeId(Integer subPracticeId) {
		this.subPracticeId = subPracticeId;
	}

	public Integer getProficeincyId() {
		return proficeincyId;
	}

	public void setProficeincyId(Integer proficeincyId) {
		this.proficeincyId = proficeincyId;
	}

	public Integer getBandId() {
		return bandId;
	}

	public void setBandId(Integer bandId) {
		this.bandId = bandId;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getXoBGId() {
		return xoBGId;
	}

	public void setXoBGId(Integer xoBGId) {
		this.xoBGId = xoBGId;
	}

	public Integer getLocalPercentage() {
		return localPercentage;
	}

	public void setLocalPercentage(Integer localPercentage) {
		this.localPercentage = localPercentage;
	}

	public Integer getDeputedPercentage() {
		return deputedPercentage;
	}

	public void setDeputedPercentage(Integer deputedPercentage) {
		this.deputedPercentage = deputedPercentage;
	}

	public Double getOnsiteCustomerRate() {
		return onsiteCustomerRate;
	}

	public void setOnsiteCustomerRate(Double onsiteCustomerRate) {
		this.onsiteCustomerRate = onsiteCustomerRate;
	}

	public Integer getOnsiteUsage() {
		return onsiteUsage;
	}

	public void setOnsiteUsage(Integer onsiteUsage) {
		this.onsiteUsage = onsiteUsage;
	}

	public Double getOffshoreCustomerRate() {
		return offshoreCustomerRate;
	}

	public void setOffshoreCustomerRate(Double offshoreCustomerRate) {
		this.offshoreCustomerRate = offshoreCustomerRate;
	}

	public Integer getOffshoreUsage() {
		return offshoreUsage;
	}

	public void setOffshoreUsage(Integer offshoreUsage) {
		this.offshoreUsage = offshoreUsage;
	}

	public Integer getTransactionYear() {
		return transaction_Year;
	}

	public void setTransactionYear(Integer transaction_year) {
		this.transaction_Year = transaction_year;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getPractice() {
		return Practice;
	}

	public void setPractice(String practice) {
		Practice = practice;
	}

	public String getSubPractice() {
		return subPractice;
	}

	public void setSubPractice(String subPractice) {
		this.subPractice = subPractice;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getProficiencyDesc() {
		return proficiencyDesc;
	}

	public void setProficiencyDesc(String proficiencyDesc) {
		this.proficiencyDesc = proficiencyDesc;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getxOSkills() {
		return xOSkills;
	}

	public void setxOSkills(String xOSkills) {
		this.xOSkills = xOSkills;
	}

	public String getxOSkillsElement() {
		return xOSkillsElement;
	}

	public void setxOSkillsElement(String xOSkillsElement) {
		this.xOSkillsElement = xOSkillsElement;
	}

	public String getxOKnowledge() {
		return xOKnowledge;
	}

	public void setxOKnowledge(String xOKnowledge) {
		this.xOKnowledge = xOKnowledge;
	}

	public String getxOBandGrade() {
		return xOBandGrade;
	}

	public void setxOBandGrade(String xOBandGrade) {
		this.xOBandGrade = xOBandGrade;
	}

	public String getxOProficiency() {
		return xOProficiency;
	}

	public void setxOProficiency(String xOProficiency) {
		this.xOProficiency = xOProficiency;
	}

	
	
}

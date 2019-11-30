package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class UniversityDto{
	private String name;
	private Set<String> subjectCourseIDs;
	private Integer tutoringSystemID;

	public UniversityDto() {
		
	}
	
	public UniversityDto(String uniName, Set<String> subjectCourseIDs, Integer tutoringSystem) {
		this.setName(uniName);
		this.setSubject(subjectCourseIDs);
		this.setTutoringSystem(tutoringSystem);
	}
	

	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}

	public Set<String> getSubject() {
		return this.subjectCourseIDs;
	}

	public void setSubject(Set<String> subjectCourseIDs) {
		this.subjectCourseIDs = subjectCourseIDs;
	}

	public Integer getTutoringSystem() {
		return this.tutoringSystemID;
	}

	public void setTutoringSystem(Integer tutoringSystemID) {
		this.tutoringSystemID = tutoringSystemID;
	}

}

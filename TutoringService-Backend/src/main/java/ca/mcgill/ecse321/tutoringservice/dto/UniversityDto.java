package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class UniversityDto{
	private String name;
	private Set<String> subjectCourseIDs;
	private TutoringSystemDto tutoringSystem;

	public UniversityDto() {
		
	}
	
	public UniversityDto(String uniName, Set<String> subjectCourseIDs, TutoringSystemDto tutoringSystem) {
		this.name = uniName;
		this.subjectCourseIDs = subjectCourseIDs;
		this.tutoringSystem = tutoringSystem;
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

	public TutoringSystemDto getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystemDto tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

}

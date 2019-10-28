package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class UniversityDto{
	private String name;
	private Set<SubjectDto> subject;
	private TutoringSystemDto tutoringSystem;

	public UniversityDto() {
		
	}
	
	public UniversityDto(String uniName, Set<SubjectDto> subjects, TutoringSystemDto tutoringSystem) {
		this.name = uniName;
		this.subject = subjects;
		this.tutoringSystem = tutoringSystem;
	}

		public void setName(String value) {
		this.name = value;
	}
	public String getName() {
		return this.name;
	}

	public Set<SubjectDto> getSubject() {
		return this.subject;
	}

	public void setSubject(Set<SubjectDto> subjects) {
		this.subject = subjects;
	}


	public TutoringSystemDto getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystemDto tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

}

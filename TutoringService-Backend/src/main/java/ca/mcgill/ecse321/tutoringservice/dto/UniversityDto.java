package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.Subject;
import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;

public class UniversityDto{
	private String name;
	private Set<Subject> subject;
	private TutoringSystem tutoringSystem;

	public UniversityDto() {
		
	}
	
	public UniversityDto(String name, Set<Subject> subjects, TutoringSystem tutoringSystem) {
		this.name = name;
		this.subject = subjects;
		this.tutoringSystem = tutoringSystem;
	}
	
	public UniversityDto(String uniName) {
		this.name = uniName;
	}

		public void setName(String value) {
		this.name = value;
	}
	public String getName() {
		return this.name;
	}

	public Set<Subject> getSubject() {
		return this.subject;
	}

	public void setSubject(Set<Subject> subjects) {
		this.subject = subjects;
	}


	public TutoringSystem getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystem tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

}

package ca.mcgill.ecse321.tutoringservice.dto;


import ca.mcgill.ecse321.tutoringservice.model.SubjectType;
import ca.mcgill.ecse321.tutoringservice.dto.UniversityDto;


public class SubjectDto{
	private String name;
	private String courseID;
	private String description;
	private SubjectType subjectType;
	private UniversityDto university;

	public SubjectDto() {
		
	}
	
	public SubjectDto(String name, String courseID, String description, SubjectType subjectType, UniversityDto university) {
		this.setName(name);
		this.setCourseID(courseID);
		this.setDescription(description);
		this.setSubjectType(subjectType);
		this.university = university;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	public String getName() {
		return this.name;
	}

	public void setCourseID(String value) {
		this.courseID = value;
	}
	public String getCourseID() {
		return this.courseID;
	}

	public void setDescription(String value) {
		this.description = value;
	}
	public String getDescription() {
		return this.description;
	}

	public void setSubjectType(SubjectType value) {
		this.subjectType = value;
	}
	public SubjectType getSubjectType() {
		return this.subjectType;
	}

	public UniversityDto getUniversity() {
		return this.university;
	}

	public void setUniversity(UniversityDto university) {
		this.university = university;
	}

}

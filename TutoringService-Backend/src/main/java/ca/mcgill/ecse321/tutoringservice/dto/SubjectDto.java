package ca.mcgill.ecse321.tutoringservice.dto;


import ca.mcgill.ecse321.tutoringservice.model.SubjectType;
import ca.mcgill.ecse321.tutoringservice.model.University;


public class SubjectDto{
	private String name;
	private String courseID;
	private String description;
	private SubjectType subjectType;
	private University university;

	public SubjectDto() {
		
	}
	
	public SubjectDto(String name, String courseID, String description, SubjectType subjectType, University university) {
		SubjectDto sb = new SubjectDto();
		sb.setName(name);
		sb.setCourseID(courseID);
		sb.setDescription(description);
		sb.setSubjectType(subjectType);
		sb.setUniversity(university);
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

	public University getUniversity() {
		return this.university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
}

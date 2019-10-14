package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

public enum SubjectType{
	HIGH_SCHOOL_COURSE,
	CGEP_COURSE,
	UNIVERSITY_COURSE;
   }

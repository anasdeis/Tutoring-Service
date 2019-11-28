package ca.mcgill.ecse321.tutoringservice;

public class Tutor {
    private String subject;
    private String offering;
    public Tutor() {

    }

    public Tutor(String subject, String offering) {
        this.subject = subject;
        this.offering = offering;
    }

    public String getSubject() {
        return subject;
    }

    public String getOffering() {
        return offering;
    }
}

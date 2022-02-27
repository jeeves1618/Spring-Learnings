package net.myphenotype.RestEndPoints.Domain;

public class Cricketer {

    private String firstName;
    private String lastName;
    private String nationalTeam;
    private int runsScored;
    private int wicketsTaken;

    public Cricketer() {
    }

    public Cricketer(String firstName, String lastName, String nationalTeam, int runsScored, int wicketsTaken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalTeam = nationalTeam;
        this.runsScored = runsScored;
        this.wicketsTaken = wicketsTaken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalTeam() {
        return nationalTeam;
    }

    public void setNationalTeam(String nationalTeam) {
        this.nationalTeam = nationalTeam;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }
}

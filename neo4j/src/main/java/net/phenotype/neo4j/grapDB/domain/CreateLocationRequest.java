package net.phenotype.neo4j.grapDB.domain;

public class CreateLocationRequest {

    private String city;
    private String country;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "CreateLocationRequest{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

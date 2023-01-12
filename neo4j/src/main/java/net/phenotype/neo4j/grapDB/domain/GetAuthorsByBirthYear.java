package net.phenotype.neo4j.grapDB.domain;

import java.util.List;

public class GetAuthorsByBirthYear {

    private List<Integer> bornYearList;

    public List<Integer> getBornYearList() {
        return bornYearList;
    }

    public void setBornYearList(List<Integer> bornYearList) {
        this.bornYearList = bornYearList;
    }
}

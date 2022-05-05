package net.myphenotype.projectverona.processing.entity;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@Scope(value = "prototype")
public class FlatHeatMap {

    private int floorNumber;
    private List<Double> priceList = new ArrayList<>();
    private List<String> priceListFmtd = new ArrayList<>();
    private List<String> withinBudget = new ArrayList<>();

    public void addPriceList(Double price){
        this.priceList.add(price);
    }

    public void addPriceListFmtd(String price){
        this.priceListFmtd.add(price);
    }

    public void addWithinBudget(String value){
        this.withinBudget.add(value);
    }
}

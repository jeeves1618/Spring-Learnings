package net.myphenotype.projectverona.processing.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Transient;

@Data
@Component
public class AssetSummary {
    private String lastDateAvailable;
    @Transient
    private double totalExpectedPrice;
    @Transient
    private double totalActualAmount;
    @Transient
    private String totalExpectedPriceFmtd;
    @Transient
    private String totalActualAmountFmtd;
}

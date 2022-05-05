package net.myphenotype.projectverona.processing.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Component
@Table(name = "asset_details")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "asset_key")
    private Integer assetKey;
    @Column (name = "asset_description")
    private String assetDescription;
    @Column (name = "date_available")
    private String dateAvailable;
    @Column (name = "expected_price")
    private double expectedPrice;
    @Column (name = "actual_amount")
    private double actualAmount;
    @Transient
    private String expectedPriceFmtd;
    @Transient
    private String actualAmountFmtd;
    @Transient
    private String lastDateAvailable;
    @Transient
    private double totalExpectedPrice;
    @Transient
    private double totalActualAmount;
    @Transient
    private String totalExpectedPriceFmtd;
    @Transient
    private String totalActualAmountFmtd;

    public Asset() {
    }

    public Asset(String assetDescription, String dateAvailable, double expectedPrice, double actualAmount) {
        this.assetDescription = assetDescription;
        this.dateAvailable = dateAvailable;
        this.expectedPrice = expectedPrice;
        this.actualAmount = actualAmount;
    }
}

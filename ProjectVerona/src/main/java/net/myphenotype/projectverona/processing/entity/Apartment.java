package net.myphenotype.projectverona.processing.entity;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Entity
@Component
@Scope(value = "prototype")
@Table(name = "apartment_details")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "apartment_key")
    private Integer apartmentKey;
    @Max(5)
    @Min(5)
    @Column (name = "apartment_name")
    private String apartmentName;
    @Max(350)
    @Column (name = "apartment_format")
    private String apartmentFormat;
    @Column (name = "super_built_up_area")
    private int superBuiltUpArea;
    @Column (name = "rera_carpet_area")
    private int reraCarpetArea;
    @Column (name = "deck_area")
    private int deckArea;
    @Column (name = "minimum_cost")
    private double minimumCost;
    @Column (name = "floor_rise")
    private double floorRise;
    @Column (name = "township_corpus")
    private double towshipCorpus;
    @Column (name = "adhoc_maintenance")
    private double adhocMaintenance;
    @Column (name = "development_charges")
    private double developmentCharges;
    @Column (name = "legal_charges")
    private double legalCharges;
    @Column (name = "generator_charges")
    private double generatorCharges;
    @Column (name = "gst_calculated")
    private double gstCalculated;
    @Column (name = "registration")
    private double registration;
    @Column (name = "total_cost")
    private double totalCost;
    @Transient
    private String minimumCostFmtd;
    @Transient
    private String floorRiseFmtd;
    @Transient
    private String towshipCorpusFmtd;
    @Transient
    private String adhocMaintenanceFmtd;
    @Transient
    private String developmentChargesFmtd;
    @Transient
    private String legalChargesFmtd;
    @Transient
    private String generatorChargesFmtd;
    @Transient
    private String gstCalculatedFmtd;
    @Transient
    private String registrationFmtd;
    @Transient
    private String totalCostFmtd;
    @Transient
    private int floor;
    @Transient
    private double shortFall;
    @Transient
    private String shortFallFmtd;

    public Apartment() {
    }

    public Apartment(String apartmentName, String apartmentFormat, int superBuiltUpArea, int reraCarpetArea, int deckArea, double minimumCost, double floorRise, double towshipCorpus, double adhocMaintenance, double developmentCharges, double legalCharges, double generatorCharges, double gstCalculated, double registration, double totalCost) {
        this.apartmentName = apartmentName;
        this.apartmentFormat = apartmentFormat;
        this.superBuiltUpArea = superBuiltUpArea;
        this.reraCarpetArea = reraCarpetArea;
        this.deckArea = deckArea;
        this.minimumCost = minimumCost;
        this.floorRise = floorRise;
        this.towshipCorpus = towshipCorpus;
        this.adhocMaintenance = adhocMaintenance;
        this.developmentCharges = developmentCharges;
        this.legalCharges = legalCharges;
        this.generatorCharges = generatorCharges;
        this.gstCalculated = gstCalculated;
        this.registration = registration;
        this.totalCost = totalCost;
    }
}

package net.myphenotype.projectverona.processing.config;

import net.myphenotype.projectverona.processing.entity.Apartment;
import net.myphenotype.projectverona.processing.entity.Asset;
import net.myphenotype.projectverona.processing.service.ApartmentService;
import net.myphenotype.projectverona.processing.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("h2")
public class ApartmentDetailsLoader implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    Apartment apartment;

    @Autowired
    Asset asset;

    @Autowired
    ApartmentService apartmentServicer;

    @Autowired
    AssetService assetService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<Apartment> apartmentList = new ArrayList<>();

        apartmentList.add(new Apartment("Apt 09","2 BHK",1027,702,36,6451465.00,46215.00,
                51350.00,69323.00,608100.00,40000,25000,0.00,0.00,0.00));
        apartmentList.add(new Apartment("Apt 03","2 BHK",1029,703,36,6463055.00,46305.00,
                51450.00,69458.00,608700.00,40000,25000,0.00,0.00,0.00));
        apartmentList.add(new Apartment("Apt 07","2 BHK",1035,702,36,6497825.00,46575.00,
                51750.00,69863.00,610500.00,40000,25000,0.00,0.00,0.00));
        apartmentList.add(new Apartment("Apt 08","2 BHK",1053,725,36,6602135.00,47385.00,
                52650.00,71078.00,615900.00,40000,25000,0.00,0.00,0.00));
        apartmentList.add(new Apartment("Apt 04","3 BHK SMART",1258,875,36,7790110.00,56610.00,
                62900.00,84915.00,677400.00,40000,25000,0.00,0.00,0.00));
        apartmentList.add(new Apartment("Apt 02","3 BHK SMART",1269,876,36,7853855.00,57105.00,
                63450.00,85658.00,680700.00,40000,25000,0.00,0.00,0.00));
        apartmentList.add(new Apartment("Apt 06","3 BHK",1552,1087,36,9493840.00,69840.00,
                77600.00,104760.00,765600.00,40000,25000,0.00,0.00,0.00));
        apartmentList.add(new Apartment("Apt 10","3 BHK",1556,1087,36,9517020.00,70020.00,
                77800.00,105030.00,766800.00,40000,25000,0.00,0.00,0.00));
        apartmentList.add(new Apartment("Apt 01","3 BHK",1541,1089,36,9430095.00,69525.00,
                77050.00,104018.00,762300.00,40000,25000,0.00,0.00,0.00));
        apartmentList.add(new Apartment("Apt 05","3 BHK",1545,1089,36,9453275.00,69525.00,
                77250.00,104288.00,763500.00,40000,25000,0.00,0.00,0.00));

        apartmentServicer.saveAll(apartmentList);

        List<Asset> assetList = new ArrayList<>();
        assetList.add(new Asset("Sale of Erode House","2022-08-31",3000000.00,0.00));
        assetList.add(new Asset("Sale of Erode Land","2022-08-31",6125000.00,0.00));

        assetService.saveAll(assetList);
    }
}

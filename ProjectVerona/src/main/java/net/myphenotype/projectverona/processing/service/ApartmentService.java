package net.myphenotype.projectverona.processing.service;

import lombok.extern.slf4j.Slf4j;
import net.myphenotype.projectverona.processing.entity.Apartment;
import net.myphenotype.projectverona.processing.entity.FlatHeatMap;
import net.myphenotype.projectverona.processing.repo.ApartmentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ApartmentService {

    @Autowired
    ApartmentsRepo apartmentsRepo;

    @Autowired
    RupeeFormatter rf;

    @Autowired
    Apartment apartment;

    @Autowired
    AssetService assetService;

    @Autowired
    FlatHeatMap flatHeatMap;

    DecimalFormat ft = new DecimalFormat("Rs ##,##,##0.00");

    public List<Apartment> findAll(){
        List<Apartment> apartmentList = apartmentsRepo.findAll();

        for(Apartment apartment : apartmentList) {
            apartment.setMinimumCostFmtd(rf.formattedRupee(ft.format(apartment.getMinimumCost())));
            apartment.setFloorRiseFmtd(rf.formattedRupee(ft.format(apartment.getFloorRise())));
            apartment.setTowshipCorpusFmtd(rf.formattedRupee(ft.format(apartment.getTowshipCorpus())));
            apartment.setAdhocMaintenanceFmtd(rf.formattedRupee(ft.format(apartment.getAdhocMaintenance())));
            apartment.setDevelopmentChargesFmtd(rf.formattedRupee(ft.format(apartment.getDevelopmentCharges())));
            apartment.setLegalChargesFmtd(rf.formattedRupee(ft.format(apartment.getLegalCharges())));
            apartment.setGeneratorChargesFmtd(rf.formattedRupee(ft.format(apartment.getGeneratorCharges())));
            apartment.setGstCalculated(apartment.getMinimumCost()*.05 + (apartment.getDevelopmentCharges() +
                    apartment.getGeneratorCharges()+apartment.getLegalCharges()+apartment.getAdhocMaintenance())*0.18);
            apartment.setGstCalculatedFmtd(rf.formattedRupee(ft.format(apartment.getGstCalculated())));
            apartment.setRegistration((apartment.getMinimumCost() + apartment.getDevelopmentCharges() + apartment.getTowshipCorpus() +
                    apartment.getGeneratorCharges()+apartment.getLegalCharges()+apartment.getAdhocMaintenance()) * 0.06);
            apartment.setRegistrationFmtd(rf.formattedRupee(ft.format(apartment.getRegistration())));
            apartment.setTotalCost(apartment.getMinimumCost() + apartment.getDevelopmentCharges() + apartment.getTowshipCorpus() +
                    apartment.getGeneratorCharges()+apartment.getLegalCharges()+apartment.getAdhocMaintenance() + apartment.getRegistration() + apartment.getGstCalculated());
            apartment.setTotalCostFmtd(rf.formattedRupee(ft.format(apartment.getTotalCost())));
        }

        return apartmentList;
    }

    public void saveAll(List<Apartment> apartmentList){

        apartmentsRepo.saveAll(apartmentList);
    }

    public List<Apartment> findByApartmentName(String apartmentName){
        Optional<Apartment> apartmentSelected = apartmentsRepo.findByApartmentName(apartmentName);
        if (apartmentSelected.isEmpty()) return null;
        List<Apartment> floorList = new ArrayList<>();

        for(int i = 0; i < 18; i++) {
            apartment.setApartmentName(apartmentSelected.get().getApartmentName());
            apartment.setApartmentFormat(apartmentSelected.get().getApartmentFormat());
            apartment.setSuperBuiltUpArea(apartmentSelected.get().getSuperBuiltUpArea());
            apartment.setReraCarpetArea(apartmentSelected.get().getReraCarpetArea());
            apartment.setDeckArea(apartmentSelected.get().getDeckArea());
            apartment.setMinimumCost(apartmentSelected.get().getMinimumCost());
            apartment.setFloorRise(apartmentSelected.get().getFloorRise());
            apartment.setTowshipCorpus(apartmentSelected.get().getTowshipCorpus());
            apartment.setAdhocMaintenance(apartmentSelected.get().getAdhocMaintenance());
            apartment.setDevelopmentCharges(apartmentSelected.get().getDevelopmentCharges());
            apartment.setLegalCharges(apartmentSelected.get().getLegalCharges());
            apartment.setGeneratorCharges(apartmentSelected.get().getGeneratorCharges());

            apartment.setFloor(i+1);
            apartment.setMinimumCostFmtd(rf.formattedRupee(ft.format(apartment.getMinimumCost())));
            apartment.setFloorRiseFmtd(rf.formattedRupee(ft.format(apartment.getFloorRise())));
            apartment.setTowshipCorpusFmtd(rf.formattedRupee(ft.format(apartment.getTowshipCorpus())));
            apartment.setAdhocMaintenanceFmtd(rf.formattedRupee(ft.format(apartment.getAdhocMaintenance())));
            apartment.setDevelopmentChargesFmtd(rf.formattedRupee(ft.format(apartment.getDevelopmentCharges())));
            apartment.setLegalChargesFmtd(rf.formattedRupee(ft.format(apartment.getLegalCharges())));
            apartment.setGeneratorChargesFmtd(rf.formattedRupee(ft.format(apartment.getGeneratorCharges())));
            apartment.setGstCalculated((apartment.getMinimumCost() + (apartment.getFloorRise()*i))*.05 + (apartment.getDevelopmentCharges() +
                    apartment.getGeneratorCharges()+apartment.getLegalCharges()+apartment.getAdhocMaintenance())*0.18);
            apartment.setGstCalculatedFmtd(rf.formattedRupee(ft.format(apartment.getGstCalculated())));
            apartment.setRegistration((apartment.getMinimumCost() + (apartment.getFloorRise()*i) + apartment.getDevelopmentCharges() + apartment.getTowshipCorpus() +
                    apartment.getGeneratorCharges()+apartment.getLegalCharges()+apartment.getAdhocMaintenance()) * 0.06);
            apartment.setRegistrationFmtd(rf.formattedRupee(ft.format(apartment.getRegistration())));
            apartment.setTotalCost(apartment.getMinimumCost() + (apartment.getFloorRise()*i) + apartment.getDevelopmentCharges() + apartment.getTowshipCorpus() +
                    apartment.getGeneratorCharges()+apartment.getLegalCharges()+apartment.getAdhocMaintenance() + apartment.getRegistration() + apartment.getGstCalculated());
            apartment.setShortFall(((apartment.getTotalCost() - assetService.getAssetSummary().getTotalExpectedPrice()) > 0 ) ? (apartment.getTotalCost() - assetService.getAssetSummary().getTotalExpectedPrice()) : 0.00 );
            apartment.setShortFallFmtd(rf.formattedRupee(ft.format(apartment.getShortFall())));
            apartment.setTotalCostFmtd(rf.formattedRupee(ft.format(apartment.getTotalCost())));
            floorList.add(apartment);
            apartment = new Apartment();
            log.info("Floor is " + apartment.getFloor());
        }

        return floorList;
    }
    public List<FlatHeatMap> getFlatHeatMap(String projectName){

        String[] apartmentArray = {"Apt 09","Apt 03","Apt 07","Apt 08","Apt 04","Apt 02","Apt 06","Apt 10","Apt 01","Apt 05"};
        int noOfFloors = 18, countOfApartments = apartmentArray.length;
        List<FlatHeatMap> flatHeatMapList = new ArrayList<>();

        for (int i = 0; i < noOfFloors; i++){
            flatHeatMap.setFloorNumber(i+1);
            for(int j = 0; j < countOfApartments; j++){
                apartment = findByApartmentNameAndFloor(apartmentArray[j],i);
                flatHeatMap.addPriceList(apartment.getTotalCost());
                if (apartment.getShortFall() > 0) {
                    flatHeatMap.addWithinBudget("false");
                } else {
                    flatHeatMap.addWithinBudget("true");
                }
                flatHeatMap.addPriceListFmtd(apartment.getTotalCostFmtd());

            }

            flatHeatMapList.add(flatHeatMap);
            flatHeatMap = new FlatHeatMap();
        }

        return flatHeatMapList;
    }

    public Apartment findByApartmentNameAndFloor(String apartmentName, int floor){
        Optional<Apartment> apartmentSelected = apartmentsRepo.findByApartmentName(apartmentName);
        if (apartmentSelected.isEmpty()) {
            log.info("Retrieved Apartment as Null");
            return null;
        }
        apartment = new Apartment();
        int i = floor;
        apartment.setApartmentName(apartmentSelected.get().getApartmentName());
        apartment.setApartmentFormat(apartmentSelected.get().getApartmentFormat());
        apartment.setSuperBuiltUpArea(apartmentSelected.get().getSuperBuiltUpArea());
        apartment.setReraCarpetArea(apartmentSelected.get().getReraCarpetArea());
        apartment.setDeckArea(apartmentSelected.get().getDeckArea());
        apartment.setMinimumCost(apartmentSelected.get().getMinimumCost());
        apartment.setFloorRise(apartmentSelected.get().getFloorRise());
        apartment.setTowshipCorpus(apartmentSelected.get().getTowshipCorpus());
        apartment.setAdhocMaintenance(apartmentSelected.get().getAdhocMaintenance());
        apartment.setDevelopmentCharges(apartmentSelected.get().getDevelopmentCharges());
        apartment.setLegalCharges(apartmentSelected.get().getLegalCharges());
        apartment.setGeneratorCharges(apartmentSelected.get().getGeneratorCharges());

        apartment.setFloor(i+1);
        apartment.setMinimumCostFmtd(rf.formattedRupee(ft.format(apartment.getMinimumCost())));
        apartment.setFloorRiseFmtd(rf.formattedRupee(ft.format(apartment.getFloorRise())));
        apartment.setTowshipCorpusFmtd(rf.formattedRupee(ft.format(apartment.getTowshipCorpus())));
        apartment.setAdhocMaintenanceFmtd(rf.formattedRupee(ft.format(apartment.getAdhocMaintenance())));
        apartment.setDevelopmentChargesFmtd(rf.formattedRupee(ft.format(apartment.getDevelopmentCharges())));
        apartment.setLegalChargesFmtd(rf.formattedRupee(ft.format(apartment.getLegalCharges())));
        apartment.setGeneratorChargesFmtd(rf.formattedRupee(ft.format(apartment.getGeneratorCharges())));
        apartment.setGstCalculated((apartment.getMinimumCost() + (apartment.getFloorRise()*i))*.05 + (apartment.getDevelopmentCharges() +
                apartment.getGeneratorCharges()+apartment.getLegalCharges()+apartment.getAdhocMaintenance())*0.18);
        apartment.setGstCalculatedFmtd(rf.formattedRupee(ft.format(apartment.getGstCalculated())));
        apartment.setRegistration((apartment.getMinimumCost() + (apartment.getFloorRise()*i) + apartment.getDevelopmentCharges() + apartment.getTowshipCorpus() +
                apartment.getGeneratorCharges()+apartment.getLegalCharges()+apartment.getAdhocMaintenance()) * 0.06);
        apartment.setRegistrationFmtd(rf.formattedRupee(ft.format(apartment.getRegistration())));
        apartment.setTotalCost(apartment.getMinimumCost() + (apartment.getFloorRise()*i) + apartment.getDevelopmentCharges() + apartment.getTowshipCorpus() +
                apartment.getGeneratorCharges()+apartment.getLegalCharges()+apartment.getAdhocMaintenance() + apartment.getRegistration() + apartment.getGstCalculated());
        apartment.setShortFall(((apartment.getTotalCost() - assetService.getAssetSummary().getTotalExpectedPrice()) > 0 ) ? (apartment.getTotalCost() - assetService.getAssetSummary().getTotalExpectedPrice()) : 0.00 );
        apartment.setShortFallFmtd(rf.formattedRupee(ft.format(apartment.getShortFall())));
        apartment.setTotalCostFmtd(rf.formattedRupee(ft.format(apartment.getTotalCost())));

        log.info("Floor is " + apartment.getFloor());

        return apartment;
    }
}

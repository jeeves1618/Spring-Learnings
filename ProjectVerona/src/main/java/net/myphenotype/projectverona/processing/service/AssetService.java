package net.myphenotype.projectverona.processing.service;

import net.myphenotype.projectverona.processing.entity.Apartment;
import net.myphenotype.projectverona.processing.entity.Asset;
import net.myphenotype.projectverona.processing.entity.AssetSummary;
import net.myphenotype.projectverona.processing.repo.ApartmentsRepo;
import net.myphenotype.projectverona.processing.repo.AssetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class AssetService {

    @Autowired
    AssetRepo assetRepo;

    @Autowired
    RupeeFormatter rf;

    @Autowired
    AssetSummary assetSummary;

    DecimalFormat ft = new DecimalFormat("Rs ##,##,##0.00");

    public List<Asset> findAll(){
        List<Asset> assetList = assetRepo.findAll();
        double totalExpectedPrice = 0, totalActualPrice = 0;
        String latestDate = "0001-01-01";
        for(Asset asset : assetList) {
            asset.setExpectedPriceFmtd((rf.formattedRupee(ft.format(asset.getExpectedPrice()))));
            asset.setActualAmountFmtd((rf.formattedRupee(ft.format(asset.getActualAmount()))));
            totalExpectedPrice = totalExpectedPrice + asset.getExpectedPrice();
            totalActualPrice = totalActualPrice + asset.getActualAmount();
            asset.setTotalActualAmount(totalActualPrice);
            asset.setTotalActualAmountFmtd(rf.formattedRupee(ft.format(totalActualPrice)));
            asset.setTotalExpectedPrice(totalExpectedPrice);
            asset.setTotalExpectedPriceFmtd(rf.formattedRupee(ft.format(totalExpectedPrice)));
            if(asset.getDateAvailable().compareTo(latestDate) > 0) {
                asset.setLastDateAvailable(asset.getDateAvailable());
                latestDate = asset.getDateAvailable();
            }
        }
        return assetList;
    }

    public AssetSummary getAssetSummary() {
        List<Asset> assetList = assetRepo.findAll();
        double totalExpectedPrice = 0, totalActualPrice = 0;
        String latestDate = "0001-01-01";
        for(Asset asset : assetList) {
            asset.setExpectedPriceFmtd((rf.formattedRupee(ft.format(asset.getExpectedPrice()))));
            asset.setActualAmountFmtd((rf.formattedRupee(ft.format(asset.getActualAmount()))));
            totalExpectedPrice = totalExpectedPrice + asset.getExpectedPrice();
            totalActualPrice = totalActualPrice + asset.getActualAmount();
            asset.setTotalActualAmount(totalActualPrice);
            asset.setTotalActualAmountFmtd(rf.formattedRupee(ft.format(totalActualPrice)));
            asset.setTotalExpectedPrice(totalExpectedPrice);
            asset.setTotalExpectedPriceFmtd(rf.formattedRupee(ft.format(totalExpectedPrice)));
            if(asset.getDateAvailable().compareTo(latestDate) > 0) {
                asset.setLastDateAvailable(asset.getDateAvailable());
                latestDate = asset.getDateAvailable();
            }
        }
        assetSummary.setTotalActualAmount(totalActualPrice);
        assetSummary.setTotalExpectedPrice(totalExpectedPrice);
        assetSummary.setTotalActualAmountFmtd(rf.formattedRupee(ft.format(totalActualPrice)));
        assetSummary.setTotalExpectedPriceFmtd(rf.formattedRupee(ft.format(totalExpectedPrice)));
        assetSummary.setLastDateAvailable(latestDate);
        return assetSummary;
    }

    public void saveAll(List<Asset> assetList){

        assetRepo.saveAll(assetList);
    }

    public Asset getById(int theId){
        Asset temp = assetRepo.getById(theId);
        temp.setActualAmountFmtd(rf.formattedRupee(ft.format(temp.getActualAmount())));
        temp.setExpectedPriceFmtd(rf.formattedRupee(ft.format(temp.getExpectedPrice())));
        temp.setTotalActualAmountFmtd(rf.formattedRupee(ft.format(temp.getActualAmount())));
        temp.setTotalExpectedPriceFmtd(rf.formattedRupee(ft.format(temp.getExpectedPrice())));
        return temp;
    }

    public void save(Asset asset){
        assetRepo.save(asset);
    }

    public void deleteById(int theId){
        assetRepo.deleteById(theId);
    }
}

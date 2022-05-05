package net.myphenotype.projectverona.processing.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.extern.slf4j.Slf4j;
import net.myphenotype.projectverona.processing.entity.*;
import net.myphenotype.projectverona.processing.repo.AssetRepo;
import net.myphenotype.projectverona.processing.service.ApartmentService;
import net.myphenotype.projectverona.processing.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(path = "/apartment")
public class MainController {

    @Autowired
    Apartment apartment;

    @Autowired
    Asset asset;

    @Autowired
    ApartmentService apartmentServicer;

    @Autowired
    AssetService assetService;


    @GetMapping(path = "/list/flats")
    public String getApartmentList(Model model){
        List<Apartment> apartmentList = apartmentServicer.findAll();
        model.addAttribute("apartments",apartmentList);
        return "apartments";
    }

    @GetMapping(path = "/list/assets")
    public String getAssetsList(Model model){
        List<Asset> assetList = assetService.findAll();
        AssetSummary assetSummary = assetService.getAssetSummary();
        model.addAttribute("assets",assetList);
        model.addAttribute("assetSummary",assetSummary);
        return "assets";
    }

    @GetMapping(path = "/asset/showFormForUpdating")
    public String ShowFormForUpdate(@RequestParam("assetID") int theID, Model model){
        //Get the book using the ID from the Service (in turn from DAO and in turn from Table)
        Asset assetToBeUpdated = assetService.getById(theID);

        //Set the Customer as the Model Attribute to Prepopulate the Form
        model.addAttribute("asset",assetToBeUpdated);

        //Send the data to the right form
        return "assetForm";
    }

    @GetMapping(path = "/list/showFormForAdding")
    public String ShowFormForAdd(Model model){
        //Get the book using the ID from the Service (in turn from DAO and in turn from Table)


        //Set the Customer as the Model Attribute to Prepopulate the Form
        model.addAttribute("asset",asset);

        //Send the data to the right form
        return "assetForm";
    }

    @PostMapping(path = "/list/addUpdateAsset")
    public String AddAsset(@ModelAttribute("asset") Asset asset){
        assetService.save(asset);
        return "redirect:/apartment/list/assets";
    }

    @PostMapping(path = "/asset/addUpdateAsset")
    public String UpdateAsset(@ModelAttribute("asset") Asset asset){
        assetService.save(asset);
        return "redirect:/apartment/list/assets";
    }

    @GetMapping(path = "/asset/showFormForDeleting")
    public String ShowFormForDelete(@RequestParam("assetID") int theID, Model model){
        //Get the Chart Entry using the ID from the Service (in turn from DAO and in turn from Table)
        Asset assetToBeDeleted = assetService.getById(theID);

        //Set the Customer as the Model Attribute to Prepopulate the Form
        model.addAttribute("asset",assetToBeDeleted);
        log.info(assetToBeDeleted.toString());

        //Send the data to the right form
        return "deleteAssetForm";
    }

    @GetMapping(path = "/asset/delete")
    public String DeleteChartEntry(@RequestParam("assetID") int theID, Model model){
        //Delete the Chart Entry
        log.info("The ID of chart entry to be deleted : " + theID);
        assetService.deleteById(theID);
        return "redirect:/apartment/list/assets";
    }

    @GetMapping(path = "/list/plan")
    public String SearchBookByPartialName(@ModelAttribute("bookSearch") FlatSearch flatSearch, Model model){
        log.info("Search Pattern: " + flatSearch.toString());
        List<Apartment> apartmentList = apartmentServicer.findByApartmentName(flatSearch.getApartmentName());
        model.addAttribute("apartments",apartmentList);
        model.addAttribute("flatSearch",flatSearch);

        return "planner";
    }

    @GetMapping(path = "/flat/search")
    public String SearchFlatByName(@ModelAttribute("flatSearch") FlatSearch flatSearch, Model model){
        log.info("Search Pattern: " + flatSearch.toString());
        List<Apartment> apartmentList = apartmentServicer.findByApartmentName(flatSearch.getApartmentName());
        model.addAttribute("apartments",apartmentList);
        model.addAttribute("flatSearch",flatSearch);

        return "planner";
    }

    @GetMapping(path = "/list/heatmap")
    public String getHeatMap(Model model){
        log.info("Retrieving Heat Map");
        List<FlatHeatMap> flatHeatMapList = apartmentServicer.getFlatHeatMap("Verona");
        model.addAttribute("heatmaplist",flatHeatMapList);
        return "heatmap";
    }

}

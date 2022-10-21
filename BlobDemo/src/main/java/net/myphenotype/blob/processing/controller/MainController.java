package net.myphenotype.blob.processing.controller;

import net.myphenotype.blob.processing.domain.BlobResponseData;
import net.myphenotype.blob.processing.entity.BlobData;
import net.myphenotype.blob.processing.repo.BlobDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/blob")
public class MainController {

    @Autowired
    BlobDataRepo blobDataRepo;

    @Autowired
    BlobResponseData blobResponseData;

    @GetMapping(path = "/get")
    public List<BlobResponseData> getData() throws SQLException, IOException {
        List<BlobData> blobDataList = blobDataRepo.findAll();
        List<BlobResponseData> blobResponseDataList = new ArrayList<>();
        for (BlobData b: blobDataList) {
            System.out.println("The key is : " + b.getBlobKey());
            System.out.println("The description is : " + b.getBlobDescription());
            System.out.println("The blob data is : " + b.getBlobData());
            System.out.println("The converted blob data is : " + (new BufferedReader(new InputStreamReader(b.getBlobData().getBinaryStream()))).readLine());
            blobResponseData.setBlobKey(b.getBlobKey());
            blobResponseData.setBlobDescription(b.getBlobDescription());
            blobResponseData.setBlobData((new BufferedReader(new InputStreamReader(b.getBlobData().getBinaryStream()))).readLine());
            blobResponseDataList.add(blobResponseData);
            new BlobResponseData();
        }
        return blobResponseDataList;
    }
}

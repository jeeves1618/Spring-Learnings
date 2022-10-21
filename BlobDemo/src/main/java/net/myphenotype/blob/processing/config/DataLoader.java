package net.myphenotype.blob.processing.config;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import net.myphenotype.blob.processing.entity.BlobData;
import net.myphenotype.blob.processing.repo.BlobDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {
    private final Faker faker = new Faker();

    @Autowired
    BlobData blobData;

    @Autowired
    BlobDataRepo blobDataRepo;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<BlobData> blobDataList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection ("jdbc:h2:mem:testdb", "sa","password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i=0; i < 10; i++){
            blobData.setBlobDescription(faker.lordOfTheRings().character());
            String value = (faker.lorem().fixedString(200));
            byte[] buff = value.getBytes();
            try {
                Blob blob = connection.createBlob();
                blob.setBytes(1, buff);
                blobData.setBlobData(blob);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            blobDataList.add(blobData);
            System.out.println("blobData : " + blobData.toString());
            blobData = new BlobData();
        }
        blobDataRepo.saveAll(blobDataList);
    }
}

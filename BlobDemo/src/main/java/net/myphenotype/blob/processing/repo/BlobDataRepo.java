package net.myphenotype.blob.processing.repo;

import net.myphenotype.blob.processing.entity.BlobData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Blob;
import java.util.List;

public interface BlobDataRepo extends JpaRepository<BlobData, Integer> {
    @Query("select b.blobData from BlobData b")
    public List<Blob> findBlobOnly();

}

package net.myphenotype.projectverona.processing.repo;

import net.myphenotype.projectverona.processing.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepo extends JpaRepository<Asset,Integer> {
}

package net.myphenotype.projectverona.processing.repo;

import net.myphenotype.projectverona.processing.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApartmentsRepo extends JpaRepository<Apartment, Integer> {

    public Optional<Apartment> findByApartmentName(String apartmentName);
}

package net.myphenotype.Librarian.Repository;

import net.myphenotype.Librarian.Entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, String> {

}

package net.myphenotype.Spring.Security.repository;

import net.myphenotype.Spring.Security.entity.Notices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticesRepository extends JpaRepository<Notices, Long> {


}

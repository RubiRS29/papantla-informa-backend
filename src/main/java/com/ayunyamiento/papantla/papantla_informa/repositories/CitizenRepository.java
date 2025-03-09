package com.ayunyamiento.papantla.papantla_informa.repositories;

import com.ayunyamiento.papantla.papantla_informa.models.CitizenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<CitizenModel, Long > {
}

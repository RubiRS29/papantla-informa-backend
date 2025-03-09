package com.ayunyamiento.papantla.papantla_informa.repositories;

import com.ayunyamiento.papantla.papantla_informa.models.RequirementsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementsRepository extends JpaRepository<RequirementsModel, Long> {
}

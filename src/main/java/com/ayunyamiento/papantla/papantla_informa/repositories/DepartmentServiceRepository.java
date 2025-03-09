package com.ayunyamiento.papantla.papantla_informa.repositories;

import com.ayunyamiento.papantla.papantla_informa.models.DepartmentServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentServiceRepository extends JpaRepository<DepartmentServiceModel, Long> {
}

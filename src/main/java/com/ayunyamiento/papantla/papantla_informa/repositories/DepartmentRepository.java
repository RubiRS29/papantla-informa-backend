package com.ayunyamiento.papantla.papantla_informa.repositories;

import com.ayunyamiento.papantla.papantla_informa.models.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentModel, Long> {

    @Query("SELECT d FROM department d LEFT JOIN FETCH d.departmentServiceModel WHERE d.departmentIdentify = :id")
    Optional<DepartmentModel> findByIdWithServices(@Param("id") String id);


}

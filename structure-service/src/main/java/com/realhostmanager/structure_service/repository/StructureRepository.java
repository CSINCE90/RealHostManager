package com.realhostmanager.structure_service.repository;

import com.realhostmanager.structure_service.model.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StructureRepository extends JpaRepository<Structure, Long> {
    List<Structure> findByUserId(Long userId);
}

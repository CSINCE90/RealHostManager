package com.rhm.alloggiati.repository;

import com.rhm.alloggiati.model.Schedina;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SchedinaRepository extends JpaRepository<Schedina, Long> {

    List<Schedina> findByUserId(String userId); // oppure Long userId se il tipo è Long
}
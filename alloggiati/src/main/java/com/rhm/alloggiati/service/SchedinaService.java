package com.rhm.alloggiati.service;

import com.rhm.alloggiati.dto.SchedinaDto;
//import com.rhm.alloggiati.model.Schedina;
import com.rhm.alloggiati.repository.SchedinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedinaService {

    @Autowired
    private SchedinaRepository schedinaRepository;

    public List<SchedinaDto> getStoricoByUser(String userId) {
        return schedinaRepository.findByUserId(userId)
                .stream()
                .map(s -> new SchedinaDto(
                        s.getId(),
                        s.getNome(),
                        s.getCognome(),
                        s.getDataArrivo() != null ? s.getDataArrivo().toString() : null,
                        s.isEsito() // oppure .isEsito() se boolean
                ))
                .collect(Collectors.toList());
    }
}

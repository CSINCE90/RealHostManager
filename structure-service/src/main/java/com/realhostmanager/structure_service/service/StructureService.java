package com.realhostmanager.structure_service.service;

import com.realhostmanager.structure_service.model.Structure;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface StructureService {
    List<Structure> getAllStructures();
    List<Structure> getStructuresByUser(Long userId);
    Structure createStructure(Structure structure);
    void deleteStructure(Long id);
}

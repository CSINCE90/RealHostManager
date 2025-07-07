package com.realhostmanager.structure_service.service;

import com.realhostmanager.structure_service.model.Structure;
import com.realhostmanager.structure_service.repository.StructureRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StructureServiceImpl implements StructureService {

    private final StructureRepository structureRepository;

    @Override
    public List<Structure> getAllStructures() {
        return structureRepository.findAll();
    }

    @Override
    public List<Structure> getStructuresByUser(Long userId) {
        return structureRepository.findByUserId(userId);
    }

    @Override
    public Structure createStructure(Structure structure) {
        return structureRepository.save(structure);
    }

    @Override
    public void deleteStructure(Long id) {
        structureRepository.deleteById(id);
    }
}

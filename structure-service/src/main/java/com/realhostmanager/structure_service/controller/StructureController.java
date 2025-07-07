package com.realhostmanager.structure_service.controller;

import com.realhostmanager.structure_service.model.Structure;
import com.realhostmanager.structure_service.service.StructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/structures")
@RequiredArgsConstructor
public class StructureController {

    private final StructureService structureService;

    @GetMapping
    public ResponseEntity<List<Structure>> getAllStructures() {
        return ResponseEntity.ok(structureService.getAllStructures());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Structure>> getStructuresByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(structureService.getStructuresByUser(userId));
    }

    @PostMapping
    public ResponseEntity<Structure> createStructure(@RequestBody Structure structure) {
        return ResponseEntity.ok(structureService.createStructure(structure));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStructure(@PathVariable Long id) {
        structureService.deleteStructure(id);
        return ResponseEntity.noContent().build();
    }
}

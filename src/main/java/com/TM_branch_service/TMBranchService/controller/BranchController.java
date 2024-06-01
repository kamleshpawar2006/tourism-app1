package com.TM_branch_service.TMBranchService.controller;

import com.TM_branch_service.TMBranchService.dto.BranchDto;
import com.TM_branch_service.TMBranchService.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tourism/api/v1/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping("/add-places")
    public ResponseEntity<Map<String, Long>> addBranch(@Valid @RequestBody BranchDto branchDTO) {
        Long newBranch = branchService.addBranch(branchDTO);
        Map<String, Long> generatedId = new HashMap<>();
        generatedId.put("branchId", newBranch);
        return ResponseEntity.ok(generatedId);
    }

    @PutMapping("/update-tariff/{branchId}")
    public ResponseEntity<BranchDto> updateTariff(@PathVariable Long branchId, @RequestParam Integer newTariff) {
        BranchDto updatedBranch = branchService.updateTariff(branchId, newTariff);
        return ResponseEntity.ok(updatedBranch);
    }
}

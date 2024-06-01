package com.TM_branch_service.TMBranchService.service;

import com.TM_branch_service.TMBranchService.dto.BranchDto;

public interface BranchService {
    Long addBranch(BranchDto branchDTO);
    BranchDto updateTariff(Long branchId, Integer newTariff);
}

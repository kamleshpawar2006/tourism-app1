package com.TM_branch_service.TMBranchService.service;

import com.TM_branch_service.TMBranchService.dto.BranchDto;
import com.TM_branch_service.TMBranchService.entity.Branch;
import com.TM_branch_service.TMBranchService.enums.Place;
import com.TM_branch_service.TMBranchService.exception.CustomException;
import com.TM_branch_service.TMBranchService.repository.BranchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.EnumSet;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Long addBranch(BranchDto branchDTO) {
        EnumSet<Place> allowedPlaces = EnumSet.allOf(Place.class);
        Place place = Place.valueOf(branchDTO.getPlace().toUpperCase());
        if(allowedPlaces.contains(place) == false) {
            throw new CustomException("Invalid place. Must be one of: ANDAMAN, THAILAND, DUBAI, SINGAPORE, MALAYSIA");
        }
        Branch branch = dtoToEntity(branchDTO);
        Branch savedBranch = branchRepository.save(branch);
        entityManager.refresh(savedBranch);
        return savedBranch.getBranchId();
        // return entityToDto(savedBranch);
    }

    @Override
    @Transactional
    public BranchDto updateTariff(Long branchId, Integer newTariff) {

        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        if (branchOptional.isPresent()) {
            Branch branch = branchOptional.get();
            branch.setTariff(newTariff);
            Branch updatedBranch = branchRepository.save(branch);
            return entityToDto(updatedBranch);
        } else {
            throw new CustomException("Branch not found");
        }
    }

    private Branch dtoToEntity(BranchDto branchDTO) {
        Branch branch = new Branch();
        branch.setBranchName(branchDTO.getBranchName());
        branch.setPlace(branchDTO.getPlace());
        branch.setWebsite(branchDTO.getWebsite());
        branch.setContact(branchDTO.getContact());
        branch.setEmail(branchDTO.getEmail());
        branch.setTariff(branchDTO.getTariff());
        return branch;
    }

    private BranchDto entityToDto(Branch branch) {
        BranchDto branchDTO = new BranchDto();
        branchDTO.setBranchName(branch.getBranchName());
        branchDTO.setPlace(branch.getPlace());
        branchDTO.setWebsite(branch.getWebsite());
        branchDTO.setContact(branch.getContact());
        branchDTO.setEmail(branch.getEmail());
        branchDTO.setTariff(branch.getTariff());
        branchDTO.setDateAdded(branch.getDateAdded());
        branchDTO.setBranchId(branch.getBranchId());
        return branchDTO;
    }
}

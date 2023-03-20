package com.sda.practicalproject.service;

import com.sda.practicalproject.model.Pet;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;

import java.util.Date;
import java.util.List;

public interface PetService {
    void addPet(
            String race,
            Date dateOfBirth,
            boolean isVaccinated,
            String ownerName
    ) throws EntityUpdateFailedException;
    List<Pet> getAllPets();
}

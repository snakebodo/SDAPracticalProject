package com.sda.practicalproject.service;

import com.sda.practicalproject.model.Pet;
import com.sda.practicalproject.repository.PetRepository;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class PetServiceImpl implements PetService{
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void addPet(String race, Date dateOfBirth, boolean isVaccinated, String ownerName) throws EntityUpdateFailedException {
        if(race == null || race.isBlank() || race.isEmpty()){
            throw new IllegalArgumentException("Please insert a valid race");
        }
        if(dateOfBirth == null){
            throw new IllegalArgumentException("Please insert a date of birth");
        }
        if(dateOfBirth.after(Date.from(Instant.now().plus(Duration.ofDays(1))))){
            throw new IllegalArgumentException("Please insert a date from the past");
        }
        if(ownerName == null || ownerName.isEmpty() || ownerName.isBlank()){
            throw new IllegalArgumentException("Please insert a valid owner name");
        }
        Pet pet = new Pet(race, dateOfBirth, isVaccinated, ownerName);
        petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

}

package com.sda.practicalproject.service;

import com.sda.practicalproject.model.Pet;
import com.sda.practicalproject.repository.PetRepository;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.exception.EntityNotFoundException;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Pet> getPetById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Please insert an correct id, must be bigger than 0");
        }
        return petRepository.findById(id);
    }

    @Override
    public void updatePet(long id, boolean isVaccinated, String ownerName) throws EntityUpdateFailedException, EntityNotFoundException {
        if(id <= 0){
            throw new IllegalArgumentException("Please insert an id bigger than 0");
        }
        if(ownerName == null || ownerName.isBlank() || ownerName.isEmpty()){
            throw new IllegalArgumentException("Please enter a valid name");
        }
        Optional<Pet> optionalPet = petRepository.findById(id);
        if(optionalPet.isPresent()){
            Pet pet = optionalPet.get();
            pet.setVaccinated(isVaccinated);
            pet.setOwnerName(ownerName);
            petRepository.update(pet);
        }else{
            throw new EntityNotFoundException("Pet id not found " + id);
        }
    }

    @Override
    public void deletePetById(long id) throws EntityUpdateFailedException, EntityNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Please insert an correct id, must be bigger than 0");
        }
        Optional<Pet> optionalPet = petRepository.findById(id);
        if(optionalPet.isPresent()){
            petRepository.delete(optionalPet.get());
        }else{
            throw new EntityNotFoundException("Pet id not found");
        }
    }
}

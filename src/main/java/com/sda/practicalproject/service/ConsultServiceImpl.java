package com.sda.practicalproject.service;


import com.sda.practicalproject.model.Consult;
import com.sda.practicalproject.model.Pet;
import com.sda.practicalproject.model.Vet;
import com.sda.practicalproject.repository.ConsultRepository;
import com.sda.practicalproject.repository.PetRepository;
import com.sda.practicalproject.repository.VetRepository;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.exception.EntityNotFoundException;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

public class ConsultServiceImpl implements ConsultService {
    private final VetRepository vetRepository;
    private final PetRepository petRepository;
    private final ConsultRepository consultRepository;

    public ConsultServiceImpl(VetRepository vetRepository, PetRepository petRepository, ConsultRepository consultRepository) {
        this.vetRepository = vetRepository;
        this.petRepository = petRepository;
        this.consultRepository = consultRepository;
    }

    @Override
    public void createConsult(long vetId, long petId, Date date, String description) throws EntityNotFoundException, EntityUpdateFailedException {
        if (vetId <= 0) {
            throw new IllegalArgumentException("Invalid vet id");
        }
        if (petId <= 0) {
            throw new IllegalArgumentException("Invalid pet id");
        }
        if (date == null) {
            throw new IllegalArgumentException("Invalid date , null value found");
        }
        if (date.before(Date.from(Instant.now().minus(Duration.ofDays(1))))) {
            throw new IllegalArgumentException("Invalid date, date from future found");
        }
        if (description == null || description.isEmpty() || description.isBlank()) {
            throw new IllegalArgumentException("Invalid description, value must not be empty or blank");
        }
        Optional<Vet> optionalVet = vetRepository.findById(vetId);
        if (optionalVet.isEmpty()){
            throw new EntityNotFoundException("Vet id was not found " + vetId);
        }
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()){
            throw new EntityNotFoundException("Pet id was not found " + petId);
        }
        Consult consult = new Consult(date, description);
        consult.setVet(optionalVet.get());
        consult.setPet(optionalPet.get());
        consultRepository.save(consult);
    }

}

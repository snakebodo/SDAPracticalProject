package com.sda.practicalproject.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "race")
    private String race;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "is_vaccinated")
    private boolean isVaccinated;
    @Column(name = "owner_name")
    private String ownerName;
    @OneToMany(mappedBy = "pet")
    private List<Consult> consults;

    public Pet() {
    }

    public Pet(String race, Date dateOfBirth, boolean isVaccinated, String ownerName) {
        this.race = race;
        this.dateOfBirth = dateOfBirth;
        this.isVaccinated = isVaccinated;
        this.ownerName = ownerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<Consult> getConsults() {
        return consults;
    }

    public void setConsults(List<Consult> consults) {
        this.consults = consults;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", race='" + race + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", isVaccinated=" + isVaccinated +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}

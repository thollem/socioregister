package com.artsgard.socioregister.repository;

import com.artsgard.socioregister.model.CountryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryModel, Long> {
    public CountryModel findByCode(String code);   
    public CountryModel findByName(String name); 
}

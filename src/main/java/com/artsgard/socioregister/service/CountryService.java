package com.artsgard.socioregister.service;

import com.artsgard.socioregister.model.CountryModel;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CountryService {
    List<CountryModel> getAllCountries();  
}

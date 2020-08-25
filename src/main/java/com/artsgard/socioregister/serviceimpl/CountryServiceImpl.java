package com.artsgard.socioregister.serviceimpl;

import com.artsgard.socioregister.service.CountryService;
import com.artsgard.socioregister.model.CountryModel;
import com.artsgard.socioregister.repository.CountryRepository;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class CountryServiceImpl implements CountryService {

    org.slf4j.Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);
    
    @Autowired
    CountryRepository countryRepo;

    @Override
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public List<CountryModel> getAllCountries()  {
        return countryRepo.findAll();
    }       
}

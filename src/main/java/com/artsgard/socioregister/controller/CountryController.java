package com.artsgard.socioregister.controller;

import com.artsgard.socioregister.model.CountryModel;
import com.artsgard.socioregister.model.LanguageModel;
import com.artsgard.socioregister.service.CountryService;
import com.artsgard.socioregister.service.LanguageService;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CountryController {
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CountryModel>> findCountries() {
        return new ResponseEntity<>(countryService.getAllCountries(), HttpStatus.OK);
    }
}
package com.artsgard.socioregister.serviceimpl;

import com.artsgard.socioregister.service.LanguageService;
import com.artsgard.socioregister.model.LanguageModel;
import com.artsgard.socioregister.repository.LanguageRepository;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class LanguageServiceImpl implements LanguageService {
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);
    
    @Autowired
    LanguageRepository languageRepository;

    @Override
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public List<LanguageModel> getAllLanguages()  {
        return languageRepository.findAll();
    }       
}

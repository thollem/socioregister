package com.artsgard.socioregister.service;

import com.artsgard.socioregister.model.LanguageModel;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface LanguageService {
    List<LanguageModel> getAllLanguages();  
}

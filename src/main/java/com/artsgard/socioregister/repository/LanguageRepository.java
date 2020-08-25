package com.artsgard.socioregister.repository;

import com.artsgard.socioregister.model.LanguageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LanguageModel, Long> {
    public LanguageModel findByCode(String code);
    public LanguageModel findByName(String name);
}

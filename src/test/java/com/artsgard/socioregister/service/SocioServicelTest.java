package com.artsgard.socioregister.service;

import com.artsgard.socioregister.DTO.FilterDTO;
import com.artsgard.socioregister.model.LanguageModel;
import com.artsgard.socioregister.model.SocioModel;
import com.artsgard.socioregister.repository.LanguageRepository;
import com.artsgard.socioregister.repository.SocioRepository;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource({"classpath:application-test.properties"})
//@Sql({"/data-h2.sql"})
@DataJpaTest
public class SocioServicelTest {

    @Autowired
    private SocioRepository socioRepo;
    
    @Autowired
    private LanguageRepository languageRepo;
 
    @Test
    public void findAllSociosTest() {
        List<SocioModel> socios = socioRepo.findAll();
        assertThat(socios).isNotEmpty();
        assertThat(socios).hasSize(3);
    }

    @Test
    public void findSocioByIdTest() {
        SocioModel sc = socioRepo.getOne(1L);
        assertThat(sc).isNotNull();
    }

    @Test
    public void findSocioByUsernameTest() {
    List<SocioModel> socios = socioRepo.findAll();
        Optional<SocioModel> optSocio = socioRepo.findByUsername("js");
        assertThat(optSocio.get().getId()).isNotNull();
        assertThat(optSocio.get().getUsername()).isEqualTo("js");
    }

    @Test
    public void saveSocioTest() {
        LanguageModel lang1 = languageRepo.findByCode("NL");
        LanguageModel lang2 = languageRepo.findByCode("ES");
        List<LanguageModel> langs = new ArrayList();
        langs.add(lang1);
        langs.add(lang2);
        SocioModel socio = new SocioModel(null, "username", "secret", "first name", "last name", "username@gmail.com", true, langs, null);
        socio.setRegisterDate(new Timestamp(System.currentTimeMillis()));
        socio.setLastCheckinDate(new Timestamp(System.currentTimeMillis()));
        socioRepo.save(socio);
        assertThat(socio.getId()).isNotNull();
        assertThat(socio.getUsername()).isEqualTo("username");
    }

    @Test
    public void updateSocioTest() {
        Optional<SocioModel> optSocio = socioRepo.findByUsername("js");
        SocioModel updateSocio = optSocio.get();
        updateSocio.setUsername("js edited");
        updateSocio.setActive(false);
        SocioModel updatedSocioFromDB = socioRepo.save(updateSocio);
        assertThat(optSocio.get()).isEqualTo(updatedSocioFromDB);
    }

    @Test
    public void deleteSocioByIdTest() {
        LanguageModel lang1 = languageRepo.findByCode("NL");
        LanguageModel lang2 = languageRepo.findByCode("ES");
        List<LanguageModel> langs = new ArrayList();
        langs.add(lang1);
        langs.add(lang2);
        SocioModel socio = new SocioModel(null, "username to delete", "secret to delete", "first name to delete", "last name to delete", "todelete@gmail.com", true, langs, null);
        socio.setRegisterDate(new Timestamp(System.currentTimeMillis()));
        socio.setLastCheckinDate(new Timestamp(System.currentTimeMillis()));
        socioRepo.save(socio);
        Long id = socio.getId();
        socioRepo.deleteById(id);
        Optional<SocioModel> deletedSocio = socioRepo.findById(id);
        assertThat(deletedSocio.isPresent()).isFalse();
    }

    @Test
    public void hasSocioByIdTest() {
       Optional<SocioModel> optSocio = socioRepo.findByUsername("js");
       SocioModel socio = optSocio.get();
       assertThat(socioRepo.existsById(socio.getId())).isTrue();
    }

    @Test
    public void isSocioActiveByIdTest() {
        Optional<SocioModel> optSocio = socioRepo.findByUsername("js");
        SocioModel socio = optSocio.get();
        socioRepo.save(socio);
        socio.setActive(Boolean.FALSE);
        assertThat(socio.getActive()).isFalse();
    }
    
    @Test
    public void testGetSociosBySortedPageByCountry() {
        FilterDTO filter = new FilterDTO();
        filter.setCountry("NL");
        List<SocioModel> socios = socioRepo.getSociosBySortedPageByCountry(10, 0, filter.getCountry());
        assertThat(socios).isNotEmpty();
        assertThat(socios).hasSize(2);
    }
    
    @Test
    public void testGetSociosBySortedPageByLanguage() {
        FilterDTO filter = new FilterDTO();
        filter.setLanguage("FR");
        List<SocioModel> socios = socioRepo.getSociosBySortedPageByLanguage(3, 0, filter.getLanguage());
        assertThat(socios).isNotEmpty();
        assertThat(socios).hasSize(1);
    }
    
    @Test
    public void testGetSociosBySortedPage() {
        List<SocioModel> socios = socioRepo.getSociosBySortedPage(2, 0);
        assertThat(socios).isNotEmpty();
        assertThat(socios).hasSize(2);
    }
}

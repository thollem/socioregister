package com.artsgard.socioregister.service;

import com.artsgard.socioregister.model.AddressModel;
import com.artsgard.socioregister.model.AddressModel.AddressType;
import com.artsgard.socioregister.model.CountryModel;
import com.artsgard.socioregister.model.SocioModel;
import com.artsgard.socioregister.repository.AddressRepository;
import com.artsgard.socioregister.repository.CountryRepository;
import com.artsgard.socioregister.repository.LanguageRepository;
import com.artsgard.socioregister.repository.SocioRepository;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//@Sql({"/data-test.sql"})
@DataJpaTest
public class AddressServiceTest {

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private SocioRepository socioRepo;

    @Autowired
    private CountryRepository countryRepo;

    @Autowired
    private LanguageRepository languageRepo;

    @Test
    public void testFindAllAddresses() {
        List<AddressModel> addresses = addressRepo.findAll();
        assertThat(addresses).isNotEmpty();
        assertThat(addresses).isNotEmpty().hasSize(2);
    }

    @Test
    public void testFindAddressById() {
        Optional<AddressModel> address = addressRepo.findById(1L);
        assertThat(address.get().getStreet()).isEqualTo("Edmondstraat 36");
    }

    @Test
    public void testFindAddressBySocioId() {
        List<AddressModel> addresses = addressRepo.findAddressesBySocioId(1L);
        assertThat(addresses).isNotEmpty().hasSize(2);
    }

    @Test
    public void testSaveAddress() {
        SocioModel socio = socioRepo.getOne(1L);
        CountryModel country = countryRepo.findByCode("NL");
        AddressModel address = new AddressModel(null, "Wagner street 4", "München", "5426", "Bauern", country, "soem description", AddressType.HOME, socio);
        addressRepo.save(address);
        assertThat(address.getId()).isNotNull();
    }

    @Test
    public void testUpdateAddress() {
        AddressModel updateAddress = addressRepo.getOne(1L);
        updateAddress.setPostalcode("edited postal code");
        updateAddress.setStreet("edit street");
        AddressModel updatedAddressFromDB = addressRepo.save(updateAddress);
        assertThat(updateAddress).isEqualTo(updatedAddressFromDB);
    }

    @Test
    public void testDeleteAddressById() {
        SocioModel socio = socioRepo.getOne(1L);
        CountryModel country = countryRepo.findByCode("NL");
        AddressModel address = new AddressModel(null, "Wagner street 4", "München", "5426", "Bauern", country, "some description", AddressType.HOME, socio);
        addressRepo.save(address);
        Long id = address.getId();
        addressRepo.deleteById(address.getId());
        assertThat(addressRepo.existsById(id)).isFalse();
    }
}

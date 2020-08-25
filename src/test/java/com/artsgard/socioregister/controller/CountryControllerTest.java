package com.artsgard.socioregister.controller;

import com.artsgard.socioregister.controller.CountryController;
import com.artsgard.socioregister.model.CountryModel;
import com.artsgard.socioregister.serviceimpl.CountryServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(CountryController.class)
public class CountryControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryServiceImpl countryServiceImpl;

    @InjectMocks
    CountryController countryController;
    
    @Autowired
    private JacksonTester<CountryModel> jsonCountry;
    
    @Autowired
    private JacksonTester<List<CountryModel>> jsonCountries;
    
    private List<CountryModel> countries;

    @BeforeEach
    public void setup() {
        CountryModel country1 = new CountryModel(1L, "Spain", "ES");
        CountryModel country2 = new CountryModel(2L, "Netherlands", "NL");
        countries = new ArrayList();
        countries.add(country1);
        countries.add(country2);
    }

    @Test
    public void testFindAllCountries() throws Exception {

        given(countryServiceImpl.getAllCountries()).willReturn(countries);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/country"))
                .andExpect((content()
                .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonCountries.write(countries).getJson()
        );
    }
}
package com.artsgard.socioregister.controller;

import com.artsgard.socioregister.controller.LanguageController;
import com.artsgard.socioregister.model.LanguageModel;
import com.artsgard.socioregister.serviceimpl.LanguageServiceImpl;
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
@WebMvcTest(LanguageController.class)
public class LanguageControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LanguageServiceImpl languageServiceImpl;

    @InjectMocks
    LanguageController languageController;
    
    @Autowired
    private JacksonTester<List<LanguageModel>> jsonLanguages;
    
    private List<LanguageModel> languages;

    @BeforeEach
    public void setup() {
        LanguageModel language1 = new LanguageModel(1L, "Spanish", "ES");
        LanguageModel language2 = new LanguageModel(2L, "Dutch", "NL");
        languages = new ArrayList();
        languages.add(language1);
        languages.add(language2);
    }

    @Test
    public void testFindAllLanguages() throws Exception {

        given(languageServiceImpl.getAllLanguages()).willReturn(languages);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/language"))
                .andExpect((content()
                .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonLanguages.write(languages).getJson()
        );
    }
}
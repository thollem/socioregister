package com.artsgard.socioregister.controller;

import com.artsgard.socioregister.DTO.SocioDTO;
import com.artsgard.socioregister.exception.ResourceNotFoundException;
import com.artsgard.socioregister.mock.SocioMock;
import com.artsgard.socioregister.model.LanguageModel;
import com.artsgard.socioregister.service.MapperService;
import com.artsgard.socioregister.serviceimpl.MapperServiceImpl;
import com.artsgard.socioregister.serviceimpl.SocioServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author artsgard
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureJsonTesters
public class SocioControllerServerTest {

    @MockBean
    private SocioServiceImpl socioService;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private JacksonTester<SocioDTO> jsonSocio;
    
    @Autowired
    private JacksonTester<List<SocioDTO>> jsonSocios;
    
    private SocioDTO socio;
    private List<SocioDTO> socios;

    private final MapperService mapperService = new MapperServiceImpl();

    @BeforeEach
    public void setup() {
        socio = mapperService.mapSocioModelToSocioDTO(SocioMock.generateSocio());
        socios = new ArrayList();
        SocioMock.generateSocios().forEach((sci) -> {
            socios.add(mapperService.mapSocioModelToSocioDTO(sci));
        });
    }

    @Test
    public void testFindAllSocios() throws Exception {
        given(socioService.findAllSocios())
                .willReturn(socios);

        ResponseEntity<SocioDTO[]> socioResponse = restTemplate
                .getForEntity("/socio", SocioDTO[].class);

        assertThat(socioResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(socioResponse.getBody().equals(socios));

    }

    @Test
    public void testFindSocioById() throws Exception {
        given(socioService.findSocioById(1L)).willReturn(socio);

        ResponseEntity<SocioDTO> socioResponse = restTemplate.getForEntity("/socio/1", SocioDTO.class);

        assertThat(socioResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(socioResponse.getBody().equals(socio));
    }

    @Test
    public void testFindSocioByUsername() throws Exception {
        given(socioService.findSocioByUsername("js")).willReturn(socio);

        ResponseEntity<SocioDTO> socioResponse = restTemplate.getForEntity("/socio/username/js", SocioDTO.class);

        assertThat(socioResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(socioResponse.getBody().equals(socio));
    }

    @Test
    public void testSaveSocio() throws Exception {
        socio.setSocioLanguages(new <LanguageModel>ArrayList());
        ResponseEntity<SocioDTO> socioResponse = restTemplate.postForEntity("/socio", socio, SocioDTO.class);

        assertThat(socioResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void testUpdatesocio() throws Exception {
        socio.setSocioLanguages(new <LanguageModel>ArrayList());
        given(socioService.updateSocio(socio)).willReturn(socio);
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonSocio.write(socio).getJson(), headers);
        
        ResponseEntity<String> socioResponse = restTemplate.exchange("/socio/", HttpMethod.PUT, entity, String.class);
        
        assertThat(socioResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(socioResponse.getBody().equals(socio));
    }
    
    @Test
    public void testDeleteSocio() throws Exception {
        try {
             restTemplate.delete("/socio/1", SocioDTO.class);
        } catch (HttpClientErrorException ex) {
            String message = ex.getResponseBodyAsString();
            throw new ResourceNotFoundException(message);
        }
    }
    
    //@Test
    public void testGetSociosBySortedPage() {
        
    }
}

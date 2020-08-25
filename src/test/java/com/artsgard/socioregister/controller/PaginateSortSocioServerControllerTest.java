package com.artsgard.socioregister.controller;

import com.artsgard.socioregister.DTO.FilterDTO;
import com.artsgard.socioregister.DTO.SocioDTO;
import com.artsgard.socioregister.mock.SocioMock;
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
import static org.mockito.Mockito.mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author artsgard
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureJsonTesters
public class PaginateSortSocioServerControllerTest {

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
    public void findAllSocioSortedByPage_default() {
        FilterDTO filter = mock(FilterDTO.class);
        given(socioService.getSociosBySortedPage(3, 0, null)).willReturn(socios);

        ResponseEntity<SocioDTO[]> socioResponse = restTemplate
                .getForEntity("/paginateSocio", SocioDTO[].class);

        assertThat(socioResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(socioResponse.getBody().equals(socios));
    }
    
    @Test
    public void findAllSocioSortedByPage_with_pag_params() {
        FilterDTO filter = mock(FilterDTO.class);
        given(socioService.getSociosBySortedPage(3, 0, null)).willReturn(socios);

        ResponseEntity<SocioDTO[]> socioResponse = restTemplate
                .getForEntity("/paginateSocio?rows=3&offset=0", SocioDTO[].class);

        assertThat(socioResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(socioResponse.getBody().equals(socios));
    }
    
    @Test
    public void findAllSocioSortedByPage_filter() {
        FilterDTO filter = mock(FilterDTO.class);
        given(socioService.getSociosBySortedPage(3, 0, filter)).willReturn(socios);

        ResponseEntity<SocioDTO[]> socioResponse = restTemplate
                .getForEntity("/paginateSocio?rows=3&offset=0", SocioDTO[].class);

        assertThat(socioResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(socioResponse.getBody().equals(socios));
       
    }
}

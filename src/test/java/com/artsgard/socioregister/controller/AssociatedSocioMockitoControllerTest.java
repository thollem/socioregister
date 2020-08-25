package com.artsgard.socioregister.controller;

import com.artsgard.socioregister.controller.AssociatedSocioController;
import com.artsgard.socioregister.service.MapperService;
import com.artsgard.socioregister.serviceimpl.AssociatedSocioServiceImpl;
import com.artsgard.socioregister.serviceimpl.MapperServiceImpl;
import com.artsgard.socioregister.serviceimpl.SocioServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class AssociatedSocioMockitoControllerTest  {

    private MockMvc mockMvc;

    @Mock
    private SocioServiceImpl socioService;
    
    @Mock
    private AssociatedSocioServiceImpl associatedSocioService;

    @InjectMocks
    AssociatedSocioController associatedSocioController;

    private final MapperService mapperService = new MapperServiceImpl();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(associatedSocioController).build();

    }
    
    @Test
    public void testRegisterAssociatedSocio() throws Exception {
      
        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.post("/associatedSocio/{socioId}/{associatedSocioId}", "1", "2"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
      
    }
    
    @Test
    public void testUpdateStateAssociatedSocio() throws Exception {
         MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.put("/associatedSocio/{socioId}/{associatedSocioId}/{state}", "1", "2", true))
                .andReturn().getResponse();

         assertThat(response.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());
    }
    
    @Test
    public void deleteAssociatedSocio() throws Exception {
         MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.delete("/associatedSocio/{socioId}/{associatedSocioId}", "1", "2"))
                .andReturn().getResponse();

         assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}

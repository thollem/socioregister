package com.artsgard.socioregister.controller;

import com.artsgard.socioregister.controller.AddressController;
import com.artsgard.socioregister.DTO.AddressDTO;
import com.artsgard.socioregister.DTO.SocioDTO;
import com.artsgard.socioregister.mock.SocioMock;
import com.artsgard.socioregister.model.AddressModel.AddressType;
import com.artsgard.socioregister.model.CountryModel;
import com.artsgard.socioregister.service.MapperService;
import com.artsgard.socioregister.serviceimpl.AddressServiceImpl;
import com.artsgard.socioregister.serviceimpl.MapperServiceImpl;
import com.artsgard.socioregister.serviceimpl.SocioServiceImpl;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressServiceImpl addressService;
    
    @MockBean
    private SocioServiceImpl socioService;

    @InjectMocks
    AddressController addressController;

    @Autowired
    private JacksonTester<AddressDTO> jsonAddress;

    @Autowired
    private JacksonTester<List<AddressDTO>> jsonAddresses;

    private List<AddressDTO> roles;
    private AddressDTO address1;
    private AddressDTO address2;
    private List<AddressDTO> addresses;
    private SocioDTO socio;
    
    private final MapperService mapperService = new MapperServiceImpl();
    
    @BeforeEach
    public void setup() {
        socio = mapperService.mapSocioModelToSocioDTO(SocioMock.generateSocio());
        address1 = new AddressDTO(1L, "Vermeerstraat 23", "Eindhoven", "6534II", "Brabant", new CountryModel(), "ok place", AddressType.HOME, 1L);
        address2 = new AddressDTO(1L, "Helmond 36", "Amsterdam", "3454JK", "Noord Holland", new CountryModel(), "Nice Place to live", AddressType.HOME, 1L);
       
        addresses = new ArrayList();
        addresses.add(address1);
        addresses.add(address2);
    }
    
    @Test
    public void testFindAllAddresses() throws Exception {

        given(addressService.findAllAddresses()).willReturn(addresses);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/address"))
                .andExpect((content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonAddresses.write(addresses).getJson()
        );
    }
      
    @Test
    public void testFindAddressById() throws Exception {
       given(addressService.findOneAddressById(1L)).willReturn(address1);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/address/1"))
                .andExpect((content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonAddress.write(address1).getJson()
        );
    }
    
    @Test
    public void testFindAddressBySocioId() throws Exception {
         given(addressService.findAddressesBySocioId(1L)).willReturn(addresses);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/address/socioId/1"))
                .andExpect((content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonAddresses.write(addresses).getJson()
        );
    }
    
   @Test
    public void testSaveAddress() throws Exception {
        AddressDTO address = new AddressDTO(null, "JSBachstraat 16", "Hilversum", "6576HW", "Noord Holland", new CountryModel(), "ok place", AddressType.HOME, 1L);
        given(addressService.saveAddress(address)).willReturn(address);
        
        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.post("/address/")
                //.andExpect(MockMvcResultMatchers.status().isOk())
                .contentType(MediaType.APPLICATION_JSON)
            .content(jsonAddress.write(address)
                .getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonAddress.write(address).getJson());
    }

    @Test
    public void testUpdateAddress() throws Exception {
        AddressDTO address = new AddressDTO(1L, "JSBachstraat 16", "Hilversum", "6576HW", "Noord Holland", new CountryModel(), "ok place", AddressType.HOME, 1L);
        given(addressService.updateAddress(address)).willReturn(address);
        
        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.put("/address/")
                .contentType(MediaType.APPLICATION_JSON)
            .content(jsonAddress.write(address)
                .getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonAddress.write(address).getJson());
    }

    @Test
    public void testDeleteAddress() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/address/{id}", "1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
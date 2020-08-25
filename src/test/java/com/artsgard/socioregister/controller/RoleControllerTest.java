package com.artsgard.socioregister.controller;

import com.artsgard.socioregister.controller.RoleController;
import com.artsgard.socioregister.mock.SocioMock;
import com.artsgard.socioregister.model.RoleModel;
import com.artsgard.socioregister.model.SocioModel;
import com.artsgard.socioregister.serviceimpl.RoleServiceImpl;
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
@WebMvcTest(RoleController.class)
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleServiceImpl roleServiceImpl;

    @InjectMocks
    RoleController roleController;

    @Autowired
    private JacksonTester<List<RoleModel>> jsonRoles;

    private List<RoleModel> roles;
    private RoleModel roleAdmin;
    private RoleModel roleSocio;
    private SocioModel socio;
    private List<SocioModel> socios;
    
    @BeforeEach
    public void setup() {
        roleAdmin = new RoleModel(1L, "ROLE_ADMIN");
        roleSocio = new RoleModel(2L, "ROLE_SOCIO");
        roles = new ArrayList();
        roles.add(roleAdmin);
        roles.add(roleSocio);
        socio = SocioMock.generateSocio();
        socios = SocioMock.generateSocios();
    }

    @Test
    public void testFindAllRoles() throws Exception {

        given(roleServiceImpl.getAllRoles()).willReturn(roles);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/role"))
                .andExpect((content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonRoles.write(roles).getJson()
        );
    }
    @Test
    public void testFindRolesBySocioId() throws Exception {

        given(roleServiceImpl.findRolesBySocioId(1L)).willReturn(roles);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/role/1"))
                .andExpect((content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonRoles.write(roles).getJson()
        );
    }

    @Test
    public void testFindRolesBySocioUsername() throws Exception {

        given(roleServiceImpl.findRolesBySocioUsername("wd")).willReturn(roles);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/role/username/wd"))
                .andExpect((content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonRoles.write(roles).getJson()
        );
    }

}

package com.artsgard.socioregister.DTO;

import com.artsgard.socioregister.model.LanguageModel;
import com.artsgard.socioregister.model.SocioAssociatedSocio;
import com.artsgard.socioregister.model.SocioAssociatedSocio.AssociatedSocioState;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author WillemDragstra
 * 
 * {
        "username": "pbxx",
        "password": "secretxx",
        "firstName": "Pierrexxx",
        "lastName": "Boulezxxx",
        "email": "boulez@gmailxx.com",
        "active": true,
        "socioLanguages": [
                {"id": "1"},
                {"id": "2"}
        ] 
    }
 * 
 */
@Data
@NoArgsConstructor
public class SocioDTO implements Serializable {
    
    public SocioDTO(Long id, String username, String password, String firstName, String lastName, String email, Boolean active, List<LanguageModel> socioLanguages, List<AddressDTO> socioAddresses) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.socioLanguages = socioLanguages;
        this.socioAddresses = socioAddresses;
    }
    
    private Long id;
    
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 20)
    private String username;
    
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 40)
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    @NotEmpty
    @NotNull
    @Email
    private String email;
    
    private Timestamp registerDate;
    
    private Timestamp lastCheckinDate;
   
    @NotNull
    private Boolean active;
    
    private List<SocioAssociatedSocio> associatedSocios;
    
    private List<AddressDTO> socioAddresses;
    
    @NotNull
    private List<LanguageModel> socioLanguages;
}

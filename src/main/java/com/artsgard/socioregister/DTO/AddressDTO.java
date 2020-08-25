package com.artsgard.socioregister.DTO;

import com.artsgard.socioregister.model.AddressModel.AddressType;
import com.artsgard.socioregister.model.CountryModel;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  {  
    "street":"KSG-straat",
    "city":"Heerlen",
    "province":"Limburg",
    "postalcode":"6413CZ",
    "description":"Nice Place to live",
    "country": {"id": 1},
    "socioId":1,
    "addressType": "HOME"
  }
*/
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class AddressDTO {
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    @NotEmpty
    private String street;

    @NotNull
    @Size(min = 2, max = 26)
    @NotEmpty
    private String city;
    
    //@PostCodeConstraint
    private String postalcode;
    
    private String province;
    
    @NotNull
    private CountryModel country;
    
    private String description;
    
    private AddressType addressType;
    
    @NotNull
    private Long socioId;

    public AddressDTO(Long id, String street, String city, String postalcode, String province, CountryModel country, String description, AddressType addressType, Long socioId) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.postalcode = postalcode;
        this.province = province;
        this.country = country;
        this.description = description;
        this.addressType = addressType;
        this.socioId = socioId;
    }
}

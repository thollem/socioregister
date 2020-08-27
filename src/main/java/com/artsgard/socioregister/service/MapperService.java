package com.artsgard.socioregister.service;

import com.artsgard.socioregister.DTO.AddressDTO;
import com.artsgard.socioregister.DTO.SocioDTO;
import com.artsgard.socioregister.model.AddressModel;
import com.artsgard.socioregister.model.SocioModel;
import org.springframework.stereotype.Service;

@Service
public interface MapperService  {
   SocioModel mapSocioDTOToSocioModel(SocioDTO dto);
   SocioDTO mapSocioModelToSocioDTO(SocioModel ent);
   AddressModel mapAddressDTOToAddressModel(AddressDTO dto);
   AddressDTO mapAddressModelToAddressDTO(AddressModel ent);
}
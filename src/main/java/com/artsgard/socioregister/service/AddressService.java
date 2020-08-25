package com.artsgard.socioregister.service;

import com.artsgard.socioregister.DTO.AddressDTO;
import com.artsgard.socioregister.exception.ResourceNotFoundException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AddressService  {
    List<AddressDTO> findAllAddresses(); 
    AddressDTO findOneAddressById(Long id) throws ResourceNotFoundException;
    List<AddressDTO> findAddressesBySocioId(Long id);
    AddressDTO saveAddress(AddressDTO address) throws ResourceNotFoundException;
    AddressDTO updateAddress(AddressDTO address);
    void deleteAddressById(Long id) throws ResourceNotFoundException;
    boolean hasAddress(Long id) throws ResourceNotFoundException;
}
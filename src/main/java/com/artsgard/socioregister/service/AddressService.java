package com.artsgard.socioregister.service;

import com.artsgard.socioregister.DTO.AddressDTO;
import com.artsgard.socioregister.exception.ResourceNotFoundException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AddressService  {
    List<AddressDTO> findAllAddresses() throws ResourceNotFoundException;
    AddressDTO findOneAddressById(Long id) throws ResourceNotFoundException;
    List<AddressDTO> findAddressesBySocioId(Long id) throws ResourceNotFoundException;
    AddressDTO saveAddress(AddressDTO address);
    AddressDTO updateAddress(AddressDTO address) throws ResourceNotFoundException;
    void deleteAddressById(Long id) throws ResourceNotFoundException;
    boolean hasAddress(Long id);
}
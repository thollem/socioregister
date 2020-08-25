package com.artsgard.socioregister.serviceimpl;

import com.artsgard.socioregister.service.AddressService;
import com.artsgard.socioregister.service.MapperService;
import com.artsgard.socioregister.DTO.AddressDTO;
import com.artsgard.socioregister.exception.ResourceNotFoundException;
import com.artsgard.socioregister.model.AddressModel;
import com.artsgard.socioregister.model.SocioModel;
import com.artsgard.socioregister.repository.AddressRepository;
import com.artsgard.socioregister.repository.SocioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private MapperService mapperService;
    
    @Autowired
    private SocioRepository socioRepository;
    
    
    @Override
    public List<AddressDTO> findAllAddresses() {
        List<AddressModel> addresses = addressRepository.findAll();
        List<AddressDTO> list = new ArrayList();
        addresses.forEach((addr) -> {
            AddressDTO addrDTO = mapperService.mapAddressModelToAddressDTO(addr);
            addrDTO.setSocioId(addr.getSocio().getId());
            list.add(addrDTO);
        });
        return list;
    }

    @Override
    public AddressDTO findOneAddressById(Long id) throws ResourceNotFoundException {
        Optional<AddressModel> opAddress = addressRepository.findById(id);
        if (opAddress.isPresent()) {
            return mapperService.mapAddressModelToAddressDTO(opAddress.get());
        } else {
            return null;
        } 
    }
    
    @Override
    public List<AddressDTO> findAddressesBySocioId(Long id) {
        List<AddressModel> addresses = addressRepository.findAddressesBySocioId(id);
        List<AddressDTO> list = new ArrayList();
        addresses.forEach((addr) -> {
            list.add(mapperService.mapAddressModelToAddressDTO(addr));
        });
        return list;
    }
    
    @Override
    public AddressDTO saveAddress(AddressDTO addressDTO) {
        AddressModel address = mapperService.mapAddressDTOToAddressModel(addressDTO);
        Optional<SocioModel> opSocio = socioRepository.findById(addressDTO.getSocioId());
        if(opSocio.isPresent()) {
             address.setSocio(opSocio.get());
             return mapperService.mapAddressModelToAddressDTO(addressRepository.save(address));
        } else {
            return null;
        }
    }

    @Override
    public AddressDTO updateAddress(AddressDTO addressDTO) throws ResourceNotFoundException {
        
        Optional<AddressModel> opAddress = addressRepository.findById(addressDTO.getId());
        AddressDTO repoAddress = null;
        for (AddressModel value : addressRepository.findAll()) {
            if (Objects.equals(value.getId(), addressDTO.getId())) {
                repoAddress = mapperService.mapAddressModelToAddressDTO(value);
            }
        }
        
        if(opAddress.isPresent()) {
            if (addressDTO.getStreet() == null) {
                addressDTO.setStreet(repoAddress.getStreet());
            }
            
            if (addressDTO.getCity() == null) {
                addressDTO.setStreet(repoAddress.getStreet());
            }
            
            if (addressDTO.getProvince() == null) {
                addressDTO.setProvince(repoAddress.getProvince());
            }
            
            if (addressDTO.getPostalcode() == null) {
                addressDTO.setPostalcode(repoAddress.getPostalcode());
            }
            
            if (addressDTO.getDescription() == null) {
                addressDTO.setDescription(repoAddress.getDescription());
            }
            
            if (addressDTO.getCountry() == null) {
                addressDTO.setCountry(repoAddress.getCountry());
            }
            
            if (addressDTO.getSocioId() == null) {
                addressDTO.setSocioId(repoAddress.getSocioId());
            }
             AddressModel addr = mapperService.mapAddressDTOToAddressModel(addressDTO);
             return mapperService.mapAddressModelToAddressDTO(addressRepository.save(addr));
            
        } else {
            return null;
        }
    }

    @Override
    public boolean hasAddress(Long id) throws ResourceNotFoundException {
        return addressRepository.existsById(id);
    }

    @Override
    public void deleteAddressById(Long id) throws ResourceNotFoundException {
        addressRepository.deleteById(id);
    }
    
}

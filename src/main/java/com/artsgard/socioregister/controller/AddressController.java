package com.artsgard.socioregister.controller;

import com.artsgard.socioregister.DTO.AddressDTO;
import com.artsgard.socioregister.service.AddressService;
import com.artsgard.socioregister.service.SocioService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(AddressController.class);
    
    @Autowired
    AddressService addressService;
    
    @Autowired
    SocioService socioService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findAllAddresses() {
        List<AddressDTO> addresses = addressService.findAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> findAddressById(@PathVariable Long id) {
        AddressDTO address = addressService.findOneAddressById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
    
    @GetMapping(path = "/socioId/{socioId}", produces = "application/json")
    public ResponseEntity<?> findAddressBySocioId(@PathVariable Long socioId) {
        List<AddressDTO> addresses = addressService.findAddressesBySocioId(socioId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
 
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> saveAddress(@Valid @RequestBody AddressDTO address) 
                                                            throws Exception {
        AddressDTO addr = addressService.saveAddress(address);
        return new ResponseEntity<>(addr, HttpStatus.CREATED);
    }
    
    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateAddress(@Valid @RequestBody AddressDTO address) 
                                                            throws Exception {
        AddressDTO addr = addressService.updateAddress(address);
         if (addr != null) {
            return new ResponseEntity<>(addr, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
   
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) throws Exception {
            addressService.deleteAddressById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
 package com.artsgard.socioregister.service;

import com.artsgard.socioregister.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 
 * @author WillemDragstra
 */
@Service
public interface AssociatedSocioService  {  
    void registerAssociatedSocio(Long socioId, Long associatedSocioId) throws ResourceNotFoundException;
    void updateStateAssociatedSocio(Long socioId, Long associatedSocioId, boolean state) throws ResourceNotFoundException;
    void deleteStateAssociatedSocio(Long socioId, Long associatedSocioId) throws ResourceNotFoundException;
}
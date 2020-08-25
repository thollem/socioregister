 package com.artsgard.socioregister.service;

import org.springframework.stereotype.Service;

/**
 * 
 * @author WillemDragstra
 */
@Service
public interface AssociatedSocioService  {  
    void registerAssociatedSocio(Long socioId, Long associatedSocioId);
    void updateStateAssociatedSocio(Long socioId, Long associatedSocioId, boolean state);
    void deleteStateAssociatedSocio(Long socioId, Long associatedSocioId);
}
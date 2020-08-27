package com.artsgard.socioregister.service;

import com.artsgard.socioregister.DTO.FilterDTO;
import com.artsgard.socioregister.DTO.SocioDTO;
import com.artsgard.socioregister.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 
 * @author WillemDragstra
 */
@Service
public interface SocioService  {
    List<SocioDTO> findAllSocios() throws ResourceNotFoundException;
    List<SocioDTO> getSociosBySortedPage(int rows, int offset, FilterDTO filter) throws ResourceNotFoundException;
    SocioDTO findSocioById(Long id) throws ResourceNotFoundException;
    SocioDTO findSocioByUsername(String username) throws ResourceNotFoundException; 
    SocioDTO saveSocio(SocioDTO socioDTO);
    SocioDTO updateSocio(SocioDTO socioDTO) throws ResourceNotFoundException;
    void deleteSocioById(Long id) throws ResourceNotFoundException;
    boolean isSocioActiveById(Long id) throws ResourceNotFoundException;
    boolean hasSocioById(Long id);
}
package com.artsgard.socioregister.service;

import com.artsgard.socioregister.DTO.FilterDTO;
import com.artsgard.socioregister.DTO.SocioDTO;
import com.artsgard.socioregister.model.SocioModel;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

/**
 * 
 * @author WillemDragstra
 */
@Service
public interface SocioService  {
    List<SocioDTO> findAllSocios();
    List<SocioDTO> getSociosBySortedPage(int rows, int offset, FilterDTO filter);
    SocioDTO findSocioById(Long id); 
    SocioDTO findSocioByUsername(String username); 
    SocioModel findSocioModelByUsername(String username); 
    SocioDTO saveSocio(SocioDTO socioDTO);
    SocioDTO updateSocio(SocioDTO socioDTO);
    void deleteSocioById(Long id);
    boolean isSocioActiveById(Long id);
    boolean hasSocioById(Long id);
}
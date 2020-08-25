package com.artsgard.socioregister.serviceimpl;

import com.artsgard.socioregister.DTO.FilterDTO;
import com.artsgard.socioregister.service.MapperService;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artsgard.socioregister.DTO.SocioDTO;
import com.artsgard.socioregister.model.SocioModel;
import com.artsgard.socioregister.repository.SocioRepository;
import com.artsgard.socioregister.service.SocioService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author WillemDragstra
 */
@Service
public class SocioServiceImpl implements SocioService {

    org.slf4j.Logger logger = LoggerFactory.getLogger(SocioServiceImpl.class);

    @Autowired
    private MapperService mapperService;

    @Autowired
    private SocioRepository socioRepò;

    /**
     *
     */
    public SocioServiceImpl() {
    }

    /**
     *
     * @return list of all socios
     */
    @Override
    public List<SocioDTO> findAllSocios() {
        List<SocioModel> socios = socioRepò.findAll();
        List<SocioDTO> socioList = new ArrayList();

        for (SocioModel sco : socios) {
            SocioDTO scDTO = mapperService.mapSocioModelToSocioDTO(sco);
            socioList.add(scDTO);
        }
        return socioList;
    }

    @Override
    public List<SocioDTO> getSociosBySortedPage(int rows, int offset, FilterDTO filter) {
        List<SocioModel> socios = null;

        if (filter.getCountry() != null) {
            socios = socioRepò.getSociosBySortedPageByCountry(rows, offset, filter.getCountry());
        } else if (filter.getLanguage() != null) {
            socios = socioRepò.getSociosBySortedPageByLanguage(rows, offset, filter.getLanguage());
        } else {
            socios = socioRepò.getSociosBySortedPage(rows, offset);
        }
        
        List<SocioDTO> socioList = new ArrayList();

        socios.stream().map(sco -> mapperService.mapSocioModelToSocioDTO(sco)).forEachOrdered(scDTO -> {
            socioList.add(scDTO);
        });
        return socioList;
    }

    /**
     *
     * @param id
     * @return single socio by id
     */
    @Override
    public SocioDTO findSocioById(Long id) {
        Optional<SocioModel> opSocio = socioRepò.findById(id);
        if (opSocio.isPresent()) {
            return mapperService.mapSocioModelToSocioDTO(opSocio.get());
        } else {
            return null;
        }
    }

    /**
     *
     * @param username
     * @return single socio by username
     */
    @Override
    public SocioDTO findSocioByUsername(String username) {
        Optional<SocioModel> opSocio = socioRepò.findByUsername(username);
        if (opSocio.isPresent()) {
            return mapperService.mapSocioModelToSocioDTO(opSocio.get());
        } else {
            return null;
        }
    }

    /**
     *
     * @param username
     * @return single socio as SocioMopdel
     */
    @Override
    public SocioModel findSocioModelByUsername(String username) {
        Optional<SocioModel> opSocio = socioRepò.findByUsername(username);
        if (opSocio.isPresent()) {
            return opSocio.get();
        } else {
            return null;
        }
    }

    /**
     *
     * @param socioDTO
     * @return save single socio
     */
    @Override
    public SocioDTO saveSocio(SocioDTO socioDTO) {
        SocioModel socio = mapperService.mapSocioDTOToSocioModel(socioDTO);
        socio.setRegisterDate(new Timestamp(System.currentTimeMillis()));
        socio.setLastCheckinDate(new Timestamp(System.currentTimeMillis()));
        return mapperService.mapSocioModelToSocioDTO(socioRepò.save(socio));
    }

    /**
     *
     * @param socioDTO
     * @param id
     * @return update single socio by id
     */
    @Override
    public SocioDTO updateSocio(final SocioDTO socioDTO) {

        SocioDTO repoSocio = null;
        for (SocioModel value : socioRepò.findAll()) {
            if (Objects.equals(value.getId(), socioDTO.getId())) {
                repoSocio = mapperService.mapSocioModelToSocioDTO(value);
            }
        }

        SocioDTO updatedDTO;
        if (repoSocio != null) {

            if (socioDTO.getUsername() == null) {
                socioDTO.setUsername(repoSocio.getUsername());
            }

            if (socioDTO.getFirstName() == null) {
                socioDTO.setFirstName(repoSocio.getFirstName());
            }

            if (socioDTO.getLastName() == null) {
                socioDTO.setLastName(repoSocio.getLastName());
            }

            if (socioDTO.getEmail() == null) {
                socioDTO.setEmail(repoSocio.getEmail());
            }

            if (socioDTO.getSocioLanguages() == null) {
                socioDTO.setEmail(repoSocio.getEmail());
            }
            socioDTO.setRegisterDate(repoSocio.getRegisterDate());
            socioDTO.setLastCheckinDate(repoSocio.getLastCheckinDate());
            socioDTO.setSocioLanguages(repoSocio.getSocioLanguages());
            socioDTO.setSocioAddresses(repoSocio.getSocioAddresses());
            socioDTO.setId(socioDTO.getId());

        } else {
            return null;
        }
        SocioModel socio = mapperService.mapSocioDTOToSocioModel(socioDTO);
        return mapperService.mapSocioModelToSocioDTO(socioRepò.save(socio));
    }

    /**
     *
     * Delete socio
     *
     * @param id
     */
    @Override
    public void deleteSocioById(Long id) {
        socioRepò.deleteById(id);
    }

    /**
     *
     * @param id
     * @return boolean has socio
     */
    @Override
    public boolean hasSocioById(Long id) {
        return socioRepò.existsById(id);
    }

    /**
     *
     * @param id
     * @return boolean is socio active
     */
    @Override
    public boolean isSocioActiveById(Long id) {
        Optional<SocioModel> opSocio = socioRepò.findById(id);
        if (opSocio.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}

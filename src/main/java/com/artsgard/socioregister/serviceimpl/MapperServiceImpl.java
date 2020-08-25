package com.artsgard.socioregister.serviceimpl;

import com.artsgard.socioregister.DTO.AddressDTO;
import com.artsgard.socioregister.service.MapperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artsgard.socioregister.DTO.SocioDTO;
import com.artsgard.socioregister.exception.UniqueConstraintException;
import com.artsgard.socioregister.model.AddressModel;
import com.artsgard.socioregister.model.LanguageModel;
import com.artsgard.socioregister.model.SocioModel;
import com.artsgard.socioregister.repository.SocioRepository;
import com.artsgard.socioregister.service.SocioService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.LoggerFactory;

/**
 *
 * @author WillemDragstra
 * 
 */
@Service
public class MapperServiceImpl implements MapperService {

    org.slf4j.Logger logger;

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private SocioRepository socioRepository;

    public MapperServiceImpl() {
        this.logger = LoggerFactory.getLogger(MapperServiceImpl.class);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     *
     * @param dto
     * @return SocioModel from SocioDTO or null
     */
    @Override
    public SocioModel mapSocioDTOToSocioModel(SocioDTO dto) {

        if (dto != null) {
            return modelMapper.map(dto, SocioModel.class);
        } else {
            return null;
        }
    }

    @Override
    public SocioDTO mapSocioModelToSocioDTO(SocioModel ent) {
        if (ent != null) {
            SocioDTO scDTO = modelMapper.map(ent, SocioDTO.class);
            
            if (ent.getSocioAddresses() != null) {
                List<AddressDTO> addresses = new ArrayList();
                for (AddressModel addrModel : ent.getSocioAddresses()) {
                    AddressDTO addrDTO = modelMapper.map(addrModel, AddressDTO.class);
                    addrDTO.setSocioId(ent.getId());
                    addresses.add(addrDTO);
                }
                scDTO.setSocioAddresses(addresses);
            }
            return scDTO;
        } else {
            return null;
        }
    }

    @Override
    public AddressModel mapAddressDTOToAddressModel(AddressDTO dto) throws UniqueConstraintException {
        if (dto != null) {
            SocioModel socio = socioRepository.getOne(dto.getSocioId());
            AddressModel addr = modelMapper.map(dto, AddressModel.class);
            addr.setSocio(socio);
            return addr;
        } else {
            return null;
        }
    }

    @Override
    public AddressDTO mapAddressModelToAddressDTO(AddressModel ent) {
        if (ent != null) {
            AddressDTO address = modelMapper.map(ent, AddressDTO.class);
            address.setSocioId(ent.getSocio().getId());
            return address;
        } else {
            return null;
        }
    }
}

package com.artsgard.socioregister.serviceimpl;

import com.artsgard.socioregister.service.RoleService;
import com.artsgard.socioregister.exception.ResourceNotFoundException;
import com.artsgard.socioregister.model.RoleModel;
import com.artsgard.socioregister.model.SocioModel;
import com.artsgard.socioregister.repository.RoleRepository;
import com.artsgard.socioregister.repository.SocioRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    org.slf4j.Logger logger;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    SocioRepository socioRepo;

    public RoleServiceImpl() {
        logger = LoggerFactory.getLogger(SocioServiceImpl.class);
    }

    @Override
    public List<RoleModel> getAllRoles() {
        List<RoleModel> list = roleRepo.findAll();
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("no Roles found!");
        } else {
            return list;
        }
    }

    @Override
    public List<RoleModel> findRolesBySocioId(Long id) throws ResourceNotFoundException {
        Optional<SocioModel> optSocio = socioRepo.findById(id);

        if (optSocio.isPresent()) {
            return roleRepo.findRolesBySocioId(id);
        } else {
            throw new ResourceNotFoundException("no Socio present with id: " + id);
        }

    }

    @Override
    public RoleModel findByName(String name) throws ResourceNotFoundException {
        RoleModel role = roleRepo.findByName(name);
        if (role == null) {
            logger.error("No Role found with name: " + name);
            throw new ResourceNotFoundException("No Roles found with name: " + name);
        }
        return role;
    }

    @Override
    public List<RoleModel> findRolesBySocioUsername(String username) throws ResourceNotFoundException {
        Optional<SocioModel> optSocio = socioRepo.findByUsername(username);

        if (optSocio.isPresent()) {
            return roleRepo.findRolesBySocioUsername(username);
        } else {
            throw new ResourceNotFoundException("no Socio present with username: " + username);
        }
    }
}

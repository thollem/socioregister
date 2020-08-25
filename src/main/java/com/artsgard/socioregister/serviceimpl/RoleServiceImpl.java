package com.artsgard.socioregister.serviceimpl;

import com.artsgard.socioregister.service.RoleService;
import com.artsgard.socioregister.service.SocioService;
import com.artsgard.socioregister.exception.ResourceNotFoundException;
import com.artsgard.socioregister.model.RoleModel;
import com.artsgard.socioregister.repository.RoleRepository;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
   org.slf4j.Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    RoleRepository roleRepo;
    
    @Autowired
    SocioService socioService;

    @Override
    public List<RoleModel> getAllRoles() {
        return roleRepo.findAll();
    }
    
    @Override
    public List<RoleModel> findRolesBySocioId(Long id) throws ResourceNotFoundException {
        socioService.hasSocioById(id);
        return roleRepo.findRolesBySocioId(id);
    }
    
    @Override
    public RoleModel findByName(String name) throws ResourceNotFoundException {
        RoleModel role = roleRepo.findByName(name);
        if (role == null) {
            logger.error("No Roles found with name: " + name);
            throw new ResourceNotFoundException("No Roles found with name: " + name);
        }
        return role;
    }

    @Override
    public List<RoleModel> findRolesBySocioUsername(String username) {
        return roleRepo.findRolesBySocioUsername(username);
    }
}

package com.artsgard.socioregister.service;

import com.artsgard.socioregister.exception.ResourceNotFoundException;
import com.artsgard.socioregister.model.RoleModel;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RoleService  {
   List<RoleModel> getAllRoles() throws ResourceNotFoundException;
   List<RoleModel> findRolesBySocioId(Long id) throws ResourceNotFoundException; 
   List<RoleModel> findRolesBySocioUsername(String username) throws ResourceNotFoundException;
   RoleModel findByName(String name) throws ResourceNotFoundException;
}
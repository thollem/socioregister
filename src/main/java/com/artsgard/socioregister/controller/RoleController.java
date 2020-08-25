package com.artsgard.socioregister.controller;

import com.artsgard.socioregister.model.RoleModel;
import com.artsgard.socioregister.service.RoleService;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<RoleModel>> findAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}", produces = "application/json")
    public ResponseEntity <List<RoleModel>> findRolesBySocioId(@PathVariable Long userId) {
        List<RoleModel> roles = roleService.findRolesBySocioId(userId);
        if (roles != null) {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/username/{username}", produces = "application/json")
    public ResponseEntity <List<RoleModel>> findRolesBySocioUsername(@PathVariable String username) {
        List<RoleModel> roles = roleService.findRolesBySocioUsername(username);
        if (roles != null) {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

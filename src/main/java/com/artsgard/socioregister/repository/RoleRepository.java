package com.artsgard.socioregister.repository;

import com.artsgard.socioregister.model.RoleModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
   static final String QUERY_BY_SOCIO_ID = "SELECT * FROM socio JOIN socio_role ON socio.id = socio_role.socio_id JOIN role ON socio_role.role_id = role.id WHERE socio.id=:id";
   static final String QUERY_BY_SOCIO_USERNAME = "SELECT * FROM socio JOIN socio_role ON socio.id = socio_role.socio_id JOIN role ON socio_role.role_id = role.id WHERE socio.username=:username";
   
   @Query(value = QUERY_BY_SOCIO_ID, nativeQuery = true)
   List<RoleModel> findRolesBySocioId(@Param("id")Long id);
   
   @Query(value = QUERY_BY_SOCIO_USERNAME, nativeQuery = true)
   List<RoleModel> findRolesBySocioUsername(@Param("username")String username);
   
   RoleModel findByName(String name);
}

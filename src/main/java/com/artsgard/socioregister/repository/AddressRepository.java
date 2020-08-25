package com.artsgard.socioregister.repository;

import com.artsgard.socioregister.model.AddressModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {

    static final String QUERY = "SELECT * FROM address WHERE socio_id = :id";

    @Query(value = QUERY, nativeQuery = true)
    public List<AddressModel> findAddressesBySocioId(@Param("id") Long id);
}

package com.artsgard.socioregister.controller;

import com.artsgard.socioregister.DTO.FilterDTO;
import com.artsgard.socioregister.DTO.SocioDTO;
import com.artsgard.socioregister.service.SocioService;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paginateSocio")
public class PaginateSortSocioController {
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(PaginateSortSocioController.class);
    
    @Autowired
    private SocioService socioService;

    @ResponseBody
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findAllSocioSortedByPage(@RequestParam(value = "rows",
            required = false, defaultValue = "3") int rows,
            @RequestParam(value = "offset",
                    required = false, defaultValue = "0") int offset, FilterDTO filter) {
        List<SocioDTO> socios = socioService.getSociosBySortedPage(rows, offset, filter);
        return new ResponseEntity<>(socios, HttpStatus.OK);
    }
}

package com.artsgard.socioregister.DTO;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author WillemDragstra
 * 
 * 
 */
@Data
@NoArgsConstructor
public class FilterDTO implements Serializable {
    
    private String country;
    
    private String language;
    
    private String gender;
    
}

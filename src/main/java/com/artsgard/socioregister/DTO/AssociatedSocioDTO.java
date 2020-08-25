package com.artsgard.socioregister.DTO;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author WillemDragstra
 * 
 * {
        "socioId": 1,
        "associatedSocioId": 2,
        "state": true
    }
 * 
 */
@Data
@NoArgsConstructor
public class AssociatedSocioDTO implements Serializable {
    
    @NotNull
    private Long socioId;
    
    @NotNull
    private Long associatedSocioId;
    
    private boolean state;
}

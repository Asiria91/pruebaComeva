package com.prueba.comeva.DTO;

import com.prueba.comeva.model.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientesDTO {
    private Integer idCliente;
    private String email;
    private String nombre;
    private Long numeroDocumento;
    private Long telefono;
    private Integer tipoDocumento;
}

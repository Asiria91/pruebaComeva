package com.prueba.comeva.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTipoDocumento;

    @Column
    private String codigo;

    @Column
    private String descripcion;

    protected TipoDocumento() {
    }

    public TipoDocumento(int idTipoDocumento,String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
}

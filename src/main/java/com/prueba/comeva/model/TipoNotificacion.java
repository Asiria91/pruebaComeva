package com.prueba.comeva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TipoNotificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTipoNotificacion;

    @Column
    private String codigo;

    @Column
    private String descripcion;

}


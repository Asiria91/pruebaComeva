package com.prueba.comeva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Notificacion {
    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="notificacion_id_notificacion_seq", initialValue=205, allocationSize=12)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
    private int idNotificacion;

    @Column
    private Boolean notificado;

    @Column
    private Boolean inscrito;

    @ManyToOne(optional = false)
    @JoinColumn(name="fk_cliente")
    private Clientes clientes;

    @ManyToOne(optional = false)
    @JoinColumn(name="fk_tipo_notificacion")
    private TipoNotificacion tipoNotificacion;
}

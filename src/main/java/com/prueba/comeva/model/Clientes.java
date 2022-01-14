package com.prueba.comeva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Clientes{
    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="clientes_id_cliente_seq", initialValue=205, allocationSize=12)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
    private int idCliente;

    @Column
    private String email;

    @Column
    private String nombre;

    @Column
    private Long numeroDocumento;

    @Column
    private Long telefono;

    @ManyToOne(optional = false)
    @JoinColumn(name="fk_tipo_documento")
    private TipoDocumento tipoDocumento;
}

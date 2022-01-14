package com.prueba.comeva.DTO;

import com.prueba.comeva.model.Clientes;
import com.prueba.comeva.model.TipoNotificacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionDTO {
    public Integer idNotificacion;
    public Boolean notificado;
    public Boolean inscrito;
    public int clientes;
    public int tipoNotificacion;
}

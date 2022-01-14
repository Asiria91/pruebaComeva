package com.prueba.comeva.persistence;

import com.prueba.comeva.model.TipoNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoNotificacionRepository extends JpaRepository<TipoNotificacion, Integer> {
}

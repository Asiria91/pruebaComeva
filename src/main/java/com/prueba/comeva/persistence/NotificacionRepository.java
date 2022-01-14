package com.prueba.comeva.persistence;

import com.prueba.comeva.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {

}

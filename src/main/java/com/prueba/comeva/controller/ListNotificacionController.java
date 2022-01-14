package com.prueba.comeva.controller;

import com.prueba.comeva.model.Clientes;
import com.prueba.comeva.model.Notificacion;
import com.prueba.comeva.persistence.NotificacionRepository;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope(value = "session")
@Component(value = "listNotificacion")
@ELBeanName(value = "listNotificacion")
@Join(path = "/Notificaciones", to = "/Notificaciones/notificaciones-list.jsf")
public class ListNotificacionController {
    @Autowired
    NotificacionRepository notificacionRepository;

    private List<Notificacion> notificacionList;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        notificacionList = notificacionRepository.findAll();
    }

    public String delete(Notificacion notificacion) {
        notificacionRepository.delete(notificacion.getIdNotificacion());
        loadData();
        return null;
    }

    public String edit(Notificacion notificacion){
        return "/Notificaciones/notificaciones-form.xhtml?faces-redirect=true&id="+notificacion.getIdNotificacion();
    }

    public List<Notificacion> getNotificacionList() {
        return notificacionList;
    }
}

package com.prueba.comeva.controller;

import com.prueba.comeva.DTO.ClientesDTO;
import com.prueba.comeva.DTO.NotificacionDTO;
import com.prueba.comeva.model.Clientes;
import com.prueba.comeva.model.Notificacion;
import com.prueba.comeva.model.TipoNotificacion;
import com.prueba.comeva.persistence.ClientesRepository;
import com.prueba.comeva.persistence.NotificacionRepository;
import com.prueba.comeva.persistence.TipoNotificacionRepository;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

@Scope(value = "session")
@Component(value = "notificacionController")
@ELBeanName(value = "notificacionController")
@Join(path = "/Notificaciones/create", to = "/Notificaciones/notificaciones-form.jsf")
public class NotificacionController {
    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private NotificacionRepository notificacionRepository;
    @Autowired
    private TipoNotificacionRepository tipoNotificacionRepository;

    private NotificacionDTO notificacionDTO = new NotificacionDTO();
    private List<Clientes> clientes;
    private List<TipoNotificacion> tipoNotificacionList;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String notificacionId = paramMap.get("id");
        System.out.println("id:"+notificacionId);
        if(notificacionId != null){
            Notificacion notificacion = notificacionRepository.findOne(Integer.parseInt(notificacionId));
            notificacionDTO.setIdNotificacion(notificacion.getIdNotificacion());
            notificacionDTO.setNotificado(notificacion.getNotificado());
            notificacionDTO.setInscrito(notificacion.getInscrito());
            notificacionDTO.setClientes(notificacion.getClientes().getIdCliente());
            notificacionDTO.setTipoNotificacion(notificacion.getTipoNotificacion().getIdTipoNotificacion());
        }
        clientes = clientesRepository.findAll();
        tipoNotificacionList = tipoNotificacionRepository.findAll();
    }

    public String save() {
        Notificacion notificacion= new Notificacion();
        if(notificacionDTO.getIdNotificacion() != null) {
            notificacion.setIdNotificacion(notificacionDTO.getIdNotificacion());
        }
        notificacion.setTipoNotificacion(tipoNotificacionRepository.getOne(notificacionDTO.getTipoNotificacion()));
        notificacion.setClientes(clientesRepository.getOne(notificacionDTO.getClientes()));
        notificacion.setNotificado(notificacionDTO.getNotificado());
        notificacion.setInscrito(notificacionDTO.getInscrito());
        notificacionRepository.save(notificacion);
        notificacionDTO = new NotificacionDTO();
        return "/Notificaciones/notificaciones-list.xhtml?faces-redirect=true";
    }

    public NotificacionDTO getNotificacionDTO() {
        return notificacionDTO;
    }

    public List<Clientes> getClientes() {
        return clientes;
    }

    public List<TipoNotificacion> getTipoNotificacionList() {
        return tipoNotificacionList;
    }
}

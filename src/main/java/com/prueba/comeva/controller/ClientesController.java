package com.prueba.comeva.controller;

import com.prueba.comeva.DTO.ClientesDTO;
import com.prueba.comeva.model.Clientes;
import com.prueba.comeva.model.TipoDocumento;
import com.prueba.comeva.persistence.ClientesRepository;
import com.prueba.comeva.persistence.TipoDocumentoRepository;
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
@Component(value = "clientesController")
@ELBeanName(value = "clientesController")
@Join(path = "/Clientes", to = "/Clientes/clientes-form.jsf")
public class ClientesController {

    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;

    private ClientesDTO clientesdto = new ClientesDTO();
    private List<TipoDocumento> tipoDocumentoList;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String clientId = paramMap.get("id");
        System.out.println("id:"+clientId);
        if(clientId != null){
            Clientes clientes = clientesRepository.findOne(Integer.parseInt(clientId));
            clientesdto.setIdCliente(clientes.getIdCliente());
            clientesdto.setEmail(clientes.getEmail());
            clientesdto.setNombre(clientes.getNombre());
            clientesdto.setTelefono(clientes.getTelefono());
            clientesdto.setTipoDocumento(clientes.getTipoDocumento().getIdTipoDocumento());
            clientesdto.setNumeroDocumento(clientes.getNumeroDocumento());
        }
        tipoDocumentoList = tipoDocumentoRepository.findAll();
    }

    public String save() {
        Clientes clientes = new Clientes();
        if(clientesdto.getIdCliente() != null) {
            clientes.setIdCliente(clientesdto.getIdCliente());
        }
        clientes.setTipoDocumento(tipoDocumentoRepository.getOne(clientesdto.getTipoDocumento()));
        clientes.setEmail(clientesdto.getEmail());
        clientes.setNombre(clientesdto.getNombre());
        clientes.setNumeroDocumento(clientesdto.getNumeroDocumento());
        clientes.setTelefono(clientesdto.getTelefono());
        clientesRepository.save(clientes);
        clientesdto = new ClientesDTO();
        return "/Clientes/clientes-list.xhtml?faces-redirect=true";
    }

    public ClientesDTO getClientesdto(){
        return clientesdto;
    }

    public void setClientesdto(ClientesDTO clientesdto) {
        this.clientesdto = clientesdto;
    }

    public List<TipoDocumento> getTipoDocumentoList() {
        return tipoDocumentoList;
    }
}

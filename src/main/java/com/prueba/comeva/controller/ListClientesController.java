package com.prueba.comeva.controller;

import com.prueba.comeva.DTO.ClientesDTO;
import com.prueba.comeva.model.Clientes;
import com.prueba.comeva.model.TipoDocumento;
import com.prueba.comeva.persistence.ClientesRepository;
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
@Component(value = "listClientes")
@ELBeanName(value = "listClientes")
@Join(path = "/", to = "/Clientes/clientes-list.jsf")
public class ListClientesController {

    @Autowired
    ClientesRepository clientesRepository;

    private List<Clientes> clientesList;
    private Clientes cliente;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        clientesList = clientesRepository.findAll();
    }

    public String delete(Clientes cliente) {
        clientesRepository.delete(cliente.getIdCliente());
        loadData();
        return null;
    }

    public String edit(Clientes cliente){
        return "/Clientes/clientes-form.xhtml?faces-redirect=true&id="+cliente.getIdCliente();
    }

    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public Clientes getCliente() {
        return cliente;
    }
}

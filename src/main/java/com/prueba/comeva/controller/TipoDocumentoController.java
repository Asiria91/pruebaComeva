package com.prueba.comeva.controller;

import com.prueba.comeva.persistence.TipoDocumentoRepository;
import com.prueba.comeva.model.TipoDocumento;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.model.SelectItem;
import java.util.HashMap;
import java.util.List;

@Scope(value = "session")
@Component(value = "listTipoDocumento")
@ELBeanName(value = "listTipoDocumento")
@Join(path = "/documento", to = "/tipoDocumento-list.jsf")
public class TipoDocumentoController {
    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;

    private List<TipoDocumento> tipoDocumentoList;
    private HashMap<String, String> selectTipos;
    private List<SelectItem> selectItems;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        selectTipos = new HashMap<String, String>();
        tipoDocumentoList = tipoDocumentoRepository.findAll();
        for (TipoDocumento tipoDocumento : tipoDocumentoList
        ){
            selectItems.add(new SelectItem(tipoDocumento.getDescripcion(), tipoDocumento.getDescripcion()));
        }
    }

    public List<TipoDocumento> getTipoDocumentoList() {
        return tipoDocumentoList;
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }
}

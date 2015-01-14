/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mandado.converter;

import br.com.mandado.dao.BairroDAO;
import br.com.mandado.domain.Bairro;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Helen
 */
@FacesConverter("bairroConverter")
public class BairroConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext,
            UIComponent componente, String valor) {
        try {
            Long codigo = Long.parseLong(valor);
            BairroDAO bairroDAO = new BairroDAO();
            Bairro bairro = bairroDAO.buscarPorCodigo(codigo);
            return bairro;
        } catch (RuntimeException ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext,
            UIComponent componente, Object objeto) {
        try {
            Bairro bairro = (Bairro) objeto;
            Long codigo = bairro.getCodigo();
            return codigo.toString();
        } catch (RuntimeException ex) {
            return null;
        }
    }

}

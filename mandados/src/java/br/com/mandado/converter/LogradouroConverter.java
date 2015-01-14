/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mandado.converter;

import br.com.mandado.dao.LogradouroDAO;
import br.com.mandado.domain.Logradouro;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Helen
 */
@FacesConverter("logradouroConverter")
public class LogradouroConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(LogradouroConverter.class.getName());

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent componente, String valor) {
        try {
            Long codigo = Long.parseLong(valor);
            LogradouroDAO logradouroDAO = new LogradouroDAO();
            Logradouro logradouro = logradouroDAO.buscarPorCodigo(codigo);
            return logradouro;
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent componente, Object objeto) {
        try {
            Logradouro logradouro = (Logradouro) objeto;
            Long codigo = logradouro.getCodigo();
            return codigo.toString();
        } catch (RuntimeException ex) {
            return null;
        }
    }

}

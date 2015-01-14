/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mandado.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author Usuario
 */
public class FacesUtil {

    public static void adicionarMsgErro(String mensagem) {
        FacesMessage facesMessage = new FacesMessage(
                FacesMessage.SEVERITY_ERROR, mensagem, mensagem);

        FacesContext facesContext = FacesContext.getCurrentInstance();

        ExternalContext externalContext = facesContext.getExternalContext();

        Flash flash = externalContext.getFlash();
        flash.setKeepMessages(true);

        facesContext.addMessage(null, facesMessage);
    }

    public static void adicionarMsgInfo(String mensagem) {
        FacesMessage facesMessage = new FacesMessage(
                FacesMessage.SEVERITY_INFO, mensagem, mensagem);
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ExternalContext externalContext = facesContext.getExternalContext();

        Flash flash = externalContext.getFlash();
        flash.setKeepMessages(true);

        facesContext.addMessage(null, facesMessage);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mandado.bean;

import br.com.mandado.dao.UsuarioDAO;
import br.com.mandado.domain.Usuario;
import br.com.mandado.util.FacesUtil;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Helen
 */
@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(AutenticacaoBean.class.getName());

    private Usuario usuarioLogado;

    /**
     * @return
     */
    public Usuario getUsuarioLogado() {
        if (usuarioLogado == null) {
            usuarioLogado = new Usuario();
        }
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado
     */
    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    /**
     * @return
     */
    public String autenticar() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioLogado = usuarioDAO.autenticar(
                    usuarioLogado.getCpf(),
                    DigestUtils.md5Hex(usuarioLogado.getSenha()));

            if (usuarioLogado == null) {
                FacesUtil.adicionarMsgErro("CPF e/ou senha inválidos");
                return null;
            } else {
                FacesUtil
                        .adicionarMsgInfo("Usuário autenticado com sucesso!");
                return "/pages/principal.xhtml?faces-redirect=true";
            }
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar autenticar no sistema: "
                    + ex.getMessage());
            return null;
        }
    }

    /**
     * @return /pages/Autenticacao.xhtml
     */
    public String sair() {
        usuarioLogado = null;
        return "/pages/autenticacao.xhtml?faces-redirect=true";
    }
}

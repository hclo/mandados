/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mandado.bean;

import br.com.mandado.dao.BairroDAO;
import br.com.mandado.dao.LogradouroDAO;
import br.com.mandado.domain.Bairro;
import br.com.mandado.domain.Logradouro;
import br.com.mandado.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Helen
 */
@ManagedBean
@ViewScoped
public class LogradouroBean implements Serializable {
    private static final Logger LOG = Logger.getLogger(LogradouroBean.class.getName());
    private static final long serialVersionUID = 1L;

    private Logradouro logradouroCadastro;

    private List<Logradouro> listaLogradouros;
    private List<Logradouro> listaLogradourosFiltrados;

    private String acao;
    private Long codigo;

    private List<Bairro> listaBairros;

    public List<Bairro> getListaBairros() {
        return listaBairros;
    }

    public void setListaBairros(List<Bairro> listaBairros) {
        this.listaBairros = listaBairros;
    }
    
    /**
     * @return logradouroCadastro
     */
    public Logradouro getLogradouroCadastro() {
        return logradouroCadastro;
    }

    /**
     * @param logradouroCadastro
     */
    public void setLogradouroCadastro(Logradouro logradouroCadastro) {
        this.logradouroCadastro = logradouroCadastro;
    }

    /**
     * @return listaLogradouros
     */
    public List<Logradouro> getListaLogradouros() {
        return listaLogradouros;
    }

    /**
     * @param listaLogradouros
     */
    public void setListaLogradouros(List<Logradouro> listaLogradouros) {
        this.listaLogradouros = listaLogradouros;
    }

    /**
     * @return listaLogradourosFiltrados
     */
    public List<Logradouro> getListaLogradourosFiltrados() {
        return listaLogradourosFiltrados;
    }

    /**
     * @param listaLogradourosFiltrados
     */
    public void setListaLogradourosFiltrados(
            List<Logradouro> listaLogradourosFiltrados) {
        this.listaLogradourosFiltrados = listaLogradourosFiltrados;
    }

    /**
     * @return acao
     */
    public String getAcao() {
        return acao;
    }

    /**
     * @param acao
     */
    public void setAcao(String acao) {
        this.acao = acao;
    }

    /**
     * @return codigo
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Cria novo logradouro
     */
    public void novo() {
        logradouroCadastro = new Logradouro();
    }

    /**
     * Salva logradouro
     */
    public void salvar() {
        try {
            LogradouroDAO logradouroDAO = new LogradouroDAO();
            
            logradouroDAO.salvar(logradouroCadastro);

            logradouroCadastro = new Logradouro();

            FacesUtil.adicionarMsgInfo("Logradouro salvo com sucesso!");
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar incluir um logradouro: "
                            + ex.getMessage());
        }
    }

    /**
     * Carrega lista de Logradouros
     */
    public void carregarPesquisa() {
        try {
            LogradouroDAO logradouroDAO = new LogradouroDAO();
            listaLogradouros = logradouroDAO.listar();
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar listar os logradouros: "
                            + ex.getMessage());
        }
    }

    /**
     * Carrega cadastro do logradouro selecionado
     */
    public void carregarCadastro() {
        try {
            if (codigo != null) {
                LogradouroDAO logradouroDAO = new LogradouroDAO();
                logradouroCadastro = logradouroDAO.buscarPorCodigo(codigo);
            } else {
                logradouroCadastro = new Logradouro();
            }
            
            BairroDAO bairroDAO = new BairroDAO();
            listaBairros = bairroDAO.listar();
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar obter os dados do logradouro: "
                            + ex.getMessage());
        }
    }

    /**
     * Exclui logradouro selecionado
     */
    public void excluir() {
        try {
            LogradouroDAO logradouroDAO = new LogradouroDAO();
            logradouroDAO.excluir(logradouroCadastro);

            FacesUtil.adicionarMsgInfo("Logradouro removido com sucesso!");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar remover o logradouro: "
                    + ex.getMessage());
        }
    }

    /**
     * Edita logradouro selecionado
     */
    public void editar() {
        try {
            LogradouroDAO logradouroDAO = new LogradouroDAO();
            
            logradouroDAO.editar(logradouroCadastro);

            FacesUtil.adicionarMsgInfo("Logradouro editado com sucesso!");
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar editar os dados do logradouro: "
                            + ex.getMessage());
        }
    }
}

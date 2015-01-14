/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mandado.bean;

import br.com.mandado.dao.BairroDAO;
import br.com.mandado.domain.Bairro;
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
public class BairroBean implements Serializable {
    private static final Logger LOG = Logger.getLogger(BairroBean.class.getName());
    private static final long serialVersionUID = 1L;

    private Bairro bairroCadastro;

    private List<Bairro> listaBairros;
    private List<Bairro> listaBairrosFiltrados;

    private String acao;
    private Long codigo;

    /**
     * @return bairroCadastro
     */
    public Bairro getBairroCadastro() {
        return bairroCadastro;
    }

    /**
     * @param bairroCadastro
     */
    public void setBairroCadastro(Bairro bairroCadastro) {
        this.bairroCadastro = bairroCadastro;
    }

    /**
     * @return listaBairros
     */
    public List<Bairro> getListaBairros() {
        return listaBairros;
    }

    /**
     * @param listaBairros
     */
    public void setListaBairros(List<Bairro> listaBairros) {
        this.listaBairros = listaBairros;
    }

    /**
     * @return listaBairrosFiltrados
     */
    public List<Bairro> getListaBairrosFiltrados() {
        return listaBairrosFiltrados;
    }

    /**
     * @param listaBairrosFiltrados
     */
    public void setListaBairrosFiltrados(
            List<Bairro> listaBairrosFiltrados) {
        this.listaBairrosFiltrados = listaBairrosFiltrados;
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
     * Cria novo bairro
     */
    public void novo() {
        bairroCadastro = new Bairro();
    }

    /**
     * Salva bairro
     */
    public void salvar() {
        try {
            BairroDAO bairroDAO = new BairroDAO();
            bairroDAO.salvar(bairroCadastro);

            bairroCadastro = new Bairro();

            FacesUtil.adicionarMsgInfo("Bairro salvo com sucesso!");
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar incluir um usuário: "
                            + ex.getMessage());
        }
    }

    /**
     * Carrega lista de Bairros
     */
    public void carregarPesquisa() {
        try {
            BairroDAO bairroDAO = new BairroDAO();
            listaBairros = bairroDAO.listar();
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar listar os usuários: "
                            + ex.getMessage());
        }
    }

    /**
     * Carrega cadastro do bairro selecionado
     */
    public void carregarCadastro() {
        try {
            if (codigo != null) {
                BairroDAO bairroDAO = new BairroDAO();
                bairroCadastro = bairroDAO.buscarPorCodigo(codigo);
            } else {
                bairroCadastro = new Bairro();
            }
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar obter os dados do usuário: "
                            + ex.getMessage());
        }
    }

    /**
     * Exclui bairro selecionado
     */
    public void excluir() {
        try {
            BairroDAO bairroDAO = new BairroDAO();
            bairroDAO.excluir(bairroCadastro);

            FacesUtil.adicionarMsgInfo("Bairro removido com sucesso!");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar remover o bairro: "
                    + ex.getMessage());
        }
    }

    /**
     * Edita bairro selecionado
     */
    public void editar() {
        try {
            BairroDAO bairroDAO = new BairroDAO();
            
            bairroDAO.editar(bairroCadastro);

            FacesUtil.adicionarMsgInfo("Bairro editado com sucesso!");
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar editar os dados do usuário: "
                            + ex.getMessage());
        }
    }
    
}

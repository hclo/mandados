package br.com.mandado.bean;

import br.com.mandado.dao.BairroDAO;
import br.com.mandado.dao.LogradouroDAO;
import br.com.mandado.dao.MandadoDAO;
import br.com.mandado.domain.Bairro;
import br.com.mandado.domain.Logradouro;
import br.com.mandado.domain.Mandado;
import br.com.mandado.domain.Status;
import br.com.mandado.util.FacesUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Helen
 */
@ManagedBean
@ViewScoped
public class MandadoBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(MandadoBean.class.getName());
    private static final long serialVersionUID = 1L;

    private Mandado mandadoCadastro;
    private Logradouro logradouroCadastro;
    private Bairro bairroCadastro;

    private List<Mandado> listaMandados;
    private List<Mandado> listaMandadosFiltrados;

    private List<Logradouro> logradouros;
    private List<Bairro> bairros;

    private String acao;
    private Long codigo;

    /**
     * @return mandadoCadastro
     */
    public Mandado getMandadoCadastro() {
        return mandadoCadastro;
    }

    /**
     * @param mandadoCadastro
     */
    public void setMandadoCadastro(Mandado mandadoCadastro) {
        this.mandadoCadastro = mandadoCadastro;
    }

    public Logradouro getLogradouroCadastro() {
        return logradouroCadastro;
    }

    public void setLogradouroCadastro(Logradouro logradouroCadastro) {
        this.logradouroCadastro = logradouroCadastro;
    }

    public Bairro getBairroCadastro() {
        return bairroCadastro;
    }

    public void setBairroCadastro(Bairro bairroCadastro) {
        this.bairroCadastro = bairroCadastro;
    }

    /**
     * @return listaMandados
     */
    public List<Mandado> getListaMandados() {
        return listaMandados;
    }

    /**
     * @param listaMandados
     */
    public void setListaMandados(List<Mandado> listaMandados) {
        this.listaMandados = listaMandados;
    }

    /**
     * @return listaMandadosFiltrados
     */
    public List<Mandado> getListaMandadosFiltrados() {
        return listaMandadosFiltrados;
    }

    /**
     * @param listaMandadosFiltrados
     */
    public void setListaMandadosFiltrados(
            List<Mandado> listaMandadosFiltrados) {
        this.listaMandadosFiltrados = listaMandadosFiltrados;
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

    public List<Logradouro> getLogradouros() {
        return logradouros;
    }

    public void setLogradouros(List<Logradouro> logradouros) {
        this.logradouros = logradouros;
    }

    public List<Bairro> getBairros() {
        return bairros;
    }

    public void setBairros(List<Bairro> bairros) {
        this.bairros = bairros;
    }

    /*
     * Action handlers
     */
    public void selecionaBairro(AjaxBehaviorEvent event) {
        LogradouroDAO logradouroDAO = new LogradouroDAO();
        logradouros = logradouroDAO.listarPorBairro(bairroCadastro);
    }

    /**
     * Cria novo mandado
     */
    public void novo() {
        mandadoCadastro = new Mandado();
        logradouroCadastro = new Logradouro();
        bairroCadastro = new Bairro();
    }

    /**
     * Salva funcionário
     */
    public void salvar() {
        try {
            MandadoDAO mandadoDAO = new MandadoDAO();
            mandadoCadastro.setBairro(bairroCadastro);
            mandadoCadastro.setLogradouro(logradouroCadastro);
            Set<Status> status = new HashSet<>();
            status.add(Status.Aguardando);
            mandadoCadastro.setStatus(status);
            mandadoDAO.salvar(mandadoCadastro);

            mandadoCadastro = new Mandado();
            logradouroCadastro = new Logradouro();
            bairroCadastro = new Bairro();

            FacesUtil.adicionarMsgInfo("Mandado salvo com sucesso!");
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar incluir um mandado: "
                            + ex.getMessage());
        }
    }

    /**
     * Carrega lista de Mandados
     */
    public void carregarPesquisa() {
        try {
            MandadoDAO mandadoDAO = new MandadoDAO();
            listaMandados = mandadoDAO.listar();
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar listar os mandados: "
                            + ex.getMessage());
        }
    }

    /**
     * Carrega cadastro do mandado selecionado
     */
    public void carregarCadastro() {
        BairroDAO bairroDAO = new BairroDAO();
        bairros = bairroDAO.listar();
        try {
            if (codigo != null) {
                MandadoDAO mandadoDAO = new MandadoDAO();
                mandadoCadastro = mandadoDAO.buscarPorCodigo(codigo);
            } else {
                mandadoCadastro = new Mandado();
                mandadoCadastro.setCarga(new Date());
                Calendar cal = Calendar.getInstance();
                cal.setTime(mandadoCadastro.getCarga());
                cal.add(Calendar.DAY_OF_MONTH, 20);
                mandadoCadastro.setPrazo(cal.getTime());
                logradouroCadastro = new Logradouro();
                bairroCadastro = new Bairro();
            }
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar obter os dados do mandado: "
                            + ex.getMessage());
        }
    }

    /**
     * Exclui mandado selecionado
     */
    public void excluir() {
        try {
            MandadoDAO mandadoDAO = new MandadoDAO();
            mandadoDAO.excluir(mandadoCadastro);

            FacesUtil.adicionarMsgInfo("Mandado removido com sucesso!");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar remover o mandado: "
                    + ex.getMessage());
        }
    }

    /**
     * Edita mandado selecionado
     */
    public void editar() {
        try {
            MandadoDAO mandadoDAO = new MandadoDAO();
            mandadoDAO.editar(mandadoCadastro);

            FacesUtil.adicionarMsgInfo("Mandado editado com sucesso!");
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar editar os dados do mandado: "
                            + ex.getMessage());
        }
    }
}

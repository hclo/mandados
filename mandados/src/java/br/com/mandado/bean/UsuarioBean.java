package br.com.mandado.bean;

import br.com.mandado.dao.UsuarioDAO;
import br.com.mandado.domain.Usuario;
import br.com.mandado.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Helen
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(UsuarioBean.class.getName());
    private static final long serialVersionUID = 1L;

    private Usuario usuarioCadastro;

    private List<Usuario> listaUsuarios;
    private List<Usuario> listaUsuariosFiltrados;

    private String acao;
    private Long codigo;

    /**
     * @return funcionaCadastro
     */
    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    /**
     * @param usuarioCadastro
     */
    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    /**
     * @return listaUsuarios
     */
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios
     */
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    /**
     * @return listaUsuariosFiltrados
     */
    public List<Usuario> getListaUsuariosFiltrados() {
        return listaUsuariosFiltrados;
    }

    /**
     * @param listaUsuariosFiltrados
     */
    public void setListaUsuariosFiltrados(
            List<Usuario> listaUsuariosFiltrados) {
        this.listaUsuariosFiltrados = listaUsuariosFiltrados;
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
     * Cria novo funcionário
     */
    public void novo() {
        usuarioCadastro = new Usuario();
    }

    /**
     * Salva funcionário
     */
    public void salvar() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioCadastro.setSenha(DigestUtils.md5Hex(usuarioCadastro
                    .getSenha()));
            usuarioDAO.salvar(usuarioCadastro);

            usuarioCadastro = new Usuario();

            FacesUtil.adicionarMsgInfo("Usuario salvo com sucesso!");
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar incluir um usuário: "
                            + ex.getMessage());
        }
    }

    /**
     * Carrega lista de Usuarios
     */
    public void carregarPesquisa() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            listaUsuarios = usuarioDAO.listar();
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar listar os usuários: "
                            + ex.getMessage());
        }
    }

    /**
     * Carrega cadastro do funcionário selecionado
     */
    public void carregarCadastro() {
        try {
            if (codigo != null) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioCadastro = usuarioDAO.buscarPorCodigo(codigo);
            } else {
                usuarioCadastro = new Usuario();
            }
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar obter os dados do usuário: "
                            + ex.getMessage());
        }
    }

    /**
     * Exclui funcionário selecionado
     */
    public void excluir() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.excluir(usuarioCadastro);

            FacesUtil.adicionarMsgInfo("Usuario removido com sucesso!");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar remover o usuario: "
                    + ex.getMessage());
        }
    }

    /**
     * Edita funcionário selecionado
     */
    public void editar() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioCadastro.setSenha(DigestUtils.md5Hex(usuarioCadastro
                    .getSenha()));
            usuarioDAO.editar(usuarioCadastro);

            FacesUtil.adicionarMsgInfo("Usuario editado com sucesso!");
        } catch (RuntimeException ex) {
            FacesUtil
                    .adicionarMsgErro("Erro ao tentar editar os dados do usuário: "
                            + ex.getMessage());
        }
    }
}

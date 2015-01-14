package br.com.mandado.dao;

import br.com.mandado.domain.Usuario;
import br.com.mandado.util.HibernateUtil;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario
 */
public class UsuarioDAO {
    private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());

    /**
     *
     * @param usuario
     */
    public void salvar(Usuario usuario) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.save(usuario);
            transacao.commit();
        } catch (RuntimeException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            sessao.close();
        }
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Usuario> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Usuario> usuarios = null;

        try {
            Query consulta = sessao.getNamedQuery("Usuario.listar");
            usuarios = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }

        return usuarios;
    }

    /**
     * @param codigo
     * @return
     */
    public Usuario buscarPorCodigo(Long codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Usuario usuario = null;

        try {
            Query consulta = sessao
                    .getNamedQuery("Usuario.buscarPorCodigo");
            consulta.setLong("codigo", codigo);
            usuario = (Usuario) consulta.uniqueResult();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }

        return usuario;
    }

    /**
     * @param usuario
     */
    public void excluir(Usuario usuario) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.delete(usuario);
            transacao.commit();
        } catch (RuntimeException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            sessao.close();
        }
    }

    /**
     * @param usuario
     */
    public void editar(Usuario usuario) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.update(usuario);
            transacao.commit();
        } catch (RuntimeException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            sessao.close();
        }
    }

    /**
     * @param cpf
     * @param senha
     * @return
     */
    public Usuario autenticar(String cpf, String senha) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Usuario usuario = null;

        try {
            Query consulta = sessao.getNamedQuery("Usuario.autenticar");
            consulta.setString("cpf", cpf);
            consulta.setString("senha", senha);

            usuario = (Usuario) consulta.uniqueResult();

        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }
        return usuario;
    }
}

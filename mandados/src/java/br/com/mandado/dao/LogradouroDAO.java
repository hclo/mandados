package br.com.mandado.dao;

import br.com.mandado.domain.Bairro;
import br.com.mandado.domain.Logradouro;
import br.com.mandado.util.HibernateUtil;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Helen
 */
public class LogradouroDAO {
    private static final Logger LOG = Logger.getLogger(LogradouroDAO.class.getName());
        /**
     *
     * @param logradouro
     */
    public void salvar(Logradouro logradouro) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.save(logradouro);
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
    public List<Logradouro> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Logradouro> logradouros = null;

        try {
            Query consulta = sessao.getNamedQuery("Logradouro.listar");
            logradouros = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }

        return logradouros;
    }

    /**
     * @param codigo
     * @return
     */
    public Logradouro buscarPorCodigo(Long codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Logradouro logradouro = null;

        try {
            Query consulta = sessao
                    .getNamedQuery("Logradouro.buscarPorCodigo");
            consulta.setLong("codigo", codigo);
            logradouro = (Logradouro) consulta.uniqueResult();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }

        return logradouro;
    }

    /**
     * @param codigo
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Logradouro> listarPorBairro(Bairro bairro) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Logradouro> logradouros = null;

        try {
            Query consulta = sessao
                    .getNamedQuery("Logradouro.listarPorBairro");
            consulta.setEntity("bairro", bairro);
            logradouros = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }
        return logradouros;
    }
    
    /**
     * @param logradouro
     */
    public void excluir(Logradouro logradouro) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.delete(logradouro);
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
     * @param logradouro
     */
    public void editar(Logradouro logradouro) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.update(logradouro);
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
}

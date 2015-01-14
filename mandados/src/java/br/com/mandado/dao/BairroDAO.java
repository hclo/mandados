/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mandado.dao;

import br.com.mandado.domain.Bairro;
import br.com.mandado.util.HibernateUtil;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Helen
 */
public class BairroDAO {
    private static final Logger LOG = Logger.getLogger(BairroDAO.class.getName());
    /**
     *
     * @param bairro
     */
    public void salvar(Bairro bairro) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.save(bairro);
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
    public List<Bairro> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Bairro> bairros = null;

        try {
            Query consulta = sessao.getNamedQuery("Bairro.listar");
            bairros = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }

        return bairros;
    }

    /**
     * @param codigo
     * @return
     */
    public Bairro buscarPorCodigo(Long codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Bairro bairro = null;

        try {
            Query consulta = sessao
                    .getNamedQuery("Bairro.buscarPorCodigo");
            consulta.setLong("codigo", codigo);
            bairro = (Bairro) consulta.uniqueResult();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }

        return bairro;
    }

    /**
     * @param bairro
     */
    public void excluir(Bairro bairro) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.delete(bairro);
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
     * @param bairro
     */
    public void editar(Bairro bairro) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.update(bairro);
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

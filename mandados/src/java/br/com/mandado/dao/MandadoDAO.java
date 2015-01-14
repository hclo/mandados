/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mandado.dao;

import br.com.mandado.domain.Mandado;
import br.com.mandado.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Helen
 */
public class MandadoDAO {

    /**
     *
     * @param mandado
     */
    public void salvar(Mandado mandado) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.save(mandado);
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
    public List<Mandado> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Mandado> mandados = null;

        try {
            Query consulta = sessao.getNamedQuery("Mandado.listar");
            mandados = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }

        return mandados;
    }

    /**
     * @param codigo
     * @return
     */
    public Mandado buscarPorCodigo(Long codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Mandado mandado = null;

        try {
            Query consulta = sessao
                    .getNamedQuery("Mandado.buscarPorCodigo");
            consulta.setLong("codigo", codigo);
            mandado = (Mandado) consulta.uniqueResult();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }

        return mandado;
    }

    /**
     * @param mandado
     */
    public void excluir(Mandado mandado) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.delete(mandado);
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
     * @param mandado
     */
    public void editar(Mandado mandado) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.update(mandado);
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

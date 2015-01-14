/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mandado.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Usuario
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * @return
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Cria um SessionFactory a partir do hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            SessionFactory sessionFactory = configuration
                    .buildSessionFactory(serviceRegistry);

            return sessionFactory;
        } catch (Exception ex) {
            // Exibe uma mensagem de erro
            System.err.println("Falha ao tentar criar o SessionFactory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * @return
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

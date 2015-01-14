package br.com.mandado.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Helen Cristina
 *
 */
public class ContextListener implements ServletContextListener {

    /**
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.getSessionFactory().close();

    }

    /**
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateUtil.getSessionFactory().openSession();

    }
}

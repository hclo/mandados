package br.com.mandado.util;

import br.com.mandado.bean.AutenticacaoBean;
import br.com.mandado.domain.Usuario;
import java.util.Map;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * @author Helen Cristina
 *
 */
@SuppressWarnings("serial")
public class AutenticacaoPhaseListener implements PhaseListener {

    /**
     *
     * @param event
     */
    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        UIViewRoot uiViewRoot = facesContext.getViewRoot();
        String paginaAtual = uiViewRoot.getViewId();

        boolean ehPaginaAutenticacao = paginaAtual
                .contains("autenticacao.xhtml");

        if (!ehPaginaAutenticacao) {
            ExternalContext externalContext = facesContext.getExternalContext();
            Map<String, Object> mapa = externalContext.getSessionMap();
            AutenticacaoBean autenticacaoBean = (AutenticacaoBean) mapa
                    .get("autenticacaoBean");

            Usuario usuario = autenticacaoBean.getUsuarioLogado();

            if (usuario.getFuncao() == null) {
                FacesUtil.adicionarMsgErro("Usuário não autenticado.");

                Application application = facesContext.getApplication();
                NavigationHandler navigationHandler = application
                        .getNavigationHandler();
                navigationHandler.handleNavigation(facesContext, null,
                        "/pages/autenticacao.xhtml?faces-redirect=true");
            }
        }
    }

    /**
     *
     * @param event
     */
    
    @Override
    public void beforePhase(PhaseEvent event) {
    }

    /**
     *
     * @return
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}

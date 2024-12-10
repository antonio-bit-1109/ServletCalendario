package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// allo shoutdown del server la session viene resettata

@WebListener
public class SessionInvalidation implements ServletContextListener, HttpSessionListener {

    private static final Set<HttpSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // No initialization needed
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        synchronized (sessions) {
            for (HttpSession session : sessions) {
                session.invalidate();
            }
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        sessions.add(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        sessions.remove(se.getSession());
    }
}

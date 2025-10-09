package utils.bootstrap;

import config.DIContainer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppBootstrap implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DIContainer injector = DIContainer.getInstance();

        // TODO: create services instances
        // register them
        // injector.registerBean(Class<T>, Object);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // TODO: destroy all services 
    }


}

package utils.bootstrap;

import config.DIContainer;
import service.impl.DonorServiceImpl;
import service.interfaces.DonorService;
import utils.JPAUtils;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppBootstrap implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DIContainer injector = DIContainer.getInstance();

        injector.registerBean(EntityManagerFactory.class, JPAUtils.getEntityManagerFactory());

        // services
        DonorServiceImpl donorSer = new DonorServiceImpl();
        injector.registerBean(DonorServiceImpl.class, donorSer);
        injector.registerBean(DonorService.class, donorSer);

        // TODO: create services instances
        // register them
        // injector.registerBean(Class<T>, Object);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // TODO: destroy all services
        EntityManagerFactory emf = DIContainer.getInstance().getBean(EntityManagerFactory.class);
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }


}

package utils.bootstrap;

import config.DIContainer;
import dao.impl.DonorDaoImpl;
import dao.interfaces.DonorDao;
import mapper.DonorMapper;
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

        DonorDao donorDao = new DonorDaoImpl();
        DonorMapper donorMapper = new DonorMapper();
        DonorService donorService = new DonorServiceImpl(donorDao, donorMapper);

        // Entity manager bean
        injector.registerBean(EntityManagerFactory.class, JPAUtils.getEntityManagerFactory());

        // Donor Beans
        injector.registerBean(DonorDao.class, donorDao);
        injector.registerBean(DonorMapper.class, donorMapper);
        injector.registerBean(DonorService.class, donorService);

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

package utils.bootstrap;

import config.DIContainer;
import controller.DonationController;
import controller.DonorController;
import controller.RecipientController;
import dao.impl.DonationDaoImpl;
import dao.impl.DonorDaoImpl;
import dao.impl.RecipientDaoImpl;
import dao.interfaces.DonationDao;
import dao.interfaces.DonorDao;
import dao.interfaces.RecipientDao;
import mapper.DonationMapper;
import mapper.DonorMapper;
import mapper.RecipientMapper;
import service.impl.DonationServiceImpl;
import service.impl.DonorServiceImpl;
import service.impl.RecipientServiceImpl;
import service.interfaces.DonationService;
import service.interfaces.DonorService;
import service.interfaces.RecipientService;
import utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppBootstrap implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // em for dao's constructor
        EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        DIContainer injector = DIContainer.getInstance();



        DonorDao donorDao = new DonorDaoImpl(emf);
        DonorMapper donorMapper = new DonorMapper();
        DonorService donorService = new DonorServiceImpl(donorDao, donorMapper, em);
        DonorController donorController = new DonorController(donorService);

        RecipientDao recipientDao = new RecipientDaoImpl(em);
        RecipientMapper recipientMapper = new RecipientMapper();
        RecipientService recipientService = new RecipientServiceImpl(recipientDao, recipientMapper, em);
        RecipientController recipientController = new RecipientController(recipientService);

        DonationDao donationDao = new DonationDaoImpl(em);
        DonationMapper donationMapper = new DonationMapper();
        DonationService donationService = new DonationServiceImpl(donationDao, donationMapper);
        DonationController donationController = new DonationController(donationService);

        // Entity manager bean
        injector.registerBean(EntityManagerFactory.class, JPAUtils.getEntityManagerFactory());

        // Donor Beans
        injector.registerBean(DonorDao.class, donorDao);
        injector.registerBean(DonorMapper.class, donorMapper);
        injector.registerBean(DonorService.class, donorService);
        injector.registerBean(DonorController.class, donorController);

        //Recipient Beans
        injector.registerBean(RecipientDao.class, recipientDao);
        injector.registerBean(RecipientMapper.class, recipientMapper);
        injector.registerBean(RecipientService.class, recipientService);
        injector.registerBean(RecipientController.class, recipientController);

        //Donation Beans
        injector.registerBean(DonationDao.class, donationDao);
        injector.registerBean(DonationMapper.class, donationMapper);
        injector.registerBean(DonationService.class, donationService);
        injector.registerBean(DonationController.class, donationController);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // TODO: destroy all services
        EntityManagerFactory emf = DIContainer.getInstance().getBean(EntityManagerFactory.class);
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }


}

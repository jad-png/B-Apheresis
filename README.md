# B-Apheresis

## Project Structure

```
B-Apheresis/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── Main.java
│   │   │   ├── config/
│   │   │   │   ├── DIContainer.java
│   │   │   │   └── LoggerConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── DonationController.java
│   │   │   │   ├── DonorController.java
│   │   │   │   └── RecipientController.java
│   │   │   ├── dao/
│   │   │   │   ├── interfaces/
│   │   │   │   │   ├── DonationDao.java
│   │   │   │   │   ├── DonorDao.java
│   │   │   │   │   └── RecipientDao.java
│   │   │   │   └── impl/
│   │   │   │       ├── DonationDaoImpl.java
│   │   │   │       ├── DonorDaoImpl.java
│   │   │   │       └── RecipientDaoImpl.java
│   │   │   ├── dto/
│   │   │   │   ├── DonationDTO.java
│   │   │   │   ├── DonorDTO.java
│   │   │   │   └── RecipientDTO.java
│   │   │   ├── entity/
│   │   │   │   ├── Account.java
│   │   │   │   ├── Donation.java
│   │   │   │   ├── Donor.java
│   │   │   │   ├── Recipient.java
│   │   │   │   └── enums/
│   │   │   │       ├── BloodType.java
│   │   │   │       ├── Gender.java
│   │   │   │       ├── MedicalCondition.java
│   │   │   │       ├── Situation.java
│   │   │   │       ├── State.java
│   │   │   │       └── Status.java
│   │   │   ├── mapper/
│   │   │   │   ├── DonationMapper.java
│   │   │   │   ├── DonorMapper.java
│   │   │   │   └── RecipientMapper.java
│   │   │   ├── service/
│   │   │   │   ├── interfaces/
│   │   │   │   │   ├── DonationService.java
│   │   │   │   │   ├── DonorService.java
│   │   │   │   │   └── RecipientService.java
│   │   │   │   └── impl/
│   │   │   │       ├── DonationServiceImpl.java
│   │   │   │       ├── DonorServiceImpl.java
│   │   │   │       ├── MatchingService.java
│   │   │   │       └── RecipientServiceImpl.java
│   │   │   ├── servlets/
│   │   │   │   └── HelloServlet.java
│   │   │   └── utils/
│   │   │       ├── JPAUtils.java
│   │   │       ├── Loggable.java
│   │   │       ├── LoggerUtil.java
│   │   │       ├── Router.java
│   │   │       └── bootstrap/
│   │   │           └── AppBootstrap.java
│   │   └── resources/
│   │       ├── logback.xml
│   │       └── META-INF/
│   │           └── persistence.xml
│   │   └── webapp/
│   │     ├── index.jsp                  # Homepage / landing page
│   │     ├── resources/                 # Static resources (CSS, JS, images)
│   │     │    ├── css/
│   │     │    │    └── styles.css
│   │     │    ├── js/
│   │     │    │    └── scripts.js
│   │     │    └── images/
│   │     └── WEB-INF/
│   │          ├── views/
│   │          │    ├── donors/          # JSPs related to Donor functionality
│   │          │    │    ├── list.jsp
│   │          │    │    ├── create.jsp
│   │          │    │    └── edit.jsp
│   │          │    ├── recipients/      # JSPs related to Recipient functionality
│   │          │    │    ├── list.jsp
│   │          │    │    ├── create.jsp
│   │          │    │    └── edit.jsp
│   │          │    ├── common/          # Shared JSP fragments (headers, footers, navbars)
│   │          │    │    ├── header.jsp
│   │          │    │    ├── footer.jsp
│   │          │    │    └── navbar.jsp
│   │          │    ├── error.jsp        # Generic error page
│   │          │    └── dashboard.jsp    # Optional dashboard page for analytics
│   │          ├── lib/                  # JSP tag libraries if any
│   │          └── web.xml                # Servlet and filter configuration, no annotations
│   │            
│   │           
│   └── test/
│       ├── java/
│       │   └── integration/
│       │       ├── DonorServiceIntegrationTest.java
│       │       ├── IntegrationTestBase.java
│       │       └── RecipientIntegratedTest.java
│       └── resources/
│           ├── logback.xml
│           └── META-INF/
│               └── persistence.xml
├── logs/
├── target/
└── README.md
```

## Key Changes

- **Modular Structure:** The codebase is now organized by domain (controller, dao, dto, entity, mapper, service, utils).
- **DAO Layer:** `dao/interfaces` and `dao/impl` separate interfaces from implementations for data access.
- **Service Layer:** `service/interfaces` and `service/impl` separate business logic interfaces from implementations.
- **Entity Layer:** All JPA entities and enums are under `entity/` and `entity/enums/`.
- **DTOs and Mappers:** DTOs and their mappers are in dedicated folders.
- **Controllers:** Controllers for Donor, Recipient, and Donation are in `controller/`.
- **Utilities:** Common utilities (logging, JPA, routing, DI) are in `utils/`.
- **Servlets:** Java servlets are in `servlets/`.
- **Web Resources:** JSPs and web.xml are under `webapp/`.
- **Test Structure:** Integration tests and test resources are under `test/`.

## Build & Run

- Use Maven to build:
  ```sh
  mvn clean install
  ```
- Main entry point for testing JPA/Hibernate: [`Main.java`](src/main/java/Main.java)
- Web application entry: Deploy the WAR to a servlet container (e.g., Tomcat).

## Logging & Persistence

- Logging is configured via `logback.xml` in `src/main/resources`.
- JPA persistence is configured in `src/main/resources/META-INF/persistence.xml`.

## Testing

- Integration tests are in `src/test/java/integration/`.
- Test resources (H2 DB, logback) are in `src/test/resources/`.

---
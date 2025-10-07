```shell
    B-Apheresis/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── apheresis/
│   │   │           ├── config/
│   │   │           │   ├── DatabaseConfig.java         # Singleton DB connection
│   │   │           │   ├── LoggerConfig.java           # Config for custom logger
│   │   │           │   ├── MapperConfig.java           # Entity ↔ DTO mapping setup
│   │   │           │   └── DIContainer.java            # Manual dependency injector
│   │   │           │
│   │   │           ├── entity/
│   │   │           │   ├── Donor.java                  # Example entity
│   │   │           │   ├── Recipient.java
│   │   │           │   └── Appointment.java
│   │   │           │
│   │   │           ├── dto/
│   │   │           │   ├── DonorDTO.java
│   │   │           │   ├── RecipientDTO.java
│   │   │           │   └── AppointmentDTO.java
│   │   │           │
│   │   │           ├── dao/
│   │   │           │   ├── DonorDAO.java
│   │   │           │   ├── RecipientDAO.java
│   │   │           │   ├── AppointmentDAO.java
│   │   │           │   └── impl/
│   │   │           │       ├── DonorDAOImpl.java
│   │   │           │       ├── RecipientDAOImpl.java
│   │   │           │       └── AppointmentDAOImpl.java
│   │   │           │
│   │   │           ├── service/
│   │   │           │   ├── DonorService.java
│   │   │           │   ├── RecipientService.java
│   │   │           │   ├── AppointmentService.java
│   │   │           │   └── impl/
│   │   │           │       ├── DonorServiceImpl.java
│   │   │           │       ├── RecipientServiceImpl.java
│   │   │           │       └── AppointmentServiceImpl.java
│   │   │           │
│   │   │           ├── controller/
│   │   │           │   ├── DonorController.java
│   │   │           │   ├── RecipientController.java
│   │   │           │   └── AppointmentController.java
│   │   │           │
│   │   │           └── utils/
│   │   │               ├── ValidationUtils.java
│   │   │               ├── DateUtils.java
│   │   │               ├── Constants.java
│   │   │               └── LoggerUtils.java
│   │   │
│   │   └── webapp/
│   │       ├── index.jsp
│   │       ├── donor/
│   │       │   ├── list.jsp
│   │       │   ├── form.jsp
│   │       │   └── details.jsp
│   │       ├── recipient/
│   │       │   ├── list.jsp
│   │       │   ├── form.jsp
│   │       │   └── details.jsp
│   │       ├── appointment/
│   │       │   ├── list.jsp
│   │       │   ├── form.jsp
│   │       │   └── details.jsp
│   │       └── WEB-INF/
│   │           └── web.xml
│   │
│   └── test/
│       └── org/apheresis/
│           └── service/
│               └── DonorServiceTest.java
└── target/

```
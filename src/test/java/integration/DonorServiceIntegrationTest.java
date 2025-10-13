package integration;

import dao.impl.DonorDaoImpl;
import dao.interfaces.DonorDao;
import dto.DonorDTO;
import entity.enums.BloodType;
import entity.enums.Gender;
import mapper.DonorMapper;
import org.junit.jupiter.api.Test;
import service.impl.DonorServiceImpl;
import service.interfaces.DonorService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DonorServiceIntegrationTest extends IntegrationTestBase {

    @Test
    void testCreateAndRetrieveDonor() {
        DonorDao dao = new DonorDaoImpl(em);
        DonorMapper mapper = new DonorMapper();
        DonorService service = new DonorServiceImpl(dao, mapper);

        DonorDTO dto = new DonorDTO();
        dto.setCin("HH421");
        dto.setFirstName("Test");
        dto.setLastName("Test");
        dto.setBloodType(BloodType.A_POS);
        dto.setGender(Gender.MALE);
        dto.setBirthday(LocalDate.of(1995, 5, 20));

        DonorDTO saved = service.createDonor(dto);
        assertThat(saved.getId()).isNotNull();

        List<DonorDTO> allDonors = service.getAllDonors();
        assertThat(allDonors).hasSize(1);

        Optional<DonorDTO> donor = service.getDonorById(saved.getId());
        assertThat(donor).isPresent();
        assertThat(donor.get().getFirstName()).isEqualTo(saved.getFirstName());
    }

    @Test
    void testUpdateAndDeleteDonor() {
        DonorDao dao = new DonorDaoImpl(em);
        DonorMapper mapper = new DonorMapper();
        DonorService service = new DonorServiceImpl(dao, mapper);

        DonorDTO dto = new DonorDTO();
        dto.setCin("CC987");
        dto.setFirstName("younes");
        dto.setLastName("Ben Said");
        dto.setBloodType(BloodType.O_NEG);
        dto.setGender(Gender.FEMALE);
        dto.setBirthday(LocalDate.of(2004, 5, 20));

        DonorDTO saved = service.createDonor(dto);
        saved.setLastName("updated");

        DonorDTO updated = service.updateDonor(saved);
        assertThat(updated.getLastName()).isEqualTo("updated");

        service.deleteDonor(updated.getId());
        assertThat(service.getAllDonors()).hasSize(0);
    }
}

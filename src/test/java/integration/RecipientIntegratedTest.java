package integration;

import dao.impl.RecipientDaoImpl;
import dao.interfaces.RecipientDao;
import dto.RecipientDTO;
import entity.enums.BloodType;
import entity.enums.Gender;
import entity.enums.Situation;
import entity.enums.State;
import mapper.RecipientMapper;
import org.junit.jupiter.api.Test;
import service.impl.RecipientServiceImpl;
import service.interfaces.RecipientService;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RecipientIntegratedTest extends IntegrationTestBase {

    @Test
    void testSaveAndFindRecipient() {
        RecipientDao dao = new RecipientDaoImpl(em);
        RecipientMapper mapper = new RecipientMapper();
        RecipientService service = new RecipientServiceImpl(dao, mapper, em);

        RecipientDTO dto = new RecipientDTO();
        dto.setCin("CC987");
        dto.setFirstName("younes");
        dto.setLastName("Ben Said");
        dto.setBloodType(BloodType.O_NEG);
        dto.setSituation(Situation.URGENT);
        dto.setState(State.WAITING);
        dto.setGender(Gender.FEMALE);
        dto.setBirthday(LocalDate.of(2004, 5, 20));

        RecipientDTO saved = service.saveRecipient(dto);
        assertThat(saved).isNotNull();

        List<RecipientDTO> recipients = service.getAllRecipients();
        assertThat(recipients).hasSize(1);

        Optional<RecipientDTO> recipient = service.getRecipient(saved.getId());
        assertThat(recipient).isPresent();
        assertThat(recipient.get().getFirstName()).isEqualTo("younes");

    }
}

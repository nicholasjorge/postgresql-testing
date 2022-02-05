package dev.georgetech;

import dev.georgetech.model.Dance;
import dev.georgetech.repository.DanceRepository;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class DatabaseIT {

  @Autowired
  private DanceRepository danceRepository;

  @Autowired
  private TestEntityManager testEntityManager;


  @Test
  void injectedComponentsAreNotNull() {
    Assertions.assertThat(danceRepository).isNotNull();
    Assertions.assertThat(testEntityManager).isNotNull();
  }

  @Test
  void shouldReturnEmptyRecords() {
    List<Dance> dances = danceRepository.findAll();

    Assertions.assertThat(dances).isEmpty();
  }

  @Test
  void shouldSaveDanceInTheDb() {
    Dance chaCha = new Dance("Cha Cha");

    Dance save = danceRepository.saveAndFlush(chaCha);

    Assertions.assertThat(save.getId()).isNotNull();
  }


  @Test
  void shouldFindInDbById() {

    Dance samba = testEntityManager.persistAndFlush(new Dance("Samba"));

    Optional<Dance> dance = danceRepository.findById(samba.getId());

    Assertions.assertThat(dance).isNotEmpty();
  }


  @Test
  void shouldSaveDancesAsBatch() {
    Dance samba = new Dance("Samba");
    Dance jive = new Dance("Jive");
    Dance chaCha = new Dance("Cha Cha");
    Dance rumba = new Dance("Rumba");
    Dance pasoDoble = new Dance("Paso Doble");

    List<Dance> dances = danceRepository.saveAllAndFlush(
        List.of(samba, rumba, chaCha, pasoDoble, jive));

    Assertions.assertThat(dances).isNotEmpty();
  }

  @Test
  void shouldDeleteById() {

    Dance samba = testEntityManager.persistAndFlush(new Dance("Samba"));

    Assumptions.assumeThat(samba.getId()).isNotNull();

    danceRepository.deleteById(samba.getId());

    Assertions.assertThat(danceRepository.findById(samba.getId())).isEmpty();
  }

}

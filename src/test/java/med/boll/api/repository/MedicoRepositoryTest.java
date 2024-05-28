package med.boll.api.repository;

import med.boll.api.dto.endereco.DadosEndereco;
import med.boll.api.dto.medico.DadosCadastroMedico;
import med.boll.api.dto.medico.Especialidade;
import med.boll.api.dto.paciente.DadosCadastroPaciente;
import med.boll.api.model.Consulta;
import med.boll.api.model.Medico;
import med.boll.api.model.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
// -----------------------------------------------------------------------------------------------------------------------------------------------
// Como estou usando o banco H2 então essas configurações não desnecessarias. Sendo utilizadas apenas em bandos de dados que não sejam em memória.
// Apaguei também o profile application-test.properties pois o Spring já configura automaticamente no H2
// -----------------------------------------------------------------------------------------------------------------------------------------------
// Tira a configuração padrão do Spring de executar o teste em um DB em memória
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Indica o profile do banco de dados de teste
//@ActiveProfiles("test")
class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deveria devolver null quando único médico cadastrado não está disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        // given or arrange
        LocalDateTime proximaSegundaAsDez = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        // when ir act
        Medico medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        Paciente paciente = cadastrarPaciente("Paciente", "paciente@voll.med");
        cadastrarConsulta(medico, paciente, proximaSegundaAsDez);

        // then or assert
        Medico medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        LocalDateTime proximaSegundaAsDez = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        Medico medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        Medico medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        entityManager.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        Medico medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email) {
        Paciente paciente = new Paciente(dadosPaciente(nome, email));
        entityManager.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "61999999999",
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}
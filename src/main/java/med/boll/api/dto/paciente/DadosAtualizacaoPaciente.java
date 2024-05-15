package med.boll.api.dto.paciente;

import jakarta.validation.constraints.NotNull;
import med.boll.api.dto.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(@NotNull Long id, String nome, String email, String telefone, DadosEndereco endereco) {
}

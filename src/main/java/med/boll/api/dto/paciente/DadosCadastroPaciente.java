package med.boll.api.dto.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.boll.api.dto.endereco.DadosEndereco;

public record DadosCadastroPaciente(
    @NotBlank
    String nome,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String telefone,
    @NotNull
    @Valid
    DadosEndereco endereco){
}

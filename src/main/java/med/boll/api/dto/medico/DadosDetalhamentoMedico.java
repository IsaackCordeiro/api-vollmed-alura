package med.boll.api.dto.medico;

import med.boll.api.model.Endereco;
import med.boll.api.model.Medico;

public record DadosDetalhamentoMedico(Long id, String nome, String crm, String telefone, Especialidade especialidade, Endereco endereco) {
    public DadosDetalhamentoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}

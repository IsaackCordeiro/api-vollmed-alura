package med.boll.api.dto.paciente;


import med.boll.api.model.Paciente;

public record DadosListagemPaciente(Long id, String nome, String email, String telefone) {
    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone());
    }
}

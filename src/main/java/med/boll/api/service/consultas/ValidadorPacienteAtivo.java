package med.boll.api.service.consultas;

import med.boll.api.dto.consulta.DadosAgendamentoConsulta;
import med.boll.api.exception.ValidacaoException;
import med.boll.api.model.Paciente;
import med.boll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo  implements ValidadorAgendamentoDeConsultas {
    @Autowired
    PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados){
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        if(!paciente.isAtivo()){
            throw new ValidacaoException("A consulta não pode ser agendada por um paciente excluído");
        }
    }
}

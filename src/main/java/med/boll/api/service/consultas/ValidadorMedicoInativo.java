package med.boll.api.service.consultas;

import med.boll.api.dto.consulta.DadosAgendamentoConsulta;
import med.boll.api.exception.ValidacaoException;
import med.boll.api.model.Medico;
import med.boll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoInativo  implements ValidadorAgendamentoDeConsultas {
    @Autowired
    MedicoRepository medicoRepository;
    public void validar(DadosAgendamentoConsulta dados){
        Medico medico = medicoRepository.getReferenceById(dados.idMedico());
        if(!medico.isAtivo()){
            throw new ValidacaoException("Não é permitido marcar consulta com médicos inativos");
        }
    }
}

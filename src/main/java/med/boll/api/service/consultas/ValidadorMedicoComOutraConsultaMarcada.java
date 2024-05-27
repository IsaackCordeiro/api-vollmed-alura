package med.boll.api.service.consultas;

import med.boll.api.dto.consulta.DadosAgendamentoConsulta;
import med.boll.api.exception.ValidacaoException;
import med.boll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaMarcada  implements ValidadorAgendamentoDeConsultas {
    @Autowired
    ConsultaRepository consultaRepository;
    public void validar(DadosAgendamentoConsulta dados){
        boolean medicoComOutraConsultaMarcada = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if(medicoComOutraConsultaMarcada){
            throw new ValidacaoException("O médico selecionado possui outra consultada agendada nesse mesmo horário");
        }
    }
}

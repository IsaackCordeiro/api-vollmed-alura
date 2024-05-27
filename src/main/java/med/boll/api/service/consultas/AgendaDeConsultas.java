package med.boll.api.service.consultas;

import med.boll.api.dto.consulta.DadosAgendamentoConsulta;
import med.boll.api.dto.consulta.DadosCancelamentoConsulta;
import med.boll.api.dto.consulta.DadosDetalhamentoConsulta;
import med.boll.api.exception.ValidacaoException;
import med.boll.api.model.Consulta;
import med.boll.api.model.Medico;
import med.boll.api.model.Paciente;
import med.boll.api.repository.ConsultaRepository;
import med.boll.api.repository.MedicoRepository;
import med.boll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    // Spring injeta todos os validadores que implementa a interface
    private List<ValidadorAgendamentoDeConsultas> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do médico informado não existe");
        }

        validadores.forEach(v -> v.validar(dados));

        Medico medico = escolherMedico(dados);

        if(medico == null){
            throw new ValidacaoException("Não existe médico disponível nessa data");
        }

        Paciente paciente = pacienteRepository.findById(dados.idPaciente()).get();
        Consulta consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatório quando o médico não for escolhido");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados){
        if (!consultaRepository.existsById(dados.id())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        Consulta consulta = consultaRepository.getReferenceById(dados.id());
        consulta.cancelar(dados.motivo());
    }
}

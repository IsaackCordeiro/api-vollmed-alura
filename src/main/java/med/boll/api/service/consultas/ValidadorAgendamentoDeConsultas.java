package med.boll.api.service.consultas;

import med.boll.api.dto.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsultas {
    public void validar(DadosAgendamentoConsulta dados);
}

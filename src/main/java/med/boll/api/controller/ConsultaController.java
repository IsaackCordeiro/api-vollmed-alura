package med.boll.api.controller;

// Trecho de código suprimido

import jakarta.validation.Valid;
import med.boll.api.dto.consulta.DadosAgendamentoConsulta;
import med.boll.api.dto.consulta.DadosCancelamentoConsulta;
import med.boll.api.dto.consulta.DadosDetalhamentoConsulta;
import med.boll.api.model.Consulta;
import med.boll.api.repository.ConsultaRepository;
import med.boll.api.service.consultas.AgendaDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        DadosDetalhamentoConsulta dadosDetalhamentoConsulta = agenda.agendar(dados);
        return ResponseEntity.ok(dadosDetalhamentoConsulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){
        agenda.cancelar(dados);

        return ResponseEntity.noContent().build(); // Devolve código 204 - No Content
    }
}

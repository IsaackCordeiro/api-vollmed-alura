package med.boll.api.repository;

import med.boll.api.dto.medico.Especialidade;
import med.boll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    public Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query("""
            select medicos
              from Medico medicos
             where medicos.especialidade = :especialidade
               and medicos.ativo = true
               and not exists (select 1
                                 from Consulta consultas
                                where consultas.medico.id = medicos.id
                                  and consultas.data = :data)
            order by rand()
            limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

}

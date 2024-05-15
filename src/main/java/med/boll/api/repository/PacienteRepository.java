package med.boll.api.repository;

import med.boll.api.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    public Page<Paciente> findAllByAtivoTrue(Pageable pageable);
}

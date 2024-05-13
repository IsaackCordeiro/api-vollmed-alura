package med.boll.api.repository;

import med.boll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    public Page<Medico> findAllByAtivoTrue(Pageable pageable);
}

package br.com.desafio.cadastroprocessos.repository;

import br.com.desafio.cadastroprocessos.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {
}

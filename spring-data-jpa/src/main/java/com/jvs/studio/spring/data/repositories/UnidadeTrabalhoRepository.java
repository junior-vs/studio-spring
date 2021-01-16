package com.jvs.studio.spring.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jvs.studio.spring.data.orm.model.UnidadeTrabalho;

@Repository
public interface UnidadeTrabalhoRepository extends JpaRepository<UnidadeTrabalho, Integer> {
}

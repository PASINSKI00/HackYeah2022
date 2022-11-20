package com.pasinski.sl.backend.task.proof;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProofTypeRepository extends JpaRepository<ProofType, Long> {
    ProofType findByName(String name);
}

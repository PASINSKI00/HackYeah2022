package com.pasinski.sl.backend.task.proof;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ProofType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_proof_type", nullable = false)
    private Long idProofType;
    String name;
}

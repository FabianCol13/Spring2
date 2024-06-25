package com.fabiancol.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idDepartamento;
    String nombreDepartamento;
}

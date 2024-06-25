package com.fabiancol.modelo;
 // @author FabianCol
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
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idEmpleado;
    String nombreEmpleado;
   @ManyToOne
    @JoinColumn(name = "departamento_id")
    Departamento departamento;


    Double sueldoEmpleado;
}


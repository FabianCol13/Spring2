package com.fabiancol.repositorio;

//@author FabianCol

import com.fabiancol.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IEmpleadoRepositorio extends JpaRepository<Empleado, Integer> {

}

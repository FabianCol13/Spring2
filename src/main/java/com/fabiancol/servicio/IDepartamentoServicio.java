package com.fabiancol.servicio;

import com.fabiancol.modelo.Departamento;

import java.util.List;

public interface IDepartamentoServicio {
    Departamento buscarDepartamentoPorId(Integer id);
    List<Departamento> listarDepartamentos();
    Departamento guardarDepartamento(Departamento departamento);
    void eliminarDepartamento(Departamento departamento);
}

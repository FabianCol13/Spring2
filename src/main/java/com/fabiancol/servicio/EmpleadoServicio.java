package com.fabiancol.servicio;
// @author FabianCol

import com.fabiancol.modelo.Empleado;
import com.fabiancol.repositorio.IEmpleadoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServicio implements IEmpleadoServicio {

    @Autowired
    private IEmpleadoRepositorio empleadoRepositorio;

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepositorio.findAll();
    }

    @Override
    public Empleado buscarEmpleadoPorId(Integer idEmpleado) {
        Empleado empleado = empleadoRepositorio.findById(idEmpleado).orElse(null);
        return empleado;
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepositorio.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepositorio.delete(empleado);
    }

}

package com.fabiancol.controlador;
// @author FabianCol

import com.fabiancol.excepcion.ExcepcionRecursoNoEncontrado;
import com.fabiancol.modelo.Departamento;
import com.fabiancol.modelo.Empleado;
import com.fabiancol.servicio.IDepartamentoServicio;
import com.fabiancol.servicio.IEmpleadoServicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("rh-sena")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoControlador {

    private static final Logger logger
            = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;
    
      @Autowired
    private IDepartamentoServicio departamentoServicio;

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados() {
        var empleados = empleadoServicio.listarEmpleados();
        empleados.forEach((empleado -> logger.info(empleado.toString())));
        return empleados;
    }
    
     @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado) {
        logger.info("Empleado a agregar: " + empleado);
        validarYEstablecerDepartamento(empleado);
        return empleadoServicio.guardarEmpleado(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> consultarEmpleadoId(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del empleado: " + id);
        }
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> ModificarEmpleadoId(@PathVariable Integer id, @RequestBody Empleado empleadoObj) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del empleado: " + id);
        }
        empleado.setNombreEmpleado(empleadoObj.getNombreEmpleado());
        empleado.setSueldoEmpleado(empleadoObj.getSueldoEmpleado());

        // Validar y establecer el nuevo departamento
        validarYEstablecerDepartamento(empleadoObj);
        empleado.setDepartamento(empleadoObj.getDepartamento());

        empleadoServicio.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> EliminarEmpleadoId(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del empleado: " + id);
        }
        empleadoServicio.eliminarEmpleado(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    private void validarYEstablecerDepartamento(Empleado empleado) {
        if (empleado.getDepartamento() != null) {
            Integer departamentoId = empleado.getDepartamento().getIdDepartamento();
            if (departamentoId != null) {
                Departamento departamento = departamentoServicio.buscarDepartamentoPorId(departamentoId);
                if (departamento == null) {
                    throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del departamento: " + departamentoId);
                }
                empleado.setDepartamento(departamento);
            }
        }
    }
    
    
/*
    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado) {
        logger.info("Emplado a agregar: " + empleado);
        return empleadoServicio.guardarEmpleado(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> consultarEmpleadoId(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontro el Id del empleado" + id);
        }
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> ModificarEmpleadoId(@PathVariable Integer id, @RequestBody Empleado empleadoObj) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontro el Id del empleado" + id);
        }
        empleado.setNombreEmpleado(empleadoObj.getNombreEmpleado());
        empleado.setDepartamentoEmpleado(empleadoObj.getDepartamentoEmpleado());
        empleado.setSueldoEmpleado(empleadoObj.getSueldoEmpleado());
        empleadoServicio.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);

    }
    
        @DeleteMapping("/empleados/{id}")
        public ResponseEntity<Map<String,Boolean>> EliminarEmpleadoId(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
          if (empleado == null) 
            throw new ExcepcionRecursoNoEncontrado("No se encontro el Id del empleado" + id);
        empleadoServicio.eliminarEmpleado(empleado);
        Map<String,Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
        }
*/
}

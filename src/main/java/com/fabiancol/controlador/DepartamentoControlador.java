package com.fabiancol.controlador;

import com.fabiancol.excepcion.ExcepcionRecursoNoEncontrado;
import com.fabiancol.modelo.Departamento;
import com.fabiancol.servicio.IDepartamentoServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rh-sena")
@CrossOrigin(value = "http://localhost:3000")
public class DepartamentoControlador {

    @Autowired
    private IDepartamentoServicio departamentoServicio;

    @GetMapping("/departamentos")
    public List<Departamento> obtenerDepartamentos() {
        return departamentoServicio.listarDepartamentos();
    }

    @GetMapping("/departamentos/{id}")
    public ResponseEntity<Departamento> consultarDepartamentoId(@PathVariable Integer id) {
        Departamento departamento = departamentoServicio.buscarDepartamentoPorId(id);
        if (departamento == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del departamento: " + id);
        }
        return ResponseEntity.ok(departamento);
    }

    @PostMapping("/departamentos")
    public Departamento agregarDepartamento(@RequestBody Departamento departamento) {
        return departamentoServicio.guardarDepartamento(departamento);
    }

    @PutMapping("/departamentos/{id}")
    public ResponseEntity<Departamento> modificarDepartamentoId(@PathVariable Integer id, @RequestBody Departamento departamentoObj) {
        Departamento departamento = departamentoServicio.buscarDepartamentoPorId(id);
        if (departamento == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del departamento: " + id);
        }
        departamento.setNombreDepartamento(departamentoObj.getNombreDepartamento());
        Departamento actualizado = departamentoServicio.guardarDepartamento(departamento);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/departamentos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarDepartamentoId(@PathVariable Integer id) {
        Departamento departamento = departamentoServicio.buscarDepartamentoPorId(id);
        if (departamento == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del departamento: " + id);
        }
        departamentoServicio.eliminarDepartamento(departamento);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}

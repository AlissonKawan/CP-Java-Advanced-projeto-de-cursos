package br.com.alisson.cp2.sistemacursos.controller;


import br.com.alisson.cp2.sistemacursos.dto.ModuloRequest;
import br.com.alisson.cp2.sistemacursos.dto.ModuloResponse;
import br.com.alisson.cp2.sistemacursos.dto.PageResponse;
import br.com.alisson.cp2.sistemacursos.service.ModuloService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ModuloController {

    private final ModuloService moduloService;

    public ModuloController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    @PostMapping("/api/cursos/{cursoId}/modulos")
    public ResponseEntity<ModuloResponse> cadastrarModulo(@PathVariable Long cursoId,@Valid @RequestBody ModuloRequest moduloRequest) {
        ModuloResponse moduloCriado = moduloService.cadastrarModulo(cursoId, moduloRequest);

        URI location = URI.create("/api/modulos/" + moduloCriado.id());

        return ResponseEntity.created(location).body(moduloCriado);
    }

    @GetMapping("/api/modulos/{id}")
    public ResponseEntity<ModuloResponse> buscarModuloPorId(@PathVariable Long id) {
        ModuloResponse modulo = moduloService.buscarModuloPorId(id);
        return ResponseEntity.ok(modulo);
    }

    @GetMapping("/api/cursos/{cursoId}/modulos")
    public ResponseEntity<PageResponse<ModuloResponse>> listarModulosPorCurso(@PathVariable Long cursoId, Pageable pageable) {
        PageResponse<ModuloResponse> pagina = moduloService.listarModulosPorCurso(cursoId, pageable);
        return ResponseEntity.ok(pagina);
    }

    @DeleteMapping("/api/modulos/{id}")
    public ResponseEntity<Void> excluirModulo(@PathVariable Long id) {
        moduloService.excluirModulo(id);
        return ResponseEntity.noContent().build();
    }
}

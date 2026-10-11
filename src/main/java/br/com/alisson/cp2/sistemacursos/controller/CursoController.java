package br.com.alisson.cp2.sistemacursos.controller;


import br.com.alisson.cp2.sistemacursos.dto.CursoRequest;
import br.com.alisson.cp2.sistemacursos.dto.CursoResponse;
import br.com.alisson.cp2.sistemacursos.dto.PageResponse;
import br.com.alisson.cp2.sistemacursos.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CursoResponse> cadastrarCurso(@Valid @RequestBody CursoRequest cursoRequest) {
        CursoResponse cursoCriado = service.cadastrarCurso(cursoRequest);

        URI location = URI.create("/api/cursos/" + cursoCriado.id());

        return ResponseEntity.created(location).body(cursoCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> buscarCursoPorId(@PathVariable Long id) {
        CursoResponse curso = service.buscarCursoPorId(id);
        return ResponseEntity.ok(curso);
    }

    @GetMapping
    public ResponseEntity<PageResponse<CursoResponse>> listarCursos(Pageable pageable) {
        PageResponse<CursoResponse> pagina = service.listarCursos(pageable);
        return ResponseEntity.ok(pagina);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> atualizarCurso(@PathVariable Long id, @Valid @RequestBody CursoRequest cursoRequest) {
        CursoResponse cursoAtualizado = service.atualizarCurso(id, cursoRequest);
        return ResponseEntity.ok(cursoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCurso(@PathVariable Long id) {
        service.excluirCurso(id);
        return ResponseEntity.noContent().build();
    }



}

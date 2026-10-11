package br.com.alisson.cp2.sistemacursos.controller;


import br.com.alisson.cp2.sistemacursos.dto.CursoRequest;
import br.com.alisson.cp2.sistemacursos.dto.CursoResponse;
import br.com.alisson.cp2.sistemacursos.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}

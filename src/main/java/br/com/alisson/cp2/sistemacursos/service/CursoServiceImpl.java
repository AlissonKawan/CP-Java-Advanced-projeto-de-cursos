package br.com.alisson.cp2.sistemacursos.service;

import br.com.alisson.cp2.sistemacursos.repository.CursoRepository;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repository;
    public CursoServiceImpl(CursoRepository repository) {
        this.repository = repository;
    }
}

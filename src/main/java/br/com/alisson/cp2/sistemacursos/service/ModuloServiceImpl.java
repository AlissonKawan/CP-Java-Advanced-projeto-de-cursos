package br.com.alisson.cp2.sistemacursos.service;

import br.com.alisson.cp2.sistemacursos.repository.CursoRepository;
import br.com.alisson.cp2.sistemacursos.repository.ModuloRepository;
import org.springframework.stereotype.Service;

@Service
public class ModuloServiceImpl implements ModuloService {

    private final ModuloRepository repository;
    private final CursoRepository cursoRepository;
    public ModuloServiceImpl(ModuloRepository repository, CursoRepository cursoRepository) {
        this.repository = repository;
        this.cursoRepository = cursoRepository;
    }






}

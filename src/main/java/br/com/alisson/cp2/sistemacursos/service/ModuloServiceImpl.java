package br.com.alisson.cp2.sistemacursos.service;

import br.com.alisson.cp2.sistemacursos.dto.ModuloRequest;
import br.com.alisson.cp2.sistemacursos.dto.ModuloResponse;
import br.com.alisson.cp2.sistemacursos.dto.PageResponse;
import br.com.alisson.cp2.sistemacursos.exceptions.ResourceNotFoundException;
import br.com.alisson.cp2.sistemacursos.mapper.ModuloMapper;
import br.com.alisson.cp2.sistemacursos.model.Curso;
import br.com.alisson.cp2.sistemacursos.model.Modulo;
import br.com.alisson.cp2.sistemacursos.repository.CursoRepository;
import br.com.alisson.cp2.sistemacursos.repository.ModuloRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModuloServiceImpl implements ModuloService {

    private final ModuloRepository repository;
    private final CursoRepository cursoRepository;
    public ModuloServiceImpl(ModuloRepository repository, CursoRepository cursoRepository) {
        this.repository = repository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public ModuloResponse cadastrarModulo(Long cursoId, ModuloRequest moduloRequest) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);

        if (cursoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Curso não encontrado");
        }
        Curso curso = cursoOptional.get();

        Modulo modulo = new Modulo();
        modulo.setTitulo(moduloRequest.titulo());
        modulo.setOrdem(moduloRequest.ordem());
        modulo.setCargaHoraria(moduloRequest.cargaHoraria());

        modulo.setCurso(curso);

        Modulo moduloSalvo = repository.save(modulo);

        return ModuloMapper.toResponse(moduloSalvo);
    }

    @Override
    public ModuloResponse buscarModuloPorId(Long id) {
        Optional <Modulo> moduloOptional = repository.findById(id);
        if (moduloOptional.isEmpty()) {
            throw new ResourceNotFoundException("Modulo Nao Encontrado");
        }

        return ModuloMapper.toResponse(moduloOptional.get());
    }

    @Override
    public PageResponse<ModuloResponse> listarModulosPorCurso(Long cursoId, Pageable pageable) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        if (cursoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Curso nao encontrado");
        }
        Page<Modulo> page = repository.findByCurso_Id(cursoId, pageable);

        List<ModuloResponse> modulosConvertidos = new ArrayList<>();
        for (Modulo modulo : page.getContent()) {
            modulosConvertidos.add(ModuloMapper.toResponse(modulo));
        }

        return new PageResponse<>(
                modulosConvertidos,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );

    }

    @Override
    public void excluirModulo(Long id) {
        Optional<Modulo> moduloOptional = repository.findById(id);
        if (moduloOptional.isEmpty()) {
            throw new ResourceNotFoundException("Modulo Nao Encontrado");
        }
        repository.deleteById(id);
    }

}

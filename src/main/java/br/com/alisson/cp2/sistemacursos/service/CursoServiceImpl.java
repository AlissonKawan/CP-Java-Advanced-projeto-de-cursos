package br.com.alisson.cp2.sistemacursos.service;

import br.com.alisson.cp2.sistemacursos.dto.CursoRequest;
import br.com.alisson.cp2.sistemacursos.dto.CursoResponse;
import br.com.alisson.cp2.sistemacursos.dto.PageResponse;
import br.com.alisson.cp2.sistemacursos.exceptions.ResourceNotFoundException;
import br.com.alisson.cp2.sistemacursos.mapper.CursoMapper;
import br.com.alisson.cp2.sistemacursos.model.Curso;
import br.com.alisson.cp2.sistemacursos.repository.CursoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repository;

    public CursoServiceImpl(CursoRepository repository) {
        this.repository = repository;
    }

    @Override
    public CursoResponse cadastrarCurso(CursoRequest request) {
        Curso curso = new Curso();
        curso.setTitulo(request.titulo());
        curso.setDescricao(request.descricao());

        Curso cursoSalvo = repository.save(curso);
        return CursoMapper.toResponse(cursoSalvo);
    }

    @Override
    public CursoResponse buscarCursoPorId(Long id) {
        Optional<Curso> idEncontrado = repository.findById(id);
        if (idEncontrado.isPresent()) {
            return CursoMapper.toResponse(idEncontrado.get());
        }
        else throw new ResourceNotFoundException("Não encontrado!");
    }

    @Override
    public PageResponse<CursoResponse> listarCursos(Pageable pageable) {

        Page<Curso> page = repository.findAll(pageable);

        List<CursoResponse> cursosConvertidos = new ArrayList<>();
        for (Curso curso : page.getContent()) {
            CursoResponse response = CursoMapper.toResponse(curso);
            cursosConvertidos.add(response);
        }

        return new PageResponse<>(
                cursosConvertidos,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );

    }
}

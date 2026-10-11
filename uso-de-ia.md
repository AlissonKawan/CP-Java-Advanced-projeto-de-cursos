# Registro de uso de IA generativa — CP2 Java Advanced

**Projeto:** Sistema de Cursos  
**Ferramenta utilizada:** ChatGPT (OpenAI)  
**Repositório do projeto:** [CP Java Advanced — Sistema de Cursos](https://github.com/AlissonKawan/CP-Java-Advanced-projeto-de-cursos)  
**Histórico de commits:** [Acompanhar o desenvolvimento passo a passo no GitHub](https://github.com/AlissonKawan/CP-Java-Advanced-projeto-de-cursos/commits/master)  
**Prazo de entrega:** 12/10/2026 às 23h55

## Uma observação para o professor

Professor, como utilizei vários chats durante o desenvolvimento da CP, ficou mais complicado reunir todos os prompts e todas as respostas em um único arquivo.

Usei o ChatGPT principalmente como um tutor. Eu pedia que ele me passasse tarefas menores, tentava fazer cada uma no meu projeto e depois enviava meu código para ele revisar. Quando eu errava alguma coisa ou não entendia um conceito, perguntava o motivo, fazia ajustes e continuava para a próxima tarefa. Assim fui construindo as entidades, DTOs, repositories, services e controllers aos poucos.

Também deixei o **[histórico completo de commits do repositório](https://github.com/AlissonKawan/CP-Java-Advanced-projeto-de-cursos/commits/master)** disponível para o senhor acompanhar como o código evoluiu passo a passo. Há commits mostrando a construção das funcionalidades e correções de erros durante o processo, inclusive um commit chamado `corrigindo erro de NotBlank`. O histórico ajuda a mostrar meu processo de desenvolvimento, mas sei que os commits não guardam todas as perguntas e respostas que troquei com a IA.

**Sobre o registro abaixo:** organizei as interações que consegui reunir. Algumas respostas estão reproduzidas parcialmente ou com adaptações de formatação, e ainda existem conversas anteriores que não consegui transcrever por completo. Portanto, este arquivo não representa um registro integral de tudo que foi conversado com a IA.

---

## Prompt 1

````text
package br.com.alisson.cp2.sistemacursos.service;

import br.com.alisson.cp2.sistemacursos.dto.ModuloRequest;
import br.com.alisson.cp2.sistemacursos.dto.ModuloResponse;

public interface ModuloService {

    ModuloResponse cadastrarModulo(Long cursoId, ModuloRequest moduloRequest);

}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 06.2 aprovada!**

Sua interface `ModuloService` está correta e atende ao contrato que precisamos para cadastrar módulos na CP2.

## Code Review

| Critério | Resultado |
|---|---|
| Interface `ModuloService` | Correto |
| Retorno `ModuloResponse` | Correto |
| Parâmetro `Long cursoId` | Correto |
| Parâmetro `ModuloRequest` | Correto |
| Imports corretos | Correto |
| Método sem implementação | Correto |

Não há nada obrigatório para corrigir.

---

## Task 06.3 — Implementar cadastrarModulo()

Arquivo: `ModuloServiceImpl.java`. **Objetivo:** cadastrar um módulo dentro de um curso que já existe no banco.

Você já implementou o cadastro de cursos. A diferença é que agora precisamos estabelecer um relacionamento entre duas entidades.

### Requisitos

No `ModuloServiceImpl`:

1. Implementar `cadastrarModulo()` com `@Override`, respeitando a assinatura da interface.
2. Buscar o curso utilizando `cursoRepository.findById(cursoId)`.
3. Se o curso não existir, lançar `ResourceNotFoundException`.
4. Criar uma entidade `Modulo`.
5. Preencher `titulo`, `ordem` e `cargaHoraria` usando o `ModuloRequest`.
6. Associar o módulo ao curso encontrado utilizando o relacionamento existente.
7. Salvar o módulo usando `ModuloRepository`.
8. Converter o módulo salvo em `ModuloResponse` utilizando `ModuloMapper.toResponse()`.

### Atenção ao relacionamento

Você já tem na entidade `Modulo`:

```java
@ManyToOne
@JoinColumn(name = "curso_id", nullable = false)
private Curso curso;
```

Isso significa que, ao criar um módulo, você precisa indicar **qual objeto `Curso` está associado a ele**.

Não basta preencher os dados do módulo: o relacionamento também precisa estar definido antes de salvar.

**Comece implementando o método no `ModuloServiceImpl`.**

Você já conhece os recursos necessários: `findById`, `Optional`, setters, `save`, Mapper e sua exceção personalizada.

Pode fazer sua tentativa inteira ou me enviar um trecho se travar. Vou avaliar o código, apontar os erros e orientar as correções, sem entregar a solução pronta.

---

## Prompt 2

````text
package br.com.alisson.cp2.sistemacursos.service;

import br.com.alisson.cp2.sistemacursos.dto.ModuloRequest;
import br.com.alisson.cp2.sistemacursos.dto.ModuloResponse;
import br.com.alisson.cp2.sistemacursos.exceptions.ResourceNotFoundException;
import br.com.alisson.cp2.sistemacursos.mapper.ModuloMapper;
import br.com.alisson.cp2.sistemacursos.model.Curso;
import br.com.alisson.cp2.sistemacursos.model.Modulo;
import br.com.alisson.cp2.sistemacursos.repository.CursoRepository;
import br.com.alisson.cp2.sistemacursos.repository.ModuloRepository;
import org.springframework.stereotype.Service;

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

}
assim ?
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Sim! Task 06.3 — Cadastro de Módulo aprovada!**

Sua implementação está correta. Você conseguiu reutilizar o que aprendeu no `CursoService` e acrescentar a lógica do relacionamento entre `Curso` e `Modulo`.

## Code Review — ModuloServiceImpl

| Critério da CP2 | Resultado |
|---|---|
| Implementação com `@Override` | Correto |
| Busca do curso com `findById()` | Correto |
| Tratamento de curso inexistente | Correto |
| Criação da entidade `Modulo` | Correto |
| Preenchimento dos três atributos | Correto |
| Associação ao curso existente | Correto |
| Persistência com `repository.save()` | Correto |
| Conversão para `ModuloResponse` | Correto |

Aprovação por revisão estática. Ainda precisamos testar o funcionamento da API.

## O detalhe mais importante que você acertou

```java
modulo.setCurso(curso);
```

Essa linha é fundamental.

Lembra quando estudamos o `@ManyToOne` e o lado dono do relacionamento?

Na sua entidade `Modulo`, você definiu:

```java
@ManyToOne
@JoinColumn(name = "curso_id", nullable = false)
private Curso curso;
```

Portanto, ao executar `modulo.setCurso(curso)`, você estabelece a associação que o JPA utilizará para preencher a chave estrangeira `curso_id`.

Você também verificou a existência do curso **antes de cadastrar o módulo**, exatamente como a CP exige.

### Uma melhoria opcional

Seu uso de `Optional.isEmpty()` seguido de `get()` está correto porque você verifica se existe um valor antes de acessá-lo.

Posteriormente, podemos simplificar usando `orElseThrow()`. Não precisa refatorar agora.

---

## Task 06.4 — Buscar um módulo por ID

Arquivo: `ModuloService.java + ModuloServiceImpl.java`.

**Objetivo:** permitir que o Service encontre um módulo pelo seu próprio identificador.

Isso será utilizado futuramente pelo endpoint `GET /api/modulos/{id}`.

Na interface `ModuloService`, declare um método chamado `buscarModuloPorId`, recebendo `Long id` e retornando `ModuloResponse`.

Na implementação `ModuloServiceImpl`, implemente o método utilizando `@Override`, consulte `ModuloRepository` utilizando `findById(id)`, lance `ResourceNotFoundException` se não existir, converta usando `ModuloMapper.toResponse()` e retorne o DTO.

**Não implemente a listagem de módulos ainda.** Vamos continuar uma operação por vez.

Quando terminar, envie sua implementação para o próximo Code Review.

---

## Prompt 3

````text
package br.com.alisson.cp2.sistemacursos.service;

import br.com.alisson.cp2.sistemacursos.dto.ModuloRequest;
import br.com.alisson.cp2.sistemacursos.dto.ModuloResponse;
import br.com.alisson.cp2.sistemacursos.exceptions.ResourceNotFoundException;
import br.com.alisson.cp2.sistemacursos.mapper.ModuloMapper;
import br.com.alisson.cp2.sistemacursos.model.Curso;
import br.com.alisson.cp2.sistemacursos.model.Modulo;
import br.com.alisson.cp2.sistemacursos.repository.CursoRepository;
import br.com.alisson.cp2.sistemacursos.repository.ModuloRepository;
import org.springframework.stereotype.Service;

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
        else  return ModuloMapper.toResponse(moduloOptional.get());
    }

}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 06.4 — Buscar Módulo por ID aprovada!**

Seu código está correto. Você implementou a busca de um módulo, tratou a possibilidade de ele não existir e utilizou o Mapper para devolver um DTO.

## Code Review — ModuloServiceImpl

| Critério da CP2 | Resultado |
|---|---|
| Implementação com `@Override` | Correto |
| Consulta com `repository.findById(id)` | Correto |
| Utilização correta de `Optional` | Correto |
| Exceção para módulo inexistente | Correto |
| Conversão com `ModuloMapper` | Correto |
| Retorno `ModuloResponse` | Correto |

### Uma pequena melhoria

Você escreveu:

```java
if (moduloOptional.isEmpty()) {
    throw new ResourceNotFoundException("Modulo Nao Encontrado");
}
else return ModuloMapper.toResponse(moduloOptional.get());
```

Isso funciona.

Entretanto, como o `if` já lança uma exceção quando o módulo não existe, você não precisa utilizar `else`.

O fluxo já será interrompido pelo `throw`.

Também recomendo incluir o ID na mensagem de erro, para facilitar a identificação do módulo solicitado.

**Não é necessário refatorar agora.** O método atende ao comportamento exigido, assumindo que a assinatura esteja declarada na interface `ModuloService`.

---

## Task 06.5 — Listar módulos de um curso com paginação

**Objetivo:** buscar somente os módulos pertencentes a um determinado curso, utilizando paginação.

Essa operação será utilizada pelo endpoint `GET /api/cursos/{cursoId}/modulos`.

Na interface `ModuloService`, declare `listarModulosPorCurso(Long cursoId, Pageable pageable)`, retornando `PageResponse<ModuloResponse>`.

Na implementação, utilize `@Override`, verifique existência do curso, lance `ResourceNotFoundException` se ausente, use `findByCurso_Id` com o ID e o `Pageable`, converta com `ModuloMapper` e retorne `PageResponse` com os metadados.

**Faça a implementação e me envie o código.** Vou avaliar se você reaproveitou corretamente os conceitos e se está de acordo com os requisitos da CP2.

Depois dessa tarefa, faltará somente a exclusão para concluir as operações obrigatórias do `ModuloService`.

---

## Prompt 4

````text
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
import org.springframework.data.domain.PageRequest;
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
    public PageResponse<ModuloResponse> listarModulosPorCurso(Long cursoId, PageRequest pageRequest) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        if (cursoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Curso nao encontrado");
        }
        Page<Modulo> page = repository.findByCurso_Id(cursoId, pageRequest);

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

}

dessa forma ?
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Sim! A lógica da sua implementação está correta!**

Você conseguiu combinar a verificação de existência do curso, a consulta paginada e a conversão para DTO. Entretanto, encontrei **um ajuste importante na assinatura do método**.

## Code Review — Task 06.5

| Critério | Resultado |
|---|---|
| Verifica se o curso existe | Correto |
| Lança `ResourceNotFoundException` | Correto |
| Consulta usando `findByCurso_Id()` | Correto |
| Converte os módulos com `ModuloMapper` | Correto |
| Constrói `PageResponse` corretamente | Correto |
| Tipo do parâmetro de paginação | Ajustar |

## 1. O problema com PageRequest

Você escreveu:

```java
public PageResponse<ModuloResponse> listarModulosPorCurso(
    Long cursoId,
    PageRequest pageRequest
)
```

Mas a task solicitava `Pageable`, não `PageRequest`.

Qual é a diferença?

- **`Pageable`** é a interface que representa as informações de paginação.
- **`PageRequest`** é uma classe que implementa `Pageable`.

Ou seja, `PageRequest` é uma forma concreta de representar a paginação, enquanto `Pageable` é o contrato.

Como estamos trabalhando com interfaces de Service, é preferível receber o tipo mais genérico.

Além disso, se você declarou `Pageable` na interface `ModuloService`, utilizar `PageRequest` na implementação não corresponde à mesma assinatura e o `@Override` não funcionará.

**Correção necessária:** substitua `PageRequest` por `Pageable` no parâmetro e ajuste o import.

## 2. A consulta está correta

```java
Page<Modulo> page = repository.findByCurso_Id(cursoId, pageRequest);
```

Você utilizou corretamente o método que criou anteriormente no `ModuloRepository`.

Essa consulta retorna somente os módulos associados ao curso informado.

Também acertou ao verificar a existência do curso antes da consulta. Assim, um curso inexistente gera uma exceção, enquanto um curso existente sem módulos retorna uma página vazia.

## 3. A conversão está correta

```java
List<ModuloResponse> modulosConvertidos = new ArrayList<>();

for (Modulo modulo : page.getContent()) {
    modulosConvertidos.add(ModuloMapper.toResponse(modulo));
}
```

Você reutilizou o mesmo raciocínio da listagem de cursos.

Isso é válido e não precisa ser substituído por `Page.map()`.

Seu retorno também está correto:

```java
return new PageResponse<>(
    modulosConvertidos,
    page.getNumber(),
    page.getSize(),
    page.getTotalElements(),
    page.getTotalPages()
);
```

Os cinco valores correspondem exatamente aos campos do seu `PageResponse`.

---

## Task 06.5 — Ajuste final

Faça apenas estas alterações:

1. Substitua `PageRequest` por `Pageable` na assinatura do método.
2. Atualize o import correspondente.
3. Confira se a assinatura está igual à declarada em `ModuloService`.

Depois disso, a implementação estará aprovada por revisão estática. Ainda precisaremos compilar e testar o funcionamento real.

A próxima será a exclusão de módulos por ID. Depois poderemos passar para os Controllers, o tratamento global de exceções e os ajustes finais da CP2.

---

## Prompt 5

````text
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
import org.springframework.data.domain.PageRequest;
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

}
é que eu tinha feito na interface com request ja corrigi
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Agora sim! Task 06.5 — Listagem paginada de módulos aprovada!**

Você corrigiu o parâmetro para `Pageable` e, pelo que explicou, também ajustou a assinatura na interface `ModuloService`.

## Code Review

| Critério | Resultado |
|---|---|
| Utiliza `Pageable` | Correto |
| Verifica se o curso existe | Correto |
| Lança exceção se não existir | Correto |
| Consulta com `findByCurso_Id()` | Correto |
| Converte módulos em DTOs | Correto |
| Retorna `PageResponse<ModuloResponse>` | Correto |
| Preserva os metadados da paginação | Correto |

Existe apenas uma melhoria de limpeza: remova o import de `PageRequest`, pois não está mais sendo utilizado.

A lógica apresentada está correta. Ainda precisamos verificar a compilação e testar o funcionamento da consulta.

---

## Task 06.6 — Excluir módulo por ID

Arquivo: `ModuloService.java + ModuloServiceImpl.java`.

**Objetivo:** implementar a exclusão de um módulo pelo identificador, sem excluir o curso ao qual ele pertence.

Essa operação será utilizada pelo endpoint `DELETE /api/modulos/{id}`.

Na interface, declare `excluirModulo`, recebendo `Long id` e retornando `void`.

Na implementação, use `@Override`, verifique a existência com `ModuloRepository`, lance `ResourceNotFoundException` se ausente, exclua o módulo pelo repository e não retorne DTO.

### Atenção ao relacionamento

Quando um módulo é excluído, **seu curso deve continuar existindo**.

O `cascade = CascadeType.ALL` que você declarou no lado `Curso` permite propagar operações do curso para seus módulos, não o contrário.

Portanto, excluir um módulo individualmente não significa excluir seu curso.

**Depois dessa tarefa, teremos implementado todas as operações obrigatórias dos dois Services.** A próxima etapa será construir os Controllers, preparar o tratamento global de erros e testar a API.

Faça sua implementação e me envie. Continuamos com você programando e eu fazendo o Code Review, sem entregar métodos prontos.

---

## Prompt 6

````text
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
import org.springframework.data.domain.PageRequest;
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
agora sim
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 06.6 — Exclusão de Módulo aprovada!**

Você implementou corretamente a lógica de exclusão e, com isso, concluiu as quatro operações obrigatórias do `ModuloService` por revisão estática.

## Code Review — Task 06.6

Seu método:

```java
@Override
public void excluirModulo(Long id) {
    Optional<Modulo> moduloOptional = repository.findById(id);

    if (moduloOptional.isEmpty()) {
        throw new ResourceNotFoundException("Modulo Nao Encontrado");
    }

    repository.deleteById(id);
}
```

| Critério | Resultado |
|---|---|
| `@Override` | Correto |
| Recebe `Long id` | Correto |
| Consulta `findById()` | Correto |
| Lança exceção se não encontrar | Correto |
| Exclui utilizando o Repository | Correto |
| Não retorna conteúdo | Correto |

### Duas melhorias pequenas

Primeiro, você pode utilizar `repository.delete(moduloOptional.get())` em vez de `deleteById(id)`, aproveitando a entidade que já buscou. Não é obrigatório para aprovar a tarefa.

Segundo, remova este import que não está sendo utilizado:

```java
import org.springframework.data.domain.PageRequest;
```

Também confirme que `void excluirModulo(Long id)` está declarado na interface `ModuloService`.

---

## Situação dos Services

**Operações de Service implementadas — 9 de 9.** CursoService com 5 operações e ModuloService com 4 operações, em revisão estática. Ainda faltam compilação, testes e integração HTTP.

## Task 07.1 — Preparar o CursoController

**Objetivo:** preparar o `CursoController` para utilizar o `CursoService` por injeção de dependência.

Arquivo: `controller/CursoController.java`.

1. Criar ou ajustar a classe `CursoController`.
2. Utilizar `@RestController`.
3. Definir `@RequestMapping("/api/cursos")` na classe.
4. Declarar uma dependência `private final CursoService service`.
5. Implementar a injeção pelo construtor.
6. Não implementar os endpoints ainda.

Depois faremos o endpoint `POST /api/cursos`, que precisa retornar **201 Created** e o cabeçalho `Location`, conforme o enunciado.

Antes de avançarmos muito, também vamos reservar uma etapa para corrigir a constraint personalizada, finalizar o tratamento global de exceções e executar os testes obrigatórios.

**Sua próxima tarefa é somente preparar o Controller.** Quando terminar, mande seu código e faço o Code Review.

---

## Prompt 7

````text
package br.com.alisson.cp2.sistemacursos.controller;


import br.com.alisson.cp2.sistemacursos.service.CursoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

}
feito
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 07.1 — CursoController aprovada!**

Seu código está correto e atende a todos os requisitos dessa etapa. Não tenho nenhuma correção para fazer.

## Code Review

| Critério | Resultado |
|---|---|
| `@RestController` | Correto |
| `@RequestMapping("/api/cursos")` | Correto |
| Dependência `CursoService` | Correto |
| Utilização de `private final` | Correto |
| Injeção pelo construtor | Correto |
| Organização e imports | Correto |

Você preparou corretamente a camada que receberá as requisições HTTP e chamará os métodos do Service.

---

## Task 07.2 — Implementar POST /api/cursos

**Objetivo:** criar o endpoint responsável por cadastrar cursos.

Agora vamos conectar o Controller ao método `cadastrarCurso()` que você já implementou no Service.

O endpoint deve responder `POST /api/cursos`, receber `CursoRequest` com `@RequestBody` e `@Valid`, chamar `service.cadastrarCurso(request)` e retornar `ResponseEntity<CursoResponse>` com **201 Created** e cabeçalho `Location: /api/cursos/{id}`.

Exemplo de body:

```json
{"titulo":"Java Avançado","descricao":"Curso sobre Spring Boot"}
```

O `id` utilizado no `Location` deve vir do `CursoResponse` devolvido pelo Service, pois o banco é responsável por gerar o identificador.

Você vai precisar de `@PostMapping`, `@RequestBody`, `@Valid`, `ResponseEntity.created(...)` e `URI.create(...)`.

**Sua tarefa agora é implementar somente esse endpoint.**

Não precisa implementar os outros métodos HTTP ainda. Se tiver dificuldade com `ResponseEntity.created()` ou `URI.create()`, me envie sua tentativa e vamos analisar a sintaxe.

Quando terminar, farei o Code Review verificando se o endpoint atende exatamente ao que a FIAP exige, sem entregar o método pronto.

---

## Prompt 8

````text
@PostMapping
public ResponseEntity<CursoResponse> cadastrarCurso(@RequestBody CursoRequest cursoRequest) {
    CursoResponse cursoCriado = service.cadastrarCurso(cursoRequest);

    URI location = URI.create("/api/cursos/" + cursoCriado.id());

    return ResponseEntity.created(location).body(cursoCriado);
} dessa forma  ?
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Sim! Sua implementação está quase perfeita.**

Você acertou a lógica do endpoint, inclusive o `ResponseEntity.created()`, que é uma parte importante da CP2.

Mas esqueceu **uma anotação obrigatória**.

## Code Review — Task 07.2

| Critério | Resultado |
|---|---|
| `@PostMapping` | Correto |
| Retorno `ResponseEntity<CursoResponse>` | Correto |
| Recebimento com `@RequestBody` | Correto |
| Chamada ao Service | Correto |
| Construção da URI com o ID criado | Correto |
| HTTP 201 + Location | Correto |
| Validação do DTO com `@Valid` | Faltando |

## 1. O que você fez corretamente

```java
CursoResponse cursoCriado = service.cadastrarCurso(cursoRequest);
```

Você delegou o cadastro ao Service. O Controller não precisa saber como o curso é persistido.

Depois:

```java
URI location = URI.create("/api/cursos/" + cursoCriado.id());
```

Você montou a URI usando o identificador gerado pelo banco.

E:

```java
return ResponseEntity.created(location).body(cursoCriado);
```

Essa chamada configura automaticamente o status **201 Created**, adiciona o cabeçalho `Location` e devolve o `CursoResponse` no corpo.

Está correto.

## 2. O que falta: @Valid

Você escreveu:

```java
public ResponseEntity<CursoResponse> cadastrarCurso(
    @RequestBody CursoRequest cursoRequest
)
```

Lembra das validações que colocou no `CursoRequest`, como `@NotBlank` e `@Size(max = 150)`?

Para que o Spring execute essas validações automaticamente ao receber o JSON, precisamos utilizar `@Valid` no parâmetro.

Sem essa anotação, o Controller poderá encaminhar um título inválido ao Service sem validar o DTO na entrada.

**Sua correção:** adicione `@Valid` ao parâmetro `cursoRequest`, mantendo `@RequestBody`.

O import necessário é:

```java
import jakarta.validation.Valid;
```

E confira se `URI` está importado de `java.net.URI`.

Depois dessa correção, podemos considerar a **Task 07.2 aprovada por revisão estática**.

A próxima será a Task 07.3: `GET /api/cursos/{id}`.

Faça o ajuste do `@Valid` e me avise. Continuamos no nosso esquema de tasks e Code Review, sem entregar métodos prontos.

---

## Prompt 9

````text
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
feit
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 07.2 — Cadastro de Curso aprovada!**

Você corrigiu o `@Valid` e agora seu endpoint está alinhado com os requisitos da CP2.

## Code Review

| Critério | Resultado |
|---|---|
| `@PostMapping` | Correto |
| `@Valid` e `@RequestBody` | Correto |
| Chamada ao `CursoService` | Correto |
| Retorno `ResponseEntity<CursoResponse>` | Correto |
| HTTP 201 Created | Correto |
| Cabeçalho `Location` com ID criado | Correto |
| Imports necessários | Correto |

Nenhuma alteração obrigatória nesse endpoint.

Aprovado por revisão estática do código enviado. Ainda precisamos executar testes para confirmar o comportamento.

---

## Task 07.3 — Implementar GET /api/cursos/{id}

**Objetivo:** permitir que o cliente consulte um curso específico pelo ID.

Implemente um novo método dentro de `CursoController` que utilize `@GetMapping("/{id}")`, receba `@PathVariable Long id`, chame `service.buscarCursoPorId(id)`, retorne `ResponseEntity<CursoResponse>` e responda com 200.

Se o curso não existir, o Service já lança `ResourceNotFoundException`. O tratamento para transformar essa exceção em HTTP 404 será implementado posteriormente no `GlobalExceptionHandler`.

**Faça somente esse endpoint agora.** Não precisa criar lógica de busca no Controller, pois ela já existe no Service.

Quando terminar, me envie o código para o próximo Code Review.

---

## Prompt 10

````text
@GetMapping("/{id}")
public ResponseEntity<CursoResponse> buscarCursoPorId(@PathVariable Long id) {
    CursoResponse curso = service.buscarCursoPorId(id);
    return ResponseEntity.ok(curso);
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 07.3 — Buscar Curso por ID aprovada!**

Seu endpoint está correto e atende aos requisitos da CP2.

## Code Review

```java
@GetMapping("/{id}")
public ResponseEntity<CursoResponse> buscarCursoPorId(@PathVariable Long id) {
    CursoResponse curso = service.buscarCursoPorId(id);
    return ResponseEntity.ok(curso);
}
```

| Critério | Resultado |
|---|---|
| `@GetMapping("/{id}")` | Correto |
| `@PathVariable Long id` | Correto |
| Chamada ao Service | Correto |
| `ResponseEntity<CursoResponse>` | Correto |
| Retorno HTTP 200 | Correto |

Você manteve corretamente a separação de responsabilidades: o Controller recebe o ID e delega a busca ao Service.

Se o curso não existir, o Service lançará `ResourceNotFoundException`. Ainda precisamos criar o `GlobalExceptionHandler` para garantir a resposta HTTP 404 exigida pela prova.

---

## Task 07.4 — Listar cursos com paginação

**Objetivo:** implementar `GET /api/cursos`, permitindo consultar os cursos por página.

Você já implementou `listarCursos(Pageable pageable)` no Service. Agora precisamos disponibilizar essa operação pela API.

O Controller deve ter método `listarCursos` com `@GetMapping` sem caminho adicional, receber `Pageable`, chamar `service.listarCursos(pageable)` e retornar `ResponseEntity<PageResponse<CursoResponse>>` com HTTP 200 e os metadados.

Exemplo: `GET /api/cursos?page=0&size=10`.

**Não precisa implementar paginação manualmente no Controller.** O `Pageable` contém os parâmetros de paginação, e seu Service já sabe trabalhar com eles.

Faça o endpoint e me envie para revisão. Depois avançaremos para `PUT` e `DELETE` de Curso.

---

## Prompt 11

````text
@GetMapping
public ResponseEntity<PageResponse<CursoResponse>> listarCursos(Pageable pageable) {
    PageResponse<CursoResponse> pagina = service.listarCursos(pageable);
    return ResponseEntity.ok(pagina);
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 07.4 — Listagem paginada de cursos aprovada!**

Seu método está correto e atende ao requisito de paginação da CP2.

## Code Review

```java
@GetMapping
public ResponseEntity<PageResponse<CursoResponse>> listarCursos(Pageable pageable) {
    PageResponse<CursoResponse> pagina = service.listarCursos(pageable);
    return ResponseEntity.ok(pagina);
}
```

| Critério | Resultado |
|---|---|
| `@GetMapping` | Correto |
| Recebe `Pageable` | Correto |
| Chama `service.listarCursos()` | Correto |
| Retorna `PageResponse<CursoResponse>` | Correto |
| Responde com HTTP 200 | Correto |

Não tenho correções obrigatórias. Apenas confirme que os imports de `Pageable` e `PageResponse` estão presentes na classe.

O Controller não precisa fazer a paginação manualmente porque seu Service já faz isso.

---

## Task 07.5 — Atualizar curso com PUT

**Objetivo:** implementar o endpoint `PUT /api/cursos/{id}` para atualizar o título e a descrição de um curso existente.

Use `@PutMapping("/{id}")`, `@PathVariable Long id`, `@Valid @RequestBody CursoRequest`, chame `service.atualizarCurso(id, request)` e retorne `ResponseEntity<CursoResponse>` com HTTP 200. Deixe a exceção ser propagada para o futuro `GlobalExceptionHandler`.

Você já conhece tudo que precisa para implementar esse endpoint. É semelhante ao POST, mas agora recebe também o ID e devolve HTTP 200 em vez de 201.

**Implemente somente o PUT e me envie o código.** Depois faremos o DELETE, encerrando os cinco endpoints de Curso.

---

## Prompt 12

````text
@PutMapping("/{id}")
public ResponseEntity<CursoResponse> atualizarCurso(@PathVariable Long id,
                                                   @Valid @RequestBody CursoRequest cursoRequest) {
    CursoResponse cursoAtualizado = service.atualizarCurso(id, cursoRequest);
    return ResponseEntity.ok(cursoAtualizado);
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 07.5 — Atualização de Curso aprovada!**

Seu método está correto. Você utilizou o mapeamento HTTP, recebeu os parâmetros necessários e delegou a atualização ao Service.

## Code Review

```java
@PutMapping("/{id}")
public ResponseEntity<CursoResponse> atualizarCurso(
        @PathVariable Long id,
        @Valid @RequestBody CursoRequest cursoRequest) {

    CursoResponse cursoAtualizado = service.atualizarCurso(id, cursoRequest);
    return ResponseEntity.ok(cursoAtualizado);
}
```

| Critério | Resultado |
|---|---|
| `@PutMapping("/{id}")` | Correto |
| `@PathVariable Long id` | Correto |
| `@Valid @RequestBody` | Correto |
| Chamada ao Service | Correto |
| Retorno `CursoResponse` | Correto |
| Status HTTP 200 | Correto |

Apenas confirme que os imports de `PutMapping` e `PathVariable` estão presentes.

### Um conceito importante

Observe esta linha:

```java
CursoResponse cursoAtualizado = service.atualizarCurso(id, cursoRequest);
```

O Controller não precisa saber **como o curso é atualizado no banco**.

Ele apenas recebe a requisição e chama o Service. Isso é exatamente a separação de responsabilidades que estamos praticando.

---

## Task 07.6 — Excluir curso com DELETE

Essa será a **última operação obrigatória do CursoController**.

**Objetivo:** implementar `DELETE /api/cursos/{id}` com `@DeleteMapping("/{id}")`, `@PathVariable Long id`, `service.excluirCurso(id)` e `ResponseEntity<Void>` com HTTP **204 No Content**, sem corpo.

Para construir essa resposta, pesquise ou utilize `ResponseEntity.noContent()`.

**Atenção:** quando um curso for excluído, seus módulos também deverão ser removidos pelo relacionamento configurado na entidade `Curso`.

**Sua próxima tarefa:** implemente somente esse método e me envie para revisão.

---

## Prompt 13

````text
@DeleteMapping
public ResponseEntity<Void> removerCurso(@PathVariable Long id) {
    service.excluirCurso(id);
    return ResponseEntity.noContent().build();
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Quase! Task 07.6 — Exclusão de Curso precisa de uma pequena correção.**

Você acertou a chamada ao Service e o retorno HTTP 204, mas faltou informar o identificador na rota.

## Code Review

Seu código:

```java
@DeleteMapping
public ResponseEntity<Void> removerCurso(@PathVariable Long id) {
    service.excluirCurso(id);
    return ResponseEntity.noContent().build();
}
```

| Critério | Resultado |
|---|---|
| `@DeleteMapping` | Correto |
| `@PathVariable Long id` | Correto |
| Chamada a `service.excluirCurso(id)` | Correto |
| `ResponseEntity<Void>` | Correto |
| HTTP 204 No Content | Correto |
| Rota `/{id}` | Faltando |

### Por que isso é importante?

Você declarou na classe:

```java
@RequestMapping("/api/cursos")
```

E no método:

```java
@DeleteMapping
```

Assim, o endpoint fica mapeado para `DELETE /api/cursos`, sem o segmento que contém o ID.

Entretanto, seu método espera receber:

```java
@PathVariable Long id
```

O Spring precisa encontrar esse `id` na URL. Sem `/{id}` no mapeamento, ele não consegue obter o identificador necessário.

**Correção:** altere somente a anotação para:

```java
@DeleteMapping("/{id}")
```

Todo o restante está correto.

Depois desse ajuste, teremos os cinco endpoints de `CursoController` implementados por revisão estática e começaremos `ModuloController`.

**Faça essa pequena correção e me avise.** Depois continuaremos construindo a CP2, um método por vez, até finalizar os requisitos e testar a aplicação.

---

## Prompt 14

````text
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
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 07.6 aprovada! E agora temos o `CursoController` completo!**

Revisei os cinco endpoints que você enviou. Todos estão corretos em relação à estrutura e aos requisitos que estamos implementando para a CP2.

## Code Review — CursoController

| Método HTTP | Endpoint | Resultado |
|---|---|---|
| POST | `/api/cursos` | 201 Created |
| GET | `/api/cursos/{id}` | 200 OK |
| GET | `/api/cursos` | 200 OK |
| PUT | `/api/cursos/{id}` | 200 OK |
| DELETE | `/api/cursos/{id}` | 204 No Content |

Você também acertou a injeção de dependência por construtor, o uso de DTOs, a validação com `@Valid` e a separação entre Controller e Service.

**Não encontrei correções obrigatórias nesse código.** A aprovação é por revisão estática; ainda precisamos compilar e testar os endpoints.

---

## Task 08.1 — Criar o ModuloController

Agora vamos trabalhar com os endpoints dos módulos.

Antes de começarmos, quero destacar uma diferença importante entre os dois Controllers.

No `CursoController`, você utilizou `@RequestMapping("/api/cursos")`.

Entretanto, na CP2 temos duas estruturas de endereço para os módulos:

```http
POST /api/cursos/{cursoId}/modulos
GET /api/cursos/{cursoId}/modulos

GET /api/modulos/{id}
DELETE /api/modulos/{id}
```

Percebe a diferença? Algumas operações dependem da identificação do curso, enquanto outras trabalham diretamente com o módulo.

Por isso, precisamos prestar atenção ao mapeamento das rotas.

Crie `controller/ModuloController.java`, com `@RestController`, uma classe pública, `private final ModuloService service` e injeção pelo construtor.

**Nesta etapa, não coloque `@RequestMapping` na classe e não crie endpoints.** Vamos definir as rotas diretamente nos métodos, porque elas possuem dois prefixos diferentes.

### Uma pergunta para você pensar enquanto programa

No `CursoController`, utilizamos `CursoService` como dependência.

No `ModuloController`, por que precisamos injetar `ModuloService` em vez de acessar diretamente o `ModuloRepository`?

Não precisa responder separadamente. Pode me enviar o código e, se quiser, sua explicação junto.

Depois dos quatro endpoints de módulos, ainda teremos o tratamento global de exceções, alguns ajustes de validação, testes e a documentação obrigatória do uso de IA.

---

## Prompt 15

````text
package br.com.alisson.cp2.sistemacursos.controller;

import br.com.alisson.cp2.sistemacursos.service.ModuloService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModuloController {

    private final ModuloService moduloService;

    public ModuloController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }
}
por que o service ja utiliza do reposiroty para  consultar o banco famosa separaçao de responsabilidades
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 08.1 — ModuloController aprovada!**

E sua explicação também está correta! Você entendeu o motivo de utilizarmos o Service em vez de acessar diretamente o Repository.

## Code Review

| Critério | Resultado |
|---|---|
| `@RestController` | Correto |
| Classe pública `ModuloController` | Correto |
| Dependência `private final ModuloService` | Correto |
| Injeção pelo construtor | Correto |
| Sem `@RequestMapping` na classe | Correto |

Nenhuma correção necessária.

### Sobre sua resposta

Você disse:

> Porque o Service já utiliza o Repository para consultar o banco. Famosa separação de responsabilidades.

Exatamente. Só quero aprofundar um detalhe: **o Service não existe apenas para chamar o Repository. Ele também concentra as regras de negócio.**

No cadastro de módulos, por exemplo:

- **ModuloController** recebe a requisição HTTP.
- **ModuloServiceImpl** verifica se o curso existe, associa o módulo e coordena o cadastro.
- **CursoRepository / ModuloRepository** executam operações de persistência.

O Controller não precisa conhecer essas regras. É isso que torna o código mais organizado e facilita sua manutenção.

---

## Task 08.2 — Cadastrar módulo pelo Controller

**Objetivo:** implementar `POST /api/cursos/{cursoId}/modulos`.

Você já implementou o método `cadastrarModulo(Long cursoId, ModuloRequest moduloRequest)` no Service. Agora precisamos disponibilizá-lo pela API.

1. Utilize `@PostMapping` com a rota completa `/api/cursos/{cursoId}/modulos`.
2. Receba `Long cursoId` com `@PathVariable`.
3. Receba `ModuloRequest` com `@Valid @RequestBody`.
4. Chame `moduloService.cadastrarModulo(cursoId, moduloRequest)`.
5. Retorne `ResponseEntity<ModuloResponse>`.
6. Responda com HTTP **201 Created**, incluindo `Location: /api/modulos/{id}`.

No cadastro de cursos, você construiu o `Location` com `URI.create("/api/cursos/" + cursoCriado.id())`.

Agora, apesar de estarmos cadastrando um módulo dentro de um curso, **o endereço para consultar o módulo individualmente é `/api/modulos/{id}`**, e não `/api/cursos/{cursoId}/modulos/{id}`.

**Sua próxima tarefa:** implemente somente esse endpoint dentro do `ModuloController` e me envie para revisão.

---

## Prompt 16

````text
package br.com.alisson.cp2.sistemacursos.controller;

import br.com.alisson.cp2.sistemacursos.dto.ModuloRequest;
import br.com.alisson.cp2.sistemacursos.dto.ModuloResponse;
import br.com.alisson.cp2.sistemacursos.service.ModuloService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 08.2 — Cadastro de Módulo aprovada!**

Seu código está correto! Você conseguiu adaptar o cadastro de cursos para o cadastro de módulos, incluindo o relacionamento com o curso e o cabeçalho `Location`.

## Code Review

| Critério | Resultado |
|---|---|
| `@PostMapping("/api/cursos/{cursoId}/modulos")` | Correto |
| `@PathVariable Long cursoId` | Correto |
| `@Valid @RequestBody ModuloRequest` | Correto |
| Chamada ao `ModuloService` | Correto |
| Retorno `ResponseEntity<ModuloResponse>` | Correto |
| Status HTTP 201 Created | Correto |
| `Location: /api/modulos/{id}` | Correto |

Nenhuma correção obrigatória.

### Um detalhe que você acertou

```java
URI location = URI.create("/api/modulos/" + moduloCriado.id());
```

Você utilizou o ID do **módulo criado**, e não o ID do curso.

Isso é importante porque o cliente poderá consultar diretamente o novo recurso por meio de `GET /api/modulos/{id}`.

Além disso, o Controller não precisa buscar o curso no banco. Essa responsabilidade já está implementada no `ModuloServiceImpl`.

---

## Task 08.3 — Buscar módulo por ID

Agora vamos implementar: `GET /api/modulos/{id}`.

Você já implementou `buscarModuloPorId(Long id)` no Service. Sua tarefa é disponibilizar esse método por HTTP.

1. Utilizar `@GetMapping("/api/modulos/{id}")`.
2. Receber o identificador usando `@PathVariable Long id`.
3. Chamar `moduloService.buscarModuloPorId(id)`.
4. Retornar `ResponseEntity<ModuloResponse>`.
5. Responder com HTTP **200 OK** quando o módulo existir.

Se o módulo não existir, o Service lançará `ResourceNotFoundException`. Posteriormente, nosso `GlobalExceptionHandler` transformará isso em HTTP 404.

**Atenção:** como seu `ModuloController` não possui `@RequestMapping` na classe, você precisa informar o caminho completo no `@GetMapping`.

**Sua próxima tarefa:** implemente somente esse GET e envie o código.

---

## Prompt 17

````text
package br.com.alisson.cp2.sistemacursos.controller;

import br.com.alisson.cp2.sistemacursos.dto.ModuloRequest;
import br.com.alisson.cp2.sistemacursos.dto.ModuloResponse;
import br.com.alisson.cp2.sistemacursos.service.ModuloService;
import jakarta.validation.Valid;
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
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 08.3 — Buscar Módulo por ID aprovada!**

Seu código está correto. Você implementou o endpoint utilizando a mesma lógica que já havia aplicado no `CursoController`.

## Code Review

```java
@GetMapping("/api/modulos/{id}")
public ResponseEntity<ModuloResponse> buscarModuloPorId(@PathVariable Long id) {
    ModuloResponse modulo = moduloService.buscarModuloPorId(id);
    return ResponseEntity.ok(modulo);
}
```

| Critério | Resultado |
|---|---|
| `@GetMapping("/api/modulos/{id}")` | Correto |
| `@PathVariable Long id` | Correto |
| Chamada ao `ModuloService` | Correto |
| Retorno `ModuloResponse` | Correto |
| HTTP 200 OK | Correto |

Nenhuma correção obrigatória.

Um detalhe importante: o `@PathVariable` recebe o ID diretamente da URL, enquanto `@RequestBody` recebe informações do corpo da requisição. Como estamos apenas consultando um módulo, não precisamos de um corpo JSON.

---

## Task 08.4 — Listar módulos de um curso com paginação

**Objetivo:** implementar `GET /api/cursos/{cursoId}/modulos`.

Esse endpoint deve listar somente os módulos pertencentes ao curso informado, utilizando a paginação que você já implementou no `ModuloServiceImpl`.

1. Criar um método chamado `listarModulosPorCurso`.
2. Utilizar `@GetMapping("/api/cursos/{cursoId}/modulos")`.
3. Receber `Long cursoId` utilizando `@PathVariable`.
4. Receber um `Pageable pageable`.
5. Chamar `moduloService.listarModulosPorCurso(cursoId, pageable)`.
6. Retornar `ResponseEntity<PageResponse<ModuloResponse>>` com HTTP 200.

O `Pageable` recebe as informações de paginação, como página e tamanho. Já o `cursoId` identifica **de qual curso queremos buscar os módulos**.

**Sua tarefa agora é implementar somente esse GET.** Você já fez uma listagem paginada no `CursoController`, então tente adaptar o mesmo raciocínio.

Depois dessa etapa, faltará apenas o DELETE de módulos para concluirmos os nove endpoints da CP2.

---

## Prompt 18

````text
@GetMapping("/api/cursos/{cursoId}/modulos")
public ResponseEntity<PageResponse<ModuloResponse>> listarModulosPorCurso(@PathVariable Long cursoId, Pageable pageable) {
    PageResponse<ModuloResponse> pagina = moduloService.listarModulosPorCurso(cursoId, pageable);
    return ResponseEntity.ok(pagina);
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 08.4 — Listagem paginada de módulos aprovada!**

Seu código está correto! Você conseguiu adaptar a paginação que já havia implementado no `CursoController`.

## Code Review

```java
@GetMapping("/api/cursos/{cursoId}/modulos")
public ResponseEntity<PageResponse<ModuloResponse>> listarModulosPorCurso(
        @PathVariable Long cursoId, Pageable pageable) {

    PageResponse<ModuloResponse> pagina =
            moduloService.listarModulosPorCurso(cursoId, pageable);

    return ResponseEntity.ok(pagina);
}
```

| Critério | Resultado |
|---|---|
| `@GetMapping` com a rota correta | Correto |
| `@PathVariable Long cursoId` | Correto |
| Recebe `Pageable` | Correto |
| Chama o método correto do Service | Correto |
| Retorna `PageResponse<ModuloResponse>` | Correto |
| Responde com HTTP 200 | Correto |

Apenas confira se adicionou estes imports ao `ModuloController`:

```java
import br.com.alisson.cp2.sistemacursos.dto.PageResponse;
import org.springframework.data.domain.Pageable;
```

Não há correções obrigatórias no método apresentado.

---

## Task 08.5 — Excluir módulo por ID

**Objetivo:** implementar `DELETE /api/modulos/{id}`.

Utilize `@DeleteMapping("/api/modulos/{id}")`, `@PathVariable Long id`, `moduloService.excluirModulo(id)`, `ResponseEntity<Void>` e **204 No Content** sem corpo.

Lembre-se: o `ModuloServiceImpl` já contém a lógica de exclusão. O Controller apenas recebe a requisição e delega a operação.

**Atenção ao relacionamento:** excluir um módulo não deve excluir o curso ao qual ele pertence.

**Implemente esse último DELETE e me envie.** Depois teremos os nove endpoints implementados por revisão estática.

A partir daí, vamos cuidar do `GlobalExceptionHandler`, das validações pendentes, dos testes de integração e do arquivo obrigatório `uso-de-ia.md`.

---

## Prompt 19

````text
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
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 08.5 aprovada! E com isso, os 9 endpoints obrigatórios da CP2 estão implementados!**

Revisei seu `ModuloController` inteiro e não encontrei erros na implementação dos quatro endpoints.

## Code Review — ModuloController

| Método | Endpoint | Status esperado | Revisão |
|---|---|---|---|
| POST | `/api/cursos/{cursoId}/modulos` | 201 | Correto |
| GET | `/api/modulos/{id}` | 200 | Correto |
| GET | `/api/cursos/{cursoId}/modulos` | 200 | Correto |
| DELETE | `/api/modulos/{id}` | 204 | Correto |

Você também utilizou corretamente `@Valid`, `@PathVariable`, `Pageable` e a injeção de dependência.

### O que você acertou no DELETE

```java
@DeleteMapping("/api/modulos/{id}")
public ResponseEntity<Void> excluirModulo(@PathVariable Long id) {
    moduloService.excluirModulo(id);
    return ResponseEntity.noContent().build();
}
```

Esse método está correto porque o Controller somente encaminha a exclusão ao Service e responde com **204 No Content**, sem devolver um corpo JSON.

Uma observação: o fato de termos implementado todos os endpoints não significa que a aplicação já esteja pronta para entrega. Ainda não verificamos a compilação nem executamos os testes.

---

## Nosso progresso na CP2

**Endpoints implementados — 9/9.** Controllers completos por revisão estática. Faltam tratamento de exceções, revisão das validações, testes e documentação do uso de IA.

## Task 09.1 — Criar o ErrorResponse

Agora vamos implementar o tratamento de erros da aplicação.

Imagine que alguém faça `GET /api/cursos/999`. Se esse curso não existir, seu Service lançará `ResourceNotFoundException("Curso não encontrado")`.

Precisamos transformar essa exceção em uma resposta HTTP adequada, com status **404 Not Found** e um corpo JSON padronizado.

Crie o arquivo `dto/ErrorResponse.java` como `record` com `LocalDateTime timestamp`, `int status`, `String error`, `String message`.

O `ErrorResponse` não precisa acessar Repository, Service nem ter anotações de entidade JPA. Sua responsabilidade é somente representar os dados de um erro.

Por enquanto, crie apenas esse `record`, sem implementar o `GlobalExceptionHandler`.

Quando terminar, envie o código para revisão. Depois construiremos o tratamento das exceções 404, 400 e 500.

---

## Prompt 20

````text
package br.com.alisson.cp2.sistemacursos.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message
) {
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 09.1 — ErrorResponse aprovada!**

Seu código está 100% correto em relação aos requisitos dessa tarefa. Não precisa alterar nada.

## Code Review

| Critério | Resultado |
|---|---|
| Utilização de `record` | Correto |
| `LocalDateTime timestamp` | Correto |
| `int status` | Correto |
| `String error` | Correto |
| `String message` | Correto |
| Import de `LocalDateTime` | Correto |

### O que esse record vai representar?

Imagine que alguém tente buscar um curso inexistente.

Nosso objetivo é devolver uma resposta semelhante a esta:

```json
{
  "timestamp": "2026-10-11T18:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Curso não encontrado"
}
```

O `record` representa a estrutura desse JSON. Ele não identifica nem trata a exceção sozinho.

É justamente isso que vamos implementar agora.

---

## Task 09.2 — Criar o GlobalExceptionHandler

Vamos fazer em pequenas etapas para você entender como o Spring transforma exceções em respostas HTTP.

**Primeira etapa: tratar somente `ResourceNotFoundException` com status 404.**

Crie a classe `GlobalExceptionHandler` no pacote `exceptions`. Adicione `@RestControllerAdvice`, crie `handleNotFound(ResourceNotFoundException ex)`, anote com `@ExceptionHandler(ResourceNotFoundException.class)`, retorne `ResponseEntity<ErrorResponse>` com 404.

**`@RestControllerAdvice`** diz ao Spring que essa classe pode tratar exceções lançadas durante o processamento das requisições pelos Controllers.

**`@ExceptionHandler`** indica qual tipo de exceção um método deve tratar.

```java
@ExceptionHandler(ResourceNotFoundException.class)
```

Isso significa: quando essa exceção chegar ao mecanismo de tratamento, o Spring poderá chamar o método correspondente.

Para montar seu `ErrorResponse`, você precisará de `LocalDateTime.now()`, `HttpStatus.NOT_FOUND.value()`, `HttpStatus.NOT_FOUND.getReasonPhrase()` e `ex.getMessage()`.

**Não basta colocar 404 dentro do JSON. A própria resposta HTTP também precisa ter status 404.**

Implemente apenas o tratamento do erro 404. Não precisa implementar os erros 400 e 500 ainda.

Quando terminar, envie o código. Vou verificar a implementação e, principalmente, se você entendeu a diferença entre **o status HTTP da resposta** e **o campo `status` dentro do DTO**.

---

## Prompt 21

````text
package br.com.alisson.cp2.sistemacursos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.alisson.cp2.sistemacursos.dto.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse erro = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 09.2 — Tratamento de erro 404 aprovada!**

Sua implementação está correta. Você conseguiu construir o primeiro tratamento global de exceções da sua API Spring Boot.

## Code Review

| Critério | Resultado |
|---|---|
| `@RestControllerAdvice` | Correto |
| `@ExceptionHandler(ResourceNotFoundException.class)` | Correto |
| Retorno `ResponseEntity<ErrorResponse>` | Correto |
| Data e hora com `LocalDateTime.now()` | Correto |
| Status 404 no DTO | Correto |
| Mensagem original da exceção | Correto |
| Status HTTP 404 na resposta | Correto |

Nenhuma correção obrigatória.

## Entendendo o que você construiu

Imagine que alguém envie `GET /api/cursos/999`.

O fluxo será: `CursoController` recebe a requisição → `CursoServiceImpl` lança `ResourceNotFoundException` → `GlobalExceptionHandler` captura e constrói `ErrorResponse` → HTTP 404 Not Found.

Um detalhe importante do seu código:

```java
return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
```

Essa linha define o status HTTP real da resposta. Já o `status` dentro de `ErrorResponse` é apenas uma informação presente no JSON.

Você acertou ambos.

---

## Task 09.3 — Tratar erros de validação (400 Bad Request)

Agora vamos tratar os erros gerados pelas validações que colocamos nos DTOs.

Por exemplo, quando alguém tenta cadastrar um curso sem título, o Spring executa a validação porque seu Controller utiliza `@Valid @RequestBody CursoRequest cursoRequest`.

Como o título possui `@NotBlank`, o Spring pode lançar `MethodArgumentNotValidException`.

**Nossa próxima tarefa é transformar essa exceção em HTTP 400, informando quais campos estão inválidos.**

### Etapa 09.3.1 — Criar ValidationErrorResponse

Crie `dto/ValidationErrorResponse.java`, um `record` com `timestamp` (`LocalDateTime`), `status` (`int`), `error` (`String`), `message` (`String`) e `fieldErrors` (`List<String>`).

Exemplo:

```json
{
  "timestamp": "2026-10-11T18:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Erro de validação",
  "fieldErrors": [
    "titulo: não deve estar em branco",
    "ordem: deve ser maior que 0"
  ]
}
```

**Sua tarefa agora:** crie somente o `ValidationErrorResponse` com esses cinco campos.

Depois vamos implementar o método que percorre os erros de validação do Spring para preencher essa lista.

Me envie sua tentativa e continuamos com o Code Review.

---

## Prompt 22

````text
package br.com.alisson.cp2.sistemacursos.exceptions;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alisson.cp2.sistemacursos.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse erro = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(campo -> campo.getField() + ": " + campo.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErrorResponse erro = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                mensagem
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Sua implementação está funcional, mas a Task 09.3 ainda precisa de um ajuste para atender ao formato que estamos construindo para a CP2.**

Você acertou uma parte importante: conseguiu extrair os erros de validação do Spring e transformá-los em mensagens compreensíveis.

## Code Review — GlobalExceptionHandler

| Critério | Resultado |
|---|---|
| `@ExceptionHandler(MethodArgumentNotValidException.class)` | Correto |
| Acesso aos erros com `getBindingResult()` | Correto |
| Utilização de `getFieldErrors()` | Correto |
| Extração do nome do campo e da mensagem | Correto |
| Retorno HTTP 400 | Correto |
| Uso de `ValidationErrorResponse` | Pendente |
| Lista de erros no JSON | Pendente |

## 1. O que você fez corretamente

```java
String mensagem = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(campo -> campo.getField() + ": " + campo.getDefaultMessage())
        .collect(Collectors.joining("; "));
```

Aqui você está executando quatro operações:

1. `getFieldErrors()` obtém os erros relacionados aos campos do DTO.
2. `stream()` permite processar os erros individualmente.
3. `map()` transforma cada erro em uma mensagem, como `"titulo: não deve estar em branco"`.
4. `Collectors.joining("; ")` junta todas as mensagens em uma única `String`.

Isso está certo.

**O problema não está na extração dos erros, mas no formato em que você os devolve.**

Atualmente, seu JSON juntaria todos os erros em `message`. Nosso objetivo é separar a mensagem geral dos erros específicos, usando `ValidationErrorResponse` com uma lista de erros em `fieldErrors`.

Em vez de `String mensagem = ...`, você precisará de `List<String> erros = ...`.

Você já sabe transformar cada erro com `.map()`. O que precisa mudar é apenas a operação terminal do Stream: em vez de `.collect(Collectors.joining("; "))`, pode usar `.toList()`.

## Task 09.3 — Correção

Faça essas três alterações:

- Crie `dto/ValidationErrorResponse.java` com os cinco campos que combinamos.
- Altere `handleValidation()` para retornar `ResponseEntity<ValidationErrorResponse>`.
- Utilize `List<String>` para preencher `fieldErrors`, mantendo uma mensagem geral como `"Erro de validação"`.

**Não altere o método `handleNotFound()`.** Ele já está correto.

Quando terminar, envie o `ValidationErrorResponse` e o `handleValidation()` atualizado.

---

## Prompt 23

````text
package br.com.alisson.cp2.sistemacursos.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alisson.cp2.sistemacursos.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404: o recurso procurado não existe
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse erro = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // 400: o corpo enviado falhou na validação (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<ErrorResponse.CampoErro> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(campo -> new ErrorResponse.CampoErro(
                        campo.getField(),
                        campo.getDefaultMessage()))
                .toList();

        ErrorResponse resposta = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Erro de validação",
                erros
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Você melhorou bastante o tratamento de validações, mas encontrei um possível erro de compilação.**

Sua lógica está correta. Porém, você mudou a estrutura do `ErrorResponse` em relação ao que tínhamos construído anteriormente.

## Code Review — Task 09.3

| Critério | Resultado |
|---|---|
| Captura `MethodArgumentNotValidException` | Correto |
| Extrai os erros com `getFieldErrors()` | Correto |
| Utiliza `stream().map().toList()` | Correto |
| Mantém campo e mensagem separados | Correto |
| Responde com HTTP 400 | Correto |
| Compatibilidade com `ErrorResponse` | Precisa verificar |

## 1. Onde está o possível problema?

Você escreveu:

```java
List<ErrorResponse.CampoErro> erros = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(campo -> new ErrorResponse.CampoErro(
                campo.getField(),
                campo.getDefaultMessage()))
        .toList();
```

Isso é uma solução válida para representar os erros de maneira estruturada.

Entretanto, anteriormente seu `ErrorResponse` tinha apenas quatro campos:

```java
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message
) {}
```

Agora você está tentando utilizar `ErrorResponse.CampoErro` e construir um `ErrorResponse` com cinco argumentos.

**Se você ainda não modificou o `ErrorResponse`, esse código não compila**, porque `CampoErro` não existe nele e o construtor recebe somente quatro argumentos.

## 2. A ideia que você utilizou é interessante

Você está tentando produzir um JSON com `fieldErrors` como lista de objetos contendo campo e mensagem.

Essa representação é até mais estruturada que a lista de strings que estávamos planejando.

Mas precisamos definir onde `CampoErro` será declarado e qual DTO será responsável pelos erros de validação.

Para manter o padrão que combinamos, podemos continuar utilizando `ValidationErrorResponse`, separado do `ErrorResponse` comum.

---

## Sua próxima tarefa — Verificar o ErrorResponse

Antes de avançarmos para o erro 500, **me envie o código atual de `ErrorResponse.java`**.

Quero conferir se você modificou esse record para incluir `CampoErro` e a lista de erros ou se implementou somente o `GlobalExceptionHandler`.

Dependendo da sua resposta, faremos o ajuste necessário sem reescrever desnecessariamente o que você já desenvolveu.

A lógica de tratamento HTTP 400 está no caminho certo. Precisamos apenas garantir que os DTOs e o Handler tenham contratos compatíveis e que o código realmente compile.

---

## Prompt 24

````text
package br.com.alisson.cp2.sistemacursos.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        List<CampoErro> erros
) {

    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message) {
        this(timestamp, status, error, message, List.of());
    }

    public record CampoErro(String campo, String mensagem) {
    }
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Agora sim! Task 09.3 — Tratamento de erros de validação aprovada!**

Com esse `ErrorResponse`, confirmei que seu `GlobalExceptionHandler` anterior está compatível. O código que você enviou está coerente e não encontrei erros de compilação nesses dois trechos.

## Code Review — ErrorResponse

| Critério | Resultado |
|---|---|
| `record` com os dados do erro | Correto |
| Lista `List<CampoErro>` | Correto |
| Construtor adicional com quatro parâmetros | Correto |
| `record CampoErro` para cada campo inválido | Correto |
| Compatibilidade com o Handler 400 e 404 | Correto |

### Duas coisas importantes que você implementou

**1. Um construtor adicional para o record**

```java
public ErrorResponse(LocalDateTime timestamp, int status, String error, String message) {
    this(timestamp, status, error, message, List.of());
}
```

Isso permite criar o mesmo `ErrorResponse` de duas maneiras: com os quatro dados básicos ou incluindo uma lista de erros.

Quando o erro for 404, o Handler poderá continuar utilizando quatro argumentos, e a lista será inicializada vazia.

**2. Um record dentro de outro record**

```java
public record CampoErro(String campo, String mensagem) {
}
```

Isso permite representar cada erro de validação separadamente. Por exemplo:

```json
{
  "campo": "titulo",
  "mensagem": "não deve estar em branco"
}
```

É uma boa estrutura para uma API.

Uma observação: anteriormente planejávamos utilizar um `ValidationErrorResponse` separado, mas sua implementação resolveu o mesmo problema reutilizando `ErrorResponse`. Podemos manter essa estrutura, desde que o enunciado não exija um DTO separado especificamente. Como os campos são diferentes, ainda vale conferir esse detalhe na revisão final.

---

## Task 09.4 — Tratar erros inesperados (HTTP 500)

Agora vamos implementar o terceiro tratamento de exceções.

Você já tem:

- **404 Not Found:** quando um curso ou módulo não existe.
- **400 Bad Request:** quando um DTO apresenta erros de validação.

Falta o **500 Internal Server Error**, utilizado quando ocorre uma falha inesperada durante o processamento da requisição.

Na classe `GlobalExceptionHandler`, crie um método chamado `handleGenericException` com `@ExceptionHandler(Exception.class)`, recebendo `Exception`, retornando `ResponseEntity<ErrorResponse>` com `LocalDateTime.now()`, status 500, `"Internal Server Error"` e mensagem genérica `"Ocorreu um erro interno no servidor"`.

**No tratamento 500, não devemos devolver diretamente `ex.getMessage()`.** Uma exceção inesperada pode conter detalhes internos como nomes de tabelas e consultas SQL.

**Implemente somente esse terceiro método e me envie.** Depois faremos a revisão final das validações e começaremos a compilar e testar sua CP2 de verdade.

---

## Prompt 25

````text
@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
    ErrorResponse erro = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "Ocorreu um erro interno no servidor"
    );

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 09.4 — Tratamento de erro 500 aprovada!**

Seu método está correto e atende aos requisitos que definimos. Com isso, temos os três tratamentos globais de exceções implementados!

## Code Review

| Critério | Resultado |
|---|---|
| `@ExceptionHandler(Exception.class)` | Correto |
| Recebe uma exceção `Exception` | Correto |
| Retorna `ResponseEntity<ErrorResponse>` | Correto |
| Utiliza `LocalDateTime.now()` | Correto |
| Define status HTTP 500 | Correto |
| Utiliza mensagem genérica | Correto |
| Não expõe detalhes internos da exceção | Correto |

### Um detalhe importante que você acertou

```java
" Ocorreu um erro interno no servidor "
```

A mensagem que você utilizou não revela informações técnicas da aplicação.

Isso é importante porque uma exceção pode conter detalhes sobre o banco de dados, consultas SQL ou configurações internas.

Seu tratamento funciona como uma última proteção para exceções não tratadas especificamente pelos outros métodos.

Uma melhoria futura seria registrar a exceção completa em logs, permitindo que o desenvolvedor investigue o problema sem revelar detalhes ao usuário da API.

---

## Tratamento de exceções — Concluído

| Exceção | Status HTTP |
|---|---|
| `ResourceNotFoundException` | 404 Not Found |
| `MethodArgumentNotValidException` | 400 Bad Request |
| `Exception` | 500 Internal Server Error |

Aprovação por revisão estática. Precisamos validar o funcionamento quando a aplicação estiver em execução.

---

## Task 10.1 — Revisar a validação personalizada

Agora precisamos conferir uma parte importante da sua CP2: a regra de que a carga horária de um módulo deve ser múltipla de 5.

Você já tinha criado uma anotação chamada `@MultiploDeCinco`, mas precisamos verificar se ela atende completamente ao requisito da atividade.

**Sua próxima tarefa é me enviar somente estes dois arquivos:**

1. A anotação `MultiploDeCinco.java`.
2. A classe que implementa `ConstraintValidator` para essa anotação.

Não precisa modificar nenhum código por enquanto.

Quero verificar especialmente se sua validação permite configurar o múltiplo, se funciona com `@Valid` e se está integrada corretamente ao `ModuloRequest`.

Depois dessa revisão, vamos partir para a compilação e os testes reais, para descobrir se tudo que construímos está funcionando junto.

---

## Prompt 26

````text
package br.com.alisson.cp2.sistemacursos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultiploDeCincoValidator.class)
public @interface MultiploDeCinco {
    String message() default "Aceito apenas valores multiplos de 5";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
package br.com.alisson.cp2.sistemacursos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultiploDeCincoValidator implements ConstraintValidator<MultiploDeCinco, Integer> {

    @Override
    public boolean isValid(Integer cargaHoraria, ConstraintValidatorContext constraintValidatorContext) {
        if (cargaHoraria == null) {
            return true;
        }
        if (cargaHoraria % 5 == 0) {
            return true;
        }
        return false;
    }
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 10.1 — Sua validação funciona, mas precisa de um ajuste para atender completamente à CP2.**

Você construiu corretamente uma anotação personalizada com Jakarta Validation. A lógica de verificar múltiplos de 5 também está certa.

O problema é que **o número 5 está fixo no código**, enquanto queremos uma anotação configurável.

## Code Review

| Critério | Resultado |
|---|---|
| `@Constraint` | Correto |
| `@Target(ElementType.FIELD)` | Correto |
| `@Retention(RetentionPolicy.RUNTIME)` | Correto |
| Implementação de `ConstraintValidator` | Correto |
| Tratamento de valores nulos | Correto |
| Verificação de múltiplos de 5 | Correto |
| Possibilidade de configurar o múltiplo | Pendente |

## 1. O que você já acertou

No seu Validator:

```java
if (cargaHoraria == null) {
    return true;
}
```

Isso está correto. A responsabilidade dessa anotação é verificar se o número é múltiplo de determinado valor.

Se o campo for obrigatório, quem deve verificar a presença de um valor é o `@NotNull`, que você já utilizou no `ModuloRequest`.

Também acertou:

```java
cargaHoraria % 5 == 0
```

O operador `%` calcula o resto da divisão. Quando o resultado é zero, sabemos que o número é múltiplo de 5.

## 2. O que precisamos melhorar?

Imagine que amanhã você queira validar outro campo, mas exigindo múltiplos de 10.

Atualmente, sua anotação `@MultiploDeCinco` sempre verifica múltiplos de 5.

Para permitir uma configuração como `@MultiploDeCinco(valor = 5)`, precisamos fazer duas alterações.

**Na anotação:** declarar um atributo configurável:

```java
int valor() default 5;
```

**No Validator:** aprender a receber o valor configurado na anotação.

Para isso, existe um método chamado:

```java
@Override
public void initialize(MultiploDeCinco anotacao) {
    // Receber a configuração da anotação
}
```

O método `initialize()` é chamado pelo mecanismo de validação para disponibilizar as configurações da anotação ao Validator.

Assim, em vez de utilizar sempre `% 5`, você poderá utilizar o valor configurado.

Uma melhoria de nomenclatura seria chamar a anotação de `@MultiploDe`, já que ela deixaria de representar exclusivamente o número cinco. Isso não é obrigatório para a lógica funcionar.

---

## Task 10.2 — Tornar a validação configurável

1. Adicione `int valor() default 5;` à anotação.
2. No Validator, crie um atributo privado do tipo `int` para guardar esse valor.
3. Implemente `initialize(MultiploDeCinco anotacao)` e obtenha o valor usando `anotacao.valor()`.
4. Substitua o número fixo no cálculo de `isValid()` pelo atributo criado.
5. Pense em como impedir que alguém configure `valor = 0`, porque não podemos calcular o resto de uma divisão por zero.

**Uma dúvida para exercitar seu raciocínio:** por que precisamos guardar o valor em um atributo da classe Validator, em vez de recebê-lo como mais um parâmetro de `isValid()`?

Faça a tentativa e me envie as duas classes atualizadas. Depois vamos compilar o projeto e testar essa validação na API.

---

## Prompt 27

````text
package br.com.alisson.cp2.sistemacursos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultiploDeCincoValidator implements ConstraintValidator<MultiploDeCinco, Integer> {

    private int valor;

    @Override
    public void initialize(MultiploDeCinco anotacao) {
        if (anotacao.valor() <= 0) {
            throw new IllegalArgumentException("O valor de @MultiploDeCinco deve ser maior que zero");
        }
        this.valor = anotacao.valor();
    }

    @Override
    public boolean isValid(Integer cargaHoraria, ConstraintValidatorContext context) {
        if (cargaHoraria == null) {
            return true;
        }
        return cargaHoraria % valor == 0;
    }
}
package br.com.alisson.cp2.sistemacursos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultiploDeCincoValidator.class)
public @interface MultiploDeCinco {
    String message() default "Aceito apenas valores multiplos de {valor}";
    int valor() default 5;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 10.2 — Validação personalizada aprovada!**

Agora sua anotação é configurável, e você também tratou o problema da divisão por zero. As duas classes estão coerentes e não encontrei erros de sintaxe nos trechos enviados.

## Code Review

| Critério | Resultado |
|---|---|
| Atributo `valor()` configurável | Correto |
| Valor padrão igual a 5 | Correto |
| Implementação de `initialize()` | Correto |
| Proteção contra divisor zero ou negativo | Correto |
| Verificação com `% valor` | Correto |
| Tratamento de `null` | Correto |
| Mensagem dinâmica com `{valor}` | Correto |

### O que você fez de mais importante

```java
@Override
public void initialize(MultiploDeCinco anotacao) {
    if (anotacao.valor() <= 0) {
        throw new IllegalArgumentException(
            "O valor de @MultiploDeCinco deve ser maior que zero"
        );
    }
    this.valor = anotacao.valor();
}
```

Esse método recebe a configuração da anotação e a guarda no atributo `valor`.

Depois, quando o Spring executar a validação:

```java
return cargaHoraria % valor == 0;
```

O cálculo utilizará o múltiplo configurado, em vez de depender do número 5 fixo no código.

Você também separou corretamente duas responsabilidades:

- `@NotNull` verifica se a carga horária foi informada.
- `@MultiploDeCinco` verifica se o valor é divisível pelo múltiplo configurado.

Uma observação: a anotação ainda se chama `MultiploDeCinco`, embora agora aceite outros valores. Isso não impede seu funcionamento, mas um nome como `MultiploDe` seria mais representativo caso você queira reutilizá-la.

---

## Task 10.3 — Compilar a aplicação

Agora precisamos sair da revisão estática e verificar se todas as classes realmente funcionam juntas.

Você já implementou os Controllers, Services, Repositories, DTOs, Mappers, tratamento de exceções e validação personalizada.

**Primeira tarefa: executar a compilação e os testes existentes.**

Abra o terminal na raiz do projeto, onde está o `pom.xml`, e execute:

```powershell
.\mvnw.cmd clean verify
```

Esse comando executa o ciclo do Maven até `verify`, incluindo compilação e testes configurados no projeto.

Se o projeto não tiver o Maven Wrapper, utilize:

```powershell
mvn clean verify
```

O resultado que procuramos é `BUILD SUCCESS`.

Se aparecer `BUILD FAILURE`, não tente mudar várias classes de uma vez. Envie a primeira mensagem de erro relevante, incluindo o arquivo e a linha indicada.

**Por enquanto, faça somente essa execução e me envie o resultado.** Assim poderemos descobrir se existe algum problema real de integração antes de começar os testes HTTP.

E lembre-se de manter o arquivo obrigatório `uso-de-ia.md` atualizado com as interações de IA exigidas pelo enunciado da CP2.

---

## Prompt 28

deu sucesso

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Task 10.3 — Compilação aprovada!**

Ótimo! O `BUILD SUCCESS` indica que o Maven conseguiu concluir o processo de build, incluindo a compilação e a execução das verificações configuradas.

Isso é um marco importante: **nosso projeto passou da revisão de código para uma compilação bem-sucedida.**

Mas atenção: compilar não garante que os endpoints funcionem corretamente. Agora precisamos testar o comportamento da API.

## Task 11.1 — Primeiro teste real da API

Vamos começar pelo cadastro de cursos.

### Passo 1 — Iniciar o Spring Boot

No terminal, na pasta do projeto, execute:

```powershell
.\mvnw.cmd spring-boot:run
```

Espere aparecer uma mensagem semelhante a:

```text
Tomcat started on port 8080
Started SistemaCursosApplication
```

A porta e o nome da classe podem variar conforme sua configuração.

### Passo 2 — Testar o POST

Abra o Postman ou Insomnia e configure `POST http://localhost:8080/api/cursos` com header `Content-Type: application/json` e body:

```json
{
  "titulo": "Java Avançado",
  "descricao": "Curso de Spring Boot e JPA"
}
```

### Passo 3 — Conferir o resultado

O esperado é receber:

- **Status:** `201 Created`
- **Header Location:** `/api/cursos/1` (o ID pode ser diferente)
- **Body:** JSON contendo `id`, `titulo` e `descricao`.

Se esse cadastro funcionar, teremos confirmado parte da integração entre Controller, Service, Repository e H2. Também confirmaremos que a API consegue serializar o `CursoResponse` e devolver o cabeçalho `Location`.

**Sua tarefa agora:** execute somente esse primeiro POST e me diga qual status HTTP recebeu e qual JSON apareceu na resposta.

Depois testaremos as validações, os relacionamentos entre cursos e módulos, a paginação e as exclusões, um cenário por vez.

---

## Prompt 29

````text
2026-10-11T18:13:35.296-03:00  INFO 7824 --- [sistema-cursos] [  restartedMain] o.s.b.h.a.H2ConsoleAutoConfiguration     : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:cursos'
2026-10-11T18:13:35.339-03:00  WARN 7824 --- [sistema-cursos] [  restartedMain] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.context.ApplicationContextException: Failed to start bean 'webServerStartStop'
2026-10-11T18:13:35.341-03:00  INFO 7824 --- [sistema-cursos] [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2026-10-11T18:13:35.345-03:00  WARN 7824 --- [sistema-cursos] [  restartedMain] o.s.b.f.support.DisposableBeanAdapter    : Invocation of destroy method failed on bean with name 'inMemoryDatabaseShutdownExecutor': org.h2.jdbc.JdbcSQLNonTransientConnectionException: Database is already closed (to disable automatic closing at VM shutdown, add ";DB_CLOSE_ON_EXIT=FALSE" to the db URL) [90121-240]
2026-10-11T18:13:35.345-03:00  INFO 7824 --- [sistema-cursos] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2026-10-11T18:13:35.347-03:00  INFO 7824 --- [sistema-cursos] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
2026-10-11T18:13:35.361-03:00  INFO 7824 --- [sistema-cursos] [  restartedMain] .s.b.a.l.ConditionEvaluationReportLogger :

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2026-10-11T18:13:35.376-03:00 ERROR 7824 --- [sistema-cursos] [  restartedMain] o.s.b.d.LoggingFailureAnalysisReporter   :

***************************
APPLICATION FAILED TO START
***************************

Description:

Web server failed to start. Port 8080 was already in use.

Action:

Identify and stop the process that's listening on port 8080 or configure this application to listen on another port.

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.270 s
[INFO] Finished at: 2026-10-11T18:13:35-03:00
[INFO] ------------------------------------------------------------------------
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

Encontrei o problema! E a boa notícia é que **não parece ser um erro no código Java da sua CP2.**

O próprio Spring informou:

```text
APPLICATION FAILED TO START

Web server failed to start.
Port 8080 was already in use.
```

Isso significa que **já existe algum processo utilizando a porta 8080**, então o Spring Boot não conseguiu iniciar o servidor nessa mesma porta.

## Por que apareceu BUILD SUCCESS mesmo com erro?

São duas coisas diferentes:

- **BUILD SUCCESS:** o Maven terminou a execução sem reportar falha no seu processo.
- **APPLICATION FAILED TO START:** a aplicação Spring Boot não conseguiu iniciar o servidor HTTP.

Portanto, sua API ainda não está funcionando nessa execução.

## Task 11.1.1 — Descobrir quem está usando a porta 8080

Vamos investigar antes de encerrar qualquer processo.

Abra outro terminal PowerShell e execute:

```powershell
netstat -ano | findstr :8080
```

Você verá algo parecido com:

```text
TCP    0.0.0.0:8080    0.0.0.0:0    LISTENING    12345
```

O último número, `12345`, é o **PID**, identificador do processo que está utilizando essa porta.

Depois, substitua pelo PID encontrado:

```powershell
tasklist /FI "PID eq 12345"
```

Esse comando mostra qual programa está utilizando a porta.

### Por que vamos fazer isso?

Imagine que seja outra instância do Spring Boot que você deixou aberta. Nesse caso, basta encerrá-la corretamente.

Mas também pode ser outro programa que você precisa manter funcionando. Por isso, não vamos finalizar processos sem identificá-los.

**Sua tarefa agora:** execute esses dois comandos e me envie os resultados.

Assim descobrimos a causa exata do conflito e resolvemos sem alterar desnecessariamente seu projeto.

Observação: os avisos do H2 sobre o fechamento do banco aparecem durante o encerramento da aplicação. O problema principal mostrado no log é a porta 8080 ocupada, então vamos resolver isso primeiro.

---

## Prompt 30

````text
[INFO] Finished at: 2026-10-11T18:13:35-03:00
[INFO] ------------------------------------------------------------------------
PS C:\Users\Administrator\Desktop\ESTUDOS\SistemaCursos>
PS C:\Users\Administrator\Desktop\ESTUDOS\SistemaCursos> netstat -ano | findstr :8080
  TCP    127.0.0.1:8080         0.0.0.0:0              LISTENING       8388
  TCP    127.0.0.1:8080         127.0.0.1:55932        ESTABLISHED     8388
  TCP    127.0.0.1:8080         127.0.0.1:59735        ESTABLISHED     8388
  TCP    127.0.0.1:55932        127.0.0.1:8080         ESTABLISHED     17708
  TCP    127.0.0.1:59711        127.0.0.1:8080         TIME_WAIT       0
  TCP    127.0.0.1:59735        127.0.0.1:8080         ESTABLISHED     17708
PS C:\Users\Administrator\Desktop\ESTUDOS\SistemaCursos>
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

Boa! Agora sabemos exatamente qual processo está ocupando a porta 8080.

No seu resultado, a linha importante é:

```text
TCP    127.0.0.1:8080    0.0.0.0:0    LISTENING    8388
```

Isso significa:

- **127.0.0.1:8080:** a porta está sendo utilizada no seu computador.
- **LISTENING:** existe um programa aguardando conexões nessa porta.
- **8388:** é o PID do processo responsável.

As linhas `ESTABLISHED` mostram que também existem conexões ativas com esse processo.

## Task 11.1.2 — Identificar o programa

No mesmo PowerShell, execute:

```powershell
tasklist /FI "PID eq 8388"
```

Esse comando vai mostrar o nome do programa.

Se aparecer `java.exe`, pode ser outra instância do seu Spring Boot, mas precisamos confirmar antes de encerrá-la.

Para identificar melhor, você também pode executar:

```powershell
Get-CimInstance Win32_Process -Filter "ProcessId = 8388" |
    Select-Object ProcessId, Name, CommandLine
```

Esse segundo comando permite verificar o comando utilizado para iniciar o processo.

**Me envie o resultado antes de finalizar qualquer coisa.** Se for uma instância antiga do SistemaCursos, vamos encerrá-la e iniciar a aplicação novamente.

Estamos resolvendo um problema de infraestrutura local, não necessariamente um defeito na sua API.

---

## Prompt 31

````text
[Imagem anexada: captura do PowerShell com `tasklist /FI "PID eq 8388"`, identificando `steamwebhelper.exe` (PID 8388). O conteúdo visual não pode ser representado byte a byte em Markdown sem incorporar a imagem.]
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

Agora encontramos o responsável!

No seu print aparece:

```text
Nome da imagem         PID
steamwebhelper.exe     8388
```

**É um processo do Steam que está utilizando a porta 8080**, impedindo o Spring Boot de iniciar.

Não precisamos encerrar o Steam nem alterar seu código Java.

## Task 11.1.3 — Iniciar o Spring Boot na porta 8081

Vamos utilizar outra porta temporariamente, sem modificar o `application.yml`.

No terminal do projeto, execute:

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"
```

Aguarde uma mensagem semelhante a:

```text
Tomcat started on port 8081
Started SistemaCursosApplication
```

Se aparecer, sua aplicação estará disponível em `http://localhost:8081`.

E o primeiro teste será `POST http://localhost:8081/api/cursos`, com o JSON:

```json
{
  "titulo": "Java Avançado",
  "descricao": "Curso de Spring Boot e JPA"
}
```

**Sua tarefa agora:** execute o comando e me diga se apareceu `Tomcat started on port 8081`.

Se funcionar, finalmente começaremos a testar nossa API de verdade, incluindo banco H2, validações e relacionamento entre cursos e módulos.

---

## Prompt 32

````text
Web server failed to start. Port 8080 was already in use.

Action:

Identify and stop the process that's listening on port 8080 or configure this application to listen on another port.

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.270 s
[INFO] Finished at: 2026-10-11T18:13:35-03:00
[INFO] ------------------------------------------------------------------------
PS C:\Users\Administrator\Desktop\ESTUDOS\SistemaCursos>
PS C:\Users\Administrator\Desktop\ESTUDOS\SistemaCursos> netstat -ano | findstr :8080
  TCP    127.0.0.1:8080         0.0.0.0:0              LISTENING       8388
  TCP    127.0.0.1:8080         127.0.0.1:55932        ESTABLISHED     8388
  TCP    127.0.0.1:8080         127.0.0.1:59735        ESTABLISHED     8388
  TCP    127.0.0.1:55932        127.0.0.1:8080         ESTABLISHED     17708
  TCP    127.0.0.1:59711        127.0.0.1:8080         TIME_WAIT       0
  TCP    127.0.0.1:59735        127.0.0.1:8080         ESTABLISHED     17708
PS C:\Users\Administrator\Desktop\ESTUDOS\SistemaCursos> .\mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"
[INFO] Scanning for projects...
[INFO]
[INFO] -----------------< br.com.alisson.cp2:sistema-cursos >------------------
[INFO] Building SistemaCursos 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> spring-boot:4.1.1:run (default-cli) > test-compile @ sistema-cursos >>>
[INFO]
[INFO] --- resources:3.5.0:resources (default-resources) @ sistema-cursos ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.15.0:compile (default-compile) @ sistema-cursos ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.5.0:testResources (default-testResources) @ sistema-cursos ---
[INFO] skip non existing resourceDirectory C:\Users\Administrator\Desktop\ESTUDOS\SistemaCursos\src\test\resources
[INFO]
[INFO] --- compiler:3.15.0:testCompile (default-testCompile) @ sistema-cursos ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] <<< spring-boot:4.1.1:run (default-cli) < test-compile @ sistema-cursos <<<
[INFO]
[INFO]
[INFO] --- spring-boot:4.1.1:run (default-cli) @ sistema-cursos ---
[INFO] Attaching agents: []

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.1.1)

2026-10-11T18:15:29.912-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] b.c.a.c.s.SistemaCursosApplication       : Starting SistemaCursosApplication using Java 24.0.2 with PID 24048 (C:\Users\Administrator\Desktop\ESTUDOS\SistemaCursos\target\classes started by Alisson Kawan in C:\Users\Administrator\Desktop\ESTUDOS\SistemaCursos)
2026-10-11T18:15:29.916-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] b.c.a.c.s.SistemaCursosApplication       : No active profile set, falling back to 1 default profile: "default"
2026-10-11T18:15:29.968-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2026-10-11T18:15:29.969-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2026-10-11T18:15:30.495-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2026-10-11T18:15:30.557-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 53 ms. Found 2 JPA repository interfaces.
2026-10-11T18:15:30.978-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] o.s.boot.tomcat.TomcatWebServer          : Tomcat initialized with port 8081 (http)
2026-10-11T18:15:30.992-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2026-10-11T18:15:30.993-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/11.0.24]
2026-10-11T18:15:31.035-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] b.w.c.s.WebApplicationContextInitializer : Root WebApplicationContext: initialization completed in 1066 ms
2026-10-11T18:15:31.237-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
2026-10-11T18:15:31.290-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] org.hibernate.orm.core                   : HHH000001: Hibernate ORM core version 7.4.5.Final
2026-10-11T18:15:31.690-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2026-10-11T18:15:31.720-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2026-10-11T18:15:31.861-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:cursos user=SA
2026-10-11T18:15:31.862-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2026-10-11T18:15:31.916-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
        Database JDBC URL [jdbc:h2:mem:cursos]
        Database driver: H2 JDBC Driver
        Database dialect: H2Dialect
        Database version: 2.4.240
        Default catalog/schema: CURSOS/PUBLIC
        Autocommit mode: undefined/unknown
        Isolation level: READ_COMMITTED [default READ_COMMITTED]
        JDBC fetch size: 100
        Pool: DataSourceConnectionProvider
        Minimum pool size: undefined/unknown
        Maximum pool size: undefined/unknown
2026-10-11T18:15:32.698-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Hibernate:
    create table curso (
        id bigint generated by default as identity,
        descricao varchar(255),
        titulo varchar(150) not null,
        primary key (id)
    )
Hibernate:
    create table modulo (
        id bigint generated by default as identity,
        carga_horaria integer not null,
        ordem integer not null,
        titulo varchar(150) not null,
        curso_id bigint not null,
        primary key (id)
    )
Hibernate:
    alter table if exists modulo
       add constraint FK55aiojg2tupnm1nmvguesrbiy
       foreign key (curso_id)
       references curso
2026-10-11T18:15:32.759-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2026-10-11T18:15:32.826-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] o.s.d.j.r.query.QueryEnhancerFactories   : Hibernate is in classpath; If applicable, HQL parser will be used.
2026-10-11T18:15:33.008-03:00  WARN 24048 --- [sistema-cursos] [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2026-10-11T18:15:33.244-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] o.s.b.h.a.H2ConsoleAutoConfiguration     : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:cursos'
2026-10-11T18:15:33.290-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] o.s.boot.tomcat.TomcatWebServer          : Tomcat started on port 8081 (http) with context path '/'
2026-10-11T18:15:33.295-03:00  INFO 24048 --- [sistema-cursos] [  restartedMain] b.c.a.c.s.SistemaCursosApplication       : Started SistemaCursosApplication in 3.757 seconds (process running for 4.147)
````

### Resposta

**Estado de fidelidade:** reprodução parcial/adaptada; conferir e substituir pela resposta original integral.

**Agora sim! Task 11.1 — Inicialização do Spring Boot aprovada!**

Sua aplicação iniciou corretamente na porta **8081**. E temos algumas confirmações importantes nos logs.

## O que os logs confirmam

| Componente | Resultado |
|---|---|
| Servidor Tomcat | Porta 8081 |
| Spring Boot 4.1.1 | Inicializado |
| Banco H2 | Conectado |
| JPA / Hibernate | Inicializado |
| Tabela `curso` | Criada |
| Tabela `modulo` | Criada |
| Chave estrangeira `curso_id` | Criada |
| Aplicação em execução | Correto |

### Um detalhe muito interessante

Observe o SQL gerado pelo Hibernate:

```sql
create table modulo (
    id bigint generated by default as identity,
    carga_horaria integer not null,
    ordem integer not null,
    titulo varchar(150) not null,
    curso_id bigint not null,
    primary key (id)
)
```

E depois:

```sql
alter table if exists modulo
    add constraint FK55aiojg2tupnm1nmvguesrbiy
    foreign key (curso_id)
    references curso
```

Lembra quando estudamos `@ManyToOne` e `@JoinColumn`?

**Agora você está vendo o efeito real dessas anotações no banco de dados.**

O Hibernate criou `curso_id` na tabela `modulo` e configurou uma chave estrangeira apontando para `curso`.

Isso confirma que o mapeamento básico do relacionamento foi reconhecido.

---

## Task 11.2 — Testar o cadastro de curso

Agora vamos testar se o Controller, Service e Repository estão funcionando juntos.

**Mantenha o terminal do Spring Boot aberto.** Se você encerrá-lo, a API deixará de responder.

No Postman, configure `POST http://localhost:8081/api/cursos`, header `Content-Type: application/json`, body raw JSON:

```json
{
  "titulo": "Java Avançado",
  "descricao": "Curso de Spring Boot e JPA"
}
```

### Resultado esperado

**201 Created**, body contendo os dados criados e `Location: /api/cursos/1` (o ID pode variar).

**Me envie o status HTTP e o JSON retornado pelo Postman.** Se houver erro, mande a resposta completa para investigarmos a causa.

Observação: o aviso `spring.jpa.open-in-view is enabled` não impede a inicialização. Podemos revisar essa configuração posteriormente. Também notei que sua aplicação está usando Java 24; vale conferir antes da entrega se a CP2 exige execução especificamente com Java 21.

Agora vamos confirmar o funcionamento do seu primeiro endpoint de verdade.

---

## Prompt 33

Beleza agora quero o arquivo .md bonito da forma como pede a CP de todos os prompts que te dei e todas as suas respostas honestamente aqui no arquivo!!!

### Resposta

**Estado:** resposta ainda não enviada nesta própria interação.

[RESPOSTA DESTA PRÓPRIA INTERAÇÃO AINDA NÃO CONCLUÍDA NO MOMENTO DA GERAÇÃO DO ARQUIVO. Adicionar a mensagem final de entrega deste chat literalmente, sem reconstrução.]

---

## Observações finais

Registrei acima os prompts e as respostas que consegui reunir. Em alguns pontos, o conteúdo está identificado como reprodução parcial ou adaptada porque não consegui recuperar a resposta original inteira no momento da organização. Também utilizei mais de um chat durante a CP, e parte dessas conversas ainda não foi incluída aqui.

O [histórico de commits do projeto](https://github.com/AlissonKawan/CP-Java-Advanced-projeto-de-cursos/commits/master) permite acompanhar a evolução do código, inclusive erros corrigidos, mas não contém por si só o histórico completo das conversas com o ChatGPT.

Sei que o enunciado solicita todos os prompts e respostas na íntegra. As partes que ainda faltam precisam ser acrescentadas para que esse requisito seja atendido.

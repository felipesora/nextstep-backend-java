package br.com.nextstep.controller;

import br.com.nextstep.dto.trilha.TrilhaRequestDTO;
import br.com.nextstep.dto.trilha.TrilhaResponseDTO;
import br.com.nextstep.service.TrilhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/trilhas")
public class TrilhaController {

    @Autowired
    private TrilhaService trilhaService;

    @GetMapping
    public ResponseEntity<Page<TrilhaResponseDTO>> listarTodos(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable paginacao) {
        var trilhas = trilhaService.listarTodos(paginacao);
        return ResponseEntity.ok(trilhas);
    }

    @GetMapping("/ativas")
    public ResponseEntity<Page<TrilhaResponseDTO>> listarAtivas(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable paginacao) {
        var trilhas = trilhaService.listarAtivas(paginacao);
        return ResponseEntity.ok(trilhas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrilhaResponseDTO> buscarPorId(@PathVariable Long id) {
        var trilha = trilhaService.buscarPorId(id);
        return ResponseEntity.ok(trilha);
    }

    @GetMapping("/procedure")
    public ResponseEntity<List<TrilhaResponseDTO>> listarTrilhasProcedure() {
        List<TrilhaResponseDTO> trilhas = trilhaService.listarTrilhasPorProcedure();
        return ResponseEntity.ok(trilhas);
    }

    @PostMapping
    public ResponseEntity<TrilhaResponseDTO> salvar(@RequestBody @Valid TrilhaRequestDTO trilhaDTO, UriComponentsBuilder uriBuilder) {
        var trilha = trilhaService.salvar(trilhaDTO);

        var uri = uriBuilder.path("/trilhas/{id}").buildAndExpand(trilha.getId()).toUri();
        return ResponseEntity.created(uri).body(trilha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrilhaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TrilhaRequestDTO trilhaDTO) {
        var trilhaAtualizada = trilhaService.atualizar(id, trilhaDTO);
        return ResponseEntity.ok(trilhaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        trilhaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.nextstep.controller;

import br.com.nextstep.dto.conteudo.ConteudoRequestDTO;
import br.com.nextstep.dto.conteudo.ConteudoResponseDTO;
import br.com.nextstep.service.ConteudoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/conteudos")
public class ConteudoController {

    @Autowired
    private ConteudoService conteudoService;

    @GetMapping
    public ResponseEntity<Page<ConteudoResponseDTO>> listarTodos(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable paginacao) {
        var conteudos = conteudoService.listarTodos(paginacao);
        return ResponseEntity.ok(conteudos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConteudoResponseDTO> buscarPorId(@PathVariable Long id) {
        var conteudo = conteudoService.buscarPorId(id);
        return ResponseEntity.ok(conteudo);
    }

    @PostMapping
    public ResponseEntity<ConteudoResponseDTO> salvar(@RequestBody @Valid ConteudoRequestDTO conteudoDTO, UriComponentsBuilder uriBuilder) {
        var conteudo = conteudoService.salvar(conteudoDTO);

        var uri = uriBuilder.path("/conteudos/{id}").buildAndExpand(conteudo.getId()).toUri();
        return ResponseEntity.created(uri).body(conteudo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConteudoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ConteudoRequestDTO conteudoDTO) {
        var conteudoAtualizado = conteudoService.atualizar(id, conteudoDTO);
        return ResponseEntity.ok(conteudoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        conteudoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

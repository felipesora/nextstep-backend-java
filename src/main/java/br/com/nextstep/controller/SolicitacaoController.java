package br.com.nextstep.controller;

import br.com.nextstep.dto.solicitacao.SolicitacaoRequestDTO;
import br.com.nextstep.dto.solicitacao.SolicitacaoResponseDTO;
import br.com.nextstep.service.SolicitacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping
    public ResponseEntity<Page<SolicitacaoResponseDTO>> listarTodos(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable paginacao) {
        var solicitacoes = solicitacaoService.listarTodos(paginacao);

        return ResponseEntity.ok(solicitacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        var solicitacao = solicitacaoService.buscarPorId(id);

        return ResponseEntity.ok(solicitacao);
    }

    @PostMapping
    public ResponseEntity<SolicitacaoResponseDTO> salvar(@RequestBody @Valid SolicitacaoRequestDTO solicitacaoDTO, UriComponentsBuilder uriBuilder) {
        System.out.println("CHEGOU NO CONTROLLER");
        var solicitacao = solicitacaoService.salvar(solicitacaoDTO);
        System.out.println("SERVICE OK: " + solicitacao);

        var uri = uriBuilder.path("/solicitacoes/{id}").buildAndExpand(solicitacao.getId()).toUri();
        return ResponseEntity.created(uri).body(solicitacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        solicitacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

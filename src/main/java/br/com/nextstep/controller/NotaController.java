package br.com.nextstep.controller;

import br.com.nextstep.dto.nota.NotaResponseDTO;
import br.com.nextstep.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @GetMapping
    public ResponseEntity<Page<NotaResponseDTO>> listarTodos(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable paginacao) {
        var notas = notaService.listarTodos(paginacao);
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/trilha/{idTrilha}")
    public ResponseEntity<Page<NotaResponseDTO>> listarNotasPorIdTrilha(@PathVariable Long idTrilha, @PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable paginacao) {
        var notas = notaService.listarNotasPorIdTrilha(idTrilha, paginacao);
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaResponseDTO> buscarPorId(@PathVariable Long id) {
        var nota = notaService.buscarPorId(id);
        return ResponseEntity.ok(nota);
    }
}
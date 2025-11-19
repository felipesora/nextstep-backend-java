package br.com.nextstep.controller;

import br.com.nextstep.dto.usuarioFinal.UsuarioFinalResponseDTO;
import br.com.nextstep.service.UsuarioFinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios/final")
public class UsuarioFinalController {

    @Autowired
    private UsuarioFinalService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioFinalResponseDTO>> listarTodos(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable paginacao) {
        var usuarios = usuarioService.listarTodos(paginacao);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioFinalResponseDTO> buscarPorId(@PathVariable Long id) {
        var usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/procedure")
    public ResponseEntity<List<UsuarioFinalResponseDTO>> listarUsuariosFinaisProcedure() {
        List<UsuarioFinalResponseDTO> usuarios = usuarioService.listarUsuariosFinaisPorProcedure();
        return ResponseEntity.ok(usuarios);
    }
}

package br.com.nextstep.controller;

import br.com.nextstep.dto.usuarioAdmin.UsuarioAdminRequestDTO;
import br.com.nextstep.dto.usuarioAdmin.UsuarioAdminResponseDTO;
import br.com.nextstep.service.UsuarioAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios/admin")
public class UsuarioAdminController {

    @Autowired
    private UsuarioAdminService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioAdminResponseDTO>> listarTodos(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable paginacao) {
        var usuarios = usuarioService.listarTodos(paginacao);

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioAdminResponseDTO> buscarPorId(@PathVariable Long id) {
        var usuario = usuarioService.buscarPorId(id);

        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioAdminResponseDTO> salvar(@RequestBody @Valid UsuarioAdminRequestDTO usuarioDTO, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.salvar(usuarioDTO);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioAdminResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioAdminRequestDTO usuarioDTO) {
        var usuarioAtualizado = usuarioService.atualizar(id, usuarioDTO);

        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.nextstep.config;

import br.com.nextstep.service.UsuarioAdminService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminInitializer {

    @Autowired
    private UsuarioAdminService usuarioAdminService;

    @PostConstruct
    public void init() {
        usuarioAdminService.salvarAdminInicial();
    }
}

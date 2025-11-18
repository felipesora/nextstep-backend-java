package br.com.nextstep.controller;

import br.com.nextstep.dto.ia.IARequestDTO;
import br.com.nextstep.dto.ia.IAResponseDTO;
import br.com.nextstep.service.IAService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ia")
public class IAController {

    private final IAService iaService;

    public IAController(IAService iaService) {
        this.iaService = iaService;
    }

    @PostMapping("/resumir")
    public IAResponseDTO gerar(@RequestBody IARequestDTO request) {
        String resposta = iaService.gerarTexto(request.getPrompt());
        return new IAResponseDTO(resposta);
    }
}

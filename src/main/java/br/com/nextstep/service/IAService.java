package br.com.nextstep.service;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
public class IAService {

    private final OllamaChatModel chatModel;

    public IAService(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String gerarTexto(String prompt) {
        String promptFinal = "Vou te passar um tema de uma trilha de estudo que estou criando, o tema dessa trilha é " + prompt + " , quero que você faça um resumo sobre o que o aluno vai aprender nessa trilha, forneça uma descrição bem completa e explique o que os alunos vão aprender. Responda no máximo com 400 caracteres. Não ultrapasse disso!";
        String resposta = chatModel.call(promptFinal);

        if (resposta.length() > 400) {
            resposta = resposta.substring(0, 400);
        }
        return resposta;
    }
}

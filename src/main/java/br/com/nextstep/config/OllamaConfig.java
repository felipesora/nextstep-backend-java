package br.com.nextstep.config;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaConfig {

    @Bean
    public OllamaChatModel chatModel() {

        OllamaApi api = new OllamaApi("http://localhost:11434");

        OllamaOptions options = OllamaOptions.builder()
                .model("deepseek-r1:8b")
                .build();

        return OllamaChatModel.builder()
                .ollamaApi(api)
                .defaultOptions(options)
                .build();
    }
}

package br.com.nextstep.service;

import br.com.nextstep.repository.TrilhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrilhaService {

    @Autowired
    private TrilhaRepository trilhaRepository;



}

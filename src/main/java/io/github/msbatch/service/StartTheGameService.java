package io.github.msbatch.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StartTheGameService {
    public void startTheGame() {
        log.info("Starting/Reset the game.");
    }
}

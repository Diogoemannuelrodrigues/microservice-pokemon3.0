package io.github.msbatch;

import io.github.msbatch.service.StartTheGameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class ListMovesComponent {

    private final StartTheGameService startTheGameService;

    /*@Scheduled(cron = "0 0 6 * * *")*/
    @Scheduled(fixedDelay = 5000)
    public void starTheGame() {
        log.info("Starter Processing unblock schedulers by time.");
        startTheGameService.startTheGame();
    }
}

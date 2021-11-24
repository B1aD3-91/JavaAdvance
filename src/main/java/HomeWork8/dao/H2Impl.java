package HomeWork8.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Slf4j
public class H2Impl implements H2Dao {

    private final JdbcTemplate jdbcTemplate;
    private static final String COMMAND_SEPARATOR = "GO";

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void executeQuery() throws IOException {
        log.info("Starting work....");
        try {
            prepareQueries().forEach(jdbcTemplate::execute);
        } catch (RuntimeException ex) {
            log.error("Execution error", ex);
        }
        log.info("Finished.");
    }

    private List<String> prepareQueries() throws IOException {
        try (var dump = this.getClass().getResourceAsStream("/dump.sql")) {
            var commands = new String(dump.readAllBytes(), StandardCharsets.UTF_8);
            return Arrays.stream(commands
                    .split(COMMAND_SEPARATOR))
                    .collect(Collectors.toList());
        }
    }
}

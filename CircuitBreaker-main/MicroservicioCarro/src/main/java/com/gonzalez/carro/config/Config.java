package com.gonzalez.carro.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.event.*;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.event.*;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.event.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public RegistryEventConsumer<CircuitBreaker> circuitBreakerEventConsumer() {
        return new RegistryEventConsumer<CircuitBreaker>() {

            @Override
            public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
                CircuitBreaker circuitBreaker = entryAddedEvent.getAddedEntry();
                circuitBreaker.getEventPublisher()
                        .onStateTransition(event -> {
                            CircuitBreaker.StateTransition stateTransition = event.getStateTransition();
                            System.out.println(String.format("Circuit breaker %s state transition from %s to %s on %s",
                                    event.getCircuitBreakerName(), stateTransition.getFromState(),
                                    stateTransition.getToState(), event.getCreationTime()));
                        })
                        .onCallNotPermitted(event -> {
                            System.out.println(String.format("Circuit breaker %s call not permitted on %s",
                                    event.getCircuitBreakerName(), event.getCreationTime()));
                        })
                        .onFailureRateExceeded(event -> {
                            System.out.println(String.format("Circuit breaker %s failure rate exceeded %f on %s",
                                    event.getCircuitBreakerName(), event.getFailureRate(), event.getCreationTime()));
                        })
                        .onSlowCallRateExceeded(event -> {
                            System.out.println(String.format("Circuit breaker %s slow call rate exceeded %f on %s",
                                    event.getCircuitBreakerName(), event.getSlowCallRate(), event.getCreationTime()));
                        })
                        .onReset(event -> {
                            System.out.println(String.format("Circuit breaker %s reset on %s",
                                    event.getCircuitBreakerName(), event.getCreationTime()));
                        })
                        .onIgnoredError(event -> {
                            System.out.println(String.format("Circuit breaker %s ignored error on %s",
                                    event.getCircuitBreakerName(), event.getCreationTime()));
                        });
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemovedEvent) {
                System.out.println(String.format("Circuit breaker %s removed",
                        entryRemovedEvent.getRemovedEntry().getName()));
            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {
                System.out.println(String.format("Circuit breaker %s replaced",
                        entryReplacedEvent.getNewEntry().getName()));
            }
        };
    }
}

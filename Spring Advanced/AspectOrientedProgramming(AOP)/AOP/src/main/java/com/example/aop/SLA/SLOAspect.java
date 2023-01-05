package com.example.aop.SLA;

import com.example.aop.modifying.ModifyingExample;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class SLOAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(SLOAspect.class);
    private SLOsConfig slOsConfig;

    public SLOAspect(SLOsConfig slOsConfig) {
        this.slOsConfig = slOsConfig;
    }

    @Around(value = "@annotation(TrackLatency)")
    public Object trackLatency(ProceedingJoinPoint pjp ,
                             TrackLatency TrackLatency) throws Throwable {

        String latencyId = TrackLatency.latency();
        SLOsConfig.SLOConfig sloConfig = slOsConfig.getSlos()
                .stream()
                .filter(s -> s.getId().equals(latencyId))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("SLO with ID " + latencyId + " is not configured."));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = pjp.proceed();
        stopWatch.stop();

        Long actualExecutionTime = stopWatch.getLastTaskTimeMillis();

        if (actualExecutionTime > sloConfig.getTreshold()) {
            LOGGER.warn("Method {} is too slow. Execution time {} millis", latencyId, actualExecutionTime);
        }

        return result;
    }
}

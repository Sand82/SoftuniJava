package com.example.aop.SLA;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DriverController {

    @TrackLatency(latency = "local_operation")
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> allDrivers() {
        return ResponseEntity.ok(List.of(
                new Driver().setName("Sand").setLicenceCategory("B"),
                new Driver().setName("Mima").setLicenceCategory("A")));
    }

    @TrackLatency(latency = "remote_operation")
    @GetMapping("/sync-drivers")
    public ResponseEntity<List<Driver>> remoteDrivers() {

        try{

            Thread.sleep(4000);
        }catch(InterruptedException e) {

            Thread.interrupted();
        }

        return ResponseEntity.ok(List.of(
                new Driver().setName("Sand").setLicenceCategory("B"),
                new Driver().setName("Mima").setLicenceCategory("A")));
    }
}

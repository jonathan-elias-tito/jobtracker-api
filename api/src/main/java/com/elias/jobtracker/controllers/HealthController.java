<<<<<<< HEAD
package com.elias.jobtracker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public String isHealth() {
        return "OK!";
    }

}
=======
package com.elias.jobtracker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public String isHealth() {
        return "OK!";
    }

}
>>>>>>> 59323f9044753c375cb291c42df55916c22c06c1

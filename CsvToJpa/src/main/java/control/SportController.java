package fr.diginamic.transport.dev.controller;


import entite.Sport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.SportService;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/sport") // /client
public class SportController {
    private SportService ss;

    @RequestMapping("/lister")
    @GetMapping// GET /car
    public List<Sport> listCarValid() {
        return ss.listAllSport();
    }
}

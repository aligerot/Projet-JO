package fr.diginamic.transport.dev.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/reservations") // /client
public class Carcontroler {
    private CarService carService;

    @RequestMapping("/creer/all")
    @GetMapping// GET /car
    public List<CarDTO> listCarValid() {
        return carService.listAllCar();
    }

    @RequestMapping("/creer")
    @GetMapping// GET /car
    public List<CarDTO> listCarValid(@RequestParam LocalDateTime dateDebut, LocalDateTime dateFin) {
        return carService.listCarInService(dateDebut,dateFin);
    }

    @GetMapping("/creer/all/car")// GET /car
    public List<Car> listCarValidFull() {
        return carService.listAllCarFull();
    }


    @PatchMapping("/carservice")
    public ResponseEntity<Car> CarChangeLong(@RequestBody String immatriculation) {
        log.info(immatriculation);
        Car car1 = carService.CarByImmatriculation(immatriculation);
        carService.changeLong(car1);
        return ResponseEntity.ok(car1);
    }

    @PostMapping("creer")
    public ResponseEntity<?> postCar(@RequestBody CarDTO carDTO){
        return ResponseEntity.ok(carService.saveCar(carDTO));
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllFonctionCar(){
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PatchMapping("/status")
    public ResponseEntity<?> patchStatusCar(@RequestBody CarDTO carDTO) throws IOException, InterruptedException{
        return ResponseEntity.ok(carService.patchCarStatus(carDTO));
    }
}

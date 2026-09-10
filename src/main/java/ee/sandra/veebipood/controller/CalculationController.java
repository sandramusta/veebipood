package ee.sandra.veebipood.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CalculationController {
    List<Double> numbers = new ArrayList<>();

    //localhost:8080/calculate/4.0/5.0
    @GetMapping("calculate/{nr1}/{nr2}")
    public double calculate1(@PathVariable double nr1, @PathVariable double nr2) {
        return nr1 + nr2;
    }

    //localhost:8080/calculate?nr1=4.0&nr2=5.0
    @GetMapping("calculate")
    public double calculate2(@RequestParam double nr1, @RequestParam double nr2) {
        return nr1 + nr2;
    }

    @GetMapping("all-numbers")
    public List<Double> getAllNumbers() {
        return numbers;
    }

    @GetMapping("save-number/{number}")
    public List<Double> saveNumber1(@PathVariable double number) {
        numbers.add(number);
        return numbers;
    }

    @PostMapping("number")
    public List<Double> saveNumber2(@RequestBody String number) {
        numbers.add(Double.valueOf(number));
        return numbers;
    }

    @GetMapping("sum-all-numbers")
    public double sumAllNumbers() {
        double sum = 0;
        for (Double number : numbers) {
            sum += number;
        }
        return sum;
    }
}

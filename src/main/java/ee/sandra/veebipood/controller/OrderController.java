package ee.sandra.veebipood.controller;

import ee.sandra.veebipood.dto.ParcelMachine;
import ee.sandra.veebipood.dto.PaymentLink;
import ee.sandra.veebipood.entity.Order;
import ee.sandra.veebipood.entity.Person;
import ee.sandra.veebipood.entity.Product;
import ee.sandra.veebipood.repository.OrderRepository;
import ee.sandra.veebipood.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.Reference;
import java.util.List;

@Log4j2
@RestController// võimaldab front-endil teha back-endi päringuid
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService; // faili tõmbame sisse

    //localhost:8080/orders
    @GetMapping("orders")
    public List<Order> getOrders(){
        log.info("Kasutaja xx vaatas kõiki tellimusi");
        return orderRepository.findAll();
    }

    @GetMapping("my-orders/{personId}")
    public List<Order> getMyOrders(@PathVariable Long personId){
        return orderRepository.findByPerson_Id(personId);
    }

    @PostMapping("orders/{personId}")
    public PaymentLink saveOrder(@PathVariable Long personId, @RequestBody List<Product> products) {
        Order order = orderService.saveOrder(personId, products);
        // Miks on vaja tellimus enne makset salvestada
        // 1. Sest siis on meil tema ID käes
        // 2. Kui raha läheb maha, aga meieni see info ei jõua, siis ei pea taastama
        return orderService.makePayment(order.getTotal(), order.getId());
        //return orderRepository.findAll();
    }

    @DeleteMapping("orders/{id}")
    public List<Order> deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return orderRepository.findAll();
    }

    @GetMapping("parcelmachines")
    public List<ParcelMachine> getParcelMachines(@RequestParam String country) {
        return orderService.getParcelMachines(country);
    }

    @GetMapping("check-payment")
    public Order checkPaymentStatus(@RequestParam String paymentReference) {
        return orderService.checkIfOrderPaid(paymentReference);
    }
    //ebaõnnestunud: a89bdff1b78ad020c185493a0ca37daa1c8c432ddc068b4d928f4a89557ebed6
    //õnnestunud: c1a60f248b39b397cf33d6619459c89760f17e613f97303b627199613b2ee0a3
    //õnnestunud: 54bad7d18b4f9f5b2d6a5693dd31bc6ce8a9a1149878a631c9855a358a740391
}

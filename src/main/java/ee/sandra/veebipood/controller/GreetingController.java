package ee.sandra.veebipood.controller;

import ee.sandra.veebipood.dto.Greeting;
import ee.sandra.veebipood.dto.HelloMessage;
import ee.sandra.veebipood.dto.UpdateRequest;
import ee.sandra.veebipood.entity.Product;
import ee.sandra.veebipood.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "https://veebipood-frontend-a9oo.onrender.com"})
public class GreetingController {
    private final ProductRepository productRepository;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/update") // uuendamiseks
    @SendTo("/topic/discount") // kuulamiseks
    public Page<Product> getPublicProducts(UpdateRequest request){
            // Pageable't ei saa siin Spring MVC moodi automaatselt lahendada, sest STOMP sõnumil pole päringuparameetreid
            Pageable pageable = PageRequest.of(request.getPage(), 6, Sort.by("stock").ascending());
            return productRepository.findByDiscountGreaterThanAndStockGreaterThan(24,0, pageable);
    }
}


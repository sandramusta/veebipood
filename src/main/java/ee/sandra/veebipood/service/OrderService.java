package ee.sandra.veebipood.service;

import ee.sandra.veebipood.dto.*;
import ee.sandra.veebipood.entity.Order;
import ee.sandra.veebipood.entity.PaymentState;
import ee.sandra.veebipood.entity.Person;
import ee.sandra.veebipood.entity.Product;
import ee.sandra.veebipood.repository.OrderRepository;
import ee.sandra.veebipood.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public Order saveOrder(Long personId, List<Product> products) {
        Order order = new Order();

        Person person = new Person();
        person.setId(personId);
        order.setPerson(person); //hiljem lisame autentimisega


        order.setProducts(products); // front-end saadab (LocalStorage-s)

        double sum = 0;
        for (Product product: products) {
            Product dbProduct = productRepository.findById(product.getId()).orElseThrow();
            dbProduct.setStock(dbProduct.getStock()-1);
            productRepository.save(dbProduct);
            sum = sum + dbProduct.getPrice();
        }
        order.setTotal(sum);
        order.setPaymentState(PaymentState.INITIAL);

        Order savedOrder = orderRepository.save(order);

        // Telli nupu vajutamine muudab laoseisu, seega saadame WebSocketi kaudu värske allahinnatud toodete lehe
        Page<Product> discountedProducts = productRepository.findByDiscountGreaterThanAndStockGreaterThan(
                24, 0, PageRequest.of(0, 6, Sort.by("stock").ascending()));
        messagingTemplate.convertAndSend("/topic/discount", discountedProducts);

        return savedOrder;
    }

    public List<ParcelMachine> getParcelMachines(String country) {
        String url = "https://www.omniva.ee/locations.json";
        RestTemplate restTemplate = new RestTemplate();
        ParcelMachine[] response = restTemplate.exchange(url, HttpMethod.GET, null, ParcelMachine[].class).getBody();
        return Arrays.stream(response)
                .filter(e -> e.getA0_NAME().equals(country))
                .toList();
    }

    public PaymentLink makePayment(double total, Long orderId) {
        String url = "https://igw-demo.every-pay.com/api/v4/payments/oneoff";
        RestTemplate restTemplate = new RestTemplate();

        PaymentBody body = new PaymentBody();
        body.setAccount_name("EUR3D1");
        body.setNonce("nohdk"+ZonedDateTime.now() + Math.random());
        body.setTimestamp(ZonedDateTime.now().toString());
        body.setAmount(total);
        body.setOrder_reference("sandra-"+orderId);
        body.setCustomer_url("http://err.ee");
        body.setApi_username("e36eb40f5ec87fa2");

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("e36eb40f5ec87fa2" , "7b91a3b9e1b74524c2e9fc282f8ac8cd");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentBody> entity = new HttpEntity<>(body, headers);

        PaymentResponse response = restTemplate.exchange(url, HttpMethod.POST,entity, PaymentResponse.class).getBody();
        PaymentLink paymentLink = new PaymentLink();
        paymentLink.setLink(response.getPayment_link());
        return paymentLink;
    }

    public Order checkIfOrderPaid(String paymentReference) {
        String url = "https://igw-demo.every-pay.com/api/v4/payments/" + paymentReference + "?api_username=e36eb40f5ec87fa2";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("e36eb40f5ec87fa2" , "7b91a3b9e1b74524c2e9fc282f8ac8cd");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        PaymentCheckResponse response = restTemplate.exchange(url, HttpMethod.GET,entity, PaymentCheckResponse.class).getBody();
        Long id = Long.valueOf(response.getOrder_reference().replace("sandra-", "")); //Long.valueOf("20") --> 20
        Order order = orderRepository.findById(id).orElseThrow();
        order.setPaymentState(PaymentState.valueOf(response.getPayment_state().toUpperCase()));
        return orderRepository.save(order);
    }
}

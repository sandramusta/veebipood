package ee.sandra.veebipood.controller;

import ee.sandra.veebipood.dto.Supplier1Product;
import ee.sandra.veebipood.dto.Supplier2Product;
import ee.sandra.veebipood.dto.Supplier3Product;
import ee.sandra.veebipood.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("supplier1")
    public List<Supplier1Product> getSupplier1Products() {
        return supplierService.getSupplier1Products();
    }

    @GetMapping("supplier2")
    public List<Supplier2Product> getSupplier2Products() {
        return supplierService.getSupplier2Products();
    }

    @GetMapping("supplier3")
    public List<Supplier3Product> getSupplier3Products() {
        return supplierService.getSupplier3Products();
    }
}

package ee.sandra.veebipood.controller;

import ee.sandra.veebipood.entity.Product;
import ee.sandra.veebipood.repository.ProductRepository;
import ee.sandra.veebipood.service.CacheService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController// võimaldab front-endil teha back-endi päringuid
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "https://veebipood-frontend-a9oo.onrender.com"})
@Log4j2
public class ProductController {

    //private String url;
    private final ProductRepository productRepository;
    private final CacheService cacheService;

    //localhost:8080/products
    @GetMapping("products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    //localhost:8080/products/public?categoryId=5124557
    @GetMapping("products/public")
    public Page<Product> getPublicProducts(Pageable pageable, @RequestParam(required = false) Long categoryId){
        if (categoryId == 0) {
            return productRepository.findAll(pageable);
        } else {
            return productRepository.findByCategoryId(categoryId, pageable);
        }
    }

    //localhost:8080/product
    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) throws ExecutionException {
        log.info("Võeti toode:{}", id);
        System.out.println("Võeti toode:" + id);
        return cacheService.getProductFromCache(id);
    }

    @PostMapping("products")
    public Product saveProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new RuntimeException("Cannot add product with id"); //katkesta kood ja viska välja viga
        }
        return productRepository.save(product);
        //return productRepository.findAll();
    }

    @PutMapping("products")
    public List<Product> editProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            throw new RuntimeException("Cannot edit product without id"); //katkesta kood ja viska välja viga
        }
        productRepository.save(product);
        cacheService.updateProduct(product);
        return productRepository.findAll();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        cacheService.deleteFromCache(id);
        return productRepository.findAll();
    }

}

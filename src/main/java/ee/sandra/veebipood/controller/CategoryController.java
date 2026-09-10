package ee.sandra.veebipood.controller;

import ee.sandra.veebipood.entity.Category;
import ee.sandra.veebipood.entity.Product;
import ee.sandra.veebipood.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "https://veebipood-frontend-a9oo.onrender.com"})
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("categories")
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @PostMapping("categories")
    public List<Category> saveCategory(@RequestBody Category category) {
        if (category.getId() != null) {
            throw new RuntimeException("Cannot add category with id"); //katkesta kood ja viska välja viga
        }
        categoryRepository.save(category);
        return categoryRepository.findAll();
    }

    @DeleteMapping("categories/{id}")
    public List<Category> deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return categoryRepository.findAll();
    }
}

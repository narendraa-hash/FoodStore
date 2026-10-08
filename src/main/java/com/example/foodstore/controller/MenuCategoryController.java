package com.example.foodstore.controller;

import com.example.foodstore.entity.MenuCategory;
import com.example.foodstore.repository.MenuCategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-categories")
public class MenuCategoryController {

    private final MenuCategoryRepository menuCategoryRepository;

    public MenuCategoryController(MenuCategoryRepository menuCategoryRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @GetMapping
    public List<MenuCategory> getCategories() {
        return menuCategoryRepository.findAll();
    }

    @PostMapping
    public MenuCategory createCategory(@RequestBody MenuCategory menuCategory) {
        return menuCategoryRepository.save(menuCategory);
    }
}

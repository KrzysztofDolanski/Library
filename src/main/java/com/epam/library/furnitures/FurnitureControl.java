package com.epam.library.furnitures;

import org.springframework.web.bind.annotation.*;


public interface FurnitureControl {


    @GetMapping("{id}")
    public String findById(@PathVariable Long id);

    @PostMapping("")
    public void add(@RequestParam Integer materialType,
                       @RequestParam Integer price,
                       @RequestParam Integer size,
                       @RequestParam String colour);

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id);
}

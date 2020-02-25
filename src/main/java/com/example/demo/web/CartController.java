package com.example.demo.web;

import com.example.demo.models.Medicine;
import com.example.demo.service.CartService;
import com.example.demo.service.SparqlService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"*", "localhost:3000"})
@RestController
@RequestMapping("/cart")
public class CartController {

    private final SparqlService sparqlService;
    private final CartService cartService;

    public CartController(SparqlService sparqlService, CartService cartService) {
        this.sparqlService = sparqlService;
        this.cartService = cartService;
    }

    @PostMapping
    @ResponseBody
    public List<Medicine> getAll(@RequestParam("username") String username) {
        return cartService.getAllByUsername(username);
    }

    @PostMapping(value = "/addToCart")
    @ResponseBody
    public Medicine addMedicineToCart(@RequestParam("username") String username, @RequestParam("genericName") String genericName) {
        return cartService.addMedicineToCart(username,genericName);
    }

    @PostMapping(value = "/delete")
    public void delete(@RequestParam("username") String username, @RequestParam("genericName") String genericName) {
        cartService.deleteMedicine(username,genericName);
    }
}

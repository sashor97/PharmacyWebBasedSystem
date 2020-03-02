package com.example.demo.service;

import com.example.demo.models.Medicine;

import java.util.List;

public interface CartService {
    public List<Medicine> getAllByUsername(String username);
    public Medicine addMedicineToCart(String username, String genericName);
    public void deleteMedicine(String username,String genericName);
    public List<Medicine> getAllSorted(String username,String type);
}

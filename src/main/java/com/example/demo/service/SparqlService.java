package com.example.demo.service;

import com.example.demo.models.Medicine;

import java.util.List;

public interface SparqlService {
    public List<Medicine> getAllMedicine(Integer limit);
}

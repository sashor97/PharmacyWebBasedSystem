package com.example.demo.service;

import com.example.demo.models.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineService {
    public List<Medicine> findAll();
    public Medicine save(Medicine medicine);
    public Optional<Medicine> findById(Long id);
    public void deleteById(Long voziloId);
    public List<Medicine> findMedicineByAggregateState(String aggregateState);
    public List<Medicine> findByGenericName(String genericName);

}

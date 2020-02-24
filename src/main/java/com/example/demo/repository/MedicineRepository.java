package com.example.demo.repository;

import com.example.demo.models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    public List<Medicine> findAll();
    public Medicine save(Medicine medicine);
    public Optional<Medicine> findById(Long id);
    public void deleteById(Long id);
    public List<Medicine> findMedicineByAggregateState(String aggregateState);
    public List<Medicine> findByGenericName(String genericName);



}
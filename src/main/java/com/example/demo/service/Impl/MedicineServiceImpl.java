package com.example.demo.service.Impl;

import com.example.demo.models.Medicine;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.service.MedicineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {

    private MedicineRepository repo;

    public MedicineServiceImpl(MedicineRepository repo){
        this.repo=repo;
    }


    @Override
    public List<Medicine> findAll() {
        return repo.findAll();
    }

    @Override
    public Medicine save(Medicine medicine) {
        return repo.save(medicine);
    }

    @Override
    public Optional<Medicine> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Medicine> findMedicineByAggregateState(String aggregateState) {
        return repo.findMedicineByAggregateState(aggregateState);
    }

    @Override
    public List<Medicine> findByGenericName(String genericName) {
        return repo.findByGenericName(genericName);
    }
}

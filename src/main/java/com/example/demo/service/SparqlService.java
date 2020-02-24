package com.example.demo.service;

import com.example.demo.models.Medicine;

import java.util.List;

public interface SparqlService {
    public List<Medicine> getAllMedicine(Integer limit);
    public List<Medicine> getAllMedicineAggregate(Integer limit,String aggregateState);
  public List<Medicine> getAllMedicineSorted(Integer limit,String type);
    public List<Medicine> searchMedicineByName(String name);
    Medicine getMedicineByGenericName(String param);
}

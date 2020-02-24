package com.example.demo.service.Impl;

import com.example.demo.models.Medicine;
import com.example.demo.models.User;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.SparqlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;
    private final SparqlService sparqlService;

    public CartServiceImpl(MedicineRepository medicineRepository, UserRepository userRepository, SparqlService sparqlService) {
        this.medicineRepository = medicineRepository;
        this.userRepository = userRepository;
        this.sparqlService = sparqlService;
    }

    @Override
    public List<Medicine> getAllByUsername(String username) {
        return userRepository.findByUsername(username).map(User::getMedicineList).orElseGet(ArrayList::new);
    }

    @Override
    public Medicine addMedicineToCart(String username, String genericName) {
        Optional<User> user = userRepository.findByUsername(username);
        Medicine medicine=sparqlService.getMedicineByGenericName(genericName);
        if(user.isPresent()){
            Medicine newMedicine=medicineRepository.save(medicine);
            user.get().getMedicineList().add(newMedicine);
            userRepository.save(user.get());
        }
        return medicine;
    }
}

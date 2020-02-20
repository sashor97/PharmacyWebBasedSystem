package com.example.demo.web;

import com.example.demo.jena.QueryDemo;

import com.example.demo.models.Medicine;
import com.example.demo.service.Impl.SparqlServiceImpl;
import com.example.demo.service.SparqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>demo controller</p>
 *
 * @author SageZhang
 * @version 2018/7/25
 */
@CrossOrigin({"*","localhost:3000"})
@RestController
@RequestMapping("/home")
public class MedicineController {

    private SparqlService sparqlService;
    public MedicineController(SparqlService sparqlService){
        this.sparqlService=sparqlService;
    }

    @RequestMapping
    @ResponseBody
    public List<Medicine> search(Optional<Integer> param) {
        if(!param.isPresent()){
            param=Optional.of(10);
        }
        return sparqlService.getAllMedicine(param.get());
    }
    @RequestMapping(value = "/{param}")
    @ResponseBody
    public List<Medicine> searchWithLimit(@PathVariable("param") Integer param) {

        return sparqlService.getAllMedicine(param);
    }
}
package com.example.demo.web;

import com.example.demo.models.Medicine;
import com.example.demo.service.SparqlService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin({"*", "localhost:3000"})
@RestController
@RequestMapping("/home")
public class MedicineController {

    private SparqlService sparqlService;

    public MedicineController(SparqlService sparqlService) {
        this.sparqlService = sparqlService;
    }

    @RequestMapping
    @ResponseBody
    public List<Medicine> home() {
        return getByPage(1);
    }

    @RequestMapping(value = "/{param}")
    @ResponseBody
    public List<Medicine> searchWithLimit(@PathVariable("param") Integer param) {

        return sparqlService.getAllMedicine(param);
    }

    @RequestMapping(value = "/filter/{param}/{state}")
    @ResponseBody
    public List<Medicine> getAggregateState(@PathVariable("param") Optional<Integer> param, @PathVariable("state") String state) {
        if (!param.isPresent()) {
            param = Optional.of(9);
        }
        if (state.equals("liquid")) {
            return sparqlService.getAllMedicineAggregate(param.get(), "liquid");
        } else if (state.equals("solid")) {
            return sparqlService.getAllMedicineAggregate(param.get(), "solid");
        } else {
            return sparqlService.getAllMedicine(9);
        }
    }

    @RequestMapping(value = "/sort/{param}/{type}")
    @ResponseBody
    public List<Medicine> getSorted(@PathVariable("param") Optional<Integer> param, @PathVariable("type") String type) {
        if (!param.isPresent()) {
            param = Optional.of(9);
        }
        return sparqlService.getAllMedicineSorted(param.get(), type);
    }


    @RequestMapping(value = "/search/{param}")
    @ResponseBody
    public List<Medicine> searchMedicineByName(@PathVariable("param") String param) {
        return sparqlService.searchMedicineByName(param);
    }

    @RequestMapping(value = "/details/{name}")
    @ResponseBody
    public Medicine getMedicineByGenericName(@PathVariable("name") String name) {
        return sparqlService.getMedicineByGenericName(name);
    }


    @RequestMapping(value = "/page/{number}")
    @ResponseBody
    public List<Medicine> getByPage(@PathVariable("number") Integer number) {
        return sparqlService.getAllMedicineByPage(number);
    }
}
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
    @RequestMapping(value = "/filter/{param}/{state}")
    @ResponseBody
    public List<Medicine> getAggregateState(@PathVariable("param")Optional<Integer> param,@PathVariable("state")String state)
    {
        if(!param.isPresent()){
            param=Optional.of(500);
        }
        if(state.equals("Liquid"))
        {
            return sparqlService.getAllMedicineAggregate(param.get(),"Liquid");
        }
        else if(state.equals("Solid"))
        {
            return sparqlService.getAllMedicineAggregate(param.get(),"Solid");
        }
        else{
            return sparqlService.getAllMedicine(10);
        }
    }
    @RequestMapping(value = "/sort/{param}/{type}")
    @ResponseBody
    public List<Medicine> getSorted(@PathVariable("param") Optional<Integer> param,@PathVariable("type") String type)
    {
        if(!param.isPresent()){
            param=Optional.of(500);
        }
        return sparqlService.getAllMedicineSorted(param.get(),type);
    }


}
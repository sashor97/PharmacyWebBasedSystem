package com.example.demo.models;


import javax.persistence.*;
import java.util.List;

@Entity
public class Medicine{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    List<User> userList;

    private String aggregateState;




    // ili validiraj u servis pri iteriranje na rezultatot od SPARQL kverito
    private String url;

    private String genericName;
    private String description;
    private Double avgWeight; // Molekularna tezina
    private String chemicalFormula;


    private String indication;


    public Long getId() {
        return id;
    }

    public String getAggregateState() {
        return aggregateState;
    }

    public void setAggregateState(String aggregateState) {
        this.aggregateState = aggregateState;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAvgWeight() {
        return avgWeight;
    }

    public void setAvgWeight(Double avgWeight) {
        this.avgWeight = avgWeight;
    }

    public String getChemicalFormula() {
        return chemicalFormula;
    }

    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }

    public Medicine(){}
    public Medicine(String aggregateState, String url, String genericName, String description, Double avgWeight, String chemicalFormula, String indication) {
        this.aggregateState = aggregateState;
        this.url = url;
        this.genericName = genericName;
        this.description = description;
        this.avgWeight = avgWeight;
        this.chemicalFormula = chemicalFormula;
        this.indication = indication;

    }
// generiraj getters i setters u IntelliJ za svite 4 atributi, so SHIFT+INSERT generiraj auto
}

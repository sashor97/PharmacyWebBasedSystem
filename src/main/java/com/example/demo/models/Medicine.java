package com.example.demo.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medicine{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aggregateState;

    // ili validiraj u servis pri iteriranje na rezultatot od SPARQL kverito
    private String url;

    private String genericName;
    private String description;
    private Double avgWeight; // Molekularna tezina
    private String chemicalFormula;


    private String brandName;

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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
    public Medicine(String aggregateState, String url, String genericName, String description, Double avgWeight, String chemicalFormula, String brandName) {
        this.aggregateState = aggregateState;
        this.url = url;
        this.genericName = genericName;
        this.description = description;
        this.avgWeight = avgWeight;
        this.chemicalFormula = chemicalFormula;
        this.brandName = brandName;
    }
// generiraj getters i setters u IntelliJ za svite 4 atributi, so SHIFT+INSERT generiraj auto
}

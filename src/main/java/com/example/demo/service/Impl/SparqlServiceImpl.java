package com.example.demo.service.Impl;

import com.example.demo.models.Medicine;
import com.example.demo.service.SparqlService;
import org.apache.commons.compress.utils.Lists;
import org.apache.jena.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class SparqlServiceImpl implements SparqlService {
    @Override
    public List<Medicine> getAllMedicine(Integer limit) {
        List<Medicine> list = Lists.newArrayList();
        String SPARQLEndpoint = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/sparql";
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX drugbank: <http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/>" +
                "SELECT distinct ?nameUrl ?genericName ?chemFormula ?state ?description  ?indication ?avgWeight  ?pharmgkbId" +
                "WHERE { " +
                "drugbank:drugs ^a ?nameUrl." +
                "?nameUrl drugbank:chemicalFormula ?chemFormula;" +
                "drugbank:genericName ?genericName;" +
                "drugbank:state ?state;"+
                "drugbank:description ?description;"+
                "drugbank:indication ?indication;"+
                "drugbank:molecularWeightAverage ?avgWeight;"+
                "drugbank:pharmgkbId ?pharmgkbId."+
                "}" + "" +
                "LIMIT "+limit;


        Query sparqlQuery = QueryFactory.create(query);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(SPARQLEndpoint, sparqlQuery)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution solution = resultSet.nextSolution();
                String resultName = solution.get("nameUrl").toString();  // mozhe da treba substring(1, length-2) za urls
                String resultGenericName = solution.get("genericName").toString();  // mozhe da treba substring(1, length-2) za urls
                String chemFormula = solution.get("chemFormula").toString();  // mozhe da treba substring(1, length-2) za urls
                String indication = solution.get("indication").toString();
//                System.out.println(resultGenericName);
//                System.out.println(resultName);
//                System.out.println(chemFormula);
                Medicine med =new Medicine();
                med.setUrl(getProperty(solution,"state"));
                med.setAggregateState(solution.get("state").toString());
                med.setGenericName(resultGenericName);
                med.setAvgWeight(Double.parseDouble(solution.get("avgWeight").toString()));
                med.setChemicalFormula(chemFormula);
                med.setIndication(indication);
                med.setDescription(solution.get("description").toString());
                med.setUrl(solution.get("nameUrl").toString());
                list.add(med);
            }
        }

        System.out.println(list);
        return list;
    }

    @Override
    public List<Medicine> getAllMedicineAggregate(Integer limit,String aggregateState) {
        List<Medicine> list1 = Lists.newArrayList();
        String SPARQLEndpoint1 = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/sparql";
        String query1 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX drugbank: <http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/>" +
                "SELECT distinct ?nameUrl ?genericName ?chemFormula ?state ?description  ?indication ?avgWeight  ?pharmgkbId" +
                "WHERE { " +
                "drugbank:drugs ^a ?nameUrl." +
                "?nameUrl drugbank:chemicalFormula ?chemFormula;" +
                "drugbank:genericName ?genericName;" +
                "drugbank:state ?state;"+
                "drugbank:description ?description;"+
                "drugbank:indication ?indication;"+
                "drugbank:molecularWeightAverage ?avgWeight;"+
                "drugbank:pharmgkbId ?pharmgkbId;"+
                "FILTER regex(?state, '"+aggregateState+"', 'i')"+
                "}" + "" +
                "LIMIT "+limit;


        Query sparqlQuery1 = QueryFactory.create(query1);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(SPARQLEndpoint1, sparqlQuery1)) {
            ResultSet resultSet1 = queryExecution.execSelect();
            while (resultSet1.hasNext()) {
                QuerySolution solution = resultSet1.nextSolution();
                String resultName = solution.get("nameUrl").toString();  // mozhe da treba substring(1, length-2) za urls
                String resultGenericName = solution.get("genericName").toString();  // mozhe da treba substring(1, length-2) za urls
                String chemFormula = solution.get("chemFormula").toString();  // mozhe da treba substring(1, length-2) za urls
                String indication = solution.get("indication").toString();
                System.out.println(resultGenericName);
                System.out.println(resultName);
                System.out.println(chemFormula);
                Medicine med =new Medicine();
                med.setUrl(getProperty(solution,"state"));
                med.setAggregateState(solution.get("state").toString());
                med.setGenericName(resultGenericName);
                med.setAvgWeight(Double.parseDouble(solution.get("avgWeight").toString()));
                med.setChemicalFormula(chemFormula);
                med.setIndication(indication);
                med.setDescription(solution.get("description").toString());
                med.setUrl(solution.get("nameUrl").toString());
                list1.add(med);
            }
        }

        System.out.println(list1);
        return list1;
    }

    @Override
    public List<Medicine> getAllMedicineSorted(Integer limit, String type) {
        List<Medicine> list2 = Lists.newArrayList();

        if (type.equals("desc")) {
            String SPARQLEndpoint2 = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/sparql";
            String query2 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "PREFIX drugbank: <http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/>" +
                    "SELECT distinct ?nameUrl ?genericName ?chemFormula ?state ?description  ?indication ?avgWeight  ?pharmgkbId" +
                    "WHERE { " +
                    "drugbank:drugs ^a ?nameUrl." +
                    "?nameUrl drugbank:chemicalFormula ?chemFormula;" +
                    "drugbank:genericName ?genericName;" +
                    "drugbank:state ?state;" +
                    "drugbank:description ?description;" +
                    "drugbank:indication ?indication;" +
                    "drugbank:molecularWeightAverage ?avgWeight;" +
                    "drugbank:pharmgkbId ?pharmgkbId." +

                    "}" + "" +
                    "ORDER BY DESC(?avgWeight)"+
            "LIMIT " + limit;


            Query sparqlQuery2 = QueryFactory.create(query2);
            try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(SPARQLEndpoint2, sparqlQuery2)) {
                ResultSet resultSet1 = queryExecution.execSelect();
                while (resultSet1.hasNext()) {
                    QuerySolution solution = resultSet1.nextSolution();
                    String resultName = solution.get("nameUrl").toString();  // mozhe da treba substring(1, length-2) za urls
                    String resultGenericName = solution.get("genericName").toString();  // mozhe da treba substring(1, length-2) za urls
                    String chemFormula = solution.get("chemFormula").toString();  // mozhe da treba substring(1, length-2) za urls
                    String indication = solution.get("indication").toString();
                    System.out.println(resultGenericName);
                    System.out.println(resultName);
                    System.out.println(chemFormula);
                    Medicine med = new Medicine();
                    med.setUrl(getProperty(solution, "state"));
                    med.setAggregateState(solution.get("state").toString());
                    med.setGenericName(resultGenericName);
                    med.setAvgWeight(Double.parseDouble(solution.get("avgWeight").toString()));
                    med.setChemicalFormula(chemFormula);
                    med.setIndication(indication);
                    med.setDescription(solution.get("description").toString());
                    med.setUrl(solution.get("nameUrl").toString());
                    list2.add(med);
                }
            }

            System.out.println(list2);
            return list2;

        } else if (type.equals("asc")) {
            String SPARQLEndpoint2 = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/sparql";
            String query2 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "PREFIX drugbank: <http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/>" +
                    "SELECT distinct ?nameUrl ?genericName ?chemFormula ?state ?description  ?indication ?avgWeight  ?pharmgkbId" +
                    "WHERE { " +
                    "drugbank:drugs ^a ?nameUrl." +
                    "?nameUrl drugbank:chemicalFormula ?chemFormula;" +
                    "drugbank:genericName ?genericName;" +
                    "drugbank:state ?state;" +
                    "drugbank:description ?description;" +
                    "drugbank:indication ?indication;" +
                    "drugbank:molecularWeightAverage ?avgWeight;" +
                    "drugbank:pharmgkbId ?pharmgkbId." +

                    "}" + "" +
                    "ORDER BY ASC(?avgWeight)"+

                    "LIMIT " + limit;


            Query sparqlQuery2 = QueryFactory.create(query2);
            try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(SPARQLEndpoint2, sparqlQuery2)) {
                ResultSet resultSet1 = queryExecution.execSelect();
                while (resultSet1.hasNext()) {
                    QuerySolution solution = resultSet1.nextSolution();
                    String resultName = solution.get("nameUrl").toString();  // mozhe da treba substring(1, length-2) za urls
                    String resultGenericName = solution.get("genericName").toString();  // mozhe da treba substring(1, length-2) za urls
                    String chemFormula = solution.get("chemFormula").toString();  // mozhe da treba substring(1, length-2) za urls
                    String indication = solution.get("indication").toString();
                    System.out.println(resultGenericName);
                    System.out.println(resultName);
                    System.out.println(chemFormula);
                    Medicine med = new Medicine();
                    med.setUrl(getProperty(solution, "state"));
                    med.setAggregateState(solution.get("state").toString());
                    med.setGenericName(resultGenericName);
                    med.setAvgWeight(Double.parseDouble(solution.get("avgWeight").toString()));
                    med.setChemicalFormula(chemFormula);
                    med.setIndication(indication);
                    med.setDescription(solution.get("description").toString());
                    med.setUrl(solution.get("nameUrl").toString());
                    list2.add(med);
                }
            }

            System.out.println(list2);
            return list2;

        }
        return list2;
    }


    @Override
    public List<Medicine> searchMedicineByName(String name) {
        List<Medicine> list = Lists.newArrayList();
        String SPARQLEndpoint = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/sparql";
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX drugbank: <http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/>" +
                "SELECT distinct ?nameUrl ?genericName ?chemFormula ?state ?description  ?indication ?avgWeight  ?pharmgkbId" +
                "WHERE { " +
                "drugbank:drugs ^a ?nameUrl." +
                "?nameUrl drugbank:chemicalFormula ?chemFormula;" +
                "drugbank:genericName ?genericName;" +
                "drugbank:state ?state;"+
                "drugbank:description ?description;"+
                "drugbank:indication ?indication;"+
                "drugbank:molecularWeightAverage ?avgWeight;"+
                "drugbank:pharmgkbId ?pharmgkbId." +
                "FILTER regex(?genericName, '"+name+"', 'i')"+
                "}" + "" +
                "LIMIT 10";


        Query sparqlQuery = QueryFactory.create(query);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(SPARQLEndpoint, sparqlQuery)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution solution = resultSet.nextSolution();
                list.add(createMedicineBySolution(solution));
            }
        }

        System.out.println(list);
        return list;
    }

    @Override
    public Medicine getMedicineByGenericName(String name) {
        String SPARQLEndpoint = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/sparql";
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX drugbank: <http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/>" +
                "SELECT distinct ?nameUrl ?genericName ?chemFormula ?state ?description  ?indication ?avgWeight  ?pharmgkbId" +
                "WHERE { " +
                "drugbank:drugs ^a ?nameUrl." +
                "?nameUrl drugbank:chemicalFormula ?chemFormula;" +
                "drugbank:genericName '"+name+"';" +
                "drugbank:genericName ?genericName;" +
                "drugbank:state ?state;"+
                "drugbank:description ?description;"+
                "drugbank:indication ?indication;"+
                "drugbank:molecularWeightAverage ?avgWeight;"+
                "drugbank:pharmgkbId ?pharmgkbId." +
                "}" + "" +
                "LIMIT 1";

        Medicine med =new Medicine();
        Query sparqlQuery = QueryFactory.create(query);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(SPARQLEndpoint, sparqlQuery)) {
            ResultSet resultSet = queryExecution.execSelect();
            if(resultSet.hasNext()){
                QuerySolution solution = resultSet.nextSolution();
               med=createMedicineBySolution(solution);
            }
        }
        return med;
    }

    private Medicine createMedicineBySolution(QuerySolution solution) {
        Medicine med=new Medicine();
        med.setUrl(getProperty(solution,"nameUrl"));
        med.setAggregateState(getProperty(solution,"state"));
        med.setGenericName(getProperty(solution,"genericName"));
        med.setAvgWeight(Double.parseDouble(solution.get("avgWeight").toString()));
        med.setChemicalFormula(getProperty(solution,"chemFormula"));
        med.setIndication(getProperty(solution,"indication"));
        med.setDescription(getProperty(solution,"description"));
        return med;
    }

    private String getProperty(QuerySolution solution, String state){
        return solution.get(state)!= null ? solution.get(state).toString() : "";
    }

}

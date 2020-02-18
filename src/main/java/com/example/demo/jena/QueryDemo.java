package com.example.demo.jena;

import org.apache.commons.compress.utils.Lists;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>demo query</p>
 *
 * @author SageZhang
 * @version 2018/7/25
 */
@Component
public class QueryDemo {
    public List<String> query(String param) {
        List<String> list = Lists.newArrayList();
        FileManager.get().addLocatorClassLoader(QueryDemo.class.getClassLoader());
        // Model model = FileManager.get().loadModel("data.ttl");
//        Model model= ModelFactory.createDefaultModel();
//        model.read("http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/drugs","TURTLE");
//
//        String queryString =
//                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
//                        "PREFIX drugbank: <http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/>" +
//                        "SELECT ?genericName" +
//                        "WHERE { " +
//                        "drugbank:drugs ^a ?nameUrl." +
//                        "?nameUrl drugbank:genericName ?genericName." +
////                        "drugbank:genericName ?genericName." +
//                        "}" + "" +
//                        "LIMIT 5";
////                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n  " +
////                        "PREFIX drugbank:  <http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/> \n"+
////                        "SELECT ?name WHERE { " +
////                        "?x a drugbank:drugs ; "+
////                        "?x  drugbank:genericName ?"+param+
////                        " . " +
////                        "}"+"" +
////                        "LIMIT 4";
//        Query query = QueryFactory.create(queryString);
//        QueryExecution qexec = QueryExecutionFactory.create(query, model);
//        try {
//            ResultSet results = qexec.execSelect();
//            while (results.hasNext()) {
//                QuerySolution soln = results.nextSolution();
//              Literal name = soln.getLiteral("genericName");
//              //  Resource name1=soln.getResource("name");
//          //      RDFNode name=soln.getResource("name");
//               // System.out.println(name1);
////                Model modelNov=ModelFactory.createDefaultModel();
////                modelNov.read(name.toString(),"TURTLE");
////                modelNov.setNsPrefix("drugbank","<http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/>");
////                list.add(modelNov.getProperty("drugbank","genericName").get);
//
////                String queryString1 =
////                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
////                                "PREFIX drugbank: <http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/>" +
////                                "SELECT ?genericName WHERE { " +
////                                "?x drugbank:genericName ?genericName."+
//////                        "?name drugbank:chemicalFormula ?genericName." +
////                                "}"+"" +
////                                "LIMIT 5";
////                Query query1 = QueryFactory.create(queryString1);
////                QueryExecution qexec1 = QueryExecutionFactory.create(query1, modelNov);
////                ResultSet results1 = qexec1.execSelect();
////                while (results1.hasNext()) {
////                    QuerySolution soln1 = results1.nextSolution();
////                Literal name2 = soln1.getLiteral("genericName");
////                list.add(name2.toString());
////                }
//                        list.add(name.toString());
//            }
//        } finally {
//            qexec.close();
//        }
//        return list;
//    }
        String SPARQLEndpoint = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/sparql";
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX drugbank: <http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/>" +
                "SELECT ?nameUrl ?genericName ?chemFormula " +
                "WHERE { " +
                "drugbank:drugs ^a ?nameUrl." +
                "?nameUrl drugbank:chemicalFormula ?chemFormula;" +
                "drugbank:genericName ?genericName." +
                "}" + "" +
                "LIMIT 5";

        Set<String> collaboratorUrls = new HashSet<>();
        List<String> lista=new ArrayList<>();
        Set<String> extraUrls = new HashSet<>();
        String artistMusicBrainzUrl = null;
        String artistCorrectName = null;
        Query sparqlQuery = QueryFactory.create(query);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(SPARQLEndpoint, sparqlQuery)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution solution = resultSet.nextSolution();
                String resultName = solution.get("nameUrl").toString();  // mozhe da treba substring(1, length-2) za urls
                String resultGenericName = solution.get("genericName").toString();  // mozhe da treba substring(1, length-2) za urls
                String chemFormula = solution.get("chemFormula").toString();  // mozhe da treba substring(1, length-2) za urls
                System.out.println(resultGenericName);
                System.out.println(resultName);
                System.out.println(chemFormula);
                lista.add(resultGenericName);

            }
        }
        return lista;

    }
}
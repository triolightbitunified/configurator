package com.bitunified.ledconfig.configuration.parser.steps;


import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.Relation;
import com.bitunified.ledconfig.domain.relation.RelationDefinition;
import com.bitunified.ledconfig.parts.Part;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    private Set<Part> parts = new HashSet<Part>();

    private Set<Relation> relations = new HashSet<Relation>();

    private List<RelationDefinition> relationDefinitions = new ArrayList<RelationDefinition>();

    private List<Model> models = new ArrayList<Model>();

    private ComposedProduct composedProduct;

    public void createParts(ParserDataResult dataResult) {
        if (dataResult != null) {
            this.models.addAll(dataResult.getModels());
            this.relationDefinitions.addAll(dataResult.getRelationDefinitions());
            this.parts.addAll(dataResult.getParts());


            composedProduct = new ComposedProduct(null, null);
            composedProduct.setName("Product lengte");

            models.add(composedProduct);
        }
    }


    public Set<Relation> getRelations() {
        return relations;
    }

    public List<Model> getModels() {
        return this.models;
    }

    public List<RelationDefinition> getRelationDefinitions() {
        return relationDefinitions;
    }
}

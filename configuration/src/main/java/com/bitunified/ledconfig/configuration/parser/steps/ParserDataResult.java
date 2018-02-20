package com.bitunified.ledconfig.configuration.parser.steps;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.relation.RelationDefinition;
import com.bitunified.ledconfig.parts.Part;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParserDataResult {
    private Set<Part> parts = new HashSet<Part>();
    private List<RelationDefinition> relationDefinitions = new ArrayList<RelationDefinition>();
    private List<Model> models = new ArrayList<Model>();

    public ParserDataResult(List<Model> models, List<RelationDefinition> relationDefinitions, Set<Part> parts) {
        this.models = models;
        this.relationDefinitions = relationDefinitions;
        this.parts = parts;
    }

    public List<Model> getModels() {
        return models;
    }

    public List<RelationDefinition> getRelationDefinitions() {
        return relationDefinitions;
    }

    public Set<Part> getParts() {
        return parts;
    }
}

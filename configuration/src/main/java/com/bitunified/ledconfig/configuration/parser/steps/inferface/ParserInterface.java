package com.bitunified.ledconfig.configuration.parser.steps.inferface;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.relation.RelationDefinition;
import com.bitunified.ledconfig.domain.relation.RelationState;
import com.bitunified.ledconfig.parts.Part;

import java.util.AbstractSequentialList;
import java.util.List;
import java.util.Set;

public interface ParserInterface {

    void createParts(List<Model> models, List<RelationDefinition> relationDefinitions, Set<Part> parts);

    RelationDefinition createRelationDefinition(AbstractSequentialList relationDefinitions);

    RelationDefinition createRelationDefinition(RelationState state, String message);
}

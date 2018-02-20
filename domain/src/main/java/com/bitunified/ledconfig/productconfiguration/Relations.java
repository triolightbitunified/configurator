package com.bitunified.ledconfig.productconfiguration;


import com.bitunified.ledconfig.domain.relation.RelationDefinition;

import java.util.ArrayList;
import java.util.List;

public class Relations {

    private List<RelationDefinition> relations = new ArrayList<RelationDefinition>();

    public List<RelationDefinition> getRelations() {
        return relations;
    }

    public void setRelations(List<RelationDefinition> relations) {
        this.relations = relations;
    }
}

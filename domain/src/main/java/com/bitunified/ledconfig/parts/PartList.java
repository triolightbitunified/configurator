package com.bitunified.ledconfig.parts;

import com.bitunified.ledconfig.domain.Relation;

import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name="partList")
public class PartList {

    private Set<Part> parts = new HashSet<Part>();

    private Set<Relation> relations =new HashSet<Relation>();

    public PartList(){

    }

    public PartList(Set<Part> parts,Set<Relation> relations) {
        this.parts=parts;
        this.relations=relations;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    public Set<Relation> getRelations() {
        return relations;
    }

    public void setRelations(Set<Relation> relations) {
        this.relations = relations;
    }
}

package com.bitunified.ledconfig.domain;


import com.bitunified.ledconfig.parts.Relatable;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Relation implements Serializable {


    private Set<Relatable> relateToLeft = new HashSet<Relatable>();


    private Set<Relatable> relateToRight = new HashSet<Relatable>();


    public Relation() {

    }

    public Relation(Relatable relateTo) {

        //addRelation(relateTo,null);
        relateTo.setRelates(this);
    }

    private void addRelation(Relatable relateTo, Orientation orientation) {

        if (orientation == null) {

            relateToLeft.add(relateTo);
            relateToRight.add(relateTo);
        }
        if (orientation == Orientation.Left) {
            relateToLeft.add(relateTo);
        } else {
            relateToRight.add(relateTo);
        }
    }


    public void addRelateTo(Relatable part, Orientation orientation) {

        this.addRelation(part, orientation);
    }

    public Set<Relatable> getRelateToRight() {
        return relateToRight;
    }

    public void setRelateToRight(Set<Relatable> relateToRight) {
        this.relateToRight = relateToRight;
    }

    public Set<Relatable> getRelateToLeft() {
        return relateToLeft;
    }

    public void setRelateToLeft(Set<Relatable> relateToLeft) {
        this.relateToLeft = relateToLeft;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relation relation = (Relation) o;

        if (relateToLeft != null ? !relateToLeft.equals(relation.relateToLeft) : relation.relateToLeft != null)
            return false;
        return relateToRight != null ? relateToRight.equals(relation.relateToRight) : relation.relateToRight == null;

    }

    @Override
    public int hashCode() {
        int result = relateToLeft != null ? relateToLeft.hashCode() : 0;
        result = 31 * result + (relateToRight != null ? relateToRight.hashCode() : 0);
        return result;
    }
}

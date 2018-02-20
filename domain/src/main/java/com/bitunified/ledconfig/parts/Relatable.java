package com.bitunified.ledconfig.parts;


import com.bitunified.ledconfig.domain.Orientation;
import com.bitunified.ledconfig.domain.Relation;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlSeeAlso({RealModel.class,Part.class})
public class Relatable {


   private String id="";

    private Relation relates = new Relation();

    private Orientation orientation;

 @JsonIgnore
 @XmlTransient
    public Relation getRelation() {
        return relates;
    }

    public void setRelates(Relation relatedTo) {
        this.relates = relatedTo;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

 @Override
 public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;

  Relatable relatable = (Relatable) o;

  return id != null ? id.equals(relatable.id) : relatable.id == null;

 }

 @Override
 public int hashCode() {
  return id != null ? id.hashCode() : 0;
 }


 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }
}

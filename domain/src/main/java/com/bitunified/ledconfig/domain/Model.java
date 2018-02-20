package com.bitunified.ledconfig.domain;


import com.bitunified.ledconfig.domain.I18N.Locale;
import com.bitunified.ledconfig.domain.modeltypes.ConfigurationModel;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.cable.Cable;
import com.bitunified.ledconfig.domain.product.cover.types.Cap;
import com.bitunified.ledconfig.domain.product.cover.types.Resin;
import com.bitunified.ledconfig.domain.product.mounting.Mounting;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import com.bitunified.ledconfig.domain.relation.RelationDefinition;
import com.bitunified.ledconfig.parts.Relatable;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.beans.Transient;
import java.io.Serializable;
import java.util.*;

@XmlSeeAlso({RealModel.class, ConfigurationModel.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeClass")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RealModel.class),
        @JsonSubTypes.Type(value = ConfigurationModel.class)
})
public class Model extends Relatable implements StepConfig, Serializable {


    private String uuid="unknown";
    private String name = "";

    private String code;
    private Integer step;

    private String imageUrl;



    private List<Property> properties = new ArrayList<Property>();

    private Map<Locale, String> translations = new HashMap<Locale, String>();


    private List<RelationDefinition> relations=new ArrayList<RelationDefinition>();

    public Model() {
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    @XmlTransient
    public Property addProperty(Property property) {
        this.properties.add( property);
        return property;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.setTranslations(Locale.nl,name);
    }

    @XmlTransient
    public Property getProperty(String propName) {
        for (Property p:properties){
            if (p.getName()==propName){
                return p;
            }
        }

        return null;
    }


    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (name != null ? !name.equals(model.name) : model.name != null) return false;
        if (code != null ? !code.equals(model.code) : model.code != null) return false;
        return step != null ? step.equals(model.step) : model.step == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (step != null ? step.hashCode() : 0);
        //result = 31 * result + (properties != null ? properties.hashCode() : 0);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<Locale, String> getTranslations() {
        return translations;
    }

    public void setTranslations(Locale locale, String s) {
        translations.put(locale, s);
    }

    public void setTranslations(Map<Locale, String> translations) {
        this.translations = translations;
    }

    public String getUuid() {
        return uuid;
    }


    @XmlTransient
    public List<RelationDefinition> getRelations() {
        return relations;
    }

    @XmlTransient
    public void setRelations(List<RelationDefinition> relations) {
        this.relations = relations;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        if (imageUrl!=null) {
            if (imageUrl.startsWith("http")){
                this.imageUrl=imageUrl;
            }else {
                this.imageUrl = "assets/images/" + imageUrl;
            }
        }
    }
}

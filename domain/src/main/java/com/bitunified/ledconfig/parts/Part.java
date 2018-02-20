package com.bitunified.ledconfig.parts;


import com.bitunified.ledconfig.domain.I18N.Locale;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.Relation;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@XmlSeeAlso({NotExistingPart.class,Relatable.class})
public class Part extends Relatable implements Serializable {

    private RealModel product;

    private Model configModel;


    private BigDecimal price;

    private String description;

    private String id;

    private String type;


    private Map<Locale,String> translations=new HashMap<Locale, String>();

    public Part(Model configModel) {
        this.configModel = configModel;
    }

    public Part(RealModel product) {
        this.product = product;
    }

    private String imageUrl;

    private String productPage;

    public Part() {

    }


    public String getDescription() {

        if ((description==null || description.isEmpty() ) && product!=null){
            return product.getName();
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public RealModel getProduct() {
        return product;
    }

    public void setProduct(RealModel product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Model getConfigModel() {
        return configModel;
    }

    public void setConfigModel(Model configModel) {
        this.configModel = configModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        return id != null ? id.equals(part.id) : part.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Map<Locale, String> getTranslations() {
        return translations;
    }

    public void setTranslations(Map<Locale, String> translations) {
        this.translations = translations;
    }
    public void setTranslations(Locale locale, String s) {
        translations.put(locale,s);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        if (imageUrl.startsWith("http")){
            this.imageUrl=imageUrl;
        }else {
            this.imageUrl = "assets/images/" + imageUrl;
        }
    }

    public String getProductPage() {
        return productPage;
    }

    public void setProductPage(String productPage) {
        this.productPage = productPage;
    }
}

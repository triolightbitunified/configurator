package com.bitunified.ledconfig.configuration.parser.steps

import com.bitunified.ledconfig.configuration.config.Executor
import com.bitunified.ledconfig.domain.Dimension
import com.bitunified.ledconfig.domain.I18N.Locale
import com.bitunified.ledconfig.domain.Margin
import com.bitunified.ledconfig.domain.Model
import com.bitunified.ledconfig.domain.Orientation
import com.bitunified.ledconfig.domain.product.PCB.LedStrip
import com.bitunified.ledconfig.domain.product.PCB.types.*
import com.bitunified.ledconfig.domain.product.accessoires.CableChannel
import com.bitunified.ledconfig.domain.product.accessoires.Clip
import com.bitunified.ledconfig.domain.product.cable.Cable
import com.bitunified.ledconfig.domain.product.cable.cableconfig.*
import com.bitunified.ledconfig.domain.product.cover.types.*
import com.bitunified.ledconfig.domain.product.mounting.*
import com.bitunified.ledconfig.domain.product.profile.Profile
import com.bitunified.ledconfig.domain.product.profile.ProfileL20
import com.bitunified.ledconfig.domain.relation.RelationDefinition
import com.bitunified.ledconfig.domain.relation.RelationState
import com.bitunified.ledconfig.parts.NotExistingPart
import com.bitunified.ledconfig.parts.Part

public class ExecImpl implements Executor {


    public RelationDefinition createRelationDefinition(List<RelationDefinition> relationDefinitions) {
        RelationDefinition relationDefinition = new RelationDefinition();

        relationDefinitions.add(relationDefinition);
        return relationDefinition;
    }


    public RelationDefinition createRelationDefinition(RelationState state, String message, List<RelationDefinition> relationDefinitions) {
        RelationDefinition relationDefinition = new RelationDefinition(state, message);
        relationDefinitions.add(relationDefinition);
        return relationDefinition;
    }

    public ParserDataResult createParts() {
        List<Model> models = new ArrayList<Model>();
        List<RelationDefinition> relationDefinitions = new ArrayList<RelationDefinition>();
        Set<Part> parts = new HashSet<Part>();

        RelationDefinition relationDefinitionL = createRelationDefinition(relationDefinitions);
        RelationDefinition relationDefinitionH = createRelationDefinition(relationDefinitions);

        ProfileL20 profileL20 = new ProfileL20(new Dimension(null));
        profileL20.getDimension().setWidth(4000);
        profileL20.setName("liniLED® Aeris Profiel L20");
        profileL20.setTranslations(Locale.en, "liniLED® Aeris Profile L20");
        profileL20.setMaxDimension(new Dimension(2750));
        profileL20.setProductPage("http://www.liniled.com/product/liniled-aeris-profile-l20");

        Margin margin = new Margin(400, null);
        profileL20.setMargin(margin);
        profileL20.setLengthForCasting(new Dimension(25));
        profileL20.setCode("D");
        profileL20.setStep(1);
        models.add(profileL20);

        Profile profileL30 = new Profile(new Dimension(null));
        profileL30.setName("liniLED® Aeris Profiel L30");
        profileL30.setTranslations(Locale.nl, profileL30.getName());
        profileL30.setTranslations(Locale.en, "liniLED® Aeris Profile L30");
        profileL30.setLengthForCasting(new Dimension(25));
        profileL30.setMaxDimension(new Dimension(2750));
        profileL30.setProductPage("http://www.liniled.com/product/liniled-aeris-profile-l30");
        profileL30.setCode("F");

        models.add(profileL30);

        Profile profileH20 = new Profile(new Dimension(null));
        profileH20.setName("liniLED® Aeris Profiel H20");
        profileH20.setLengthForCasting(new Dimension(25));
        profileH20.setMaxDimension(new Dimension(2750));
        profileH20.setTranslations(Locale.en, "liniLED® Aeris Profile H20");
        profileH20.setCode("E");
        profileH20.setProductPage("http://www.liniled.com/product/liniled-aeris-profile-h20");

        models.add(profileH20);


        Profile profileH30 = new Profile(new Dimension(null));
        profileH30.setName("liniLED® Aeris Profiel H30");
        profileH30.setLengthForCasting(new Dimension(25));
        profileH30.setMaxDimension(new Dimension(2750));
        profileH30.setTranslations(Locale.en, "liniLED® Aeris Profile H30");
        profileH30.setCode("G");

        profileH30.setProductPage("http://www.liniled.com/product/liniled-aeris-profile-h30");
        models.add(profileH30);


        relationDefinitionL.addModel(profileL20, profileL30);

        relationDefinitionH.addModel(profileH20, profileH30);

        RelationDefinition relationDefinitionNotDiffuus = createRelationDefinition(RelationState.ALLOWEDWITHWARNING, "Product will not be diffuse.", relationDefinitions);
        RelationDefinition relationDefinitionNotDiffuusTW = createRelationDefinition(RelationState.ALLOWEDWITHWARNING, "Product will not be diffuse.", relationDefinitions);

        RelationDefinition relationDefinitionDiffuus1 = createRelationDefinition(RelationState.ALLOWEDWITHINFO, "Product will be diffuse", relationDefinitions);


        RelationDefinition relationDefinitionRGBCable = createRelationDefinition(relationDefinitions);
        RelationDefinition relationDefinitionTWCable = createRelationDefinition(relationDefinitions);

        RelationDefinition relationDefinitionNOTRGBTW = createRelationDefinition(relationDefinitions);

        RelationDefinition relationDefinitionNoEndCap = createRelationDefinition(relationDefinitions);

        RelationDefinition relationDefinitionCover = createRelationDefinition(relationDefinitions);
        RelationDefinition relationDefinitionResin = createRelationDefinition(relationDefinitions);

        relationDefinitionDiffuus1.addModel(profileH20, profileH30);

        relationDefinitionNotDiffuus.addModel(profileL20, profileL30);
        relationDefinitionNotDiffuusTW.addModel(profileL20, profileL30);
        Part partL20 = new Part(profileL20);
        partL20.setPrice(BigDecimal.valueOf(36.09));
        partL20.setId("10713");
        partL20.setDescription("liniLED® Aeris Profiel L20 4 m");
        partL20.setTranslations(Locale.en, "liniLED® Aeris Profile L20 4 m");
        partL20.setImageUrl("10710profileL20.jpg");
        parts.add(partL20);


        EndCap endCap = new EndCap();


        Part partEndCapLC20 = new Part(endCap);
        partEndCapLC20.setPrice(BigDecimal.valueOf(8.22));
        partEndCapLC20.setId("10902");
        partEndCapLC20.setDescription("liniLED® Aeris Eindkap LC20");
        partEndCapLC20.setTranslations(Locale.en, "liniLED® Aeris End Cap LC20");
        parts.add(partEndCapLC20);
        profileL20.getRelation().addRelateTo(partEndCapLC20, null);


        Part partEndCapL20 = new Part(endCap);
        partEndCapL20.setPrice(BigDecimal.valueOf(7.84));
        partEndCapL20.setId("10900");
        partEndCapL20.setDescription("liniLED® Aeris Eindkap L20");
        partEndCapL20.setTranslations(Locale.en, "liniLED® Aeris End Cap L20");
        parts.add(partEndCapL20);
        profileL20.getRelation().addRelateTo(partEndCapL20, null);


        Part partProfielH20 = new Part(profileH20);
        partProfielH20.setPrice(BigDecimal.valueOf(63.39));
        partProfielH20.setId("10717");
        partProfielH20.setDescription("liniLED® Aeris Profiel H20 4 m");
        partProfielH20.setTranslations(Locale.en, "liniLED® Aeris Profile H20 4 m");
        partProfielH20.setImageUrl("10714profileH20.jpg");
        parts.add(partProfielH20);


        Part partEndCapH20 = new Part(endCap);
        partEndCapH20.setPrice(BigDecimal.valueOf(8.43));
        partEndCapH20.setId("10904");
        partEndCapH20.setDescription("liniLED® Aeris Eindkap H20");
        partEndCapH20.setTranslations(Locale.en, "liniLED® Aeris End Cap H20");
        parts.add(partEndCapH20);
        profileH20.getRelation().addRelateTo(partEndCapH20, null);

        Part partEndCapHC20 = new Part(endCap);
        partEndCapHC20.setPrice(BigDecimal.valueOf(8.53));
        partEndCapHC20.setId("10906");
        partEndCapHC20.setDescription("liniLED® Aeris Eindkap HC20");
        partEndCapHC20.setTranslations(Locale.en, "liniLED® Aeris End Cap HC20");
        parts.add(partEndCapHC20);
        profileH20.getRelation().addRelateTo(partEndCapHC20, null);


        Part partEndCapC20 = new Part(endCap);
        partEndCapC20.setPrice(BigDecimal.valueOf(7.63));
        partEndCapC20.setId("10908");
        partEndCapC20.setDescription("liniLED® Aeris Eindkap C20");
        partEndCapC20.setTranslations(Locale.en, "liniLED® Aeris End Cap C20");
        parts.add(partEndCapC20);
        profileH20.getRelation().addRelateTo(partEndCapC20, null);
        profileL20.getRelation().addRelateTo(partEndCapC20, null);

        Part part = new Part(endCap);
        part.setPrice(BigDecimal.valueOf(8.32));
        part.setId("10903");
        part.setDescription("liniLED® Aeris Eindkap LC20 O");
        part.setTranslations(Locale.en, "liniLED® Aeris End Cap LC20 O");
        parts.add(part);


        Part partEndCapL20Open = new Part(endCap);
        partEndCapL20Open.setPrice(BigDecimal.valueOf(8.00));
        partEndCapL20Open.setId("10901");
        partEndCapL20Open.setDescription("liniLED® Aeris Eindkap L20 O");
        partEndCapL20Open.setTranslations(Locale.en, "liniLED® Aeris End Cap L20 O");
        parts.add(partEndCapL20Open);
        profileL20.getRelation().addRelateTo(partEndCapL20Open, null);

        part = new Part(endCap);
        part.setPrice(BigDecimal.valueOf(8.53));
        part.setId("10905");
        part.setDescription("liniLED® Aeris Eindkap H20 O");
        part.setTranslations(Locale.en, "liniLED® Aeris End Cap H20 O");
        parts.add(part);
        profileH20.getRelation().addRelateTo(part, null);

        Part partHC20O = new Part(endCap);
        partHC20O.setPrice(BigDecimal.valueOf(8.64));
        partHC20O.setId("10907");
        partHC20O.setDescription("liniLED® Aeris Eindkap HC20 O");
        partHC20O.setTranslations(Locale.en, "liniLED® Aeris End Cap HC20 O");
        parts.add(partHC20O);
        profileH20.getRelation().addRelateTo(partHC20O, null);

        Cap capDiffuse = new CapDiffuse();
        capDiffuse.setName("liniLED® Aeris Afdekkap Diffuus");
        capDiffuse.setTranslations(Locale.en, "liniLED® Aeris Cover Diffuse");
        capDiffuse.getProperty(Cap.TRANSLUCENCY_S).setValue("diffuse");
        capDiffuse.setCode("L");
        capDiffuse.setMaxDimension(new Dimension(4000));
        models.add(capDiffuse);

        relationDefinitionL.addModel(capDiffuse);
        relationDefinitionH.addModel(capDiffuse);
        relationDefinitionDiffuus1.addModel(capDiffuse);
        relationDefinitionNotDiffuus.addModel(capDiffuse);
        relationDefinitionCover.addModel(capDiffuse);

        Part partCapL20 = new Part(capDiffuse);
        partCapL20.setPrice(BigDecimal.valueOf(40.92));
        partCapL20.setId("10803");
        partCapL20.setDescription("liniLED® Aeris Kap D L20 4 m");
        partCapL20.setTranslations(Locale.en, "liniLED® Aeris Cover D L20 4 m");
        partCapL20.setImageUrl("10800coverDiffuseL20.jpg");
        profileL20.getRelation().addRelateTo(partCapL20, null);
        parts.add(partCapL20);


        Cap capClear = new CapClear(new Dimension(null));
        capClear.setName("liniLED® Aeris Afdekkap Helder");
        capClear.setTranslations(Locale.en, "liniLED® Aeris Cover Clear");
        capClear.getDimension().setWidth(100);
        capClear.setMaxDimension(new Dimension(4000));
        capClear.setCode("R");
        models.add(capClear);
        relationDefinitionL.addModel(capClear);
        relationDefinitionCover.addModel(capClear);
        partCapL20 = new Part(capClear);
        partCapL20.setPrice(BigDecimal.valueOf(35.62));
        partCapL20.setId("10807");
        partCapL20.setDescription("liniLED® Aeris Kap C L20 4 m");
        partCapL20.setTranslations(Locale.en, "liniLED® Aeris Cover C L20 4 m");
        partCapL20.setImageUrl("10824coverClearL30.jpg");
        profileL20.getRelation().addRelateTo(partCapL20, null);
        parts.add(partCapL20);


        partCapL20 = new Part(capDiffuse);
        partCapL20.setPrice(BigDecimal.valueOf(54.11));
        partCapL20.setId("10811");
        partCapL20.setDescription("liniLED® Aeris Kap D H20 4 m");
        partCapL20.setTranslations(Locale.en, "liniLED® Aeris Cover D H20 4 m");
        partCapL20.setProductPage("http://www.liniled.nl/product/liniled-aeris-kap-d-h20/");
        partCapL20.setImageUrl("10828coverDiffuseH30.jpg");
        profileH20.getRelation().addRelateTo(partCapL20, null);
        parts.add(partCapL20);


        Part partCapL30D = new Part(capDiffuse);
        partCapL30D.setPrice(BigDecimal.valueOf(43.46));
        partCapL30D.setId("10823");
        partCapL30D.setImageUrl("10800coverDiffuseL20.jpg");
        partCapL30D.setDescription("liniLED® Aeris Kap D L30 4 m");
        partCapL30D.setTranslations(Locale.en, "liniLED® Aeris Cover D L30 4 m");
        profileL30.getRelation().addRelateTo(partCapL30D, null);
        parts.add(partCapL30D);


        Part partCapL30 = new Part(capClear);
        partCapL30.setPrice(BigDecimal.valueOf(37.74));
        partCapL30.setId("10827");
        partCapL30.setDescription("liniLED® Aeris Kap C L30 4 m");
        partCapL30.setTranslations(Locale.en, "liniLED® Aeris Cover C L30 4 m");
        partCapL30.setImageUrl("10824coverClearL30.jpg");
        profileL30.getRelation().addRelateTo(partCapL30, null);
        parts.add(partCapL30);


        Part partCapH30 = new Part(capDiffuse);
        partCapH30.setPrice(BigDecimal.valueOf(55.76));
        partCapH30.setId("10831");
        partCapH30.setDescription("liniLED® Aeris Kap D H30 4 m");
        partCapH30.setTranslations(Locale.en, "liniLED® Aeris Cover D H30 4 m");
        partCapH30.setImageUrl("10828coverDiffuseH30.jpg");
        partCapH30.setProductPage("http://www.liniled.nl/product/liniled-aeris-kap-d-h20/");
        profileH30.getRelation().addRelateTo(partCapH30, null);
        parts.add(partCapH30);

        Part partProfielL30 = new Part(profileL30);

        partProfielL30.setPrice(BigDecimal.valueOf(49.29));
        partProfielL30.setId("10733");
        partProfielL30.setDescription("liniLED® Aeris Profiel L30 4 m");
        partProfielL30.setTranslations(Locale.en, "liniLED® Aeris Profile L30 4 m");
        partProfielL30.setImageUrl("10730profileL30.jpg");
        parts.add(partProfielL30);

        Part partEndCapL30 = new Part(endCap);
        partEndCapL30.setPrice(BigDecimal.valueOf(8.00));
        partEndCapL30.setId("10920");
        partEndCapL30.setDescription("liniLED® Aeris Eindkap L30");
        partEndCapL30.setTranslations(Locale.en, "liniLED® Aeris End Cap L30");
        partEndCapL30.setImageUrl("10920liniLEDAerisEndCapL30.jpg");
        parts.add(partEndCapL30);
        profileL30.getRelation().addRelateTo(partEndCapL30, null);


        Part partEndCapLC30 = new Part(endCap);
        partEndCapLC30.setPrice(BigDecimal.valueOf(8.43));
        partEndCapLC30.setId("10922");
        partEndCapLC30.setDescription("liniLED® Aeris Eindkap LC30");
        partEndCapLC30.setTranslations(Locale.en, "liniLED® Aeris End Cap LC30");
        partEndCapLC30.setImageUrl("10902liniLEDAerisEndCapLC20.jpg");
        parts.add(partEndCapLC30);
        profileL30.getRelation().addRelateTo(partEndCapLC30, null);


        Part partProfielH30 = new Part(profileH30);
        partProfielH30.setPrice(BigDecimal.valueOf(76.69));
        partProfielH30.setId("10737");
        partProfielH30.setDescription("liniLED® Aeris Profiel H30 4 m");
        partProfielH30.setTranslations(Locale.en, "liniLED® Aeris Profile H30 4 m");
        partProfielH30.setImageUrl("10734profileH30.jpg");
        parts.add(partProfielH30);


        Part partEndCapH30 = new Part(endCap);
        partEndCapH30.setPrice(BigDecimal.valueOf(8.43));
        partEndCapH30.setId("10924");
        partEndCapH30.setDescription("liniLED® Aeris Eindkap H30");
        partEndCapH30.setTranslations(Locale.en, "liniLED® Aeris End Cap H30");
        partEndCapH30.setImageUrl("10904liniLEDAerisEndCapH20.jpg");
        parts.add(partEndCapH30);
        profileH30.getRelation().addRelateTo(partEndCapH30, null);


        Part partEndCapHC30 = new Part(endCap);
        partEndCapHC30.setPrice(BigDecimal.valueOf(8.53));
        partEndCapHC30.setId("10926");
        partEndCapHC30.setDescription("liniLED® Aeris Eindkap HC30");
        partEndCapHC30.setTranslations(Locale.en, "liniLED® Aeris End Cap HC30");

        parts.add(partEndCapHC30);
        profileH30.getRelation().addRelateTo(partEndCapHC30, null);


        Part partEndCapC30 = new Part(endCap);
        partEndCapC30.setPrice(BigDecimal.valueOf(7.90));
        partEndCapC30.setId("10928");
        partEndCapC30.setDescription("liniLED® Aeris Eindkap C30");
        partEndCapC30.setTranslations(Locale.en, "liniLED® Aeris End Cap C30");

        parts.add(partEndCapC30);
        profileH30.getRelation().addRelateTo(partEndCapC30, null);

        Part partL30O = new Part(endCap);
        partL30O.setPrice(BigDecimal.valueOf(8.11));
        partL30O.setId("10921");
        partL30O.setDescription("liniLED® Aeris Eindkap L30 O");
        partL30O.setTranslations(Locale.en, "liniLED® Aeris End Cap L30 O");
        partL30O.setImageUrl("10901liniLEDAerisEndCapL20O.jpg");
        parts.add(partL30O);
        profileL30.getRelation().addRelateTo(partL30O, null);


        Part partLC30O = new Part(endCap);
        partLC30O.setPrice(BigDecimal.valueOf(8.53));
        partLC30O.setId("10923");
        partLC30O.setDescription("liniLED® Aeris Eindkap LC30 O");
        partLC30O.setTranslations(Locale.en, "liniLED® Aeris End Cap LC30 O");

        parts.add(partLC30O);

        profileL30.getRelation().addRelateTo(partLC30O, null);

        Part partH30O = new Part(endCap);
        partH30O.setPrice(BigDecimal.valueOf(8.53));
        partH30O.setId("10925");
        partH30O.setDescription("liniLED® Aeris Eindkap H30 O");
        partH30O.setTranslations(Locale.en, "liniLED® Aeris End Cap H30 O");

        parts.add(partH30O);

        profileH30.getRelation().addRelateTo(partH30O, null);

        Part partHC30O = new Part();
        partHC30O.setPrice(BigDecimal.valueOf(8.64));
        partHC30O.setId("10927");
        partHC30O.setDescription("liniLED® Aeris Eindkap HC30 O");
        partHC30O.setTranslations(Locale.en, "liniLED® Aeris End Cap HC30 O");
        partHC30O.setImageUrl("10907liniLEDAerisEndCapHC20O.jpg");
        parts.add(partHC30O);
        profileH30.getRelation().addRelateTo(partHC30O, null);


        CableEntry cableEntry = new LeftViaEndCapCableEntry();
        models.add(cableEntry);

        cableEntry.setName("Left side via end cap");
        cableEntry.setCode("A");
        cableEntry.setImageUrl("leftSideViaEndCap.jpg");
        cableEntry.getRelation().addRelateTo(partL30O, Orientation.Left);
        cableEntry.getRelation().addRelateTo(partHC30O, Orientation.Left);
        cableEntry.getRelation().addRelateTo(partH30O, Orientation.Left);
        margin = new Margin(10, 5);
        cableEntry.setMargins(margin);
        part = new NotExistingPart(cableEntry);

        part.setId("ca1");
        parts.add(part);

        cableEntry = new LeftViaBottomCableEntry();
        cableEntry.setName("Left side via bottom");
        cableEntry.setImageUrl("leftSideViaBottom.jpg");
        models.add(cableEntry);
        margin = new Margin(15, 5);
        cableEntry.setMargins(margin);
        cableEntry.setCode("B");
        part = new NotExistingPart(cableEntry);
        part.setId("ca2");
        parts.add(part);

        cableEntry = new LeftViaSideCableEntry();
        cableEntry.setName("Left side via side");
        cableEntry.setImageUrl("leftSideViaSide.jpg");
        models.add(cableEntry);
        relationDefinitionNoEndCap.addModel(cableEntry);
        margin = new Margin(15, 5);
        cableEntry.setMargins(margin);
        cableEntry.setCode("C");
        part = new NotExistingPart(cableEntry);
        part.setId("ca3");
        parts.add(part);


        cableEntry = new RightViaSideCableEntry();
        cableEntry.setName("Right side via side");
        cableEntry.setImageUrl("rightSideViaSide.jpg");
        models.add(cableEntry);

        margin = new Margin(5, 15);
        cableEntry.setMargins(margin);
        cableEntry.setCode("D");
        part = new NotExistingPart(cableEntry);
        part.setId("ca4");
        parts.add(part);

        cableEntry = new CenterViaSideCableEntry();
        cableEntry.setName("Center via side");
        cableEntry.setImageUrl("centerViaSide.jpg");
        models.add(cableEntry);

        margin = new Margin(5, 5);
        cableEntry.setMargins(margin);
        cableEntry.setCode("E");
        part = new NotExistingPart(cableEntry);
        part.setId("ca5");
        parts.add(part);

        Mounting mounting = new NoEndCapsMounting();
        mounting.setName("Geen eindkappen");
        mounting.setTranslations(Locale.nl, "Geen eindkappen");
        mounting.setTranslations(Locale.en, "No endcaps");
        mounting.setCode("A");

        models.add(mounting);

        margin = new Margin(2, 2);
        mounting.setMargins(margin);
        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20, Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapH20, Orientation.Right);

        part.setId("m1");
        parts.add(part);

        mounting = new EndCapRightMounting();
        mounting.setName("Eindkap aan de rechter zijde");
        mounting.setTranslations(Locale.en, "Endcap right side");
        margin = new Margin(0, 2);
        mounting.setCode("B");
        mounting.setMargins(margin);
        models.add(mounting);


        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20, Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapH20, Orientation.Right);
        part.setId("m2");
        parts.add(part);

        mounting = new EndCapLeftMounting();
        mounting.setName("Eindkap aan linkerzijde");
        mounting.setTranslations(Locale.en, "Endcap left side");
        margin = new Margin(2, 0);
        mounting.setCode("C");
        mounting.setMargins(margin);
        models.add(mounting);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20, Orientation.Left);
        mounting.getRelation().addRelateTo(partEndCapH20, Orientation.Left);
        part.setId("m3");
        parts.add(part);


        mounting = new EndCapBothSidesMounting();
        mounting.setName("Eindkappen aan beide zijdes");
        mounting.setTranslations(Locale.nl, mounting.getName());
        mounting.setTranslations(Locale.en, "Endcaps both sides");
        margin = new Margin(2, 2);
        mounting.setCode("D");
        mounting.setMargins(margin);
        models.add(mounting);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapL20, null);
        mounting.getRelation().addRelateTo(partEndCapH20, null);
        mounting.getRelation().addRelateTo(partEndCapL30, null);
        mounting.getRelation().addRelateTo(partEndCapH30, null);

        part.setId("m4");
        parts.add(part);

        mounting = new HighEndCapRightMounting();
        mounting.setName("Hoge eindkap aan de rechter zijde");
        mounting.setTranslations(Locale.en, "High endcap right side");
        margin = new Margin(0, 2);
        mounting.setCode("E");
        mounting.setMargins(margin);
        models.add(mounting);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapLC20, Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapHC20, Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapLC30, Orientation.Right);
        mounting.getRelation().addRelateTo(partEndCapHC30, Orientation.Right);
        part.setId("m5");
        parts.add(part);

        mounting = new HighEndCapLeftMounting();
        mounting.setName("Hoge eindkap aan de linker zijde");
        mounting.setTranslations(Locale.en, "High endcap left side");
        margin = new Margin(2, 0);
        mounting.setCode("F");
        mounting.setMargins(margin);
        models.add(mounting);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapLC20, Orientation.Left);
        mounting.getRelation().addRelateTo(partEndCapHC20, Orientation.Left);
        mounting.getRelation().addRelateTo(partEndCapLC30, Orientation.Left);
        mounting.getRelation().addRelateTo(partEndCapHC30, Orientation.Left);
        part.setId("m6");
        parts.add(part);

        mounting = new HighEndCapBothMounting();
        mounting.setName("Hoge eindkap aan beide zijdes");
        mounting.setTranslations(Locale.en, "High endcaps both sides");
        margin = new Margin(2, 2);
        mounting.setCode("G");
        mounting.setMargins(margin);
        models.add(mounting);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapLC20, null);
        mounting.getRelation().addRelateTo(partEndCapHC20, null);
        mounting.getRelation().addRelateTo(partEndCapLC30, null);
        mounting.getRelation().addRelateTo(partEndCapHC30, null);
        part.setId("m7");
        parts.add(part);

        mounting = new HighEndCapBothCableChannelMounting();
        mounting.setName("Hoge eindkap aan beide zijdes van kabelgoot");
        mounting.setTranslations(Locale.en, "High endcap both sides of cable channel");
        margin = new Margin(2, 2);
        mounting.setCode("H");
        mounting.setMargins(margin);
        models.add(mounting);

        part = new NotExistingPart(mounting);
        mounting.getRelation().addRelateTo(partEndCapLC20, null);
        mounting.getRelation().addRelateTo(partEndCapHC20, null);
        mounting.getRelation().addRelateTo(partEndCapLC30, null);
        mounting.getRelation().addRelateTo(partEndCapHC30, null);
        part.setId("m8");
        parts.add(part);

        CableChannel cableChannel = new CableChannel(new Dimension(null));
        cableChannel.setName("Cable Channel");
        cableChannel.setCode("b");
        models.add(cableChannel);

        part = new Part(cableChannel);
        part.setPrice(BigDecimal.valueOf(7.16));
        part.setId("10750");
        part.setDescription("liniLED® Aeris Kabelgoot 20 1 m");
        part.setTranslations(Locale.en, "liniLED® Aeris Cable Channel 20 1 m");
        part.setImageUrl("10754cableChannel30.jpg");
        parts.add(part);

        part = new Part(cableChannel);
        part.setPrice(BigDecimal.valueOf(14.36));
        part.setId("10751");
        part.setDescription("liniLED® Aeris Kabelgoot 20 2 m");
        part.setTranslations(Locale.en, "liniLED® Aeris Cable Channel 20 2 m");
        part.setImageUrl("10754cableChannel30.jpg");
        parts.add(part);

        part = new Part(cableChannel);
        part.setPrice(BigDecimal.valueOf(21.52));
        part.setId("10752");
        part.setDescription("liniLED® Aeris Kabelgoot 20 3 m");
        part.setTranslations(Locale.en, "liniLED® Aeris Cable Channel 20 3 m");
        part.setImageUrl("10754cableChannel30.jpg");
        parts.add(part);

        part = new Part(cableChannel);
        part.setPrice(BigDecimal.valueOf(28.73));
        part.setId("10753");
        part.setDescription("liniLED® Aeris Kabelgoot 20 4 m");
        part.setTranslations(Locale.en, "liniLED® Aeris Cable Channel 20 4 m");
        part.setImageUrl("10754cableChannel30.jpg");
        parts.add(part);

        part = new Part(cableChannel);
        part.setPrice(BigDecimal.valueOf(9.86));
        part.setId("10754");
        part.setDescription("liniLED® Aeris Kabelgoot 30 1 m");
        part.setTranslations(Locale.en, "liniLED® Aeris Cable Channel 30 1 m");
        part.setImageUrl("10754cableChannel30.jpg");
        parts.add(part);

        part = new Part(cableChannel);
        part.setPrice(BigDecimal.valueOf(19.66));
        part.setId("10755");
        part.setDescription("liniLED® Aeris Kabelgoot 30 2 m");
        part.setTranslations(Locale.en, "liniLED® Aeris Cable Channel 30 2 m");
        part.setImageUrl("10754cableChannel30.jpg");
        parts.add(part);

        part = new Part(cableChannel);
        part.setPrice(BigDecimal.valueOf(29.52));
        part.setId("10756");
        part.setDescription("liniLED® Aeris Kabelgoot 30 3 m");
        part.setTranslations(Locale.en, "liniLED® Aeris Cable Channel 30 3 m");
        part.setImageUrl("10754cableChannel30.jpg");
        parts.add(part);

        part = new Part(cableChannel);
        part.setPrice(BigDecimal.valueOf(39.38));
        part.setId("10757");
        part.setDescription("liniLED® Aeris Kabelgoot 30 4 m");
        part.setTranslations(Locale.en, "liniLED® Aeris Cable Channel 30 4 m");
        part.setImageUrl("10754cableChannel30.jpg");
        parts.add(part);


        Cable cable = new Cable(new Dimension(null));
        cable.setName("Cable Mono Demo 1 m");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("mono");
        cable.setTranslations(Locale.en, "Cable Mono Demo 1 m");
        cable.setImageUrl("mono.jpg");
        cable.setCode("K");
        relationDefinitionNOTRGBTW.addModel(cable);

        models.add(cable);

        part = new Part(cable);
        part.setPrice(BigDecimal.valueOf(13.99));
        part.setId("11214");
        part.setDescription("liniLED® Aansluitkabel Mono Demo 1 m");
        part.setTranslations(Locale.en, "liniLED® Connectorcable Mono Demo 1 m");
        part.setType("MTR");
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Kabel RGB PUR 1 m met PU connector set");
        cable.setTranslations(Locale.en, "Cable RGB PUR 1 m with PU connector set");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PU");
        cable.setCode("X");
        relationDefinitionRGBCable.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60013");
        part.setPrice(BigDecimal.valueOf(6.63));
        part.setImageUrl("phoenixmalerender.jpg");
        part.setTranslations(Locale.en, "Cable RGB PUR 1 m with PU connector set");
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Cable X RGB PUR White 1 m with open end");
        cable.setTranslations(Locale.en, "Cable X RGB PUR White 1 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("RGB");
        cable.setCode("G");
        relationDefinitionRGBCable.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60020");
        part.setImageUrl("cableIIandX.jpg");
        part.setPrice(BigDecimal.valueOf(6.25));
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Cable X RGB PUR White 5 m with open end");
        cable.setTranslations(Locale.en, "Cable X RGB PUR White 5 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("RGB");
        cable.setCode("H");
        relationDefinitionRGBCable.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60020b");
        part.setImageUrl("cableIIandX.jpg");
        part.setPrice(BigDecimal.valueOf(31.25));
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Cable X RGB PUR White 10 m with open end");
        cable.setTranslations(Locale.en, "Cable X RGB PUR White 10 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("RGB");
        cable.setCode("J");
        relationDefinitionRGBCable.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60020");
        part.setImageUrl("cableIIandX.jpg");
        part.setPrice(BigDecimal.valueOf(62.5));
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Cable IV RGB PVC Black 1 m with open end");
        cable.setTranslations(Locale.en, "Cable IV RGB PVC Black 1 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("RGB");
        cable.setCode("R");
        relationDefinitionRGBCable.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60022");
        part.setImageUrl("60013liniLEDRGBPUConnectorSet.jpg");
        part.setPrice(BigDecimal.valueOf(2.28));
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Cable IV RGB PVC Black 5 m with open end");
        cable.setTranslations(Locale.en, "Cable IV RGB PVC Black 5 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("RGB");
        cable.setCode("S");
        relationDefinitionRGBCable.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60022b");
        part.setImageUrl("60013liniLEDRGBPUConnectorSet.jpg");
        part.setPrice(BigDecimal.valueOf(11.4));
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Cable IV RGB PVC Black 10 m with open end");
        cable.setTranslations(Locale.en, "Cable IV RGB PVC Black 10 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("RGB");
        cable.setCode("T");
        relationDefinitionRGBCable.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60022c");
        part.setImageUrl("60013liniLEDRGBPUConnectorSet.jpg");
        part.setPrice(BigDecimal.valueOf(22.8));
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Cable RGB PUR 5 m with PU connector set");
        cable.setTranslations(Locale.en, "Cable RGB PUR 5 m with PU connector set");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("RGB");
        cable.setCode("Y");
        relationDefinitionRGBCable.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60014");
        part.setImageUrl("phoenixmalerender.jpg");
        part.setPrice(BigDecimal.valueOf(10.41));
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Cable RGB PUR 10 m with PU connector set");
        cable.setTranslations(Locale.en, "Cable RGB PUR 10 m with PU connector set");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("RGB");
        cable.setCode("Z");
        relationDefinitionRGBCable.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60015");
        part.setImageUrl("phoenixmalerender.jpg");
        part.setPrice(BigDecimal.valueOf(15.68));
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Kabel Mono PUR 1 m met open eind");
        cable.setTranslations(Locale.en, "Cable Mono PUR 1 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PVCopenend");
        cable.setCode("D");
        models.add(cable);

        relationDefinitionResin.addModel(cable);
        relationDefinitionCover.addModel(cable);
        part = new Part(cable);
        part.setId("60006");
        part.setPrice(BigDecimal.valueOf(4.13));
        part.setImageUrl("phoenixmalerender.jpg");
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Kabel Mono PUR 1 m met PU connector set");
        cable.setTranslations(Locale.en, "Cable Mono PUR 1 m with PU connector set");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PU");
        cable.setCode("U");
        models.add(cable);
        relationDefinitionResin.addModel(cable);
        relationDefinitionCover.addModel(cable);
        part = new Part(cable);
        part.setId("60010");
        part.setPrice(BigDecimal.valueOf(5.42));
        part.setImageUrl("phoenixmalerender.jpg");
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Kabel Mono PUR 4 m met PU connector set");
        cable.setTranslations(Locale.en, "Cable Mono PUR 4 m with PU connector set");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PU");
        cable.setCode("V");
        models.add(cable);
        relationDefinitionResin.addModel(cable);
        relationDefinitionCover.addModel(cable);
        part = new Part(cable);
        part.setId("60011");
        part.setPrice(BigDecimal.valueOf(8.92));
        part.setImageUrl("phoenixmalerender.jpg");
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Kabel Mono PUR 10 m met PU connector set");
        cable.setTranslations(Locale.en, "Cable Mono PUR 10 m with PU connector set");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("PU");
        cable.setCode("W");
        models.add(cable);
        relationDefinitionResin.addModel(cable);
        relationDefinitionCover.addModel(cable);
        part = new Part(cable);
        part.setId("60012");
        part.setImageUrl("phoenixmalerender.jpg");
        part.setPrice(BigDecimal.valueOf(13.29));
        parts.add(part);
        //60012	Kabel Mono PUR 10 m with PU connector set	W	€ 13,29

        //-----------------------------TW cables -----------------------------


        cable = new Cable(new Dimension(null));
        cable.setName("Cable V TW PVC White 1 m with open end");
        cable.setTranslations(Locale.en, "Cable V TW PVC White 1 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("TW");
        cable.setCode("N");
        relationDefinitionTWCable.addModel(cable);
        relationDefinitionCover.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60023");
        part.setPrice(BigDecimal.valueOf(4.24));
        part.setImageUrl("cableVandIX.jpg");
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Cable V TW PVC White 5 m with open end");
        cable.setTranslations(Locale.en, "Cable V TW PVC White 5 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("TW");
        cable.setCode("P");
        relationDefinitionTWCable.addModel(cable);
        relationDefinitionCover.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60023b");
        part.setPrice(BigDecimal.valueOf(21.2));
        part.setImageUrl("cableVandIX.jpg");
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Cable V TW PVC White 10 m with open end");
        cable.setTranslations(Locale.en, "Cable V TW PVC White 10 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("TW");
        cable.setCode("Q");
        relationDefinitionTWCable.addModel(cable);
        relationDefinitionCover.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60023c");
        part.setPrice(BigDecimal.valueOf(42.4));
        part.setImageUrl("cableVandIX.jpg");
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Cable IX TW PUR White 1 m with open end");
        cable.setTranslations(Locale.en, "Cable IX TW PUR White 1 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("TW");
        cable.setCode("L");
        relationDefinitionTWCable.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60019");
        part.setPrice(BigDecimal.valueOf(5.62));
        part.setImageUrl("cableVandIX.jpg");
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Cable IX TW PUR White 5 m with open end");
        cable.setTranslations(Locale.en, "Cable IX TW PUR White 5 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("TW");
        cable.setCode("M");
        relationDefinitionTWCable.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60019b");
        part.setPrice(BigDecimal.valueOf(28.1));
        part.setImageUrl("cableVandIX.jpg");
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Cable IX TW PUR White 10 m with open end");
        cable.setTranslations(Locale.en, "Cable IX TW PUR White 10 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("TW");
        cable.setCode("O");
        relationDefinitionTWCable.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60019c");
        part.setPrice(BigDecimal.valueOf(56.2));
        part.setImageUrl("cableVandIX.jpg");
        parts.add(part);

        //----------------------------END TW Cables----------------------------------

        //-----------------NOT FOR RGB OR TW CABLES -----------


        cable = new Cable(new Dimension(null));
        cable.setName("Cable III Mono PVC White 1 m with open end");
        cable.setTranslations(Locale.en, "Cable III Mono PVC White 1 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("OTH");
        cable.setCode("A");
        relationDefinitionNOTRGBTW.addModel(cable);
        relationDefinitionCover.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60021");
        part.setPrice(BigDecimal.valueOf(4.19));
        part.setImageUrl("cableIandIII.jpg");
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Cable III Mono PVC White 5 m with open end");
        cable.setTranslations(Locale.en, "Cable III Mono PVC White 5 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("OTH");
        cable.setCode("B");
        relationDefinitionNOTRGBTW.addModel(cable);
        relationDefinitionCover.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60021b");
        part.setPrice(BigDecimal.valueOf(20.95));
        part.setImageUrl("cableIandIII.jpg");
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Cable III Mono PVC White 10 m with open end");
        cable.setTranslations(Locale.en, "Cable III Mono PVC White 10 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("OTH");
        cable.setCode("C");
        relationDefinitionNOTRGBTW.addModel(cable);
        relationDefinitionCover.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60021c");
        part.setPrice(BigDecimal.valueOf(41.90));
        part.setImageUrl("cableIandIII.jpg");
        parts.add(part);

        cable = new Cable(new Dimension(null));
        cable.setName("Cable VIII Mono PUR White 1 m with open end");
        cable.setTranslations(Locale.en, "Cable VIII Mono PUR White 1 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("OTH");
        cable.setCode("D");
        relationDefinitionNOTRGBTW.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60018");
        part.setPrice(BigDecimal.valueOf(4.61));
        part.setImageUrl("cableIandIII.jpg");
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Cable VIII Mono PUR White 5 m with open end");
        cable.setTranslations(Locale.en, "Cable VIII Mono PUR White 5 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("OTH");
        cable.setCode("E");
        relationDefinitionNOTRGBTW.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60018b");
        part.setPrice(BigDecimal.valueOf(23.05));
        part.setImageUrl("cableIandIII.jpg");
        parts.add(part);


        cable = new Cable(new Dimension(null));
        cable.setName("Cable VIII Mono PUR White 10 m with open end");
        cable.setTranslations(Locale.en, "Cable VIII Mono PUR White 10 m with open end");
        cable.getProperty(Cable.CABLE_TYPE_S).setValue("OTH");
        cable.setCode("F");
        relationDefinitionNOTRGBTW.addModel(cable);
        relationDefinitionResin.addModel(cable);
        models.add(cable);
        part = new Part(cable);
        part.setId("60018c");
        part.setPrice(BigDecimal.valueOf(46.1));
        part.setImageUrl("cableIandIII.jpg");
        parts.add(part);

        //----- END --NOT FOR RGB OR TW CABLES --------------


        ResinClear resinClear = new ResinClear(null);
        resinClear.setName("liniLED® Helder Ingieten");
        resinClear.setTranslations(Locale.en, "liniLED® Clear Resin");
        resinClear.setCode("C");
        resinClear.setStep(2);
        resinClear.setImageUrl("resinClear.jpg");
        models.add(resinClear);
        relationDefinitionH.addModel(resinClear);
        relationDefinitionL.addModel(resinClear);
        relationDefinitionResin.addModel(resinClear);
        part = new Part(resinClear);
        part.setId("95000");
        part.setDescription("liniLED® L20 Helder Ingieten");
        part.setTranslations(Locale.en, "liniLED® L20 Clear Resin");
        part.setPrice(BigDecimal.valueOf(43.13));
        part.setImageUrl("10824coverClearL30.jpg");
        part.setType("MTR");
        parts.add(part);
        profileL20.getRelation().addRelateTo(part, null);
        //        "95000","Ingieten liniLED® L20 Helder","11.50","MTR","","10"

        ResinDiffuse resinDiffuse = new ResinDiffuse(null);
        resinDiffuse.setName("liniLED® Diffuus Ingieten");
        resinDiffuse.setTranslations(Locale.en, "liniLED® Diffuse Resin");
        resinDiffuse.setCode("D");
        resinDiffuse.setImageUrl("resinDiffuse.jpg");
        models.add(resinDiffuse);

        relationDefinitionL.addModel(resinDiffuse);
        relationDefinitionH.addModel(resinDiffuse);
        relationDefinitionNotDiffuus.addModel(resinDiffuse);
        relationDefinitionDiffuus1.addModel(resinDiffuse);
        relationDefinitionResin.addModel(resinDiffuse);
        part = new Part(resinDiffuse);
        part.setId("95001");
        part.setPrice(BigDecimal.valueOf(43.13));
        part.setDescription("liniLED® L20 Diffuus Ingieten");
        part.setTranslations(Locale.en, "liniLED® L20 Diffuse Resin");
        part.setType("MTR");
        parts.add(part);
        profileL20.getRelation().addRelateTo(part, null);

//        "95001","Ingieten liniLED® L20 Diffuus","11.50","MTR","","10"


        part = new Part(resinClear);
        part.setDescription("liniLED® L30 Helder Ingieten");
        part.setTranslations(Locale.en, "liniLED® L30 Clear Resin");
        part.setPrice(BigDecimal.valueOf(86.25));
        part.setType("MTR");
        part.setId("95003");
        part.setImageUrl("");
        profileL30.getRelation().addRelateTo(part, null);
        parts.add(part);

        //        "95003","Ingieten liniLED® L30 Helder","17.25","MTR","","10"


        part = new Part(resinDiffuse);
        part.setDescription("liniLED® L30 Diffuus Ingieten");
        part.setTranslations(Locale.en, "liniLED® L30 Diffuse Resin");
        part.setId("95004");
        part.setPrice(BigDecimal.valueOf(86.25));
        part.setType("MTR");
        profileL30.getRelation().addRelateTo(part, null);
        parts.add(part);
        //        "95004","Ingieten liniLED® L30 Diffuus","17.25","MTR","","10"


        part = new Part(resinClear);
        part.setPrice(BigDecimal.valueOf(23));
        part.setId("95010");
        part.setDescription("Ingieten liniLED® H20 Helder");
        part.setTranslations(Locale.en, "liniLED® H20 Clear Resin");
        part.setType("MTR");
        part.setPrice(BigDecimal.valueOf(86.25));
        parts.add(part);
        profileH20.getRelation().addRelateTo(part, null);
//        "95010","Ingieten liniLED® H20 Helder","23.00","MTR","","10"


        part = new Part(resinDiffuse);
        part.setPrice(BigDecimal.valueOf(23));
        part.setId("95011");
        part.setDescription("Ingieten liniLED® H20 Diffuus");
        part.setTranslations(Locale.en, "liniLED® H20 Diffuse Resin");
        part.setType("MTR");
        part.setPrice(BigDecimal.valueOf(86.25
        ));
        parts.add(part);
        profileH20.getRelation().addRelateTo(part, null);
        //        "95011","Ingieten liniLED® H20 Diffuus","23.00","MTR","","10"


        part = new Part(resinClear);
        part.setPrice(BigDecimal.valueOf(174.37));
        part.setId("95013");
        part.setDescription("Ingieten liniLED® H30 Helder");
        part.setTranslations(Locale.en, "liniLED® H30 Clear Resin");
        part.setType("MTR");
        parts.add(part);
        profileH30.getRelation().addRelateTo(part, null);
//        "95013","Ingieten liniLED® H30 Helder","46.50","MTR","","10"

        part = new Part(resinDiffuse);
        part.setPrice(BigDecimal.valueOf(174.37));
        part.setId("95014");
        part.setDescription("Ingieten liniLED® H30 Diffuus");
        part.setTranslations(Locale.en, "liniLED® H30 Diffuse Resin");
        part.setType("MTR");
        parts.add(part);
        profileH30.getRelation().addRelateTo(part, null);
//        "95014","Ingieten liniLED® H30 Diffuus","46.50","MTR","","10"


        LedStrip ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("iniLED® PCB Deco rood");
        ledStrip.setTranslations(Locale.en, "iniLED® PCB Red D");
        ledStrip.setMaxDimension(new Dimension(2600));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("rood");
        ledStrip.setTranslations(Locale.en, "liniLED® PCB Red D");
        ledStrip.setCode("DR");

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);
        models.add(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(36.96));
        part.setId("12182");
        part.setDescription("liniLED® PCB Rood D");
        part.setTranslations(Locale.en, "liniLED® PCB Red D");
        part.setType("MTR");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);


        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB Groen Deco");
        ledStrip.setMaxDimension(new Dimension(2600));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("groen");
        ledStrip.setCode("DG");
        ledStrip.setTranslations(Locale.en, "liniLED® PCB Green D");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(48.40));
        part.setId("12183");
        part.setType("MTR");
        part.setDescription("liniLED® PCB Groen D");
        part.setTranslations(Locale.en, "liniLED® PCB Green D");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);
        //"21005","liniLED® PCB Groen D","10.50","MTR","","10"

        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® Deco blauw");
        ledStrip.setMaxDimension(new Dimension(2600));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("blauw");
        ledStrip.setTranslations(Locale.en, "liniLED® PCB Blue D");
        ledStrip.setCode("DB");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(48.4));
        part.setId("21006");
        part.setType("MTR");
        part.setDescription("liniLED® PCB Blauw D");
        part.setTranslations(Locale.en, "liniLED® PCB Blue D");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);
        //"21006","liniLED® PCB Blauw D","10.50","MTR","","10"


        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® Deco amber");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("amber");
        ledStrip.setTranslations(Locale.en, "liniLED® PCB Amber D");
        ledStrip.setCode("DA");

        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(36.96));
        part.setId("12181");
        part.setType("MTR");
        part.setDescription("liniLED® PCB Amber D");
        part.setTranslations(Locale.en, "liniLED® PCB Amber D");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);


        LedStrip ledStripRGB = new RGBLedStrip(new Dimension(null));
        ledStripRGB.setName("liniLED® PCB RGB 160");
        ledStripRGB.setMaxDimension(new Dimension(2600));
        ledStripRGB.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStripRGB.getProperty(LedStrip.COLOR_TYPE).setValue("rgb");
        ledStripRGB.setCode("RD");
        ledStripRGB.setTranslations(Locale.en, "liniLED® PCB RGB 160");
        models.add(ledStripRGB);
        relationDefinitionL.addModel(ledStripRGB);
        relationDefinitionH.addModel(ledStripRGB);
        relationDefinitionRGBCable.addModel(ledStripRGB);

        part = new Part(ledStripRGB);
        part.setPrice(BigDecimal.valueOf(69.52));
        part.setId("12187");
        part.setType("MTR");
        part.setDescription("liniLED® PCB RGB 160");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);


        ledStripRGB = new RGBLedStrip(new Dimension(null));
        ledStripRGB.setName("liniLED® PCB RGB 240");
        ledStripRGB.setMaxDimension(new Dimension(2600));
        ledStripRGB.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStripRGB.getProperty(LedStrip.COLOR_TYPE).setValue("rgb");
        ledStripRGB.setCode("RQ");
        ledStripRGB.setTranslations(Locale.en, "liniLED® PCB RGB 240");
        models.add(ledStripRGB);
        relationDefinitionL.addModel(ledStripRGB);
        relationDefinitionH.addModel(ledStripRGB);
        relationDefinitionRGBCable.addModel(ledStripRGB);

        part = new Part(ledStripRGB);
        part.setPrice(BigDecimal.valueOf(77.95));
        part.setId("12139");
        part.setType("MTR");
        part.setDescription("liniLED® PCB RGB 240");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);

        //"21002","liniLED® PCB RGB D","15.00","MTR","","10"


        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB UWW 2400K D");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("white");
        ledStrip.getProperty(PowerLedStrip.KELVIN_TYPE).setValue("2400");

        ledStrip.setCode("DX");

        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(51.04));
        part.setId("12141a");
        part.setType("MTR");
        part.setDescription("liniLED® PCB UWW 2400K D");
        part.setImageUrl("12197PCB-UWW-2400K-Deco.jpg");

        parts.add(part);


        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB EWW 2700K D");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("white");
        ledStrip.getProperty(PowerLedStrip.KELVIN_TYPE).setValue("2700");

        ledStrip.setCode("DY");

        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(51.04));
        part.setId("12141b");
        part.setType("MTR");
        part.setDescription("liniLED® PCB EWW 2700K D");
        part.setImageUrl("12197PCB-UWW-2400K-Deco.jpg");

        parts.add(part);


        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB WW 3000K D");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("white");
        ledStrip.getProperty(PowerLedStrip.KELVIN_TYPE).setValue("3000");

        ledStrip.setCode("DW");

        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(51.04));
        part.setId("12141");
        part.setType("MTR");
        part.setDescription("liniLED® PCB WW 3000K D");
        part.setImageUrl("12197PCB-UWW-2400K-Deco.jpg");

        parts.add(part);


        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB NW 4000K D");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("white");
        ledStrip.getProperty(PowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("DN");


        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(51.04));
        part.setId("12142");
        part.setType("MTR");
        part.setDescription("liniLED® PCB NW 4000K D");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);

        ledStrip = new DecoLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB KW 6500K D");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(LedStrip.COLOR_TYPE).setValue("white");
        ledStrip.getProperty(PowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("DC");

        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(51.04));
        part.setId("12143");
        part.setType("MTR");
        part.setDescription("liniLED® PCB KW 6500K D");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);


        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB Red P");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(PowerLedStrip.COLOR_TYPE).setValue("rood");
        ledStrip.setTranslations(Locale.en, "liniLED® PCB Red P");
        ledStrip.setCode("PR");

        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(69.52));
        part.setId("12176");
        part.setType("MTR");
        part.setDescription("liniLED® PCB Rood P");
        part.setTranslations(Locale.en, "liniLED® PCB Red P");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);

        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® Power groen");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(PowerLedStrip.COLOR_TYPE).setValue("groen");
        ledStrip.setTranslations(Locale.en, "liniLED® PCB Green P");
        ledStrip.setCode("PG");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(69.52));
        part.setId("12178");
        part.setType("MTR");
        part.setDescription("liniLED® PCB Groen P");
        part.setTranslations(Locale.en, "liniLED® PCB Green P");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);


        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® Power blauw");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.setTranslations(Locale.en, "liniLED® PCB Blue P");
        ledStrip.setCode("PB");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(69.52));
        part.setId("12177");
        part.setType("MTR");
        part.setDescription("liniLED® PCB Blauw P");
        part.setTranslations(Locale.en, "liniLED® PCB Blue P");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);
        //"21019","liniLED® PCB Blauw P","19.00","MTR","","10"

        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® Power amber");
        ledStrip.setTranslations(Locale.en, "liniLED® PCB Amber P");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.setCode("PA");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(69.52));
        part.setId("12179");
        part.setType("MTR");
        part.setDescription("liniLED® PCB Amber P");
        part.setTranslations(Locale.en, "liniLED® PCB Amber P");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);

        //"21021","liniLED® PCB Amber","16.00","MTR","","10"

        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB EWW 2400K P");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("PU");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(63.36));
        part.setId("12198");
        part.setType("MTR");
        part.setDescription("liniLED® PCB UWW 2400K P");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);


        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB EWW 2700K P");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("PE");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(63.36));
        part.setId("12188");
        part.setType("MTR");
        part.setDescription("liniLED® PCB EWW 2700K P");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);

        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB WW 3000K P");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("PW");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(63.36));
        part.setId("12189");
        part.setType("MTR");
        part.setDescription("liniLED® PCB WW 3000K P");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);

        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB NW 4000K P");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("PN");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(63.36));
        part.setId("12190");
        part.setType("MTR");
        part.setDescription("liniLED® PCB NW 4000K P");
        part.setImageUrl("12182-PCB-Red-Deco.jpg");
        parts.add(part);

        ledStrip = new PowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB KW 6500K P");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("PC");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNotDiffuus.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(63.36));
        part.setId("12191");
        part.setType("MTR");
        part.setDescription("liniLED® PCB KW 6500K P");
        parts.add(part);


        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB UWW 2400K HP");
        ledStrip.setMaxDimension(new Dimension(2670));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("HU");

        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12199");
        part.setType("MTR");
        part.setDescription("liniLED® PCB UWW 2400K HP (PSP)");

        parts.add(part);


        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB EWW 2700K HP");
        ledStrip.setMaxDimension(new Dimension(2700));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(200);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("HE");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12192");
        part.setType("MTR");
        part.setDescription("liniLED® PCB EWW 2700K HP (PSP)");
        parts.add(part);


        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB WW 3000K HP");
        ledStrip.setMaxDimension(new Dimension(1000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(50);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("HW");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12193");
        part.setType("MTR");
        part.setDescription("liniLED® PCB WW 3000K HP (PSP)");
        parts.add(part);

        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB NW 4000K HP");
        ledStrip.setMaxDimension(new Dimension(1000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(50);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("HN");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(19));
        part.setId("12194");
        part.setType("MTR");
        part.setDescription("liniLED® PCB NW 4000K HP (PSP)");
        parts.add(part);

        ledStrip = new HighPowerLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB KW 6500K HP");
        ledStrip.setMaxDimension(new Dimension(1000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(50);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("HC");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(19));
        part.setId("12195");
        part.setType("MTR");
        part.setDescription("liniLED® PCB KW 6500K HP (PSP)");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB FW 2000K Photon 1200");
        ledStrip.setMaxDimension(new Dimension(9000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2000");
        ledStrip.setCode("NF");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12147");
        part.setType("MTR");
        part.setDescription("liniLED® PCB FW 2000K Photon 1200");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB FW 2000K Photon 2000");
        ledStrip.setMaxDimension(new Dimension(9000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("QF");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12153");
        part.setType("MTR");
        part.setDescription("liniLED® PCB FW 2000K Photon 2000");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB FW 2000K Photon 3000");
        ledStrip.setMaxDimension(new Dimension(3000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2000");
        ledStrip.setCode("WF");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12159");
        part.setType("MTR");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB FW 2000K Photon 4000");
        ledStrip.setMaxDimension(new Dimension(3000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2000");
        ledStrip.setCode("XF");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(179.38));
        part.setId("12165");
        part.setType("MTR");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB UWW 2400K Photon 1200");
        ledStrip.setMaxDimension(new Dimension(9000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("NU");

        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12148");
        part.setType("MTR");
        part.setDescription("liniLED® PCB UWW 2400K Photon 1200");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB UWW 2400K Photon 2000");
        ledStrip.setMaxDimension(new Dimension(4500));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("QU");
        models.add(ledStrip);
        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);
        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12154");
        part.setType("MTR");
        part.setDescription("liniLED® PCB UWW 2400K Photon 2000");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB UWW 2400K Photon 3000");
        ledStrip.setMaxDimension(new Dimension(3000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("WU");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(146.48));
        part.setId("12160");
        part.setType("MTR");
        part.setDescription("liniLED® PCB UWW 2400K Photon 3000");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);

        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB UWW 2400K Photon 4000");
        ledStrip.setMaxDimension(new Dimension(3000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("XU");

        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(179.38));
        part.setId("12166");
        part.setType("MTR");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        part.setDescription("liniLED® PCB UWW 2400K Photon 4000");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB EWW 2700K Photon 1200");
        ledStrip.setMaxDimension(new Dimension(9000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("NE");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12149");
        part.setType("MTR");
        part.setDescription("liniLED® PCB EWW 2700K Photon 1200");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB EWW 2700K Photon 2000");
        ledStrip.setMaxDimension(new Dimension(4500));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("QE");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12155");
        part.setType("MTR");
        part.setDescription("liniLED® PCB EWW 2700K Photon 2000");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB EWW 2700K Photon 3000");
        ledStrip.setMaxDimension(new Dimension(3000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("WE");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(146.48));
        part.setId("12161");
        part.setType("MTR");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        part.setDescription("liniLED® PCB EWW 2700K Photon 3000");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB EWW 2700K Photon 4000");
        ledStrip.setMaxDimension(new Dimension(3000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2400");
        ledStrip.setCode("XE");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(179.38));
        part.setId("12167");
        part.setType("MTR");
        part.setDescription("liniLED® PCB EWW 2700K Photon 4000");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB WW 3000K Photon 1200");
        ledStrip.setMaxDimension(new Dimension(9000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("NW");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12150");
        part.setType("MTR");
        part.setDescription("liniLED® PCB WW 3000K Photon 1200");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB WW 3000K Photon 2000");
        ledStrip.setMaxDimension(new Dimension(4500));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("QW");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12156");
        part.setType("MTR");
        part.setDescription("liniLED® PCB WW 3000K Photon 2000");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB WW 3000K Photon 3000");
        ledStrip.setMaxDimension(new Dimension(3000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("WW");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(146.48));
        part.setId("12162");
        part.setType("MTR");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB WW 3000K Photon 4000");
        ledStrip.setMaxDimension(new Dimension(3000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("3000");
        ledStrip.setCode("XW");

        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(179.38));
        part.setId("12168");
        part.setType("MTR");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB NW 4000K Photon 1200");
        ledStrip.setMaxDimension(new Dimension(9000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("NN");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12151");
        part.setType("MTR");
        part.setDescription("liniLED® PCB NW 4000K Photon 1200");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB NW 4000K Photon 2000");
        ledStrip.setMaxDimension(new Dimension(4500));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("QN");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12157");
        part.setType("MTR");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB NW 4000K Photon 3000");
        ledStrip.setMaxDimension(new Dimension(3000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("WN");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(146.48));
        part.setId("12163");
        part.setType("MTR");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB NW 4000K Photon 4000");
        ledStrip.setMaxDimension(new Dimension(3000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("XN");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(179.38));
        part.setId("12169");
        part.setType("MTR");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB CW 6500K Photon 1200");
        ledStrip.setMaxDimension(new Dimension(900));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("NC");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(127.77));
        part.setId("12152");
        part.setType("MTR");
        part.setDescription("liniLED® PCB CW 6500K Photon 1200");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB CW 6500K Photon 2000");
        ledStrip.setMaxDimension(new Dimension(4500));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("4000");
        ledStrip.setCode("QC");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(130.56));
        part.setId("12158");
        part.setType("MTR");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB CW 6500K Photon 3000");
        ledStrip.setMaxDimension(new Dimension(3000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("WC");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(146.48));
        part.setId("12164");
        part.setType("MTR");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new PhotonLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB CW 6500K Photon 4000");
        ledStrip.setMaxDimension(new Dimension(3000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(100);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("6500");
        ledStrip.setCode("XC");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNOTRGBTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(179.38));
        part.setId("12170");
        part.setType("MTR");
        part.setImageUrl("liniLED-PCB-Photon.jpg");
        parts.add(part);


        ledStrip = new TunnableLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB TW 2700K-6500K 1200");
        ledStrip.setMaxDimension(new Dimension(9000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(750);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("NZ");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionTWCable.addModel(ledStrip);
        relationDefinitionNotDiffuusTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(139.70));
        part.setId("12173");
        part.setTranslations(Locale.en, "liniLED® PCB TW 2700K-6500K 1200");
        part.setType("MTR");
        part.setImageUrl("liniLEDTW.jpg");
        parts.add(part);


        ledStrip = new TunnableLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB TW 2700K-6500K 1700");
        ledStrip.setMaxDimension(new Dimension(4500));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(750);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("QZ");
        models.add(ledStrip);

        relationDefinitionTWCable.addModel(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionNotDiffuusTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(152.49));
        part.setId("12144");
        part.setType("MTR");
        part.setImageUrl("liniLEDTW.jpg");
        part.setTranslations(Locale.en, "liniLED® PCB TW 2700K-6500K 1700");
        parts.add(part);

        ledStrip = new TunnableLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB TW 2700K-6500K 2500");
        ledStrip.setMaxDimension(new Dimension(3000));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(750);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("WZ");

        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionTWCable.addModel(ledStrip);
        relationDefinitionNotDiffuusTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(195.83));
        part.setId("12145");
        part.setType("MTR");
        part.setImageUrl("liniLEDTW.jpg");
        parts.add(part);

        ledStrip = new TunnableLedStrip(new Dimension(null));
        ledStrip.setName("liniLED® PCB TW 2700K-6500K 3300");
        ledStrip.setMaxDimension(new Dimension(2100));
        ledStrip.getProperty(LedStrip.SECTION_WIDTH).setValue(750);
        ledStrip.getProperty(HighPowerLedStrip.KELVIN_TYPE).setValue("2700");
        ledStrip.setCode("XZ");
        models.add(ledStrip);

        relationDefinitionL.addModel(ledStrip);
        relationDefinitionH.addModel(ledStrip);

        relationDefinitionTWCable.addModel(ledStrip);
        relationDefinitionNotDiffuusTW.addModel(ledStrip);

        part = new Part(ledStrip);
        part.setPrice(BigDecimal.valueOf(219.73));
        part.setId("12146");
        part.setType("MTR");
        part.setImageUrl("liniLEDTW.jpg");
        parts.add(part);


        part = new Part(null);
        part.setPrice(BigDecimal.valueOf(4.71));
        part.setId("60000");
        part.setType("ST");
        part.setDescription("Dubbelz Tape 6mm tbv PCB (l=50m)");
        parts.add(part);


        Clip clip = new Clip();
        clip.setName("Mounting Clip");
        clip.setCode("a");

        models.add(clip);
        part = new Part(clip);
        part.setId("10890");
        part.setPrice(BigDecimal.valueOf(1.86));
        part.setDescription("Clip 20");
        part.setImageUrl("10891clip30.jpg");
        parts.add(part);


        part = new Part(clip);
        part.setId("10891");
        part.setPrice(BigDecimal.valueOf(1.86));
        part.setDescription("Clip 30");
        part.setImageUrl("10891clip30.jpg");
        parts.add(part);


        part = new NotExistingPart(clip);
        part.setId("clip");
        part.setDescription("Clip.");
        parts.add(part);

        Part arbeid = new Part();
        arbeid.setId("ARBEID");
        arbeid.setDescription("Arbeidsminuut intern magazijn");
        arbeid.setPrice(BigDecimal.valueOf(0.59));
        arbeid.setType("MINUUT");
        parts.add(arbeid);

        Part tule = new Part();
        tule.setId("GRIJP_KTULE_EINDKAP");
        tule.setDescription("Kabeltule t.b.v. eindkap");
        tule.setPrice(BigDecimal.valueOf(0.07));
        tule.setType("ST");
        parts.add(tule);

        tule = new Part();
        tule.setId("GRIJP_KTULE_ALU_ZWART");
        tule.setDescription("Kabeltule t.b.v. ALU zwart");
        tule.setPrice(BigDecimal.valueOf(0.07));
        tule.setType("ST");
        parts.add(tule);

        tule = new Part();
        tule.setId("GRIJP_KTULE_ALU_WIT");
        tule.setDescription("Kabeltule t.b.v. ALU wit");
        tule.setPrice(BigDecimal.valueOf(0.11));
        tule.setType("ST");
        parts.add(tule);

        Part sticker = new Part();
        sticker.setId("GRIJP_PSTICKER");
        sticker.setDescription("Productsticker");
        sticker.setPrice(BigDecimal.valueOf(0.25));
        sticker.setType("ST");
        parts.add(sticker);

        Part handleiding = new Part();
        handleiding.setId("HANDLEIDING");
        handleiding.setDescription("Handleiding");
        handleiding.setPrice(BigDecimal.valueOf(0.70));
        handleiding.setType("ST");
        parts.add(handleiding);
        ParserDataResult result = new ParserDataResult(models, relationDefinitions, parts);
        return result;

    }

    public ParserDataResult execute() {
        return createParts();

    }
}

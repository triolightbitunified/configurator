/** About aeris.js

 **

 ** This document contains the liniLED® Aeris Configuration tool. This tool is intended to be used

 ** by Triolight B.V. internally for the purpose of checking configurations eiher given by

 ** customers or created via the steps in the manual. After entering a (valid) configuration, the

 ** tool will display the selected steps and options and inform the user with the cost of the

 ** selected profile.

 **

 ** This tool has been writen in javascript and runs on jQuery for most of the DOM-edditing actions.

 ** Besides this tool used jGrid and jAlert for some extra functionality and is written to be used

 ** within the Triolight Intranet.

 **

 ** The whole code has been written with command statements after each line to explain the code

 ** globaly. To understand the global working og this tool, please take notice of the following:

 **

 **   On startup

 **     The tool registeres on the 'pageLoaderComplete' event. This event is triggered by the

 **     Triolight Intranet once the page has been loaded. On this event, the function

 **     'fnBindInterface()' is called.

 **

 **   fnBindInterface()

 **     This function prepares the DOM for running this tool. After this 'fnReset()' is called.

 **

 **   fnReset()

 **     This function resets the content of the page and starts the Aris Configuration Tool. During

 **     this function, multiple event-handlers are linked to objects on the page in order to track

 **     the actions of the user. The most important event-handler is on the input field (where the

 **     user enteres the configuration) on which the function 'fnProcessConfiguration()' is linked

 **     to be called on submitting a new configuration.

 **

 **   fnProcessConfiguration()

 **     This function is called on entering a new configuration. The function will load the entered

 **     code and tries to validate this code by using the function 'fnValidateConfiguration()'. If

 **     a valid code was entered and the validation function returns a valid configuration object,

 **     the content of this object is displayed by the function. If the configuration was not

 **     valid, this function will show an error message.

 **

 **   fnValidateConfiguration()

 **     This function will run a quick check on the entered configuration code and will determ the

 **     first character of the entered configuration. Based on this character, a dedicated

 **     validation function will be called.

 **       A:      - error message, this is a old type and is not supported -

 **       B:      this wil rund the function 'fnValidateConfiguration_typeB()'

 **       others: - error message, no support for this type of code -

 **

 **   fnValidateConfiguration_typeB()

 **     liniLED® Aeris Casted Profile (UP67)

 **     This function will create a configuration based on the entered code. First the function will

 **     try to read the entered configuration code and determine the selected options from a local

 **     defined array of options ('CONFIGURATIONS' and 'ACCESSORIES'). After this, the function will

 **     check for combinations that are not allowed or raise a warning.

 **

 **   PARTS (array)

 **     This array contains all parts that are used by any configuration in this file. In this

 **     array, a part is added with its reference as index. The part itself is a object with the

 **     following information:

 **       code  The article number of the part (UNIT4) as string

 **       name  The dutch describtion of the part (UNIT4) as string

 **       VVP   The price (VVP in UNIT4) as float

 **       type  The type/unit the product is registered as string (for example ST or M)

 **

 **/



$(document).ready(function () {

    /* Globals */

    var helpProviderPath = [];                                                                      // Array with state of the program

    var myInterface = null;                                                                         // Direct link to interface object

    var VVP2BVPfactor = 5.3;                                                                        // Conversion factor between VVP and BVP


    /* List of all used articles */

    var PARTS = [];

    PARTS[10713] = {code: "10713", name: "liniLED<sup>&reg</sup> Aeris Profiel L20 4 m", VVP: 6.81, type: "ST"};

    PARTS[10717] = {code: "10717", name: "liniLED<sup>&reg</sup> Aeris Profiel H20 4 m", VVP: 11.96, type: "ST"};

    PARTS[10733] = {code: "10733", name: "liniLED<sup>&reg</sup> Aeris Profiel L30 4 m", VVP: 9.30, type: "ST"};

    PARTS[10737] = {code: "10737", name: "liniLED<sup>&reg</sup> Aeris Profiel H30 4 m", VVP: 14.47, type: "ST"};

    PARTS[10750] = {code: "10750", name: "liniLED<sup>&reg</sup> Aeris Kabelgoot 20 1 m", VVP: 1.36, type: "ST"};
    /** BVP is calculated with default factor **/

    PARTS[10751] = {code: "10751", name: "liniLED<sup>&reg</sup> Aeris Kabelgoot 20 2 m", VVP: 2.71, type: "ST"};
    /** BVP is calculated with default factor **/

    PARTS[10752] = {code: "10752", name: "liniLED<sup>&reg</sup> Aeris Kabelgoot 20 3 m", VVP: 4.07, type: "ST"};
    /** BVP is calculated with default factor **/

    PARTS[10753] = {code: "10753", name: "liniLED<sup>&reg</sup> Aeris Kabelgoot 20 4 m", VVP: 5.42, type: "ST"};
    /** BVP is calculated with default factor **/

    PARTS[10754] = {code: "10754", name: "liniLED<sup>&reg</sup> Aeris Kabelgoot 30 1 m", VVP: 1.73, type: "ST"};
    /** BVP is calculated with default factor **/

    PARTS[10755] = {code: "10755", name: "liniLED<sup>&reg</sup> Aeris Kabelgoot 30 2 m", VVP: 3.45, type: "ST"};
    /** BVP is calculated with default factor **/

    PARTS[10756] = {code: "10756", name: "liniLED<sup>&reg</sup> Aeris Kabelgoot 30 3 m", VVP: 5.18, type: "ST"};
    /** BVP is calculated with default factor **/

    PARTS[10757] = {code: "10757", name: "liniLED<sup>&reg</sup> Aeris Kabelgoot 30 4 m", VVP: 6.90, type: "ST"};
    /** BVP is calculated with default factor **/

    PARTS[10890] = {code: "10890", name: "liniLED<sup>&reg</sup> Aeris Clip 20", VVP: 0.35, type: "ST"};
    /** BVP is calculated with default factor **/

    PARTS[10891] = {code: "10891", name: "liniLED<sup>&reg</sup> Aeris Clip 30", VVP: 0.35, type: "ST"};
    /** BVP is calculated with default factor **/

    PARTS[10900] = {code: "10900", name: "liniLED<sup>&reg</sup> Aeris Eindkap L20", VVP: 1.48, type: "ST"};

    PARTS[10901] = {code: "10901", name: "liniLED<sup>&reg</sup> Aeris Eindkap L20 O", VVP: 1.51, type: "ST"};

    PARTS[10902] = {code: "10902", name: "liniLED<sup>&reg</sup> Aeris Eindkap LC20", VVP: 1.55, type: "ST"};

    PARTS[10903] = {code: "10903", name: "liniLED<sup>&reg</sup> Aeris Eindkap LC20 O", VVP: 1.57, type: "ST"};

    PARTS[10904] = {code: "10904", name: "liniLED<sup>&reg</sup> Aeris Eindkap H20", VVP: 1.61, type: "ST"};
    /** Price information missing, worst case bet is equal to 10905 **/

    PARTS[10905] = {code: "10905", name: "liniLED<sup>&reg</sup> Aeris Eindkap H20 O", VVP: 1.61, type: "ST"};

    PARTS[10906] = {code: "10906", name: "liniLED<sup>&reg</sup> Aeris Eindkap HC20", VVP: 1.63, type: "ST"};
    /** Price information missing, worst case bet is equal to 10907 **/

    PARTS[10907] = {code: "10907", name: "liniLED<sup>&reg</sup> Aeris Eindkap HC20 O", VVP: 1.63, type: "ST"};

    PARTS[10908] = {code: "10908", name: "liniLED<sup>&reg</sup> Aeris Eindkap C20", VVP: 1.44, type: "ST"};

    PARTS[10920] = {code: "10920", name: "liniLED<sup>&reg</sup> Aeris Eindkap L30", VVP: 1.51, type: "ST"};

    PARTS[10921] = {code: "10921", name: "liniLED<sup>&reg</sup> Aeris Eindkap L30 O", VVP: 1.53, type: "ST"};

    PARTS[10922] = {code: "10922", name: "liniLED<sup>&reg</sup> Aeris Eindkap LC30", VVP: 1.59, type: "ST"};

    PARTS[10923] = {code: "10923", name: "liniLED<sup>&reg</sup> Aeris Eindkap LC30 O", VVP: 1.61, type: "ST"};

    PARTS[10924] = {code: "10924", name: "liniLED<sup>&reg</sup> Aeris Eindkap H30", VVP: 1.59, type: "ST"};

    PARTS[10925] = {code: "10925", name: "liniLED<sup>&reg</sup> Aeris Eindkap H30 O", VVP: 1.61, type: "ST"};

    PARTS[10926] = {code: "10926", name: "liniLED<sup>&reg</sup> Aeris Eindkap HC30", VVP: 1.61, type: "ST"};

    PARTS[10927] = {code: "10927", name: "liniLED<sup>&reg</sup> Aeris Eindkap HC30 O", VVP: 1.63, type: "ST"};

    PARTS[10928] = {code: "10928", name: "liniLED<sup>&reg</sup> Aeris Eindkap C30", VVP: 1.51, type: "ST"};

    PARTS[11214] = {code: "11214", name: "liniLED<sup>&reg</sup> Aansluitkabel Demo", VVP: 2.64, type: "MTR"};

    PARTS[21002] = {code: "21002", name: "liniLED<sup>&reg</sup> PCB RGB D", VVP: 15.00, type: "MTR"};

    PARTS[21004] = {code: "21004", name: "liniLED<sup>&reg</sup> PCB Rood D", VVP: 7.80, type: "MTR"};

    PARTS[21005] = {code: "21005", name: "liniLED<sup>&reg</sup> PCB Groen D", VVP: 10.50, type: "MTR"};

    PARTS[21006] = {code: "21006", name: "liniLED<sup>&reg</sup> PCB Blauw D", VVP: 10.50, type: "MTR"};

    PARTS[21003] = {code: "21003", name: "liniLED<sup>&reg</sup> PCB Amber D", VVP: 7.80, type: "MTR"};

    PARTS[21017] = {code: "21017", name: "liniLED<sup>&reg</sup> PCB RGB P", VVP: 38.60, type: "MTR"};

    PARTS[21018] = {code: "21018", name: "liniLED<sup>&reg</sup> PCB Rood P", VVP: 16.00, type: "MTR"};

    PARTS[21020] = {code: "21020", name: "liniLED<sup>&reg</sup> PCB Groen P", VVP: 19.00, type: "MTR"};

    PARTS[21019] = {code: "21019", name: "liniLED<sup>&reg</sup> PCB Blauw P", VVP: 19.00, type: "MTR"};

    PARTS[21021] = {code: "21021", name: "liniLED<sup>&reg</sup> PCB Amber", VVP: 16.00, type: "MTR"};

    PARTS['21032A'] = {code: "21032A", name: "liniLED<sup>&reg</sup> PCB UWW 2400K HP (PSP)", VVP: 19.00, type: "MTR"};

    PARTS['21013A'] = {code: "21013A", name: "liniLED<sup>&reg</sup> PCB EWW 2700K HP (PSP)", VVP: 19.00, type: "MTR"};

    PARTS['21014A'] = {code: "21014A", name: "liniLED<sup>&reg</sup> PCB WW 3000K HP (PSP)", VVP: 19.00, type: "MTR"};

    PARTS['21015A'] = {code: "21015A", name: "liniLED<sup>&reg</sup> PCB NW 4000K HP (PSP)", VVP: 19.00, type: "MTR"};

    PARTS['21016A'] = {code: "21016A", name: "liniLED<sup>&reg</sup> PCB KW 6500K HP (PSP)", VVP: 19.00, type: "MTR"};

    PARTS[60000] = {code: "60000", name: "Dubbelz Tape 6mm tbv PCB (l=50m)", VVP: 4.71, type: "ST"};

    PARTS[60004] = {code: "60004", name: "Kabel Mono Wit", VVP: 0.27, type: "MTR"};

    PARTS[60005] = {code: "60005", name: "Kabel RGB Wit", VVP: 0.31, type: "MTR"};

    PARTS[60006] = {code: "60006", name: "Kabel Mono PUR", VVP: 0.78, type: "MTR"};

    PARTS[60007] = {code: "60007", name: "Kabel RGB PUR", VVP: 0.90, type: "MTR"};

    PARTS[60010] = {code: "60010", name: "Kabel Mono PUR M12 Male 1 m", VVP: 5.42, type: "ST"};

    PARTS[60011] = {code: "60011", name: "Kabel Mono PUR M12 Male 5 m", VVP: 8.92, type: "ST"};

    PARTS[60012] = {code: "60012", name: "Kabel Mono PUR M12 Male 10 m", VVP: 13.29, type: "ST"};

    PARTS[60013] = {code: "60013", name: "Kabel RGB PUR M12 Male 1 m", VVP: 6.63, type: "ST"};

    PARTS[60014] = {code: "60014", name: "Kabel RGB PUR M12 Male 5 m", VVP: 10.41, type: "ST"};

    PARTS[60015] = {code: "60015", name: "Kabel RGB PUR M12 Male 10 m", VVP: 15.68, type: "ST"};

    PARTS[95000] = {code: "95000", name: "Ingieten liniLED<sup>&reg</sup> L20 Helder", VVP: 11.50, type: "MTR"};

    PARTS[95001] = {code: "95001", name: "Ingieten liniLED<sup>&reg</sup> L20 Diffuus", VVP: 11.50, type: "MTR"};

    PARTS[95003] = {code: "95003", name: "Ingieten liniLED<sup>&reg</sup> L30 Helder", VVP: 17.25, type: "MTR"};
    /** Price information missing, educated ques based on 95000 **/

    PARTS[95004] = {code: "95004", name: "Ingieten liniLED<sup>&reg</sup> L30 Diffuus", VVP: 17.25, type: "MTR"};
    /** Price information missing, educated ques based on 95001 **/

    PARTS[95010] = {code: "95010", name: "Ingieten liniLED<sup>&reg</sup> H20 Helder", VVP: 23.00, type: "MTR"};
    /** Price information missing, educated ques based on 95000 **/

    PARTS[95011] = {code: "95011", name: "Ingieten liniLED<sup>&reg</sup> H20 Diffuus", VVP: 23.00, type: "MTR"};
    /** Price information missing, educated ques based on 95001 **/

    PARTS[95013] = {code: "95013", name: "Ingieten liniLED<sup>&reg</sup> H30 Helder", VVP: 46.50, type: "MTR"};
    /** Price information missing, educated ques based on 95000 **/

    PARTS[95014] = {code: "95014", name: "Ingieten liniLED<sup>&reg</sup> H30 Diffuus", VVP: 46.50, type: "MTR"};
    /** Price information missing, educated ques based on 95001 **/

    PARTS['ARBEID'] = {code: "ARBEID-MIN", name: "Arbeidsminuut intern magazijn", VVP: 0.59, type: "MINUUT"};

    PARTS['GRIJP0'] = {code: "GRIJP", name: "Kabeltule t.b.v. eindkap", VVP: 0.07, type: "ST"};

    PARTS['GRIJP1'] = {code: "GRIJP", name: "Kabeltule t.b.v. ALU zwart", VVP: 0.07, type: "ST"};

    PARTS['GRIJP2'] = {code: "GRIJP", name: "Kabeltule t.b.v. ALU wit", VVP: 0.11, type: "ST"};

    PARTS['GRIJP3'] = {code: "GRIJP", name: "Productsticker", VVP: 0.25, type: "ST"};

    PARTS['GRIJP4'] = {code: "GRIJP", name: "Handleiding", VVP: 0.70, type: "ST"};

    PARTS['GRIJP5'] = {code: "GRIJP", name: "Verpakking (per meter)", VVP: 0.50, type: "ST"};


    /* Functions */

    function fnFormatEuro(c) {

        /** This function returns the passed value in a formated string as follows: € #,00 */

        if (!$.isNumeric(c))                                                                         // Test if the input is numeric

            return "&ndash;";                                                                       // Return dash sign for non numeric

        c = c + '';                                                                                 // Transform to string

        c = c.replace('.', ',');                                                                    // Replace decimal points

        var i = c.indexOf(',');                                                                     // Find location of decimal point

        if (i < 0)                                                                                   // Test if no decimal point was found

            c += ",00";                                                                             // No decimal point found, add decimals

        else                                                                                        // decimal point found

            while (c.length - i < 3)                                                                 // Loop based on found decimal location

                c += "0";                                                                           // Add '0' to the end of the string

        i = c.indexOf(',');                                                                         // Find location of decimal point

        while (i > 3) {                                                                               // Loop while 4 or more digits remain

            c = c.substr(0, i - 3) + '.' + c.substr(i - 3);                                         // Add point

            i = c.indexOf('.');                                                                     // Find first location of point

        }

        return "<div class=\"currencyLeft\">&euro; </div>" + c.substr(0, c.indexOf(',') + 3);       // Return formated string

    }

    function fnFormatAmounth(a, u) {

        /** This function returns the passed value in a formated string folowed by the set unit as follows: #,00 UNIT */

        if (!$.isNumeric(a))                                                                         // Test if the input is numeric

            return "&ndash;";                                                                       // Return dash sign for non numeric

        a = a + '';                                                                                 // Transform to string

        a = a.replace('.', ',');                                                                    // Replace decimal points

        var i = a.indexOf(',');                                                                     // Find location of decimal point

        if (i < 0)                                                                                   // Test if no decimal point was found

            return "&nbsp;" + a + ",00&nbsp;" + u + "&nbsp;";                                       // Nodecimal point found, return value

        while (a.length - i < 3)                                                                     // Loop based on found decimal location

            a += "0";                                                                               // Add '0' to the end of the string

        return "&nbsp;" + a.substr(0, i + 3) + "&nbsp;" + u + "&nbsp;";                             // Return formated string

    }

    function fnHelpProvider() {

        /** Indirect event handler, this function is registered by the function 'fnBindInterface'

         * to the helpProvider object. Every time the user triggers the help event of this object

         * this function is called.                                                             */

        var currentHelpPath = null;                                                                 // Preset current help path to null

        try {

            if (helpProviderPath.length > 0)                                                         // Test length of array

                currentHelpPath = helpProviderPath[helpProviderPath.length - 1];                        // Copy last item of the array

        } catch (e) {
        }                                                                                 // Create catch for save scripting

        switch (currentHelpPath) {                                                                    // Get help for correct item

            case null:

                /* Empty array - start page */

                $.helpProvider.messageBox(
                    "Digitale liniLED<sup>&reg;</sup> Aeris configurator. " +

                    "Volg de stappen zoals deze zijn aangegeven of vul een artikelnummer in om een geldige configuratie up te bouwen. " +

                    "Zodra dit is gedaan zal er extra informatie worden weergegeven en komen er extra functies beschikbaar.",

                    "Aeris configurator");                                                          // Show default message

                return;

            default:

                /* Unknown item */

                $.helpProvider.messageBox(
                    "Helaas, er is geen extra informatie beschikbaar voor dit item.",

                    "Aeris configurator");                                                          // Show message

                return;

        }

    }

    function fnBindInterface() {

        /** Event handler for 'pageLoaderComplete', this event is fired by the pageLoader script

         * once the page is successfully loaded. This function removes any interface containers

         * and creates a new interface container from the first found container that has the ID

         * 'aeris_scriptTarget'. Also the local helpProvider function is linked to the global

         * helpProvider ofject. At last the function 'fnReset' is called to initialize the user

         * interface.                                                                           */

        $.helpProvider.register(fnHelpProvider);                                                    // Register helpProvider

        $("div#APP_aeris_UI").remove();                                                             // Remove old interfaces

        $("div#aeris_scriptTarget").first().each(function () {                                        // Get first container

            myInterface = $(this);                                                                  // Load jQuery object link

            myInterface.attr('ID', "APP_aeris_UI");                                                 // Rename container as UI

        });

        fnReset();                                                                                  // Call UI reset function

    }

    function fnReset() {

        /** This function is used to reset the userinterface and to (re)load the interface.      */

        helpProviderContext = [];                                                                   // (Re)set to blank array

        myInterface.addClass("boxed");                                                              // Add class 'boxed' to interface

        myInterface.html(
            "<fieldset>" +

            "<p>" +

            "Gebruik het onderstaande veld om een artikelnummer/configuratie in te vullen, gebruik het stappenplan uit de catalogus om zelf een nummer samen te stellen. " +

            "Na het invullen van een geldig nummer zullen er verschillende gegevens worden berekend en kunnen de knoppen onder het veld worden gebruikt voor het aanroepen van extra functies." +

            "</p>" +

            "<form id=\"APP_aeris_inputform\" action=\"\" method=\"post\">" +

            "<div class=\"inputContainer\">" +

            "<label for=\"configuration\">Configuratie:</label>" +

            "<input type=\"text\" name=\"configuration\" id=\"configuration\" placeholder=\"Typ hier het nummer...\" value=\"\">" +

            "<span id=\"configurationError\" class=\"error\"></span>" +

            "</div>" +

            "</form>" +

            "</fieldset>" +

            "<div id=\"result\">" +

            "<div class=\"boxedWithTitleLeft\">" +

            "<span>Configuratie info:&nbsp;<span id=\"VerifiedConfig\"></span></span>" +

            "<div>" +

            "<div id=\"ConfigInfo\">" +

            "</div>" +

            "<ul>" +

            "<li id=\"BVP\">Bruto verkoopprijs: <span id=\"BVPvalue\">&ndash;</span></li>" +

            "<li id=\"UNIT4\">Informatie voor invoer in UNIT4.</li>" +

            "<li id=\"Assembly\">Assemblage instructies.</li>" +

            "</ul>" +

            "</div>" +

            "</div>" +

            "<div id=\"complexProduct\"><table id=\"ComplexConfigInfo\"></table></div>" +

            "<div style=\"clear:both;\"></div>" +

            "</div>"
        );                                                                                      // Toevoegen van HTML aan de UI

        $("#ComplexConfigInfo").jqGrid({                                                            // Create jqGrid

            regional: 'nl',                                                                        // Set dutch

            datatype: "local",                                                                      // Use local data

            data: [],                                                                               // No data loaded

            height: 100,                                                                            // Set height in px

            colNames: ['Product', 'Omschrijving', '&agrave; (bruto)', 'Aantal', 'Totaal'],           // Set headings for the grid

            colModel: [                                                                              // Load grid parameters

                {name: 'code', index: 'code', width: 110, align: "center", key: true},

                {name: 'name', index: 'name', width: 300, align: "left"},

                {name: 'bvp', index: 'bvp', width: 75, align: "right"},

                {name: 'count', index: 'count', width: 75, align: "center"},

                {name: 'total', index: 'total', width: 75, align: "right"}

            ],

            sortable: false,                                                                        // Do not allow sorting

            footerrow: true,                                                                        // Enable footer row

            userDataOnFooter: true,                                                                 // Use custom footer row

            loadonce: true,                                                                         // Do not reload data

            caption: "Offerte en/of order informatie:"                                              // Set grid caption

        });

        myInterface.find("#APP_aeris_inputform input").keypress(function (e) {                        // Add event handler on keypress in form

            myInterface.find("#APP_aeris_inputform input#configuration").removeClass("valid invalid");// Remove classes since the object was edited

            if (e.which == 13) {                                                                      // Test for return key

                e.preventDefault();                                                                 // Prevent default event handler

                $("#APP_aeris_inputform").submit();                                                 // Submit the form

                return false;                                                                       // Return false to stop the execution of the event handler

            }

        });

        myInterface.find("#APP_aeris_inputform").submit(function (e) {                                // Add event handler on the form submission

            e.preventDefault();                                                                     // Prevent default event handler

            fnProcessConfiguration();                                                               // Call process pfunction

        });

        myInterface.find("input#configuration").select().focus();                                   // Select input form

    }

    function fnCreateBlankConfiguration() {

        /** This function returns a new and blank configuration object.                           */

        return {                                                                                    // Create a new and blank configuration

            isValid: false,                                                                     // Empty configuration is not valid

            errMsg: "Leeg object geladen...",                                                  // Error message informing the configuratio is empty

            setErrorMsg: function (msg) {                                                             // Add shortcut function for setting error message

                this.errMsg = msg;                                                                  // Set error message

                return this;                                                                        // Return original object

            },

            warning: "",                                                                       // No warning message

            info: "Geen informatie geladen...",                                             // No information

            complex: false,                                                                    // Not complex by default

            artNmb: '',                                                                       // No article number

            artNmbShort: '',                                                                       // No short article number

            steps: [false, false, false, false, false, false, false, false],                 // All configuration steps are empty

            accessories: [],                                                                       // Empty accessory array

            totalLength: 0,                                                                        // Calculated total lengt is zero

            symmetric: false,                                                                    // Profile is not symetric by default (may also be true, will be calculated later on)

            UNIT4: fnCreateBlankUNIT4(),                                                     // Load new UNIT4 object

            AssemblyInfo: null,                                                                     // No assembly info for new object

        };

    }

    function fnCreateBlankUNIT4() {

        /** This function returns a new and blank UNIT4 object.                                   */

        return {                                                                                    // Create a new and blank configuration

            isValid: false,                                                                    // Empty object is not valid

            Prod: "",                                                                       // No product code

            Desc: "",                                                                       // No product description

            Zoek: "AERIS",                                                                  // Default serach name

            Omschrijving: {                                                                         // Long descriptions by language

                DE: "",                                                                             // German

                EN: "",                                                                             // Englisch

                FR: "",                                                                             // French

                NL: "",                                                                             // Dutch

            },

            VVP: false,                                                                    // VVP value

            BVP: false,                                                                    // BVP value

            partlist: [],                                                                       // Empty part list

            accessories: [],                                                                       // Empty accessories list

        };

    }

    function fnValidateConfiguration(input) {

        /** This function creates and validates a configuration from the givven input.

         * @param input (string) The input to translate into a configuration.

         */


        /* Set up new configuration and prepare data */

        var newConfig = fnCreateBlankConfiguration();                                               // Create a new and blank configuration object

        if (input === undefined)                                                                     // Test if input has been received

            return newConfig.setErrorMsg("Fout tijdens het valideren van de invoer, geen invoer ontvangen");// No input received, return an error message

        input = input.replace(/\s+/g, '').toUpperCase();                                            // Convert all input characters to higher case

        if (input.Length < 1)                                                                        // Test if at least the first character is entered

            return newConfig.setErrorMsg("Fout tijdens het valideren van de invoer, lege invoer ontvangen");// Blank input received, return an error message


        /* Apply switch for configuration type */

        switch (input[0]) {                                                                           // Apply switch on the first character in the input

            case 'A':                                                                               // Handle 'A'-type configuration (old line-profiles)

                return newConfig.setErrorMsg("Er is geen ondersteuning beschikbaar voor de oude A-nummers!");// Non-supported type entered, return an error message

            case 'B':                                                                               // Handle 'B'-type configuration (casted profiles)

                return fnValidateConfiguration_typeB(input, newConfig);                             // Call specific validator for this configuration type

            default:                                                                                // Handle 'B'-type configuration (casted profiles)

                return newConfig.setErrorMsg("Er is geen ondersteuning beschikbaar voor configuraties die beginnen met een '" + input[0] + "'");// Non-supported type entered, return an error message

        }

        return newConfig;                                                                           // Program will not get here, just to be sure...

    }

    function fnValidateConfiguration_typeB(input, newConfig) {

        /** This function fills and validates a B-type configuration from the givven input.

         * @param input (string) The input to translate into a configuration.

         * @param newConfig (object) A configuration object to append.

         */


        /* Check the input again */

        if (input === undefined)                                                                     // Test if input has been received

            return newConfig.setErrorMsg("Fout tijdens het valideren van de invoer, geen invoer ontvangen");// No input received, return an error message

        input = input.replace(/\s+/g, '').toUpperCase();                                            // Convert all input characters to higher case

        if (input.Length < 1)                                                                        // Test if at least the first character is entered

            return newConfig.setErrorMsg("Fout tijdens het valideren van de invoer, lege invoer ontvangen");// Blank input received, return an error message

        if (input[0] !== 'B')                                                                        // Test if the entered configuration starts with the character 'B'

            return newConfig.setErrorMsg("liniLED<sup>&reg;</sup> Aeris configuratie moet met een 'B' beginnen!");// Wrong start character, return error message


        /* Copy type information  */

        newConfig.artNmb = input[0];                                                                // Copy the first character

        input = input.substr(1);                                                                    // Strip the first character from the content to process


        /* Create configuration and accessory matrix */

        var CONFIGURATIONS = [{                                                                     // Step 1

            options: [{

                code: '1',

                name: "liniLED<sup>&reg</sup> Aeris Profiel L20",

                Desc: "Aeris L20",

                max: 2750,

                Omschrijving: {

                    DE: "liniLED&reg; Aeris L20",

                    EN: "liniLED&reg; Aeris L20",

                    FR: "liniLED&reg; Aeris L20",

                    NL: "liniLED&reg; Aeris L20",

                },

                addLengthForCasting: 25,

            }, {

                code: '2',

                name: "liniLED<sup>&reg</sup> Aeris Profiel H20",

                Desc: "Aeris H20",

                max: 2750,

                Omschrijving: {

                    DE: "liniLED&reg; Aeris H20",

                    EN: "liniLED&reg; Aeris H20",

                    FR: "liniLED&reg; Aeris H20",

                    NL: "liniLED&reg; Aeris H20",

                },

                addLengthForCasting: 25,

            }, {

                code: '4',

                name: "liniLED<sup>&reg</sup> Aeris Profiel L30",

                Desc: "Aeris L30",

                max: 2750,

                Omschrijving: {

                    DE: "liniLED&reg; Aeris L30",

                    EN: "liniLED&reg; Aeris L30",

                    FR: "liniLED&reg; Aeris L30",

                    NL: "liniLED&reg; Aeris L30",

                },

                addLengthForCasting: 25,

            }, {

                code: '5',

                name: "liniLED<sup>&reg</sup> Aeris Profiel H30",

                Desc: "Aeris H30",

                max: 2750,

                Omschrijving: {

                    DE: "liniLED&reg; Aeris H30",

                    EN: "liniLED&reg; Aeris H30",

                    FR: "liniLED&reg; Aeris H30",

                    NL: "liniLED&reg; Aeris H30",

                },

                addLengthForCasting: 25,

            },

            ],

            error: function (o) {

                return "Ongeldige keuze '" + o + "' in stap 1, liniLED<sup>&reg;</sup> Aeris Profiel!";

            },

        }, {                                                                                    // Step 2

            options: [{

                code: 'M',

                name: "liniLED<sup>&reg</sup> RGB Deco",

                section: 200,

                max: 2600,

                Desc: "RGB D",

                Omschrijving: {

                    DE: "RGB Deco",

                    EN: "RGB Deco",

                    FR: "RGB Deco",

                    NL: "RGB Deco"

                }

            }, {

                code: 'R',

                name: "liniLED<sup>&reg</sup> Rood Deco",

                section: 200,

                max: 2600,

                Desc: "Rood D",

                Omschrijving: {

                    DE: "Rot Deco",

                    EN: "Red Deco",

                    FR: "Rouge Deco",

                    NL: "Rood Deco"

                }

            }, {

                code: 'G',

                name: "liniLED<sup>&reg</sup> Groen Deco",

                section: 200,

                max: 2600,

                Desc: "Groen D",

                Omschrijving: {

                    DE: "Gr&uuml;n Deco",

                    EN: "Green Deco",

                    FR: "Vert Deco",

                    NL: "Groen Deco"

                }

            }, {

                code: 'B',

                name: "liniLED<sup>&reg</sup> Blauw Deco",

                section: 200,

                max: 2600,

                Desc: "Blauw D",

                Omschrijving: {

                    DE: "Blau Deco",

                    EN: "Blue Deco",

                    FR: "Bleu Deco",

                    NL: "Blauw Deco"

                }

            }, {

                code: 'A',

                name: "liniLED<sup>&reg</sup> Amber Deco",

                section: 200,

                max: 2600,

                Desc: "Amber D",

                Omschrijving: {

                    DE: "Amber Deco",

                    EN: "Amber Deco",

                    FR: "Ambre Deco",

                    NL: "Amber Deco"

                }

            }, {

                code: 'P',

                name: "liniLED<sup>&reg</sup> RGB Power",

                section: 100,

                max: 2700,

                Desc: "RGB P",

                Omschrijving: {

                    DE: "RGB Power",

                    EN: "RGB Power",

                    FR: "RGB Power",

                    NL: "RGB Power"

                }

            }, {

                code: 'C',

                name: "liniLED<sup>&reg</sup> Rood Power",

                section: 100,

                max: 2700,

                Desc: "Rood P",

                Omschrijving: {

                    DE: "Rot Power",

                    EN: "Red Power",

                    FR: "Rouge Power",

                    NL: "Rood Power"

                }

            }, {

                code: 'D',

                name: "liniLED<sup>&reg</sup> Groen Power",

                section: 100,

                max: 2700,

                Desc: "Groen P",

                Omschrijving: {

                    DE: "Gr&uuml;n Power",

                    EN: "Green Power",

                    FR: "Vert Power",

                    NL: "Groen Power"

                }

            }, {

                code: 'E',

                name: "liniLED<sup>&reg</sup> Blauw Power",

                section: 100,

                max: 2700,

                Desc: "Blauw P",

                Omschrijving: {

                    DE: "Blau Power",

                    EN: "Blue Power",

                    FR: "Blue Power",

                    NL: "Blauw Power"

                }

            }, {

                code: 'F',

                name: "liniLED<sup>&reg</sup> Amber Power",

                section: 100,

                max: 2700,

                Desc: "Amber P",

                Omschrijving: {

                    DE: "Amber Power",

                    EN: "Amber Power",

                    FR: "Ambre Power",

                    NL: "Amber Power"

                }

            }, {

                code: '1',

                name: "liniLED<sup>&reg</sup> Ultra Warm Wit 2400K High Power",

                section: 50,

                max: 2700,

                Desc: "EWW 2400K HP",

                Omschrijving: {

                    DE: "Ultra Warmwei&szlig; 2400K HP",

                    EN: "Ultra Warm White 2400K HP",

                    FR: "Blanc Ultra Chaud 2400K HP",

                    NL: "Ultra Warm Wit 2400K HP"

                }

            }, {

                code: '2',

                name: "liniLED<sup>&reg</sup> Extra Warm Wit 2700K High Power",

                section: 50,

                max: 2700,

                Desc: "EWW 2700K HP",

                Omschrijving: {

                    DE: "Extra Warmwei&szlig; 2700K HP",

                    EN: "Extra Warm White 2700K HP",

                    FR: "Blanc Extra Chaud 2700K HP",

                    NL: "Extra Warm Wit 2700K HP"

                }

            }, {

                code: '3',

                name: "liniLED<sup>&reg</sup> Warm Wit 3000K High Power",

                section: 50,

                max: 2700,

                Desc: "WW 3000K HP",

                Omschrijving: {

                    DE: "Warmwei&szlig; 3000K HP",

                    EN: "Warm White 3000K HP",

                    FR: "Blanc Chaud 3000K HP",

                    NL: "Warm Wit 3000K HP"

                }

            }, {

                code: '4',

                name: "liniLED<sup>&reg</sup> Natuurlijk Wit 4000K High Power",

                section: 50,

                max: 2700,

                Desc: "NW 4000K HP",

                Omschrijving: {

                    DE: "Neutralwei&szlig; 4000K HP",

                    EN: "Natural White 4000K HP",

                    FR: "Blanc Naturel 4000K HP",

                    NL: "Natuurlijk Wit 4000K HP"

                }

            }, {

                code: '6',

                name: "liniLED<sup>&reg</sup> Koud Wit 6500K High Power",

                section: 50,

                max: 2700,

                Desc: "KW 6500K HP",

                Omschrijving: {

                    DE: "Kaltwei&szlig; 6500K HP",

                    EN: "Cold White 6500K HP",

                    FR: "Blanc Froid 6500K HP",

                    NL: "Koud Wit 6500K HP"

                }

            },

            ],

            error: function (o) {

                return "Ongeldige keuze '" + o + "' in stap 2, liniLED<sup>&reg;</sup> PCB LED strip!";

            }

        }, {                                                                                    // Step 3

            options: [{

                code: '1',

                name: "PVC met open einde, 1 m",

                Omschrijving: {

                    DE: "1 m PVC Kabel",

                    EN: "1 m PVC cable",

                    FR: "1 m PVC c&acirc;ble",

                    NL: "1 m PVC kabel"

                }

            }, {

                code: '2',

                name: "PVC met open einde, 5 m",

                Omschrijving: {

                    DE: "5 m PVC Kabel",

                    EN: "5 m PVC cable",

                    FR: "5 m PVC c&acirc;ble",

                    NL: "5 m PVC kabel"

                }

            }, {

                code: '3',

                name: "PVC met open einde, 10 m",

                Omschrijving: {

                    DE: "10 m PVC Kabel",

                    EN: "10 m PVC cable",

                    FR: "10 m PVC c&acirc;ble",

                    NL: "10 m PVC kabel"

                }

            }, {

                code: '4',

                name: "PUR met open einde, 1 m",

                Omschrijving: {

                    DE: "1 m PUR Kabel",

                    EN: "1 m PUR cable",

                    FR: "1 m PUR c&acirc;ble",

                    NL: "1 m PUR kabel"

                }

            }, {

                code: '5',

                name: "PUR met open einde, 5 m",

                Omschrijving: {

                    DE: "5 m PUR Kabel",

                    EN: "5 m PUR cable",

                    FR: "5 m PUR c&acirc;ble",

                    NL: "5 m PUR kabel"

                }

            }, {

                code: '6',

                name: "PUR met open einde, 10 m",

                Omschrijving: {

                    DE: "10 m PUR Kabel",

                    EN: "10 m PUR cable",

                    FR: "10 m PUR c&acirc;ble",

                    NL: "10 m PUR kabel"

                }

            }, {

                code: '7',

                name: "PUR met liniLED<sup>&reg;</sup> PU Aansluitset, 1 m",

                Omschrijving: {

                    DE: "1 m PUR Kabel mit PU Anschluss-Set",

                    EN: "1 m PUR cable with PU Connection Set",

                    FR: "1 m PUR c&acirc;ble avec PU C&acirc;ble de Connextion",

                    NL: "1 m PUR kabel met PU aansluitset"

                }

            }, {

                code: '8',

                name: "PUR met liniLED<sup>&reg;</sup> PU Aansluitset, 5 m",

                Omschrijving: {

                    DE: "5 m PUR Kabel mit PU Anschluss-Set",

                    EN: "5 m PUR cable with PU Connection Set",

                    FR: "5 m PUR c&acirc;ble avec PU C&acirc;ble de Connextion",

                    NL: "5 m PUR kabel met PU aansluitset"

                }

            }, {

                code: '9',

                name: "PUR met liniLED<sup>&reg;</sup> PU Aansluitset, 10 m",

                Omschrijving: {

                    DE: "10 m PUR Kabel mit PU Anschluss-Set",

                    EN: "10 m PUR cable with PU Connection Set",

                    FR: "10 m PUR c&acirc;ble avec PU C&acirc;ble de Connextion",

                    NL: "10 m PUR kabel met PU aansluitset"

                }

            }, {

                code: '0',

                name: "PVC met demo connector, 1 m",

                Omschrijving: {

                    DE: "1 m PVC Demo Kabel",

                    EN: "1 m PVC demo cable",

                    FR: "1 m PVC demo c&acirc;ble",

                    NL: "1 m PVC demo kabel"

                }

            },

            ],

            error: function (o) {

                return "Ongeldige keuze '" + o + "' in stap 3, Kabelsoort &amp; kabellengte!";

            }

        }, {                                                                                    // Step 4

            options: [{

                code: '1',

                name: "Aan de linkerzijde via de eindkap",

                left: 10,

                right: 5,

                Omschrijving: {

                    DE: "an der linken Seite in de Endkappe",

                    EN: "on the left side via the end cap",

                    FR: "",

                    NL: "aan de linkerzijde via de eindkap"

                }

            }, {

                code: '2',

                name: "Aan de linkerzijde via de onderkant",

                left: 15,

                right: 5,

                Omschrijving: {

                    DE: "an der linken Seite der Eunterseite",

                    EN: "on the left side via the bottom",

                    FR: "",

                    NL: "aan de linkerzijde via de onderkant"

                }

            }, {

                code: '3',

                name: "Aan de linkerzijde via de zijkant",

                left: 15,

                right: 5,

                Omschrijving: {

                    DE: "an der linken Seite der Seitenansicht",

                    EN: "on the left side via the side",

                    FR: "",

                    NL: "aan de linkerzijde via de zijkant"

                }

            }, {

                code: '4',

                name: "Aan de rechterzijde via de zijkant",

                left: 5,

                right: 15,

                Omschrijving: {

                    DE: "an der rechten Seite der Seitenansicht",

                    EN: "on the right side via the side",

                    FR: "",

                    NL: "aan de rechterzijde via de zijkant"

                }

            }, {

                code: '5',

                name: "In het midden via de zijkant",

                left: 5,

                right: 5,

                Omschrijving: {

                    DE: "in der Mitte der Seitenansicht",

                    EN: "",

                    FR: "in the center via the side",

                    NL: "in het midden via de zijkant"

                }

            },

            ],

            error: function (o) {

                return "Ongeldige keuze '" + o + "' in stap 4, Positie kabelingang!";

            }

        }, {                                                                                    // Step 5

            options: [{

                code: '1',

                name: "Geen eindkappen gemonteerd",

                left: 0,

                right: 0,

                Omschrijving: {

                    DE: "Keine Endkappen montiert mit",

                    EN: "Without end caps mounted with",

                    FR: "",

                    NL: "Zonder eindkappen gemonteerd met"

                }

            }, {

                code: '2',

                name: "Eindkap aan de rechterzijde",

                left: 0,

                right: 2,

                Omschrijving: {

                    DE: "Eine Endkappe an der rechten Seite und",

                    EN: "One end cap on the right side and",

                    FR: "",

                    NL: "Een eindkap aan de rechterzijde en"

                }

            }, {

                code: '3',

                name: "Eindkap aan de linkerzijde",

                left: 2,

                right: 0,

                Omschrijving: {

                    DE: "Eine Endkappe an der linken Seite und",

                    EN: "One end cap on the left side and",

                    FR: "",

                    NL: "Een eindkap aan de linkerzijde en"

                }

            }, {

                code: '4',

                name: "Eindkappen aan beide zijden",

                left: 2,

                right: 2,

                Omschrijving: {

                    DE: "Endkappen an beiden Seiten und",

                    EN: "End caps on both sides and",

                    FR: "",

                    NL: "Eindkappen aan beide zijden en"

                }

            }, {

                code: '5',

                name: "Hoge eindkap aan de rechterzijde t.b.v. kabelgoot",

                left: 0,

                right: 2,

                Omschrijving: {

                    DE: "Eine Hohe Endkappe an der rechten Seite und",

                    EN: "One high end cap on the right side and",

                    FR: "",

                    NL: "Een hoge eindkap aan de rechterzijde en"

                }

            }, {

                code: '6',

                name: "Hoge eindkap aan de linkerzijde t.b.v. kabelgoot",

                left: 2,

                right: 0,

                Omschrijving: {

                    DE: "Eine Hohe Endkappe an der linken Seite und",

                    EN: "One high end cap on the left side and",

                    FR: "",

                    NL: "Een hoge eindkap aan de linkerzijde en"

                }

            }, {

                code: '7',

                name: "Hoge eindkap aan de beide zijden t.b.v. kabelgoot",

                left: 2,

                right: 2,

                Omschrijving: {

                    DE: "Hohe Endkappen an beiden Seiten und",

                    EN: "End caps on both sides and",

                    FR: "",

                    NL: "Hoge eindkappen aan beide zijden en"

                }

            }, {

                code: '8',

                name: "Eindkappen aan beide zijden met losse kappen op de kabelgoot",

                left: 2,

                right: 2,

                Omschrijving: {

                    DE: "Endkappen an beiden Seiten mit seperaten kappen für Kabelprofil und",

                    EN: "End caps on both sides with seperate caps for the cable channel and",

                    FR: "",

                    NL: "Eindkappen aan beide zijden met losse kappen op het kabelprofiel en"

                }

            },

            ],

            error: function (o) {

                return "Ongeldige keuze '" + o + "' in stap 5, Montage opties voor eindkap(pen)!";

            }

        }, {                                                                                    // Step 6

            options: [{

                code: 'C',

                name: "Heldere giethars",

                Omschrijving: {

                    DE: "Klar gegossenen Profil",

                    EN: "clear casted profile",

                    FR: "Effacer profil coul&eacute;",

                    NL: "helder gegoten profiel"

                }

            }, {

                code: 'D',

                name: "Diffuse giethars",

                Omschrijving: {

                    DE: "Diffuse gegossenen Profil",

                    EN: "diffuse casted profile",

                    FR: "diffuse profil coul&eacute;",

                    NL: "diffuus gegoten profiel"

                }

            },

            ],

            error: function (o) {

                return "Ongeldige keuze '" + o + "' in stap 6, Helder/Diffuus giethars!";

            }

        },

        ];

        var ACCESSORIES = {                                                                         // Step 9

            options: [{

                code: 'A',

                name: "Bevestigingsclips voor het profiel",

                Desc: "Bevestigingsclips",

                Omschrijving: {

                    DE: "Montageklammen",

                    EN: "Mounting clips",

                    FR: "Clips du fixation",

                    NL: "Bevestigingsclips",

                },

                conflict: false,

                Part: function (configuration) {

                    var amounth = Math.ceil(configuration.totalLength / 500);

                    if (amounth < 2) amounth = 2;

                    if (configuration.steps[0].code == '1' || configuration.steps[0].code == '2')

                        return {Part: 10890, Count: amounth};

                    if (configuration.steps[0].code == '4' || configuration.steps[0].code == '5')

                        return {Part: 10891, Count: amounth};

                    return null;

                },

            }, {

                code: 'B',

                name: "Kabelgoot voor het profiel",

                Desc: "Kabelgoot",

                Omschrijving: {

                    DE: "Kabelprofil",

                    EN: "Cable channel",

                    FR: "Goulotte",

                    NL: "liniLED&reg; Aeris L20",

                },

                conflict: false,

                Part: function (configuration) {

                    var length = Math.ceil(configuration.totalLength / 1000);

                    if (length < 1 || length > 4)

                        return null;

                    if (configuration.steps[0].code == '1' || configuration.steps[0].code == '2')

                        return {
                            Part: (10749 + length),
                            Count: 1,
                            Remark: "Op maat meeleveren bij profiel (" + (configuration.totalLength - (configuration.steps[4].left + configuration.steps[4].right)) + " mm)"
                        };

                    if (configuration.steps[0].code == '4' || configuration.steps[0].code == '5')

                        return {
                            Part: (10753 + length),
                            Count: 1,
                            Remark: "Op maat meeleveren bij profiel (" + (configuration.totalLength - (configuration.steps[4].left + configuration.steps[4].right)) + " mm)"
                        };

                    return null;

                },

            },

            ],

            error: function (o) {

                return "Ongeldige keuze '" + o + "' in stap 9, optionele accessoires!";

            },

        };


        /* First check run, plain data check */

        var m = -1;                                                                                 // Create a local dummy variable

        for (var q = 0; q < 6; q++) {                                                                 // Loop through configuration steps 1...6

            m = (CONFIGURATIONS[q].options.length - 1);                                             // Determine the number of options in the configuration

            while (m >= 0) {                                                                          // Loop through the options

                if (input[0] == CONFIGURATIONS[q].options[m].code)                                   // Check if the input matches

                    newConfig.steps[q] = CONFIGURATIONS[q].options[m];                              // Copy option to configuration

                if (newConfig.steps[q] === false)                                                    // Test if the step was loaded

                    m--;                                                                            // Not loaded, alter the search index

                else

                    m = -1;                                                                           // Loaded, end this search

            }

            if (newConfig.steps[q] === false)                                                        // Test if a valid input was found

                return newConfig.setErrorMsg(CONFIGURATIONS[q].error(input[0]));                    // Return error message

            newConfig.steps[q].conflict = false;                                                    // Preset conflict flag

            newConfig.artNmb += newConfig.steps[q].code;                                            // Update article number

            input = input.substr(1);                                                                // Strip a character of the content to process

        }

        // Proceed with step 7

        var length = null;                                                                          // Preload detected value with 'not detected'

        m = true;                                                                                   // Set search flag

        while (m) {                                                                                   // Loop through the data to find the entered value

            var n = ("0123456789").indexOf(input[0]);                                               // Locate the content of the first character

            if (n < 0)                                                                               // Test if the character was found

                m = false;                                                                          // Character not fount, stop search

            else {                                                                                  // Character found

                length = (length * 10) + n;                                                         // Update value with found information

                input = input.substr(1);                                                            // Strip a character of the content to process

            }

        }

        if (length === null || isNaN(length))                                                        // Test if a valid number was found

            return newConfig.setErrorMsg("Geen lengte voor de LED strip gevonden bij stap 7!");     // No length information found, report error

        if (length > newConfig.steps[1].max)                                                         // Test for maximum length

            return newConfig.setErrorMsg("Opgegeven lengte (" + length + " mm) langer dan maximum voor bij stap 2 gekozen LED strip (" + newConfig.steps[1].max + " mm)");// Entered length exceeds maximum, report error

        if (length < newConfig.steps[1].section)                                                     // Test for minimum length

            return newConfig.setErrorMsg("Opgegeven lengte (" + length + " mm) korter dan kleinste lengte voor bij stap 2 gekozen LED strip (" + newConfig.steps[1].max + " mm)");// Entered length deseeds minimum, report error

        if ((length % newConfig.steps[1].section) !== 0)                                             // Test section length

            return newConfig.setErrorMsg("Opgegeven lengte (" + length + " mm) moet een veelvoud van de sectielengte zijn (" + newConfig.steps[1].section + " mm voor bij stap 2 gekozen LED strip)");// Entered length not matching section length, report error

        newConfig.steps[6] = {value: length, name: length + " mm"};                                 // Store length in configuration

        length = length + '';                                                                       // Convert to string

        while (length.length < 4) length = '0' + length;                                             // Add leading zeros

        newConfig.steps[6].code = length;                                                           // Store code in configuration

        newConfig.steps[6].conflict = false;                                                        // Preset conflict flag

        newConfig.artNmb += newConfig.steps[6].code;                                                // Update article number

        newConfig.totalLength = newConfig.steps[3].left + newConfig.steps[3].right + newConfig.steps[4].left + newConfig.steps[4].right + newConfig.steps[6].value;// Calculate total length of fixture

        // Proceed with step 8

        if (input.length > 0) {                                                                       // Test if there is content left to process

            if (input[0] == '-') {                                                                    // Test if the user has entered an optional total length (this starts with a '-')

                input = input.substr(1);                                                            // Strip '-' character of the content to process

                length = null;                                                                      // Preload detected value with 'not detected'

                m = true;                                                                           // Set search flag

                while (m) {                                                                           // Loop through the data to find the entered value

                    var n = ("0123456789").indexOf(input[0]);                                       // Locate the content of the first character

                    if (n < 0)                                                                       // Test if the character was found

                        m = false;                                                                  // Character not fount, stop search

                    else {                                                                           // Character found

                        length = (length * 10) + n;                                                 // Update value with found information

                        input = input.substr(1);                                                    // Strip a character of the content to process

                    }

                }

                if (length !== null && (!isNaN(length))) {                                            // Test if a valid number was found

                    if (length > newConfig.steps[0].max)                                             // Test for maximum length

                        return newConfig.setErrorMsg("Opgegeven totale lengte (" + length + " mm) langer dan maximum voor bij stap 1 gekozen profiel (" + newConfig.steps[0].max + " mm)");// Entered length exceeds maximum, report error

                    if (newConfig.totalLength > length)                                              // Test for minimum length

                        return newConfig.setErrorMsg("Opgegeven totale lengte (" + length + " mm) is te klein. Het armatuur moet minimaal " + newConfig.totalLength + " mm lang zijn");// Entered length deseeds minimum, report error

                    newConfig.totalLength = length;                                                 // Store length in configuration

                    length = length + '';                                                           // Convert to string

                    newConfig.steps[7] = {value: length, name: "Opgegeven lengte " + length + " mm"};// Store length in configuration

                    while (length.length < 4) length = '0' + length;                                 // Add leading zeros

                    newConfig.steps[7].code = length;                                               // Store code in configuration

                    newConfig.steps[7].conflict = false;                                            // Preset conflict flag

                    newConfig.artNmb += "-" + newConfig.steps[7].code;                              // Update article number

                }

            }

        }

        // Proceed with step 9

        newConfig.artNmbShort = newConfig.artNmb;                                                   // Copy formated article number to short number (number whitout accessories)

        var unknownOptions = "";                                                                    // Preset variable to contain unknown objects found

        var foundOptions = "";                                                                      // Preset variable to contain known objects found

        while (input.length > 0) {                                                                    // Loop through all remaining characters

            if (("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789").indexOf(input[0]) >= 0) {                    // Only allow (alfa)numeric characters

                m = (ACCESSORIES.options.length - 1);                                               // Determine the number of accessory options

                while (m >= 0) {                                                                      // Loop through the options

                    if (input[0] == ACCESSORIES.options[m].code) {                                    // Check if the input matches

                        if ((foundOptions).indexOf(input[0]) < 0) {                                   // Test if the option was already selected, if so, ignore

                            newConfig.accessories.push(ACCESSORIES.options[m]);                     // Copy option to configuration

                            newConfig.artNmb += ", " + input[0];                                    // Update article number

                            foundOptions += input[0];                                               // Set option 'selected'

                        }

                        m = -1;                                                                     // End search, option found

                    } else

                        m--;                                                                        // Decrease search index

                }

                if (m == 0 && (unknownOptions).indexOf(input[0]) < 0)                                // Test if the option was found and if not if (faulty) option was already detected, if so, ignore

                    unknownOptions += "', '" + input[0];                                            // Add to list of unknown options

            }

            input = input.substr(1);                                                                // Strip a character of the content to process

        }

        if (unknownOptions !== "")                                                                   // Test for errors

            return newConfig.setErrorMsg(ACCESSORIES.error(unknownOptions.sunstr(4)));              // Return error message

        // All steps processed, no (fatal) error

        newConfig.errMsg = false;                                                                   // Reset error messages


        /* Second check run, wrong combi check */

        newConfig.isValid = true;                                                                   // Assume the configuration is valid until proved otherwise

        // Stap 3

        if (newConfig.steps[2].code == '0' && (newConfig.steps[1].code == 'M' || newConfig.steps[1].code == 'P')) {// Demo connector cannot be combined with RGB LED strip

            newConfig.isValid = false;                                                              // Invalidate configuration

            newConfig.steps[1].conflict = true;                                                     // Set confict marker on step 2

            newConfig.steps[2].conflict = true;                                                     // Set confict marker on step 3

            newConfig.warning += "<span>Conflict in keuze '0' in stap 3, niet mogelijk in combinatie met keuze 'M' of 'P' in stap 2!</span>";// Add warning message

        }

        // Stap 5

        if ((newConfig.steps[4].code == '1' || newConfig.steps[4].code == '2' || newConfig.steps[4].code == '5') && newConfig.steps[3].code == '1') {// Cable entrance via end cap only possible if end cap is selected

            newConfig.isValid = false;                                                              // Invalidate configuration

            newConfig.steps[3].conflict = true;                                                     // Set confict marker on step 4

            newConfig.steps[4].conflict = true;                                                     // Set confict marker on step 5

            newConfig.warning += "<span>Conflict in keuze '" + newConfig.steps[4].code + "' in stap 5, niet mogelijk in combinatie met keuze '1' in stap 4!</span>";// Add warning message

        }

        // Stap 6

        if (newConfig.steps[5].code == 'D' && newConfig.steps[0].code == '2' && (newConfig.steps[1].code != 'M' || newConfig.steps[1].code != 'R' || newConfig.steps[1].code != 'G' || newConfig.steps[1].code != 'B' ||

            newConfig.steps[1].code != 'A' || newConfig.steps[1].code != 'P' || newConfig.steps[1].code != 'C' || newConfig.steps[1].code != 'D' || newConfig.steps[1].code != 'E' || newConfig.steps[1].code != 'F' )) {// Check if the profile will be diffuse

            newConfig.steps[1].conflict = true;                                                     // Set confict marker on step 2

            newConfig.steps[5].conflict = true;                                                     // Set confict marker on step 6

            newConfig.warning += "<span>LET OP: Dit product zal geen diffuus beeld geven door de keuze in stap 2!</span>";// Add warning message

        }

        // Stap 7/8

        length = newConfig.steps[3].left;                                                           // Load left side offset

        if (newConfig.steps[3].right > length) length = newConfig.steps[3].right;                      // Test if right side offset is bigger, if so load this value

        length = (2 * length) + newConfig.steps[6].value + newConfig.steps[4].left + newConfig.steps[4].right;// Calculate minimum symmetrical length

        if (length > newConfig.totalLength) {                                                         // Test if the fixture length is less than the minimum symmetrical length

            newConfig.steps[3].conflict = true;                                                     // Set confict marker on step 4

            newConfig.steps[6].conflict = true;                                                     // Set confict marker on step 7

            newConfig.steps[7].conflict = true;                                                     // Set confict marker on step 8

            newConfig.warning += "<span>LET OP: De lichtbron kan niet gecentreerd in het product worden geplaats (min. " + length + " mm nodig, dit kan in stap 8 worden aangegeven).</span>";// Add warning message

        } else newConfig.symmetric = true;                                                          // Fixture is symmetrical

        // Options

        if (newConfig.accessories.length > 0) {                                                       // Test if there are accessories selected

            if (foundOptions.indexOf('A') >= 0 && foundOptions.indexOf('B') >= 0) {                   // Test if option A and B are both selected

                newConfig.accessories.forEach(function (item) {                                       // Loop through all accessories

                    if (item.code == 'A' || item.code == 'B')                                        // Test if the option is of type A or B

                        item.conflict = true;                                                       // Set conflict flag

                });

                newConfig.warning += "<span>LET OP: Zowel bevestigingsclip als kabelgoot toegevoegd aan dit product, de klant kan slechts &eacute;&eacute;n methode van montage gebruiken.</span>";// Add warning message

            }

        }


        /* Create configuration information */

        var configInfo = "<ol type=\"1\">";                                                         // Create local variable to append configuration information

        for (var q = 0; q < 7; q++)                                                                  // Loop through configuration steps 1...7

            configInfo += "<li" + (newConfig.steps[q].conflict ? " class=\"warning\"" : "") + ">" + newConfig.steps[q].name + "</li>";// Add step information

        if (newConfig.steps[7] === false)                                                            // Test if configuration step 8 was added

            configInfo += "<li>Niet opgegeven, berekend " + newConfig.totalLength + " mm</li>";     // Add step information

        else

            configInfo += "<li" + (newConfig.steps[7].conflict ? " class=\"warning\"" : "") + ">" + newConfig.steps[7].name + "</li>";// Add step information

        if (newConfig.accessories.length > 0) {                                                       // Test if there are accessories loaded

            configInfo += "<li><b>Extra accessoires :<ul>";                                         // Add list item

            newConfig.accessories.forEach(function (item) {                                           // Loop through all accessories

                configInfo += "<li" + (item.conflict ? " class=\"warning\"" : "") + ">" + item.name + "</li>";// Add accesory information

            });

            configInfo += "<ul></li>";                                                              // Close added list timen

        }

        configInfo += "</ol><hr />";                                                                // Close the generated list object

        if (newConfig.isValid)                                                                       // Test if the loaded configuration is valid

            newConfig.info = "<p>De volgende geldige configuratie is geladen:</p>" + configInfo;    // Valid configuration, add this information

        else

            newConfig.info = "<p>De volgende configuratie is opgegeven maar bevat fouten:</p>" + configInfo;// Invalid configuration, add this information


        /* Test if the configuration is value */

        if (newConfig.isValid !== true)                                                              // Test if the configuration is (still) valid

            return newConfig;                                                                       // Only proceed if valid, tested invalid so return


        /* Create UNIT4 information */

        var cr = "<br />", s = " ", p = ". ";                                                       // Preload a set of local variables to use later in the process

        newConfig.UNIT4.isValid = true;                                                             // Set valid flag

        newConfig.UNIT4.Prod = newConfig.artNmbShort;                                               // Copy (short) article numer

        newConfig.UNIT4.Desc = ("Gegoten " + newConfig.steps[0].Desc + " " + newConfig.steps[1].Desc).substr(0, 35);// Create internal describtion based on the configuration and limit its size to 35 characters

        newConfig.UNIT4.Omschrijving.DE =

            newConfig.artNmbShort + cr +

            newConfig.steps[0].Omschrijving.DE + s +

            newConfig.steps[1].Omschrijving.DE + s +

            newConfig.steps[5].Omschrijving.DE + p +

            newConfig.steps[2].Omschrijving.DE + s +

            newConfig.steps[3].Omschrijving.DE + p +

            newConfig.steps[4].Omschrijving.DE + s +

            newConfig.steps[6].value + " mm LED Streife in einem Leuchte mit ein L&auml;nge von " +

            newConfig.totalLength + " mm" + (newConfig.symmetric === true ? " zentriert" : "" ) + ".";// Format and set German text

        newConfig.UNIT4.Omschrijving.EN =

            newConfig.artNmbShort + cr +

            newConfig.steps[0].Omschrijving.EN + s +

            newConfig.steps[1].Omschrijving.EN + s +

            newConfig.steps[5].Omschrijving.EN + p +

            newConfig.steps[2].Omschrijving.EN + s +

            newConfig.steps[3].Omschrijving.EN + p +

            newConfig.steps[4].Omschrijving.EN + s +

            newConfig.steps[6].value + " mm LED strip" + (newConfig.symmetric === true ? " centered" : "") +

            " in a fixture of " + newConfig.totalLength + " mm.";                                   // Format and set Englisch text

        newConfig.UNIT4.Omschrijving.FR = "-Vertaling niet beschikbaar-";                           // French not supported (yet)

        newConfig.UNIT4.Omschrijving.NL =

            newConfig.artNmbShort + cr +

            newConfig.steps[0].Omschrijving.NL + s +

            newConfig.steps[1].Omschrijving.NL + s +

            newConfig.steps[5].Omschrijving.NL + p +

            newConfig.steps[2].Omschrijving.NL + s +

            newConfig.steps[3].Omschrijving.NL + p +

            newConfig.steps[4].Omschrijving.NL + s +

            newConfig.steps[6].value + " mm LED strip" + (newConfig.symmetric === true ? " gecentreerd" : "") +

            " in een armatuur van " + newConfig.totalLength + " mm.";                               // Format and set Dutch text

        // Create list of subarticles

        var Subs = null;                                                                            // Create a local variabele

        switch (newConfig.steps[0].code) {                                                            // Test for selected profile (step 1)

            case '1':                                                                               // Option 1 selected

                newConfig.UNIT4.partlist.push({
                    Part: 10713,
                    Count: ((newConfig.totalLength - (newConfig.steps[4].left + newConfig.steps[4].right)) + newConfig.steps[0].addLengthForCasting) / 4000
                });// Add the profile to the list

                Subs = [10900, 10901, 10902, 10903, 10908, 95000, 95001];                           // Load article numbers of end caps and castings

                break;

            case '2':                                                                               // Option 2 selected

                newConfig.UNIT4.partlist.push({
                    Part: 10717,
                    Count: ((newConfig.totalLength - (newConfig.steps[4].left + newConfig.steps[4].right)) + newConfig.steps[0].addLengthForCasting) / 4000
                });// Add the profile to the list

                Subs = [10904, 10905, 10906, 10907, 10908, 95010, 95011];                           // Load article numbers of end caps and castings

                break;

            case '4':                                                                               // Option 4 selected

                newConfig.UNIT4.partlist.push({
                    Part: 10733,
                    Count: ((newConfig.totalLength - (newConfig.steps[4].left + newConfig.steps[4].right)) + newConfig.steps[0].addLengthForCasting) / 4000
                });// Add the profile to the list

                Subs = [10920, 10921, 10922, 10923, 10928, 95003, 95004];                           // Load article numbers of end caps and castings

                break;

            case '5':                                                                               // Option 5 selected

                newConfig.UNIT4.partlist.push({
                    Part: 10737,
                    Count: ((newConfig.totalLength - (newConfig.steps[4].left + newConfig.steps[4].right)) + newConfig.steps[0].addLengthForCasting) / 4000
                });// Add the profile to the list

                Subs = [10924, 10925, 10926, 10927, 10928, 95013, 95014];                           // Load article numbers of end caps and castings

                break;

            default:                                                                                // Selected opstion not found

                newConfig.UNIT4.isValid = false;                                                    // Clear valid flag

                break;

        }

        if (Subs.length != 7)                                                                        // Test if a valid set of end cap references and castings is loaded

            newConfig.UNIT4.isValid = false;                                                        // Not loaded, clear valid flag

        else {

            switch (newConfig.steps[4].code) {                                                        // Test for selected end caps (step 5)

                case '1':                                                                           // Option 1 selected

                    /** THIS IS CORRECT!! Option is no end caps but for production at least one is needed. This will be cut-off after casting. **/

                    newConfig.UNIT4.partlist.push({Part: Subs[0], Count: 1});                       // Add end cap

                    break;

                case '2':                                                                           // Option 2 selected

                    newConfig.UNIT4.partlist.push({Part: Subs[0], Count: 1});                       // Add end cap

                    break;

                case '3':                                                                           // Option 3 selected

                    if (newConfig.steps[3].code == '1')                                              // Test if cable passes through the end cap

                        newConfig.UNIT4.partlist.push({Part: Subs[1], Count: 1});                   // Add end cap with hole

                    else

                        newConfig.UNIT4.partlist.push({Part: Subs[0], Count: 1});                   // Add end cap

                    break;

                case '4':                                                                           // Option 4 selected

                    if (newConfig.steps[3].code == '1') {                                             // Test if cable passes through the end cap

                        newConfig.UNIT4.partlist.push({Part: Subs[0], Count: 1});                   // Add end cap

                        newConfig.UNIT4.partlist.push({Part: Subs[1], Count: 1});                   // Add end cap with hole

                    } else

                        newConfig.UNIT4.partlist.push({Part: Subs[0], Count: 2});                   // Add end cap

                    break;

                case '5':                                                                           // Option 5 selected

                    newConfig.UNIT4.partlist.push({Part: Subs[2], Count: 1});                       // Add high end cap

                    break;

                case '6':                                                                           // Option 6 selected

                    if (newConfig.steps[3].code == '1')                                              // Test if cable passes through the end cap

                        newConfig.UNIT4.partlist.push({Part: 10903, Count: 1});

                    else

                        newConfig.UNIT4.partlist.push({Part: Subs[2], Count: 1});                   // Add high end cap

                    break;

                case '7':                                                                           // Option 7 selected

                    if (newConfig.steps[3].code == '1') {                                             // Test if cable passes through the end cap

                        newConfig.UNIT4.partlist.push({Part: Subs[2], Count: 1});                   // Add high end cap

                        newConfig.UNIT4.partlist.push({Part: Subs[3], Count: 1});                   // Add high end cap with hole

                    } else

                        newConfig.UNIT4.partlist.push({Part: Subs[2], Count: 2});                   // Add high end cap

                    break;

                case '8':                                                                           // Option 8 selected

                    if (newConfig.steps[3].code == '1') {                                             // Test if cable passes through the end cap

                        newConfig.UNIT4.partlist.push({Part: Subs[0], Count: 1});                   // Add end cap

                        newConfig.UNIT4.partlist.push({Part: Subs[1], Count: 1});                   // Add end cap with hole

                    } else

                        newConfig.UNIT4.partlist.push({Part: Subs[0], Count: 2});                   // Add end cap

                    newConfig.UNIT4.partlist.push({Part: Subs[4], Count: 2});                       // Add end cap for cable channel

                    break;

                default:                                                                            // Selected opstion not found

                    newConfig.UNIT4.isValid = false;                                                // Clear valid flag

                    break;

            }

        }

        switch (newConfig.steps[1].code) {                                                            // Test for selected light source (step 2)

            case 'M':

                newConfig.UNIT4.partlist.push({Part: 21002, Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case 'R':

                newConfig.UNIT4.partlist.push({Part: 21004, Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case 'G':

                newConfig.UNIT4.partlist.push({Part: 21005, Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case 'B':

                newConfig.UNIT4.partlist.push({Part: 21006, Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case 'A':

                newConfig.UNIT4.partlist.push({Part: 21003, Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case 'P':

                newConfig.UNIT4.partlist.push({Part: 21017, Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case 'C':

                newConfig.UNIT4.partlist.push({Part: 21018, Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case 'D':

                newConfig.UNIT4.partlist.push({Part: 21020, Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case 'E':

                newConfig.UNIT4.partlist.push({Part: 21019, Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case 'F':

                newConfig.UNIT4.partlist.push({Part: 21021, Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case '1':

                newConfig.UNIT4.partlist.push({Part: '21032A', Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case '2':

                newConfig.UNIT4.partlist.push({Part: '21013A', Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case '3':

                newConfig.UNIT4.partlist.push({Part: '21014A', Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case '4':

                newConfig.UNIT4.partlist.push({Part: '21015A', Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            case '6':

                newConfig.UNIT4.partlist.push({Part: '21016A', Count: newConfig.steps[6].value / 1000});// Add LED strip

                break;

            default:                                                                                // Selected opstion not found

                newConfig.UNIT4.isValid = false;                                                    // Clear valid flag

                break;

        }

        switch (newConfig.steps[2].code) {                                                            // Test for selected cable type (step 3)

            case '1':

                if (newConfig.steps[1].code == 'M' || newConfig.steps[1].code == 'P')                // Test if the type is RGB or Mono

                    newConfig.UNIT4.partlist.push({Part: 60005, Count: 1});                         // Add RGB cable

                else

                    newConfig.UNIT4.partlist.push({Part: 60004, Count: 1});                         // Add Mono cable

                break;

            case '2':

                if (newConfig.steps[1].code == 'M' || newConfig.steps[1].code == 'P')                // Test if the type is RGB or Mono

                    newConfig.UNIT4.partlist.push({Part: 60005, Count: 5});                         // Add RGB cable

                else

                    newConfig.UNIT4.partlist.push({Part: 60004, Count: 5});                         // Add Mono cable

                break;

            case '3':

                if (newConfig.steps[1].code == 'M' || newConfig.steps[1].code == 'P')                // Test if the type is RGB or Mono

                    newConfig.UNIT4.partlist.push({Part: 60005, Count: 10});                        // Add RGB cable

                else

                    newConfig.UNIT4.partlist.push({Part: 60004, Count: 10});                        // Add Mono cable

                break;

            case '4':

                if (newConfig.steps[1].code == 'M' || newConfig.steps[1].code == 'P')                // Test if the type is RGB or Mono

                    newConfig.UNIT4.partlist.push({Part: 60007, Count: 1});                         // Add RGB cable

                else

                    newConfig.UNIT4.partlist.push({Part: 60006, Count: 1});                         // Add Mono cable

                break;

            case '5':

                if (newConfig.steps[1].code == 'M' || newConfig.steps[1].code == 'P')                // Test if the type is RGB or Mono

                    newConfig.UNIT4.partlist.push({Part: 60007, Count: 5});                         // Add RGB cable

                else

                    newConfig.UNIT4.partlist.push({Part: 60006, Count: 5});                         // Add Mono cable

                break;

            case '6':

                if (newConfig.steps[1].code == 'M' || newConfig.steps[1].code == 'P')                // Test if the type is RGB or Mono

                    newConfig.UNIT4.partlist.push({Part: 60007, Count: 10});                        // Add RGB cable

                else

                    newConfig.UNIT4.partlist.push({Part: 60006, Count: 10});                        // Add Mono cable

                break;

            case '7':

                if (newConfig.steps[1].code == 'M' || newConfig.steps[1].code == 'P')                // Test if the type is RGB or Mono

                    newConfig.UNIT4.partlist.push({Part: 60013, Count: 1});                         // Add RGB cable

                else

                    newConfig.UNIT4.partlist.push({Part: 60010, Count: 1});                         // Add Mono cable

                break;

            case '8':

                if (newConfig.steps[1].code == 'M' || newConfig.steps[1].code == 'P')                // Test if the type is RGB or Mono

                    newConfig.UNIT4.partlist.push({Part: 60014, Count: 1});                         // Add RGB cable

                else

                    newConfig.UNIT4.partlist.push({Part: 60011, Count: 1});                         // Add Mono cable

                break;

            case '9':

                if (newConfig.steps[1].code == 'M' || newConfig.steps[1].code == 'P')                // Test if the type is RGB or Mono

                    newConfig.UNIT4.partlist.push({Part: 60015, Count: 1});                         // Add RGB cable

                else

                    newConfig.UNIT4.partlist.push({Part: 60012, Count: 1});                         // Add Mono cable

                break;

            case '0':

                newConfig.UNIT4.partlist.push({Part: 11214, Count: 1});                             // Add Mono cable

                break;

            default:                                                                                // Selected opstion not found

                newConfig.UNIT4.isValid = false;                                                    // Clear valid flag

                break;

        }

        switch (newConfig.steps[5].code) {                                                            // Test for selected casting (step 6)

            case 'D':

                newConfig.UNIT4.partlist.push({
                    Part: Subs[6],
                    Count: Math.max(1, ((newConfig.totalLength - (newConfig.steps[4].left + newConfig.steps[4].right)) + newConfig.steps[0].addLengthForCasting) / 1000)
                });// Add casting

                break;

            case 'C':

                newConfig.UNIT4.partlist.push({
                    Part: Subs[5],
                    Count: Math.max(1, ((newConfig.totalLength - (newConfig.steps[4].left + newConfig.steps[4].right)) + newConfig.steps[0].addLengthForCasting) / 1000)
                });// Add casting

                break;

            default:                                                                                // Selected opstion not found

                newConfig.UNIT4.isValid = false;                                                    // Clear valid flag

                break;

        }

        switch (newConfig.steps[1].code) {                                                            // Test if tape needs to be applied

            case '1':

            case '2':

            case '3':

            case '4':

            case '6':

                break;                                                                              // No tape for those options

            case 'M':

            case 'R':

            case 'G':

            case 'B':

            case 'A':

            case 'P':

            case 'C':

            case 'D':

            case 'E':

            case 'F':

                newConfig.UNIT4.partlist.push({Part: 60000, Count: newConfig.steps[6].value / 50000});// Add tape

                break;

            default:                                                                                // Selected option not found

                newConfig.UNIT4.isValid = false;                                                    // Clear valid flag

                break;

        }

        newConfig.UNIT4.partlist.push({Part: 'ARBEID', Count: 15});                                 // Add work on the profile

        switch (newConfig.steps[3].code) {                                                            // Test for tule to add

            case '1':

                newConfig.UNIT4.partlist.push({Part: 'GRIJP0', Count: 1});                          // Add tule

                break;

            case '2':

                newConfig.UNIT4.partlist.push({Part: 'GRIJP1', Count: 1});                          // Add tule

                break;

            case '3':

            case '4':

            case '5':

                newConfig.UNIT4.partlist.push({Part: 'GRIJP2', Count: 1});                          // Add tule

                break;

            default:                                                                                // Selected option not found

                newConfig.UNIT4.isValid = false;                                                    // Clear valid flag

                break;

        }

        newConfig.UNIT4.partlist.push({Part: 'GRIJP3', Count: 1});                                  // Add sticker

        newConfig.UNIT4.partlist.push({Part: 'GRIJP4', Count: 1});                                  // Add manual

        newConfig.UNIT4.partlist.push({Part: 'GRIJP5', Count: (newConfig.totalLength / 1000) + 0.25});// Add packaging material

        // Create list of accessory articles

        newConfig.accessories.forEach(function (item) {                                               // Loop through all accessories

            var accessoryPart = item.Part(newConfig);                                               // Load information for this accessory

            if (accessoryPart !== null)                                                              // Test if a part was returned

                newConfig.UNIT4.accessories.push(accessoryPart);                                    // Add accessory material

        });

        // Calculate VVP, BVP and complex information

        try {                                                                                       // Create safe try-catch block

            newConfig.UNIT4.partlist.forEach(function (item) {                                        // Loop through all items

                newConfig.UNIT4.VVP += PARTS[item.Part].VVP * item.Count;                           // Increase VVP with VVP of this item

            });

            newConfig.UNIT4.BVP = newConfig.UNIT4.VVP * VVP2BVPfactor;                              // Calculate BVP from VVP

            if (newConfig.UNIT4.accessories.length > 0) {                                             // Test if the configuration contains accessories

                newConfig.complex = {data: [], total: 0};                                           // Create new object

                newConfig.complex.data.push({                                                       // Add configured profile to complex output

                    code: newConfig.UNIT4.Prod,

                    name: "&nbsp;" + newConfig.UNIT4.Desc,

                    count: fnFormatAmounth(1, "ST"),

                    bvp: fnFormatEuro(newConfig.UNIT4.BVP) + "&nbsp;",

                    total: fnFormatEuro(newConfig.UNIT4.BVP) + "&nbsp;",

                });

                newConfig.complex.total += newConfig.UNIT4.BVP;                                     // Add the cost of the profile to the total

                newConfig.UNIT4.accessories.forEach(function (item) {                                 // Loop through all items

                    var remark = "";                                                                // Preload an empty remark

                    if (typeof(item.Remark) !== 'undefined')                                         // Test if the item  contains a remark

                        remark = "<br />&nbsp;<b><u>Opmerking:</u>&nbsp;" + item.Remark + "</b>";   // Add remark

                    newConfig.complex.data.push({                                                   // Add accessory information to complex output

                        code: PARTS[item.Part].code,

                        name: "&nbsp;" + PARTS[item.Part].name + remark,

                        count: fnFormatAmounth(item.Count, PARTS[item.Part].type),

                        bvp: fnFormatEuro(PARTS[item.Part].VVP * VVP2BVPfactor) + "&nbsp;",

                        total: fnFormatEuro(PARTS[item.Part].VVP * VVP2BVPfactor * item.Count) + "&nbsp;",

                    });

                    newConfig.complex.total += PARTS[item.Part].VVP * VVP2BVPfactor * item.Count;   // Add the cost of the accessory to the total

                });

            }

        } catch (e) {                                                                                 // Catch errors

            newConfig.UNIT4.VVP = false;                                                            // Clear VVP

            newConfig.UNIT4.BVP = false;                                                            // Clear BVP

            newConfig.UNIT4.complex = false;                                                        // Clear complex information

            newConfig.warning += "<span>Fout tijdens berekenen VVP en BVP informatie.</span>";      // Add warning message

        }

        // Test valid flag

        if (newConfig.UNIT4.isValid !== true) {                                                       // Test if the object is valid

            newConfig.UNIT4.VVP = false;                                                            // Clear VVP

            newConfig.UNIT4.BVP = false;                                                            // Clear BVP

            newConfig.UNIT4.complex = false;                                                        // Clear complex information

            newConfig.warning += "<span>Stuklijst is niet juist opgemaakt of bevat fouten.</span>"; // Add warning message

        }


        /* Create assembly instructions */

        newConfig.AssemblyInfo =                                                                    // Load default message

            "<p>" +

            "Helaas voorziet deze tool in een volledige, stap voor stap, assemblage instructie. " +

            "Gebruik de informatie die is verkregen van de afdeling R&amp;D in combinatie met de weergegeven configuratie en de stuklijst voor het bepalen van de assemblage stappen. " +

            "</p>" +

            "<div>" +

            "Zaaglengte profiel (voor gieten): " + ((newConfig.totalLength - (newConfig.steps[4].left + newConfig.steps[4].right)) + newConfig.steps[0].addLengthForCasting) + " mm" +

            "</div>" +

            "<div>" +

            "Zaaglengte profiel na gieten (evt. incl. linker eindkap): " + (newConfig.totalLength - newConfig.steps[4].right) + " mm" +

            "</div>" +

            "<div>" +

            "Eindlengte profiel bij levereing (evt. incl. eindkap of eindkappen): " + (newConfig.totalLength) + " mm" +

            "</div>";


        /* Validated */

        return newConfig;                                                                           // Return the validated object

    }

    function fnProcessConfiguration() {

        /** This function is called to process a new configuration, this configuration is loaded

         * using the function fnConfigurationValidator().                                        */

        myInterface.find("#APP_aeris_inputform input#configuration").removeClass("valid warning invalid");// Remove classes since the object needs to be tested

        var newConfig = fnValidateConfiguration(myInterface.find("#APP_aeris_inputform input#configuration").val());// Copy the entered value and create and validate the new configuration

        if (newConfig.errMsg !== false) {                                                             // Test if the configuration contains an error message

            myInterface.find("#APP_aeris_inputform input#configuration").addClass("invalid");       // Add invalid class since the enterd configuration was not valid

            if (newConfig.errMsg != null) {                                                           // Test if an error message was added

                $("form#APP_aeris_inputform span#configurationError").html(newConfig.errMsg).fadeIn(250);// Add error message to the DOM and make message visible

                $("form#APP_aeris_inputform input#configuration").select().focus();                 // Select input form

            }

            return;                                                                                 // Stop processing and return

        }

        myInterface.find("#result").slideUp(250, function () {                                        // Hide information

            myInterface.find("#BVPvalue").html("&ndash;");                                          // Reset BVP field

            myInterface.find("#UNIT4").removeClass("clickable").off("click");                       // Remove click event and class

            myInterface.find("#Assembly").removeClass("clickable").off("click");                    // Remove click event and class

            $("form#APP_aeris_inputform span#configurationError").fadeOut(250);                     // Hide error message

            myInterface.find("#APP_aeris_inputform input#configuration").val(newConfig.artNmb);     // Copy the formated configuration code in the input field

            myInterface.find("#VerifiedConfig").html(newConfig.artNmb);                             // Copy the formated configuration code in the header

            myInterface.find("#ConfigInfo").html(newConfig.info);                                   // Add config info to the DOM

            myInterface.find("#complexProduct").hide();                                             // Hide complex information

            $('#ComplexConfigInfo').jqGrid('clearGridData').trigger('reloadGrid');                  // Clear the grid

            if (newConfig.warning.length > 0)                                                        // Test for warnings

                myInterface.find("#ConfigInfo").append(newConfig.warning + "<hr />");               // Append warnings to the DOM

            if (newConfig.isValid !== true)                                                          // Test if the configuration is valid

                myInterface.find("#APP_aeris_inputform input#configuration").addClass("invalid");   // Add invalid class since the enterd configuration contains errors

            else if (newConfig.warning.length > 0)                                                   // Test for warnings

                myInterface.find("#APP_aeris_inputform input#configuration").addClass("warning");   // Add warning class since the enterd configuration contains some warning info

            else

                myInterface.find("#APP_aeris_inputform input#configuration").addClass("valid");     // Add valid class since the enterd configuration passed validation

            if (newConfig.isValid !== true)                                                          // Test if the configuration is valid

                return myInterface.find("#result").slideDown(250);                                  // Not valid, show configuration information and end processing

            if (newConfig.complex === false)                                                         // Test if this configuration is complex

                myInterface.find("#BVPvalue").html(fnFormatEuro(newConfig.UNIT4.BVP));              // Simple product, copy the (formated) BVP info

            else {

                myInterface.find("#complexProduct").show();                                         // Complex product, show complex information

                $('#ComplexConfigInfo').jqGrid('clearGridData')                                     // Clear data grind

                    .jqGrid('setGridParam', {                                                        // Set grid parameters

                        data: newConfig.complex.data,                                               // Load new data

                        userData: {count: "Totaal:", total: fnFormatEuro(newConfig.complex.total) + "&nbsp;"}
                    })// Load footer data

                    .trigger('reloadGrid');                                                         // Trigger reload and update grid

                myInterface.find("#BVPvalue").html("(zie tabel met offerte en/of order informatie)");// Do not show price for complex products

            }

            myInterface.find("#UNIT4").addClass("clickable").on("click", function (e) {               // Make clickable and add event handler

                e.preventDefault();                                                                 // Prevent default event handler

                fnShowUNIT4info(newConfig.UNIT4);                                                   // Call function to show information

            });

            myInterface.find("#Assembly").addClass("clickable").on("click", function (e) {            // Make clickable and add event handler

                e.preventDefault();                                                                 // Prevent default event handler

                fnShowAssemblyInfo(newConfig.AssemblyInfo);                                         // Call function to show information

            });

            myInterface.find("#result").slideDown(250);                                             // Show configuration information

        });

    }

    function fnShowUNIT4info(UNIT4info) {

        /** This function is called to show the UNIT4 information on the current validated object. */

        if (UNIT4info.isValid !== true)                                                              // Test if the passed object is valid

            return;                                                                                 // Not valid, return

        fnHideUNIT4info();                                                                          // Hide any old objects

        fnShowOverlay();                                                                            // Add overlay

        $("body").append(                                                                           // Add content to the DOM

            "<div id=\"APP_aeris_UI_UNIT4Container\" style=\"position: " + (fnNeedIE6fix() ? 'absolute' : 'fixed') + "\">" +

            "<div class=\"UNIT4formContainer\">" +

            "<h1>UNIT4 informatie: " + UNIT4info.Prod + "</h1>" +

            "<fieldset>" +

            "<form id=\"UNIT4form\" action=\"\" method=\"post\">" +

            "<p>" +

            "De informatie in dit scherm kan worden gebruikt voor het aanmaken van het product met het nummer '" + UNIT4info.Prod + "' in UNIT4. " +

            "De onderstaande gegevens zijn gebaseerd op het ingevoerde nummer, eventuele accessoires  zijn hierbij <b><u><i>niet</i></u></b> meegenomen, deze dienen load door de afdeling verkoop te worden opgenomen in een offerte en/of order. " +

            "</p>" +

            "<table class=\"UNIT4details\">" +

            "<tr>" +

            "<td class=\"option\">Product:</td>" +

            "<td id=\"APP_aeris_UNIT4_Prod\"></td>" +

            "<td></td>" +

            "<tr>" +

            "</tr>" +

            "<td class=\"option\">Omschrijving: (intern)</td>" +

            "<td id=\"APP_aeris_UNIT4_Desc\">-</td>" +

            "<td></td>" +

            "</tr>" +

            "</tr>" +

            "<td class=\"option\">Zoeknaam:</td>" +

            "<td id=\"APP_aeris_UNIT4_Zoek\">-</td>" +

            "<td></td>" +

            "</tr>" +

            "</tr>" +

            "<td class=\"option\">Productomschrijving Duits:</td>" +

            "<td id=\"APP_aeris_UNIT4_OmsDE\" class=\"textarea\"></td>" +

            "<td></td>" +

            "</tr>" +

            "</tr>" +

            "<td class=\"option\">Productomschrijving Engels:</td>" +

            "<td id=\"APP_aeris_UNIT4_OmsEN\" class=\"textarea\"></td>" +

            "<td></td>" +

            "</tr>" +

            "</tr>" +

            "<td class=\"option\">Productomschrijving Frans:</td>" +

            "<td id=\"APP_aeris_UNIT4_OmsFR\" class=\"textarea\"></td>" +

            "<td></td>" +

            "</tr>" +

            "</tr>" +

            "<td class=\"option\">Productomschrijving NL:</td>" +

            "<td id=\"APP_aeris_UNIT4_OmsNL\" class=\"textarea\"></td>" +

            "<td></td>" +

            "</tr>" +

            "</tr>" +

            "<td class=\"option\">VVP: (kostprijs)</td>" +

            "<td id=\"APP_aeris_UNIT4_VVP\">-</td>" +

            "<td></td>" +

            "</tr>" +

            "</tr>" +

            "<td class=\"option\">Bruto verkoopprijs:</td>" +

            "<td id=\"APP_aeris_UNIT4_BVP\">-</td>" +

            "<td></td>" +

            "</tr>" +

            "</table>" +

            "<div id=\"jqGridContainer_List\"><table id=\"jqGrid_List\"></table></div>" +

            "<input type=\"submit\" id=\"close\" name=\"close\" value=\"Sluiten\">" +

            "</form>" +

            "</fieldset>" +

            "</div>" +

            "</div>");

        $("#APP_aeris_UI_UNIT4Container #APP_aeris_UNIT4_Prod").html(UNIT4info.Prod);               // Copy Product number

        $("#APP_aeris_UI_UNIT4Container #APP_aeris_UNIT4_Desc").html(UNIT4info.Desc);               // Copy product name

        $("#APP_aeris_UI_UNIT4Container #APP_aeris_UNIT4_Zoek").html(UNIT4info.Zoek);               // Copy Search name

        $("#APP_aeris_UI_UNIT4Container #APP_aeris_UNIT4_OmsDE").html(UNIT4info.Omschrijving.DE);   // Copy German description

        $("#APP_aeris_UI_UNIT4Container #APP_aeris_UNIT4_OmsEN").html(UNIT4info.Omschrijving.EN);   // Copy English description

        $("#APP_aeris_UI_UNIT4Container #APP_aeris_UNIT4_OmsFR").html(UNIT4info.Omschrijving.FR);   // Copy French description

        $("#APP_aeris_UI_UNIT4Container #APP_aeris_UNIT4_OmsNL").html(UNIT4info.Omschrijving.NL);   // Copy Dutch description

        $("#APP_aeris_UI_UNIT4Container #APP_aeris_UNIT4_VVP").html(fnFormatEuro(UNIT4info.VVP));   // Copy and format VVP

        $("#APP_aeris_UI_UNIT4Container #APP_aeris_UNIT4_BVP").html(fnFormatEuro(UNIT4info.BVP));   // Copy and format BVP

        $("div#APP_aeris_UI_UNIT4Container form#UNIT4form").submit(function (e) {                     // Add event handler to 'close' button

            e.preventDefault();                                                                     // Prevent default event handler

            fnHideUNIT4info();                                                                      // Call function to close the form

        });

        $("#jqGrid_List").jqGrid({

            regional: 'nl',                                                                        // Set dutch

            datatype: "local",                                                                      // Use local data

            data: [],                                                                               // No data loaded

            height: 150,                                                                            // Set height in px

            colNames: ['Product', 'Omschrijving', 'VVP', 'Aantal', 'Totaal'],                        // Set headings for the grid

            colModel: [                                                                              // Load grid parameters

                {name: 'code', index: 'code', width: 90, align: "center", key: true},

                {name: 'name', index: 'name', width: 270, align: "left"},

                {name: 'vvp', index: 'vvp', width: 70, align: "right"},

                {name: 'count', index: 'count', width: 100, align: "center"},

                {name: 'total', index: 'total', width: 70, align: "right"}

            ],

            sortable: false,                                                                        // Do not allow sorting

            loadonce: true,                                                                         // Do not reload data

            caption: "Stuklijst:"                                                                   // Set grid caption

        });

        try {                                                                                       // Create safe try-catch block

            var newData = [];                                                                       // Create empty array for loading data

            UNIT4info.partlist.forEach(function (item) {                                              // Loop through all items

                newData.push({                                                                      // Add part to array

                    code: PARTS[item.Part].code,

                    name: "&nbsp;" + PARTS[item.Part].name,

                    count: fnFormatAmounth(item.Count, PARTS[item.Part].type),

                    vvp: fnFormatEuro(PARTS[item.Part].VVP) + "&nbsp;",

                    total: fnFormatEuro(PARTS[item.Part].VVP * item.Count) + "&nbsp;",

                });

            });

            $('#jqGrid_List').jqGrid('clearGridData')                                               // Clear all data in the jqGrid

                .jqGrid('setGridParam', {data: newData})                                            // Add the new data

                .trigger('reloadGrid');                                                             // Trigger reload and update the jqGrid

        } catch (e) {                                                                                 // Ctach errors

            jError("Er is een fout opgetreden tijdens het opmaken van de stuklijst.", "Fout tijdens opstellen stuklijst...");// Pop error message

        }

        $(window).on('resize', fnRepositionUNIT4info).trigger('resize');                            // Add event handler to window for resize event and trigger this event

    }

    function fnHideUNIT4info() {

        /** This function will hide the UNIT4 information.                                      */

        $("body div#APP_aeris_UI_UNIT4Container").remove();                                         // Remove the container from the DOM

        $("#APP_aeris_UI_Overlay").remove();                                                        // Remove the overlay from the DOM

        $(window).off('resize', fnRepositionUNIT4info);                                             // Remove the event handler

    }

    function fnRepositionUNIT4info() {

        /** This function reposition the container on a resize event.                           */

        $("#jqGrid_List").setGridWidth($("div#APP_aeris_UI_UNIT4Container div#jqGridContainer_List").width() - 2);// Update jqGrid width (- 2 for borders)

        var top = (($(window).height() / 2) - ($("#APP_aeris_UI_UNIT4Container").outerHeight() / 2)) + $.alerts.verticalOffset;// Calculate the top location

        var left = (($(window).width() / 2) - ($("#APP_aeris_UI_UNIT4Container").outerWidth() / 2)) + $.alerts.horizontalOffset;// Calculate the left location

        if (top < 0) top = 0;                                                                        // Top location cannot be negative

        if (left < 0) left = 0;                                                                      // Left location cannot be negative

        if (fnNeedIE6fix())                                                                          // Test if IE6 patch is needed

            top = top + $(window).scrollTop();                                                      // Update top position (IE6 patch)

        $("#APP_aeris_UI_UNIT4Container").css({                                                     // Set new position

            top: top + 'px',

            left: left + 'px'

        });

        $("#APP_aeris_UI_UNIT4Overlay").height($(document).height());                               // Resize the overlay to fit the window

    }

    function fnShowAssemblyInfo(AssemblyInfo) {

        /** This function is called to show the assembly information on the current validated object. */

        if (AssemblyInfo == null)                                                                    // Test if the passed object contains data

            AssemblyInfo = "* Geen assemblage informatie geladen *";                                // Not valid, set default message

        fnHideAssemblyinfo();                                                                       // Hide any old objects

        fnShowOverlay();                                                                            // Add overlay

        $("body").append(                                                                           // Add content to the DOM

            "<div id=\"APP_aeris_UI_AssemblyContainer\" style=\"position: " + (fnNeedIE6fix() ? 'absolute' : 'fixed') + "\">" +

            "<div class=\"AssemblyFormContainer\">" +

            "<h1>Assemblage informatie:</h1>" +

            "<fieldset>" +

            "<form id=\"Assemblyform\" action=\"\" method=\"post\">" +

            AssemblyInfo +

            "<input type=\"submit\" id=\"close\" name=\"close\" value=\"Sluiten\">" +

            "</form>" +

            "</fieldset>" +

            "</div>" +

            "</div>");

        $("div#APP_aeris_UI_AssemblyContainer form#Assemblyform").submit(function (e) {               // Add event handler to 'close' button

            e.preventDefault();                                                                     // Prevent default event handler

            fnHideAssemblyinfo();                                                                   // Call function to close the form

        });

        $(window).on('resize', fnRepositionAssemblyInfo).trigger('resize');                         // Add event handler to window for resize event and trigger this event

    }

    function fnHideAssemblyinfo() {

        /** This function will hide the assembly information.                                      */

        $("body div#APP_aeris_UI_AssemblyContainer").remove();                                      // Remove the container from the DOM

        $("#APP_aeris_UI_Overlay").remove();                                                        // Remove the overlay from the DOM

        $(window).off('resize', fnRepositionAssemblyInfo);                                          // Remove the event handler

    }

    function fnRepositionAssemblyInfo() {

        /** This function reposition the container on a resize event.                           */

        var top = (($(window).height() / 2) - ($("#APP_aeris_UI_AssemblyContainer").outerHeight() / 2)) + $.alerts.verticalOffset;// Calculate the top location

        var left = (($(window).width() / 2) - ($("#APP_aeris_UI_AssemblyContainer").outerWidth() / 2)) + $.alerts.horizontalOffset;// Calculate the left location

        if (top < 0) top = 0;                                                                        // Top location cannot be negative

        if (left < 0) left = 0;                                                                      // Left location cannot be negative

        if (fnNeedIE6fix())                                                                          // Test if IE6 patch is needed

            top = top + $(window).scrollTop();                                                      // Update top position (IE6 patch)

        $("#APP_aeris_UI_AssemblyContainer").css({                                                  // Set new position

            top: top + 'px',

            left: left + 'px'

        });

        $("#APP_aeris_UI_Overlay").height($(document).height());                                    // Resize the overlay to fit the window

    }

    function fnShowOverlay() {

        /** This function will remove any excisting overlays from the DOM and adds a new one.   */

        $("#APP_aeris_UI_Overlay").remove();                                                        // Remove any old objects

        $("body").append('<div id="APP_aeris_UI_Overlay"></div>');                                  // Add a new overlay

    }

    function fnNeedIE6fix() {

        /** This function returns true if the IE6 patch is needed.                              */

        if (navigator.appName == 'Microsoft Internet Explorer') {

            var ua = navigator.userAgent;

            var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");

            if (re.exec(ua) != null)

                return (parseFloat(RegExp.$1) <= 6);

        }

        return false;

    }


    /* Events */

    $("body>main>div").one("pageLoaderComplete", fnBindInterface);                                  // Bind event handler (single trigger)


});

//# sourceURL=aeris.j
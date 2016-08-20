$(document).ready(function(){
    $("form").submit(function(e){
        var values = {};
		$.each($("form").serializeArray(), function(i, field) {
		    values[field.name] = field.value;
		});
		
		writeXML(values);
        e.preventDefault();
    });
});

function writeXML(values) {
	var XML = new XMLWriter();
	
    XML.BeginNode("mconference");
    XML.Node("name", values["conference-name"]);
    XML.Node("logo", values["logo-url"]);
    XML.Node("venue", values["venue"]);

    XML.BeginNode("time");
    var start = new Date(values["start-date"]);
    XML.Node("start", start.getTime().toString());
    var end = new Date(values["end-date"]);
    XML.Node("end", end.getTime().toString());
	XML.EndNode();

	XML.BeginNode("splashbg");
	XML.Attrib("type", values["splash-bg"]);
	XML.WriteString(values["splash-bg-value"]);
	XML.EndNode();

	XML.Node("aboutbg", values["about-bg"]);
	XML.Node("details", values["details"]);
	XML.Node("reglink", values["reg-link"]);
	XML.Node("xmlupdatelink", values["xmlupdate-link"]);

	XML.BeginNode("sponsors");
	XML.Attrib("type", values["sponsors"]);
	if(values["sponsors"] == 'individual') {
		var i;
		for (i = 1; values["sponsor-"+i+"-name"]; i++) { 
	    	XML.BeginNode("item");
	    	XML.Attrib("id", i.toString());
	    	XML.Node("name", values["sponsor-"+i+"-name"]);
			XML.Node("image", values["sponsor-"+i+"-link"]);
			XML.EndNode();
		}
	} 
	else {
		XML.WriteString(values["sponsors-link"]);
	}
	XML.EndNode();

	XML.BeginNode("talks");
	for (i = 1; values["talk-"+i+"-name"]; i++) { 
	    	XML.BeginNode("item");
	    	XML.Attrib("id", i.toString());
	    	XML.Node("name", values["talk-"+i+"-name"]);
	    	XML.Node("desc", values["description-"+i]);

	    	var start = new Date(values["date-"+i] + " " + values["start-time-"+i]);
	    	XML.Node("start", start.getTime().toString());

	    	var end = new Date(values["date-"+i] + " " + values["end-time-"+i]);
	    	XML.Node("end", end.getTime().toString());

	    	XML.Node("venue", values["venue-"+i]);
			XML.Node("image", values["talk-"+i+"-img"]);
			XML.EndNode();
		}
	XML.EndNode();

	XML.EndNode();
	var blob = new Blob([XML.ToString()], {type: "application/xml;charset=utf-8"});
  	saveAs(blob, "config.xml");
}

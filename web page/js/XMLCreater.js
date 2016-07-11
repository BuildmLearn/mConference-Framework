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
    XML.Node("start", values["venue"]);
    XML.Node("end", values["venue"]);
	XML.EndNode();

	XML.BeginNode("splashbg");
	XML.Attrib("type", values["splash-bg"]);
	XML.WriteString(values["splash-bg-value"]);
	XML.EndNode();

	XML.Node("aboutbg", values["about-bg"]);
	XML.Node("details", values["details"]);
	XML.Node("reglink", values["reg-link"]);

	XML.EndNode();
	var blob = new Blob([XML.ToString()], {type: "application/xml;charset=utf-8"});
  	saveAs(blob, "config.xml");
}
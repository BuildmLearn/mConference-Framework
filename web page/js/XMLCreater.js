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
    XML.BeginNode("Foo");
	XML.Attrib("Bar", "Some Value");
	XML.WriteString("Hello World");
	XML.EndNode();
	var blob = new Blob([XML.ToString()], {type: "application/xml;charset=utf-8"});
  	saveAs(blob, "kkk.xml");
}
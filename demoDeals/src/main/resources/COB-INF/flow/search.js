
function searchSite(){
    var searchSite = cocoon.getComponent("searchSite");
    var viewData = searchSite.process(cocoon.request, new java.lang.String(cocoon.parameters["pagenum"]));
	cocoon.sendPage("ShowPage", viewData);    
}
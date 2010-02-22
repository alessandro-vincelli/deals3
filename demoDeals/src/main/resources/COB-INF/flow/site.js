
function pageFlow(){
    var displayPage = cocoon.getComponent("displayPage");
    var viewData = displayPage.process(cocoon.request, new java.lang.String(cocoon.parameters["pagenum"]));
    cocoon.sendPage("ShowPage", viewData);
}
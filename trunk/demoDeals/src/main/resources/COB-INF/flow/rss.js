
function rssFlow(){
    var displayPage = cocoon.getComponent("displayRss");
    var viewData = displayPage.process(cocoon.request);
    cocoon.sendPage("ShowRss", viewData); 
}
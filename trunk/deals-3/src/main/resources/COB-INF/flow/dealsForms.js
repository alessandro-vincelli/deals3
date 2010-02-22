cocoon.load("servlet:forms:/resource/internal/flow/javascript/Form.js");
//defineClass("org.deals.framework.cms.forms.wrapper.CategoryFW");
importClass (Packages.org.deals.framework.cms.forms.wrapper.CategoryFW);
function authentication() {
/*
    var form = new Form("cocoon://resource/internal/forms/Authentication.xml");
	
    form.createBinding("cocoon://resource/internal/forms/binding/Authentication_binding.xml");
    
    var eh = cocoon.getComponent("authenticationEH");
    
    eh.initForm(form.getWidget(), cocoon.request, form.binding);
    
    form.getWidget().setFormHandler(eh);
    
    var verified = false;
    var ac = cocoon.getComponent("authenticationCheck");
    while(!(verified)){
    	form.showForm("Authentication-display-pipeline?menu=false");
    	verified = ac.verify(cocoon.request, form.lookupWidget ("deals_username").getValue(), form.lookupWidget ("deals_password").getValue());
    }
	
	print("______form" + form.isValid);

    //var viewData = { "username" : form.getChild("name").getValue() }*/
    //cocoon.sendPage("Authentication-display-pipeline?menu=false", viewData);
    cocoon.sendPage("Authentication-display-pipeline?menu=false");
    //cocoon.redirectTo("management.cflow")
}

function management() {
    var form = new Form("cocoon://resource/internal/forms/Management.xml");
    form.locale= _determineLocale();
    form.createBinding("cocoon://resource/internal/forms/binding/Management_binding.xml");
    var eh = cocoon.getComponent("managementEH");
    eh.initForm(form.getWidget(), cocoon.request, form.binding);
    form.getWidget().setFormHandler(eh);
    form.showForm("Management-display-pipeline");
    var viewData = { "username" : form.getChild("name").getValue() }
    cocoon.redirectTo("management.cflow")
}

function section() {
    var form = new Form("cocoon://resource/internal/forms/Section.xml");
    form.locale= _determineLocale();
    form.createBinding("cocoon://resource/internal/forms/binding/Section_binding.xml");
    var eh = cocoon.getComponent("sectionEH");
    var formWrapped = eh.initForm(form.getWidget(), cocoon.request, form.binding);
    var formManager = cocoon.getComponent("sectionManager");
    formManager.load(formWrapped, cocoon.request, form.binding)
    form.getWidget().setFormHandler(eh);
    form.showForm("Section-display-pipeline");
	formManager.persist(formWrapped, cocoon.request, form.binding)
    cocoon.redirectTo("management.cflow");
}

function category() {
    var form = new Form("cocoon://resource/internal/forms/Category.xml");
    form.locale= _determineLocale();
    form.createBinding("cocoon://resource/internal/forms/binding/Category_binding.xml");
    var eh = cocoon.getComponent("categoryEH");
    var formWrapped = eh.initForm(form.getWidget(), cocoon.request, form.binding);
    form.getWidget().setFormHandler(eh);
    var categoryManager = cocoon.getComponent("categoryManager");
    categoryManager.load(formWrapped, cocoon.request, form.binding)          
    form.showForm("Category-display-pipeline");
	categoryManager.persist(formWrapped, cocoon.request, form.binding)
    cocoon.redirectTo("management.cflow");
}

function item() {
    var form = new Form("cocoon://resource/internal/forms/Item.xml");
    form.locale= _determineLocale();
    form.createBinding("cocoon://resource/internal/forms/binding/Item_binding.xml");
    var eh = cocoon.getComponent("itemEH");
    var formManager = cocoon.getComponent("itemManager");
    var formWrapped= eh.initForm(form.getWidget(), cocoon.request, form.binding);
    form.getWidget().setFormHandler(eh);
    formManager.load(formWrapped, cocoon.request, form.binding);
    form.locale = _determineLocale();
    form.showForm("Item-display-pipeline");
	formManager.persist(formWrapped, cocoon.request, form.binding)
    cocoon.redirectTo("management.cflow");
}

function usersmanager() {
    var form = new Form("cocoon://resource/internal/forms/UsersManager.xml");
    form.locale= _determineLocale();
    form.createBinding("cocoon://resource/internal/forms/binding/UsersManager_binding.xml");
    var eh = cocoon.getComponent("usersManagerEH");
    eh.initForm(form.getWidget(), cocoon.request, form.binding);
    form.getWidget().setFormHandler(eh);
    form.showForm("UsersManager-display-pipeline");
    cocoon.redirectTo("management.cflow");
}

function profilesmanager() {
    var form = new Form("cocoon://resource/internal/forms/ProfilesManager.xml");
    form.locale= _determineLocale();
    form.createBinding("cocoon://resource/internal/forms/binding/ProfilesManager_binding.xml");
    var eh = cocoon.getComponent("profilesManagerEH");
    eh.initForm(form.getWidget(), cocoon.request, form.binding);
    form.getWidget().setFormHandler(eh);
    form.showForm("ProfilesManager-display-pipeline");
    cocoon.redirectTo("management.cflow");
}

function resourcesrepo() {
    var form = new Form("cocoon://resource/internal/forms/ResourcesRepo.xml");
    form.locale = _determineLocale();
    form.createBinding("cocoon://resource/internal/forms/binding/ResourcesRepo_binding.xml");
    var eh = cocoon.getComponent("resourcesRepoEH");
    eh.initForm(form.getWidget(), cocoon.request, form.binding);
    form.getWidget().setFormHandler(eh);
    form.showForm("ResourcesRepo-display-pipeline");
    cocoon.redirectTo("management.cflow");
}

function admin() {
    var form = new Form("cocoon://resource/internal/forms/Admin.xml");
    form.locale = _determineLocale();
    form.createBinding("cocoon://resource/internal/forms/binding/Admin_binding.xml");
    var eh = cocoon.getComponent("adminEH");
    eh.initForm(form.getWidget(), cocoon.request, form.binding);
    form.getWidget().setFormHandler(eh);
    form.showForm("Admin-display-pipeline");
    cocoon.redirectTo("management.cflow");
}

function commentsmanager() {
    var form = new Form("cocoon://resource/internal/forms/CommentsManager.xml");
    form.locale= _determineLocale();
    form.createBinding("cocoon://resource/internal/forms/binding/CommentsManager_binding.xml");
    var eh = cocoon.getComponent("commentsManagerEH");
    eh.initForm(form.getWidget(), cocoon.request, form.binding);
    form.getWidget().setFormHandler(eh);
    while (true){
    	form.showForm("CommentsManager-display-pipeline");
    }
    cocoon.redirectTo("management.cflow");
}

function logout() {
	cocoon.session.invalidate();
    cocoon.redirectTo("authentication.cflow");
}
function test() {
    var form = new Form("cocoon://resource/internal/forms/Test.xml");	      
    form.showForm("Test-display-pipeline-noajax");
}

function _determineLocale() {
    var localetemp =  cocoon.session.getAttribute('cauth-user-deals').getAttribute('CMS_LOGGED_USER_LANGUAGE');
    return Packages.org.apache.cocoon.i18n.I18nUtils.parseLocale(localetemp);
}
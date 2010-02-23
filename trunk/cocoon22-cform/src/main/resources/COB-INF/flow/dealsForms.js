cocoon.load("servlet:forms:/resource/internal/flow/javascript/Form.js");

function simpleForm() {
    var form = new Form("cocoon://resource/internal/forms/simpleForm.xml");
    form.createBinding("cocoon://resource/internal/forms/binding/simpleForm_binding.xml");
    var eh = cocoon.getComponent("simpleFormEH");
    var formManager = cocoon.getComponent("simpleFormManager");
    formManager.load(form.getWidget(), cocoon.request, form.binding)
    form.getWidget().setFormHandler(eh);
    form.showForm("simpleForm-display-pipeline");
	formManager.persist(form.getWidget(), cocoon.request, form.binding)
    cocoon.redirectTo("simpleForm");
}
cocoon.load("servlet:forms:/resource/internal/flow/javascript/Form.js");
importClass (Packages.org.deals.framework.cms.forms.formbean.UsersManagerFB);

function usersmanager() {
    var form = new Form("cocoon://resource/internal/forms/UsersManager.xml");
    form.createBinding("cocoon://resource/internal/forms/binding/UsersManager_binding.xml");
    var dao = cocoon.getComponent("userDAOHibernate");
    var fb = new UsersManagerFB();
    fb.setUsers(dao.findAll());
	form.load(fb);
    form.showForm("UsersManager-display-pipeline");
    form.save(fb);
	dao.makePersistenAll(fb.getUsers());
    cocoon.redirectTo("UsersManager.cflow");
}



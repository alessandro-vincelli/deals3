package org.deals.framework.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.deals.framework.bean.APageComponentsType;
import org.deals.framework.bean.APagePage;
import org.deals.framework.bean.AProfilePages;
import org.deals.framework.bean.Category;
import org.deals.framework.bean.CmsSection;
import org.deals.framework.bean.Comments;
import org.deals.framework.bean.ComponentType;
import org.deals.framework.bean.Item;
import org.deals.framework.bean.PageState;
import org.deals.framework.bean.PageType;
import org.deals.framework.bean.Section;
import org.deals.framework.bean.Template;
import org.deals.framework.bean.UserProfile;
import org.deals.framework.bean.Users;
import org.deals.framework.bean.WebComponent;
import org.deals.framework.bean.WebPage;
import org.deals.framework.bean.WebResource;
import org.deals.framework.core.util.UtilHttpSession;
import org.deals.framework.dao.APageComponentsTypeDAO;
import org.deals.framework.dao.APagePageDAO;
import org.deals.framework.dao.CmsSectionDAO;
import org.deals.framework.dao.CommentsDAO;
import org.deals.framework.dao.ComponentTypeDAO;
import org.deals.framework.dao.PageStateDAO;
import org.deals.framework.dao.PageTypeDAO;
import org.deals.framework.dao.SectionDAO;
import org.deals.framework.dao.TemplateDAO;
import org.deals.framework.dao.UserDAO;
import org.deals.framework.dao.UserProfileDAO;
import org.deals.framework.dao.WebComponentDAO;
import org.deals.framework.util.DateUtils;

public class WebCMS extends WebSite implements WebSiteInterface {

	// il WEBCMS deve avere un comportamento diverso da quello del WebSite,
	// deve essere pi� permissivo, non usa il filtro, quindi il filtro � sempre
	// disabled
	private Logger log = Logger.getLogger(getClass());

	//private UserDAO usDao = new UserDAOHibernate();
	private UserDAO usDao;

	private TemplateDAO templateDao;

	private PageStateDAO pageStateDao;

	private WebComponentDAO wcDao;

	private UserProfileDAO upDao;

	private APageComponentsTypeDAO apctDao;

	private ComponentTypeDAO ctDao;
	
	private CmsSectionDAO cmsSectionDAO;
	
	private PageTypeDAO pageTypeDAO;
	
	private APagePageDAO appDao;
	
	private CommentsDAO comDao;

	public WebCMS() {
		// disabilita il filtro delle webpage...
		disableFilter();
	}

	// ///////////////////////////////////////////////////////////////////////////////////////
	// AUTENTICAZIONE
	// ///////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Controlla l'esistenza dell'utente con la password specificata
	 * 
	 * @param username
	 *            nome utente
	 * @param password
	 *            password utente
	 * @return utente trovato oppure null se non c'� un utente corrispondente
	 */
	public Users checkUser(String username, String password) {
		return usDao.getUser(username, password);
	}

	/**
	 * Cancella una webcomponent
	 * 
	 * @return
	 */
	public WebComponent getWebComponent(Integer id) {
		return wcDao.findById(id, false);
	}
	/**
	 * Cancella una webcomponent
	 * 
	 * @return
	 */
	public void deleteWebComponent(WebComponent wc) {
		wcDao.makeTransient(wc);
	}

	/**
	 * Save webcomponent
	 * 
	 * @return
	 */
	public void saveWebComponent(WebComponent wc) {
		wcDao.makePersistent(wc);
	}

	/**
	 * Save all webcomponent
	 * 
	 * @return
	 */
	public void saveAllWebComponent(List<WebComponent> wc) {
		wcDao.makePersistenAll(wc);
	}

	
	/**
	 * Cancella una categoria
	 * 
	 * @return
	 */
	public void deleteCategory(WebPage category) {
		categoryDao.makeTransient(category);
	}

	/**
	 * Cancella una scheda
	 * 
	 * @return
	 */
	public void deleteItem(WebPage item) {
		itemDao.makeTransient(item);
	}

	/**
	 * Cancella una sezione
	 * 
	 * @return
	 */
	public void deleteSection(WebPage section) {
		secDao.makeTransient(section);
	}

	/**
	 * restituisce tutti i tipi di assocazioni a risorse
	 * 
	 * @return
	 */

	public List<APageComponentsType> getAPageComponentsTypes() {
		return apctDao.findAll();
	}

	
	/**
	 *  restituisce le voci del men� abilitate al profilo 
	 *  TODO
	 * @return
	 */
	
	public List<CmsSection> getCmsSection(){
		return cmsSectionDAO.findAllActiveSorted();
	}
	
	/**
	 * restituisce tutti i tipi di componenti
	 * 
	 * @return
	 */

	public List<ComponentType> getComponentsType() {
		return ctDao.findAll();
	}
	
	/**
	 * return all comments
	 * 
	 * @return
	 */

	public List<Comments> getComments() {
		return comDao.getAllComments();
	}
	
	/**
	 * return comment by Id
	 * 
	 * @return
	 */

	public Comments getComments(Long id) {
		return comDao.findById(id);
	}
	
	/**
	 * delete comments
	 * 
	 * @return
	 */

	public void deleteComments(Comments entity) {
		comDao.makeTransient(entity);
	}
	
	/**
	 * save comment
	 * @return 
	 */

	public void saveComment(Comments comment) {
		comDao.makePersistent(comment);
	}

	/**
	 * save all comment
	 * @return 
	 */

	public void saveAllComments(List<Comments> comments) {
		comDao.makePersistenAll(comments);
	}
	
	/**
	 * 
	 * Restituisce il PageState di default
	 * 
	 * 
	 * @return
	 */
	public PageState getDefaultPageState() {
		// TODO nel file di configurazione
		return pageStateDao.findById(PageState.DRAFT_ID, false);
	}

	/**
	 * 
	 * Restituisce il template di default
	 * 
	 * TODO ora solo il primo
	 * 
	 * @return
	 */
	public Template getDefaultTemplate() {
		return templateDao.findAll().iterator().next();
	}

	/**
	 * 
	 * Restituisce il peso massimo per le sezioni presenti
	 * 
	 * @return
	 */
	public int getMaxWeightSection() {
		SectionDAO secDao1 = (SectionDAO) secDao;
		return secDao1.getMaxWeightSection();
	}
	
	/**
	 * 
	 * Restituisce i mime tipe delle risorse presenti nel datase
	 * 
	 * @return lista di mime tipe 
	 */

	public List<String> getDistinctMimeType() {
		return wcDao.getDistinctMimeType();
	}

	/**
	 * Restituisce lo stato pagina richiesto
	 * 
	 * @return
	 */
	public PageState getPageState(Integer pageStateId) {
		return pageStateDao.findById(pageStateId, false);
	}

	/**
	 * Restituisce la lista di tutti gli stati pagina possibili presenti nel db
	 * 
	 * @return
	 */
	public List<PageState> getPageStates() {
		return pageStateDao.findAll();
	}

	/**
	 * 
	 * Restituisce il PageType
	 * 
	 * @return
	 */
	public PageType getPageType(Integer typeID) {
		return pageTypeDAO.findById(typeID, false);
	}

	/**
	 * Non filtra nulla, deve tirare su tutto
	 * 
	 * @return
	 */
	public List<WebPage> getSections() {
		return secDao.findAll();
	}
	
	/**
	 * sections filtered by session user
	 * 
	 * @return
	 */
	public List<WebPage> getSections(HttpSession session) {
		UtilHttpSession hos = new UtilHttpSession(session);
		Users user = getUser(hos.getloggedUserId());
		if(user.getProfile().getUpId().equals(1)){
			return getSections();
		}
		else{
			List<WebPage> sections = new ArrayList<WebPage>();
			Set<AProfilePages> app = user.getProfile().getAProfilePageses();
			for (Iterator<AProfilePages> iterator = app.iterator(); iterator.hasNext();) {
				AProfilePages profilePages = (AProfilePages) iterator.next();
				if(profilePages.getWebPage().getPageType().equals(PageType.SECTION())){
					sections.add((Section)getSection(profilePages.getWebPage().getWpId()));
				}
			}
			return sections;	
		}
		
	}
	
	
	/**
	 * Restituisce il template richiesto
	 * 
	 * @param templateId
	 * @return
	 */
	public Template getTemplate(Integer templateId) {
		return templateDao.findById(templateId, false);
	}

	/**
	 * Restituisce la lista di tutti i template presenti nel db
	 * 
	 * @return
	 */
	public List<Template> getTemplates() {
		return templateDao.findAll();
	}

	public Category newCategory() {
		// FIXME vedi newSection in Section Binder
		Category page = new Category();
		page.setWpPublished(DateUtils.now());
		page.setWpWeight(0);
		return page;
	}

	public Item newItem() {
		// FIXME vedi newItem in Item Binder
		Item page = new Item();
		page.setWpPublished(DateUtils.now());
		page.setWpWeight(0);
		return page;
	}

	public Section newSection() {
		// FIXME vedi newSection in Section Binder
		Section page = new Section();
		page.setWpPublished(DateUtils.now());
		return page;
	}

	/**
	 * Salva una category
	 * 
	 * @return
	 */
	public void saveCategory(WebPage category) {
		categoryDao.makePersistent(category);
	}

	/**
	 * Salva una scheda
	 * 
	 * @return
	 */
	public void saveItem(WebPage item) {
		itemDao.makePersistent(item);
	}

	/**
	 * Salva una sezione
	 * 
	 * @return
	 */
	public void saveSection(WebPage section) {
		secDao.makePersistent(section);
	}
	
	/**
	 * Saves all items
	 * 
	 * @param items
	 *            itmes to be save
	 */
	public void saveAllItems(List<WebPage> items) {
		itemDao.makePersistenAll(items);
	}
	
	/**
	 * Saves all apagepage - relations
	 * 
	 * @param apagepage
	 *            apagepage to be save
	 */
	public void saveAllAPagePage(List<APagePage> aPagePages) {
		appDao.makePersistenAll(aPagePages);
	}

	/**
	 * Alza la priorit� della categoria passata
	 * 
	 * @param sectionId:
	 *            id della sezione
	 * @return
	 */
	public boolean setUpCategory(Integer categoryId, List<APagePage> categories) {
		// NON PUO' USARE QUELLO SOPRA PERCHE' LA PRIORITA' SE LA TIENE NEL
		// PROPRIO CAMPO, E QUI STA IL DANNO....
		// comunque si pu� fare un update selettivo, solo della weight,
		// quindi:
		// 1. tiro su tutte le sezioni ordinate per priorit�
		// 2. individuo l'indice della mia e della precedente,
		// 3. ottengo le priorit� da settare,
		// 4. faccio l'update delle priorit� e salvo

		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getWebPageByAppChild().getWpId().equals(categoryId)) {
				if (i == 0) {
					return false;
				} else {
					// indice trovato i...
					// 1. scambiare le priorit� tra questa e la precedente,
					int temp = categories.get(i).getAppWeight();
					categories.get(i).setAppWeight(categories.get(i - 1).getAppWeight());
					categories.get(i - 1).setAppWeight(temp);

					// 2. scambiare gli elementi nell'array con lo swap
					Collections.swap(categories, i, i - 1);
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Alza la priorit� della scheda passata
	 * 
	 * @param itemId:
	 *            id della item
	 * @return
	 */
	public boolean setUpItem(Integer itemId, List<APagePage> items) {
		// NON PUO' USARE QUELLO SOPRA PERCHE' LA PRIORITA' SE LA TIENE NEL
		// PROPRIO CAMPO, E QUI STA IL DANNO....
		// comunque si pu� fare un update selettivo, solo della weight,
		// quindi:
		// 1. tiro su tutte le sezioni ordinate per priorit�
		// 2. individuo l'indice della mia e della precedente,
		// 3. ottengo le priorit� da settare,
		// 4. faccio l'update delle priorit� e salvo

		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getWebPageByAppChild().getWpId().equals(itemId)) {
				if (i == 0) {
					return false;
				} else {
					// indice trovato i...
					// 1. scambiare le priorit� tra questa e la precedente,
					int temp = items.get(i).getAppWeight();
					items.get(i).setAppWeight(items.get(i - 1).getAppWeight());
					items.get(i - 1).setAppWeight(temp);

					// 2. scambiare gli elementi nell'array con lo swap
					Collections.swap(items, i, i - 1);
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Alza la priorit� della sezione passata
	 * 
	 * @param sectionId:
	 *            id della sezione
	 * @return
	 */
	public boolean setUpSection(Integer sectionId, List<WebPage> sections) {
		// NON PUO' USARE QUELLO SOPRA PERCHE' LA PRIORITA' SE LA TIENE NEL
		// PROPRIO CAMPO, E QUI STA IL DANNO....
		// comunque si pu� fare un update selettivo, solo della weight,
		// quindi:
		// 1. tiro su tutte le sezioni ordinate per priorit�
		// 2. individuo l'indice della mia e della precedente,
		// 3. ottengo le priorit� da settare,
		// 4. faccio l'update delle priorit� e salvo

		for (int i = 0; i < sections.size(); i++) {
			if (sections.get(i).getWpId().equals(sectionId)) {
				if (i == 0) {
					return false;
				} else {
					// indice trovato i...
					// 1. scambiare le priorit� tra questa e la precedente,
					int temp = sections.get(i).getWpWeight();
					sections.get(i).setWpWeight(sections.get(i - 1).getWpWeight());
					sections.get(i - 1).setWpWeight(temp);

					// 2. scambiare gli elementi nell'array con lo swap
					Collections.swap(sections, i, i - 1);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Abassa la priorit� della sezione passata
	 * 
	 * @param sectionId:
	 *            id della sezione
	 * @return
	 */
	public boolean tearDownCategory(Integer categoryId, List<APagePage> categories) {

		// NON PUO' USARE QUELLO SOPRA PERCHE' LA PRIORITA' SE LA TIENE NEL
		// PROPRIO CAMPO, E QUI STA IL DANNO....
		// comunque si pu� fare un update selettivo, solo della weight,
		// quindi:
		// 1. tiro su tutte le sezioni ordinate per priorit�
		// 2. individuo l'indice della mia e della precedente,
		// 3. ottengo le priorit� da settare,
		// 4. faccio l'update delle priorit� e salvo

		// Object[] secs = sections.toArray();
		// WebPage section = null;
		// WebPage sectionSotto = null;

		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getWebPageByAppChild().getWpId().equals(categoryId)) {
				if (i == categories.size() - 1) {
					return false;
				} else {
					// indice trovato i...
					// 1. scambiare le priorit� tra questa e la successiva,
					int temp = categories.get(i).getAppWeight();
					categories.get(i).setAppWeight(categories.get(i + 1).getAppWeight());
					categories.get(i + 1).setAppWeight(temp);

					// 2. scambiare gli elementi nell'array con lo swap
					Collections.swap(categories, i, i + 1);
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Abassa la priorit� della scheda passata
	 * 
	 * @param itemId:
	 *            id della item
	 * @return
	 */
	public boolean tearDownItem(Integer itemId, List<APagePage> items) {

		// NON PUO' USARE QUELLO SOPRA PERCHE' LA PRIORITA' SE LA TIENE NEL
		// PROPRIO CAMPO, E QUI STA IL DANNO....
		// comunque si pu� fare un update selettivo, solo della weight,
		// quindi:
		// 1. tiro su tutte le sezioni ordinate per priorit�
		// 2. individuo l'indice della mia e della precedente,
		// 3. ottengo le priorit� da settare,
		// 4. faccio l'update delle priorit� e salvo

		// Object[] secs = sections.toArray();
		// WebPage section = null;
		// WebPage sectionSotto = null;

		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getWebPageByAppChild().getWpId().equals(itemId)) {
				if (i == items.size() - 1) {
					return false;
				} else {
					// indice trovato i...
					// 1. scambiare le priorit� tra questa e la successiva,
					int temp = items.get(i).getAppWeight();
					items.get(i).setAppWeight(items.get(i + 1).getAppWeight());
					items.get(i + 1).setAppWeight(temp);

					// 2. scambiare gli elementi nell'array con lo swap
					Collections.swap(items, i, i + 1);
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Abassa la priorit� della sezione passata
	 * 
	 * @param sectionId:
	 *            id della sezione
	 * @return
	 */
	public boolean tearDownSection(Integer sectionId, List<WebPage> sections) {

		// NON PUO' USARE QUELLO SOPRA PERCHE' LA PRIORITA' SE LA TIENE NEL
		// PROPRIO CAMPO, E QUI STA IL DANNO....
		// comunque si pu� fare un update selettivo, solo della weight,
		// quindi:
		// 1. tiro su tutte le sezioni ordinate per priorit�
		// 2. individuo l'indice della mia e della precedente,
		// 3. ottengo le priorit� da settare,
		// 4. faccio l'update delle priorit� e salvo

		// Object[] secs = sections.toArray();
		// WebPage section = null;
		// WebPage sectionSotto = null;

		for (int i = 0; i < sections.size(); i++) {
			if (sections.get(i).getWpId().equals(sectionId)) {
				if (i == sections.size() - 1) {
					return false;
				} else {
					// indice trovato i...
					// 1. scambiare le priorit� tra questa e la successiva,
					int temp = sections.get(i).getWpWeight();
					sections.get(i).setWpWeight(sections.get(i + 1).getWpWeight());
					sections.get(i + 1).setWpWeight(temp);

					// 2. scambiare gli elementi nell'array con lo swap
					Collections.swap(sections, i, i + 1);
					return true;
				}
			}
		}
		return false;
	}

	// ////////////////////////////////////////////////////////////////////////////////////
	// G E S T I O N E I M M A G I N I ///
	// ////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Restituisce tutto il repository delle immagini presente nel CMS
	 * 
	 * @return tutte le immagini presenti nel CMS
	 */
	public List<WebResource> getImages() {
		List<WebResource> result = new Vector<WebResource>();
		List<WebComponent> images = wcDao.getImages();
		// settare l'href corretto...
		for (Iterator<WebComponent> iter = images.iterator(); iter.hasNext();) {
			result.add((WebResource) iter.next());
		}
		return result;
	}

	/**
	 * Restituisce tutte le web resources presenti nel CMS ordinate per??? FIXME
	 * 
	 * @return
	 */
	public List<WebComponent> getWebResources() {
		return wcDao.getWebResources();
	}

	// ////////////////////////////////////////////////////////////////////////////////////
	// G E S T I O N E U T E N T I ///
	// ////////////////////////////////////////////////////////////////////////////////////

	public List<Users> getAllUsers() {
		// return usDao.findAll();
		return usDao.getAllUsers();
	}

	/**
	 * Salva un utente
	 * 
	 * @param user
	 *            utente da salvare
	 */
	public Users getNewUser(Integer id) {
		Users user = new Users();
		return user;
	}

	/**
	 * Restituisce un utente per ID
	 * 
	 * @param Id
	 *            utente
	 */
	public Users getUser(Integer id) {
		return usDao.findById(id, true);

	}

	/**
	 * Elimina un utente
	 * 
	 * @param user
	 *            utente da eliminare
	 */
	public void deleteUser(Users user) {
		usDao.makeTransient(user);
	}

	/**
	 * Salva un utente
	 * 
	 * @param user
	 *            utente da salvare
	 */
	public void saveUser(Users user) {
		usDao.makePersistent(user);
	}

	/**
	 * Saves all users
	 * 
	 * @param users
	 *            users to be save
	 */
	public void saveAllUser(List<Users> users) {
		usDao.makePersistenAll(users);
	}

	
	/**
	 * Restituisce la lista di tutti i profili, compreso l'amministratore
	 * 
	 * @return
	 */
	public List<UserProfile> getProfili() {
		// return upDao.findAll();
		return upDao.getAllUserProfile();
	}

	/**
	 * Restituisce la lista dei profili, escluso l'amministratore. Utilizzato
	 * per la gestione dei permessi
	 * 
	 * @return
	 */
	public List<UserProfile> getProfiliGestiti() {
		List<UserProfile> profili = getProfili();
		// siccome tiro su i profili ordinati per id,
		// so per certo che il primo � l'amministratore
		//profili.remove(0);
		return profili;
	}

	public List<UserProfile> getProfiliGestiti(WebPage page) {
		List<UserProfile> profili = getProfiliGestiti();

		// ora passo a checkare tutti i profili per cui esiste una associazione
		// con la pagina
		Set apps = page.getAProfilePages();
		
		for (Iterator iter = profili.iterator(); iter.hasNext();) {
			UserProfile profile = (UserProfile) iter.next();		
			for (Iterator iter2 = apps.iterator(); iter2.hasNext();) {
				AProfilePages app = (AProfilePages) iter2.next();
				if (app.getProfile().equals(profile)) {
					profile.setAssociated(true);
				}
			}		
		}
		
		return profili;
	}

	public UserProfile geProfile(Integer id) {
		return upDao.findById(id, true);
	}
	
	public void deleteProfile(UserProfile profile) {
		upDao.makeTransient(profile);
	}

	public void saveProfile(UserProfile profile) {
		upDao.makePersistent(profile);
	}
	
	public String[] getTags(){
		List<WebPage> wps = itemDao.findAll();
		HashSet<String> tags = new HashSet<String>();
		for (Iterator iter = wps.iterator(); iter.hasNext();) {
			WebPage wp = (WebPage) iter.next();
			if(StringUtils.isNotEmpty(wp.getWpTags())){
				tags.addAll(Arrays.asList(wp.getWpTags().split(";")));
			}
		}
		return (String[]) tags.toArray(new String[tags.size()]);
	}

	public void setUsDao(UserDAO usDao) {
		this.usDao = usDao;
	}

	public void setCmsSectionDAO(CmsSectionDAO cmsSectionDAO) {
		this.cmsSectionDAO = cmsSectionDAO;
	}

	public void setTemplateDao(TemplateDAO templateDao) {
		this.templateDao = templateDao;
	}

	public void setPageStateDao(PageStateDAO pageStateDao) {
		this.pageStateDao = pageStateDao;
	}

	public void setWcDao(WebComponentDAO wcDao) {
		this.wcDao = wcDao;
	}

	public void setUpDao(UserProfileDAO upDao) {
		this.upDao = upDao;
	}

	public void setApctDao(APageComponentsTypeDAO apctDao) {
		this.apctDao = apctDao;
	}

	public void setCtDao(ComponentTypeDAO ctDao) {
		this.ctDao = ctDao;
	}

	public void setPageTypeDAO(PageTypeDAO pageTypeDAO) {
		this.pageTypeDAO = pageTypeDAO;
	}

	public void setAppDao(APagePageDAO appDao) {
		this.appDao = appDao;
	}

	public void setComDao(CommentsDAO comDao) {
		this.comDao = comDao;
	}
	
}

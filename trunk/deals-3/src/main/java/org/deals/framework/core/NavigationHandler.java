package org.deals.framework.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.deals.framework.bean.Comments;
import org.deals.framework.bean.WebPage;
import org.deals.framework.dao.WebPageDAO;
import org.deals.framework.util.DateUtils;
import org.deals.framework.util.SafeUtil;

/// TODO possibile ottimizzazione, mettere qui un bel metodo di init
/// dal cms si puo' chiamare la init per forzare l'aggiornamento,
/// altrimenti si usa sempre la stessa struttura...		

public class NavigationHandler {

	private TreeRoot doc = null;

	private WebPageDAO wpDao;


	public void setWpDao(WebPageDAO wpDao) {
		this.wpDao = wpDao;
	}

	public String getNavigationMenu(WebPage selectedPage, String queryString) {
		// removed, whit the IF below the navigationHandler NEVERE change  
		//if (doc == null)
			createNavigationMenu();

		if (selectedPage != null)
			doc.selectPage(selectedPage, parseQueryString(queryString));

		return doc.toXML();
	}

	// TODO migliorare il supporto al bread crumbs menu
	public String getBreadCrumbsMenu(WebPage selectedPage) {
		if (doc == null)
			createNavigationMenu();
		// deve tirar fuori il ramo corrispondente alla pagina selezionata
		if (doc != null) {
			// visita gli alberi e colleziona i nodi selected
			TreeRoot bct = new TreeRoot("bread-crumbs-menu");
			for (int i = 0; i < doc.getChildren().size(); i++) {
				TreeNode root = doc.getChildren().get(i);
				if (root.selected) {
					bct.addChild(root);
					break;
				}
			}

			List<TreeNode> children = bct.getChildren();
			while (children != null && children.size() > 0) {
				// elimina tutti i figli non selezionati
				for (int i = 0; i < children.size(); i++)
					if (!children.get(i).selected) {
						children.remove(children.get(i));
						i--;
					}
				if (children.size() > 0)
					children = children.get(0).children;
			}
			return bct.toXML();
		}
		return "";
	}

	private void createNavigationMenu() {
		doc = new TreeRoot("navigation-menu");
		// 1. recuperare le section dal db
		List<WebPage> sections = wpDao.findAll();
		// 2. per ogni section:
		for (Iterator iter = sections.iterator(); iter.hasNext();) {
			WebPage sec = (WebPage) iter.next();
			if (WebSite.filterPage(sec, true)!=null) {
				TreeNode node = new TreeNode(sec, null);
				doc.addChild(node);
			}
		}
	}

	private List<Integer> parseQueryString(String qS) {
		List<Integer> queue = new LinkedList<Integer>();
		if (!SafeUtil.isNullOrEmpty(qS)) {
			String[] parts = qS.split("&amp;");
			for (int i = 0; i < parts.length; i++) {
				String pageId = parts[i].split("=")[1];
				queue.add(Integer.parseInt(pageId));
			}
		}
		return queue;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	class TreeNode {

		private WebPage page;

		private TreeNode parent;

		private List<TreeNode> children;

		private boolean selected = false;

		public TreeNode(WebPage page, TreeNode parent) {
			this.page = page;
			this.parent = parent;
			createChildren();
		}

		private void createChildren() {
			if (page != null) {
				List<WebPage> wpChildren = page.getChildren();
				for (Iterator iter = wpChildren.iterator(); iter.hasNext();) {
					WebPage element = (WebPage) iter.next();
					if (WebSite.filterPage(element, true)!=null)
						addChild(new TreeNode(element, this));
				}
			}
		}

		public void select() {
			selected = true;
		}

		public void addChild(TreeNode tn) {
			if (children == null)
				children = new Vector<TreeNode>();
			children.add(tn);
		}

		public String getQueryString() {
			if (parent != null) {
				String qs = "";
				if (!SafeUtil.isNullOrEmpty(parent.getQueryString()))
					qs += parent.getQueryString() + "&amp;"
							+ getQsEncoding(parent.getWebPage());
				else
					qs += "?" + getQsEncoding(parent.getWebPage());

				return qs;
			} else
				return "";
		}

		/**
		 * Seleziona la pagina nel men� di navigazione. Se viene fornita una
		 * queryString da cui dedurre i padri della pagina verr� selezionato
		 * tutto il ramo relativo alla discendenza della pagina, altrimenti
		 * verr� selezionata soltanto la pagina, in quanto non si hanno
		 * informazioni sui padri, teoricamente per� si dovrebbe selezionare il
		 * primo ramo disponibile
		 * 
		 * @param selectedPage
		 * @param stackQs
		 */
		public void selectPage(WebPage selectedPage, List<Integer> stackQs) {

			if (stackQs != null && stackQs.size() > 0) {
				selectPageWithQueryString(selectedPage, stackQs);
			} else {
				// non ho info sui padri per cui devo andare a beccarmi la
				// pagina e al ritorno selezionarmi i padri
				selectPageWithoutQueryString(selectedPage);
			}			
		}

		private boolean selectPageWithoutQueryString(WebPage selectedPage) {
			List<TreeNode> children = getChildren();
			// se l'ho trovata restituisco true

			if (getWebPage() != null && getWebPage().equals(selectedPage)) {
				select();
				return true;
			}

			// altrimenti non � la pagina giusta, se non ha figli restituisco
			// false
			if (getChildren() == null)
				return false;

			for (Iterator iter = children.iterator(); iter.hasNext();) {
				TreeNode element = (TreeNode) iter.next();
				boolean result = element
						.selectPageWithoutQueryString(selectedPage);
				if (result) {
					select();
					return true;
				}
			}
			return false;
		}

		private void selectPageWithQueryString(WebPage selectedPage,
				List<Integer> stackQs) {
			// deve scorrere tutta la struttura seguendo le indicazioni della
			// queryString...
			// 1. elaborazione queryString per ottenere una List di id di pagina
			// 2. si seguono gli id della query string, terminati quelli si
			// passa al controllo della selectedPage
			if (stackQs.size() == 0) {

				// TODO TODO TODO non ci sono informazioni sui padri, per cui
				// devo selezionare il primo
				// ramo ... continuare
				// deve cercare la selectedPage tra i propri figli
				List<TreeNode> children = getChildren();
				if (children != null) {
					for (Iterator iter = children.iterator(); iter.hasNext();) {
						TreeNode node = (TreeNode) iter.next();
						node.selectPageWithQueryString(selectedPage, stackQs); // tanto
																				// stackQs
						// � vuoto
						if (node.getWebPage().getWpId().equals(
								selectedPage.getWpId())) {
							node.select();
							break;
						}
					}
				}
			} else {
				Integer pageId = stackQs.remove(0);
				for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
					TreeNode node = (TreeNode) iter.next();
					if (node.getWebPage().getWpId().equals(pageId)) {
						node.select();
						node.selectPageWithQueryString(selectedPage, stackQs);
						break;
					}
				}
			}
		}

		public String toXML() {
			if (page != null) {
				StringBuffer xml = new StringBuffer();
				xml
						.append("<"
								+ page.getPageType().getPtName()
								+ " id=\""
								+ page.getWpId()
								+ "\""
								+ " selected=\""
								+ selected
								+ "\">\n"
								+ "<name>"
								+ SafeUtil.safeToString(page.getWpName())
								+ "</name>\n"
								+ "<author>"
								+ SafeUtil.safeToString(page.getUsersByWpCreator().getUsFirstname() + " " + page.getUsersByWpCreator().getUsSurname())
								+ "</author>\n"
								+ "<content-title>"
								+ SafeUtil.safeToString(page
										.getWpContentTitle())
								+ "</content-title>\n"
								+ "<header>"
								+ SafeUtil.safeToString(page.getWpHeader())
								+ "</header>\n"
								// + "<content>"
								// + SafeUtil.safeToString(page.getContent())
								// + "</content>\n"
								+ "<footer>"
								+ SafeUtil.safeToString(page.getWpFooter())
								+ "</footer>\n"
								+ "<published>"
								+ (page.getWpPublished() != null ? DateUtils
										.toDateStringCustom(page
												.getWpPublished()) : "")
								+ "</published>\n"
								+ "<modified>"
								+ (page.getWpLastModified() != null ? (page
												.getWpLastModified()) : "")
								+ "</modified>\n"
								+ "<number-comments>"
								+ getNumberComments()
								+ "</number-comments>\n"
								+ "<href>"
								+ UrlCreator.createUrl(page.getWpId(), page
										.getPageType()) + this.getQueryString()
								+ "</href>");

				xml.append("<children>");
				if (children != null) {
					for (Iterator iter = children.iterator(); iter.hasNext();) {
						TreeNode element = (TreeNode) iter.next();
						xml.append(element.toXML());
					}
				}
				xml.append("</children>");
				xml.append("</" + page.getPageType().getPtName() + ">\n");
				return xml.toString();
			} else
				return "";
		}

		public WebPage getWebPage() {
			return page;
		}

		public List<TreeNode> getChildren() {
			return children;
		}

		private String getQsEncoding(WebPage page) {
			String enc = page.getPageType().getPtName().substring(0, 1);
			enc += "=" + page.getWpId();
			return enc;
		}
		
		private int getNumberComments(){
			int numComm = 0;
			for (Comments comment : (Set<Comments>)page.getCommentses()) {
				if (comment.getCommentApproved() == 1){
					numComm = numComm + 1;
				}
			}
			return numComm;
		}
		

	}

	class TreeRoot extends TreeNode {

		private String treeName;

		public TreeRoot(String treeName) {
			super(null, null);
			this.treeName = treeName;
		}

		@Override
		public String toXML() {
			StringBuffer xml = new StringBuffer();
			xml.append("<" + treeName + ">");
			if (getChildren() != null) {
				for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
					TreeNode element = (TreeNode) iter.next();
					xml.append(element.toXML());
				}
			}
			xml.append("</" + treeName + ">");
			return xml.toString();
		}

	}


//	public static void main(String[] args) throws IOException {
//		NavigationHandler n = new NavigationHandler();
//		n.createNavigationMenu();
//		FileWriter fw = new FileWriter("C:/testXML.xml");
//		fw.write(XmlUtils.prettyPrint(n.doc.toXML()));
//		fw.close();
//	}
	
	
}

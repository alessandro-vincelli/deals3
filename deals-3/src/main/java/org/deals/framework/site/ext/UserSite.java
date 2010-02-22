/*
 * Created on Feb 12, 2007
 *
 */
package org.deals.framework.site.ext;

import java.util.Date;

import org.deals.framework.util.DateUtils;

public class UserSite {
	
	  // Fields    

    private Integer idUbo;
    private String utente;
    private String username;
    private String pwd;
    private Date dataAttivaz;
    private String stato;
    private Date dataCessa;
    private Date dataUltimoAgg;
    private String mail;
    private String nome;
    private String cognome;
    private String codfiscPiva;
    private String ragioneSociale;
    private String indirizzo;
    private String comune;
    private String provincia;
    private String cap;
    private String telefono;
    private String emailNewsletter;
    private Short consensoNewsletter;
    private String contratto;
    private String ipAttivazione;
    private String pwdDomanda;
    private String pwdRisposta;
	
    public UserSite(String utente, String username, String pwd, Date dataAttivaz, String stato, Date dataCessa, Date dataUltimoAgg, String mail, String nome, String cognome, String codfiscPiva, String indirizzo, String comune, String provincia, String cap, String telefono, String emailNewsletter, String ipAttivazione, String pwdDomanda, String pwdRisposta) {
        this.utente = utente;
        this.username = username;
        this.pwd = pwd;
        this.dataAttivaz = dataAttivaz;
        this.stato = stato;
        this.dataCessa = dataCessa;
        this.dataUltimoAgg = dataUltimoAgg;
        this.mail = mail;
        this.nome = nome;
        this.cognome = cognome;
        this.codfiscPiva = codfiscPiva;
        this.indirizzo = indirizzo;
        this.comune = comune;
        this.provincia = provincia;
        this.cap = cap;
        this.telefono = telefono;
        this.emailNewsletter = emailNewsletter;
        this.ipAttivazione = ipAttivazione;
        this.pwdDomanda = pwdDomanda;
        this.pwdRisposta = pwdRisposta;
    }
    
    // Property accessors

    public Integer getIdUbo() {
        return this.idUbo;
    }
    
    public void setIdUbo(Integer idUbo) {
        this.idUbo = idUbo;
    }

    public String getUtente() {
        return this.utente;
    }
    
    public void setUtente(String utente) {
        this.utente = utente;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return this.pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getDataAttivaz() {
        return this.dataAttivaz;
    }
    
    public void setDataAttivaz(Date dataAttivaz) {
        this.dataAttivaz = dataAttivaz;
    }

    public String getStato() {
        return this.stato;
    }
    
    public void setStato(String stato) {
        this.stato = stato;
    }

    public Date getDataCessa() {
        return this.dataCessa;
    }
    
    public void setDataCessa(Date dataCessa) {
        this.dataCessa = dataCessa;
    }

    public Date getDataUltimoAgg() {
        return this.dataUltimoAgg;
    }
    
    public void setDataUltimoAgg(Date dataUltimoAgg) {
        this.dataUltimoAgg = dataUltimoAgg;
    }

    public String getMail() {
        return this.mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return this.cognome;
    }
    
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodfiscPiva() {
        return this.codfiscPiva;
    }
    
    public void setCodfiscPiva(String codfiscPiva) {
        this.codfiscPiva = codfiscPiva;
    }

    public String getRagioneSociale() {
        return this.ragioneSociale;
    }
    
    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getIndirizzo() {
        return this.indirizzo;
    }
    
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getComune() {
        return this.comune;
    }
    
    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return this.provincia;
    }
    
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCap() {
        return this.cap;
    }
    
    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmailNewsletter() {
        return this.emailNewsletter;
    }
    
    public void setEmailNewsletter(String emailNewsletter) {
        this.emailNewsletter = emailNewsletter;
    }

    public Short getConsensoNewsletter() {
        return this.consensoNewsletter;
    }
    
    public void setConsensoNewsletter(Short consensoNewsletter) {
        this.consensoNewsletter = consensoNewsletter;
    }

    public String getContratto() {
        return this.contratto;
    }
    
    public void setContratto(String contratto) {
        this.contratto = contratto;
    }

    public String getIpAttivazione() {
        return this.ipAttivazione;
    }
    
    public void setIpAttivazione(String ipAttivazione) {
        this.ipAttivazione = ipAttivazione;
    }

    public String getPwdDomanda() {
        return this.pwdDomanda;
    }
    
    public void setPwdDomanda(String pwdDomanda) {
        this.pwdDomanda = pwdDomanda;
    }

    public String getPwdRisposta() {
        return this.pwdRisposta;
    }
    
    public void setPwdRisposta(String pwdRisposta) {
        this.pwdRisposta = pwdRisposta;
    }
   
	
	/**
	 * Restituisce il tag XML corrispondente alla descrizione della pagina
	 * @param description descrizione della pagina
	 * @return attributo XML corrispondente alla descrizione della pagina
	 */
	protected String getTagIdUbo(Integer idUbo) {
		if (idUbo==null) idUbo=-1;
		return "<id_ubo>" + idUbo +"</id_ubo>";		
	}
	
	protected String getTagUtente(String utente) {
		if (utente==null) utente="";
		return "<utente>" + utente +"</utente>";		
	}
	protected String getTagUsername(String username) {
		if (username==null) username="";
		return "<username>" + username +"</username>";		
	}
	protected String getTagDataAttivaz(Date dataAttivaz) {
		if (dataAttivaz == null) return "<data_attivaz/>";
		else return "<data_attivaz>" + DateUtils.toDateStringCustom(dataAttivaz) +"</data_attivaz>";		
	}
	protected String getTagStato(String stato) {
		if (stato==null) stato="";
		return "<stato>" + stato +"</stato>";		
	}
	protected String getTagDataDataCessa(Date dataCessa) {
		if (dataCessa == null) return "<data_cessa/>";
		else return "<data_cessa>" + DateUtils.toDateStringCustom(dataCessa) +"</data_cessa>";		
	}
	protected String getTagDataUltimoAgg(Date dataUltimoAgg) {
		if (dataUltimoAgg == null) return "<data_ultimo_agg/>";
		else return "<data_ultimo_agg>" + DateUtils.toDateStringCustom(dataUltimoAgg) +"</data_ultimo_agg>";		
	}
	protected String getTagMail(String mail) {
		if (mail==null) mail="";
		return "<mail>" + mail +"</mail>";		
	}
	protected String getTagNome(String nome) {
		if (nome==null) nome="";
		return "<nome>" + nome +"</nome>";		
	}
	protected String getTagCognome(String cognome) {
		if (cognome==null) cognome="";
		return "<cognome>" + cognome +"</cognome>";		
	}
	protected String getTagCodfiscPiva(String codfiscPiva) {
		if (codfiscPiva==null) codfiscPiva="";
		return "<codfisc_piva>" + codfiscPiva +"</codfisc_piva>";		
	}
	protected String getTagRagioneSociale(String ragioneSociale) {
		if (ragioneSociale==null) ragioneSociale="";
		return "<ragione_sociale>" + ragioneSociale +"</ragione_sociale>";		
	}
	protected String getTagIndirizzo(String indirizzo) {
		if (indirizzo==null) indirizzo="";
		return "<indirizzo>" + indirizzo +"</indirizzo>";		
	}
	protected String getTagComune(String comune) {
		if (comune==null) comune="";
		return "<comune>" + comune +"</comune>";		
	}
	protected String getTagProvincia(String provincia) {
		if (provincia==null) provincia="";
		return "<provincia>" + provincia +"</provincia>";		
	}
	protected String getTagCap(String cap) {
		if (cap==null) cap="";
		return "<cap>" + cap +"</cap>";		
	}
	protected String getTagTelefono(String telefono) {
		if (telefono==null) telefono="";
		return "<telefono>" + telefono +"</telefono>";		
	}
	protected String getTagEmailNewsletter(String emailNewsletter) {
		if (emailNewsletter==null) emailNewsletter="";
		return "<email_newsletter>" + emailNewsletter +"</email_newsletter>";		
	}
	protected String getTagConsensoNewsletter(Short consensoNewsletter) {
		if (consensoNewsletter==null) consensoNewsletter=0;
		return "<consenso_newsletter>" + consensoNewsletter +"</consenso_newsletter>";		
	}
	protected String getTagPwdDomanda(String pwdDomanda) {
		if (pwdDomanda==null) pwdDomanda="";
		return "<pwd_domanda>" + pwdDomanda +"</pwd_domanda>";		
	}
	protected String getTagPwdRisposta(String pwdRisposta) {
		if (pwdRisposta==null) pwdRisposta="";
		return "<pwd_risposta>" + pwdRisposta +"</pwd_risposta>";		
	}
	
	/**
	 * Restituisce la codifica XML dell'utente bolletta on line
	 * @return codifica XML della pagina Web 
	 */
	public String toXML() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<user-info>");
			sb.append(getTagIdUbo(this.getIdUbo()));
			sb.append(getTagUtente(this.getUtente()));
			sb.append(getTagUsername(this.getUsername()));
			sb.append(getTagDataAttivaz(this.getDataAttivaz()));
			sb.append(getTagStato(this.getStato()));
			sb.append(getTagDataDataCessa(this.getDataCessa()));
			sb.append(getTagDataUltimoAgg(this.getDataUltimoAgg()));
			sb.append(getTagMail(this.getMail()));
			sb.append(getTagNome(this.getNome()));
			sb.append(getTagCognome(this.getCognome()));
			sb.append(getTagCodfiscPiva(this.getCodfiscPiva()));
			sb.append(getTagRagioneSociale(this.getRagioneSociale()));
			sb.append(getTagIndirizzo(this.getIndirizzo()));
			sb.append(getTagComune(this.getComune()));
			sb.append(getTagProvincia(this.getProvincia()));
			sb.append(getTagCap(this.getCap()));
			sb.append(getTagTelefono(this.getTelefono()));
			sb.append(getTagEmailNewsletter(this.getEmailNewsletter()));
			sb.append(getTagConsensoNewsletter(this.getConsensoNewsletter()));
			sb.append(getTagPwdDomanda(this.getPwdDomanda()));
			sb.append(getTagPwdRisposta(this.getPwdRisposta()));
		sb.append("</user-info>");

		return sb.toString();		
	}
}

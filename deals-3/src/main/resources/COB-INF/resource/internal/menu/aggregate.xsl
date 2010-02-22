<?xml version="1.0"?>

<!--
	* Copyright 2005 Alessandro Vincelli
	*
	* @author <a href="mailto:av@alessandro.vincelli.name">Alessandro Vincelli</a>
	*
	* This program is free software; you can redistribute it and/or
	* modify it under the terms of the GNU General Public License
	* as published by the Free Software Foundation; either version 2
	* of the License, or (at your option) any later version.
	*
	* This program is distributed in the hope that it will be useful,
	* but WITHOUT ANY WARRANTY; without even the implied warranty of
	* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	* GNU General Public License for more details.
	*
	*
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:jx="http://apache.org/cocoon/templates/jx/1.0"
	xmlns:session="http://apache.org/xsp/session/2.0" xmlns:i18n="http://apache.org/cocoon/i18n/2.1">
	<xsl:param name="form_template" />
	<xsl:param name="pageid" />
	<xsl:param name="context" />
	<xsl:param name="current-date" />

	<!--
		devo mettere l'espressione in una variabile perchè altrimenti l'XPATH dell'xsl tira via le graffe il loro contenuto
	-->

	<xsl:variable name="cmsMenu">${cocoon.session.getAttribute('cauth-user-deals').getAttribute('CMS_LOGGED_MENU_SECTION')}</xsl:variable>
	<xsl:variable name="cmsSectionName">${cm.cmssName}</xsl:variable>
	<xsl:variable name="cmsSectionUri">${cm.cmssUri}</xsl:variable>
	<xsl:variable name="cmsSectionDescr">${cm.cmssDescription}</xsl:variable>


	<xsl:template match="/page">

			<head>
				<title>Deals CMS: Site builder</title>
			</head>
			<body>
				<div id="container">

					<div id="testata">
						<div id="logo"></div>
						<div id="settings">

							<div id="data_odierna">
								<xsl:value-of select="$current-date" />
								- ${cocoon.session.getAttribute('cauth-user-deals').getAttribute('firstName')} ${cocoon.session.getAttribute('cauth-user-deals').getAttribute('lastName')}
								<a href="logout.cflow" title="Esci dal cms" class="logout">Sign out</a>
							</div>

						</div>
						<div id="ricerca">
							<form method="post" action="" id="cercainterna">
								<fieldset>
									<legend><i18n:text i18n:catalogue="deals">deals.common.searchon</i18n:text></legend>
									<label for="ricerca">
										<span><i18n:text i18n:catalogue="deals">deals.common.search</i18n:text></span>
									</label>
									<div id="prericerca"></div>
									<div>
										<input id="inputricerca" title="Cerca nella Intranet" name="ricerca" type="text" />
									</div>
									<div id="postricerca"></div>
									<a href="" title="Per aiuto, consulta il manuale" class="btnaiuto"></a>
									<a href="" title="Collegati al front-end del sito" class="btnweb"></a>
								</fieldset>
							</form>

						</div>
					</div>

					<div id="menu" class="clearfix">
						<ul id="vocimenu">
							<jx:forEach var="cm" items="{$cmsMenu}">
								<li>
									<a href="{$context}/deals/{$cmsSectionUri}" title="{$cmsSectionDescr}">
										<i18n:text i18n:catalogue="deals">${cm.cmssName}</i18n:text>
										<!--
											-${cm.cmssForm}-${cm.cmssForm.indexOf(request.getRequestURI()) > 0}-${request.getRequestURI()}
										-->
									</a>
								</li>
								<!--			    
									<li class="set"><a href="{$context}/deals/{$cmsSectionUri}" title="{$cmsSectionDescr}">${cm.cmssName}</a></li>			    
								-->
							</jx:forEach>
						</ul>

						<ul class="menu5">
							<li class="bact">
								<a href="#nogo"><!--[if IE 7]><!--></a><!--<![endif]-->
								<!--[if lte IE 6]><table><tr><td><![endif]-->
								<ul>

									<li class="sub">
										<a class="drop" href="#nogo"><i18n:text i18n:catalogue="deals">deals.common.fastmenu.manpage</i18n:text><!--[if IE 7]><!--></a><!--<![endif]-->
										<!--[if lte IE 6]><table><tr><td><![endif]-->
										<ul id="icone">
											<li id="scheda">
												<a href="Item.cflow?pageid=-1"><i18n:text i18n:catalogue="deals">deals.common.fastmenu.newitem</i18n:text></a>
											</li>
											<li id="categoria">
												<a href="Category.cflow?pageid=-1"><i18n:text i18n:catalogue="deals">deals.common.fastmenu.newcategory</i18n:text></a>
											</li>
											<li id="sezione">
												<a href="Section.cflow?pageid=-1"><i18n:text i18n:catalogue="deals">deals.common.fastmenu.newitem</i18n:text></a>
											</li>
										</ul>
										<!--[if lte IE 6]></td></tr></table></a><![endif]-->
									</li>

									<li class="sub">
										<a class="drop" href="#nogo"><i18n:text i18n:catalogue="deals">deals.common.fastmenu.filterpages</i18n:text><!--[if IE 7]><!--></a><!--<![endif]-->
										<!--[if lte IE 6]><table><tr><td><![endif]-->
										<ul id="icone">
											<li id="bozze">
												<a href="#nogo"><i18n:text i18n:catalogue="deals">deals.common.fastmenu.drafts</i18n:text></a>
											</li>
											<li id="scadenti">
												<a href="#nogo"><i18n:text i18n:catalogue="deals">deals.common.fastmenu.expiring</i18n:text></a>
											</li>
											<li id="scadute">
												<a href="#nogo"><i18n:text i18n:catalogue="deals">deals.common.fastmenu.expired</i18n:text></a>
											</li>
										</ul>
										<!--[if lte IE 6]></td></tr></table></a><![endif]-->
									</li>

								</ul>

								<!--[if lte IE 6]></td></tr></table></a><![endif]-->
							</li>

						</ul>

					</div>



					<xsl:apply-templates />
					<div id="footer">
						Deals CMS Vers. 3.0
						<a href="mailto:av@alessandro.vincelli.name">Send a feedback</a>
					</div>

				</div>
			</body>
	</xsl:template>
	<xsl:template match="@*|node()" priority="-2">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>
	<xsl:template match="text()" priority="-1">
		<xsl:value-of select="." />
	</xsl:template>
</xsl:stylesheet>
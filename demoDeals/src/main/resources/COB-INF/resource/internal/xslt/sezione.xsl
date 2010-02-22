<!--
	Copyright 2006 Alessandro Vincelli a.vincelli@gmail.com
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:d="http://outerx.org/daisy/1.0"
	xmlns:n="http://outerx.org/daisy/1.0#navigation" xmlns:i18n="http://apache.org/cocoon/i18n/2.1" xmlns:urlencoder="xalan://java.net.URLEncoder"
	xmlns:page="http://apache.org/cocoon/paginate/1.0">

	<!--  <xsl:variable name="mountPoint" select="string(page/context/mountPoint)"/>-->
	<xsl:param name="context" />
	<xsl:variable name="mountPoint" />
	<!--    <xsl:variable name="layoutType" select="page/context/layoutType"/>-->
	<xsl:variable name="layoutType" select="base" />
    <xsl:include href="common.xsl" />
    
	<xsl:template match="web-page">
		<html>
			<head>
				<title>
					<xsl:copy-of select="page-info/page-title/node()" />
				</title>
				<xsl:choose>
					<xsl:when test="$layoutType = 'mini'">
						<link rel="stylesheet" type="text/css" href="{$mountPoint}/resources/styles/mini.css" />
					</xsl:when>
					<xsl:otherwise>
						<link rel="stylesheet" type="text/css" href="styles/main.css" />
						<script type="text/javascript" src="scripts/swfobject.js" />
					</xsl:otherwise>
				</xsl:choose>
				<xsl:copy-of select="extraHeadContent/node()" />
				<script type="text/javascript" src="{$mountPoint}/resources/js/daisy.js" />
				<script type="text/javascript" src="{$mountPoint}/resources/js/menu.js" />
			</head>
			<xsl:choose>
				<xsl:when test="$layoutType = 'plain'">
					<!--          <xsl:call-template name="plainLayout"/>-->
				</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="defaultLayout" />
				</xsl:otherwise>
			</xsl:choose>
		</html>
	</xsl:template>

	<xsl:template name="defaultLayout">

		<body>
			<div id="containerinterno" class="clearfix">
				<xsl:call-template name="sinistra" />

				<div id="destra">
					<div id="contattami">
						<a href="mailto:rossi.gianluca@crumbria.it" title="Scrivi a Gianluca"></a>
					</div>
					<div id="nav2">
						<div class="col49sx">
							<p>
								<a href="{$context}/" title="torna alla home page">Homepage</a>
							</p>
						</div>
						<div class="col49dx">
							<form id="cerca" action="{$context}/search/(1).html" method="POST">
								<fieldset>
									<legend>Cerca nel sito</legend>
									<div>
										<label for="cerca1">Cerca nel sito</label>
										<input type="text" name="searchtext" title="Cerca nel sito" id="ricerca" class="input" />
										<input type="hidden" name="href" value="{//page-info/href}" />
										<input type="submit" value="Cerca" alt="Cerca" title="cerca" class="submit" />
									</div>
								</fieldset>
							</form>
						</div>
					</div>

					<div id="spazio">
						<!--
							<div class="post">
							<xsl:for-each select="//bread-crumbs-menu/section">
							<a href="{href}" title="{name}">
							<xsl:value-of select="name"/>
							</a>
							</xsl:for-each>
							<xsl:for-each select="//bread-crumbs-menu/section/children/item">
							<a href="{href}" title="{name}">
							<xsl:value-of select="name"/>
							</a> </xsl:for-each>
							</div>-->

						<!-- PAGINAZIONE -->
						<xsl:call-template name="paginazione" />
						
							<xsl:for-each select="//page-components/sub-pages/items/child-item">
								<div id="listacom" class="clearfix">
									<dl>
										<dd>
											<xsl:value-of select="page-info/published" />
										</dd>
										<dt>
											<a href="{page-info/href}" title="{page-content/content-title}">
												<xsl:value-of select="page-content/content-title" />
											</a>
										</dt>
										<dd>
											<xsl:value-of select="page-content/header" />
										</dd>
										<dd class="cmd">
											<a href="{page-info/href}" title="Continua a leggere">Continua a leggere</a>
										</dd>
									</dl>
								</div>
							</xsl:for-each>
							
						<!-- PAGINAZIONE -->
						<xsl:call-template name="paginazione" />
					</div>
				</div>
			</div>

            <xsl:call-template name="bottom" />
		</body>
	</xsl:template>

	<xsl:template name="paginazione">
		<xsl:choose>
			<xsl:when test="//page-components/sub-pages/items/child-item ">
				<div class="paginazione">
					<div class="numero">
						<xsl:variable name="web-page-id" select="//web-page/@id" />
						<img src="images/page.gif"
							alt="Sono stati trovati {count(//navigation-menu/section[@id=$web-page-id]/children/item)} risultati" />
						Sono stati trovati
						<xsl:value-of select="count(//navigation-menu/section[@id=$web-page-id]/children/item)" />
						articoli
					</div>
					<div class="pagine">
						<span>
							<a href="{//page:link[@type='prev']/@uri}" title="indietro">
								<img src="images/indietro.gif" alt="indietro" />
							</a>
						</span>
						<span>
							Pagina
							<xsl:value-of select="//page:page/@current" />
							di
							<xsl:value-of select="//page:page/@total" />
						</span>
						<span>
							<a href="{//page:link[@type='next']/@uri}" title="avanti">
								<img src="images/avanti.gif" alt="avanti" />
							</a>
						</span>
					</div>
				</div>
			</xsl:when>
			<xsl:otherwise>
				<div class="avviso">Nessuna pagina presente.</div>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

</xsl:stylesheet>
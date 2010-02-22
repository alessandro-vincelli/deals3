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
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:d="http://outerx.org/daisy/1.0" xmlns:n="http://outerx.org/daisy/1.0#navigation"
    xmlns:i18n="http://apache.org/cocoon/i18n/2.1" xmlns:urlencoder="xalan://java.net.URLEncoder">

    <!--  <xsl:variable name="mountPoint" select="string(page/context/mountPoint)"/>-->
    <xsl:param name="context"/>
    <xsl:variable name="mountPoint" >{$context}/</xsl:variable>
    <!--    <xsl:variable name="layoutType" select="page/context/layoutType"/>-->
    <xsl:variable name="layoutType" select="base"/>
    <xsl:include href="common.xsl" />
    
    <xsl:template match="web-page">
        <html>
            <head>
                <title>
                    <xsl:value-of select="page-info/name"/>
                </title>
                <xsl:choose>
                    <xsl:when test="$layoutType = 'mini'">
                        <link rel="stylesheet" type="text/css" href="{$mountPoint}/resources/styles/mini.css"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <link rel="stylesheet" type="text/css" href="styles/main.css"/>

                        <script type="text/javascript" src="scripts/swfobject.js"/>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:copy-of select="extraHeadContent/node()"/>
                <script type="text/javascript" src="scripts/scripts.js"></script>
				<script type="text/javascript" src="scripts/prototype.js"></script>
				<script type="text/javascript" src="scripts/scriptaculous.js?load=effects"></script>
				<script type="text/javascript" src="scripts/lightbox.js"></script>
				<script src="scripts/sifr.js" type="text/javascript"></script>
				<script src="scripts/sifr-addons.js" type="text/javascript"></script>
                <link rel="stylesheet" href="styles/lightbox.css" type="text/css" media="screen" />
            </head>
            <xsl:choose>
                <xsl:when test="$layoutType = 'plain'">
                    <!--          <xsl:call-template name="plainLayout"/>-->
                </xsl:when>
                <xsl:otherwise>
                    <xsl:call-template name="defaultLayout"/>
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
                            <input type="text" name="searchtext" title="Cerca nel sito" id="ricerca" class="input"/>
							<input type="hidden" name="href" value="{//page-info/href}" />
                            <input type="submit" value="Cerca" alt="Cerca" title="cerca" class="submit"/>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>

        <div id="spazio">


<!--        <div class="post">
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


        <xsl:for-each select="//page-content">
            <div class="post">
                <p class="data">
                    <xsl:value-of select="//page-info/created"/>
                </p>
                <h2>
                    <xsl:value-of select="content-title"/>
                </h2>
                <p>
                    <xsl:value-of select="header"/>.</p>
                <p>
                    <xsl:copy-of select="content"/>.</p>
            </div>
        </xsl:for-each>
<xsl:if test="//web-page/page-components/web-resource[@type = 'generic_page_image']">
		<h4>Galleria d'immagini</h4>
<div class="fontpiccola">Per vedere la galleria, clicca su una delle foto seguenti e naviga tra le immagini cliccando "avanti" e "indietro". Al termine, premi "chiudi".</div>
<xsl:for-each select="//web-page/page-components/web-resource[@type = 'generic_page_image']">
    <xsl:variable name="basename" select="substring-before(fgposizi,'.jpg')"/>
    <xsl:value-of select="fgposizi" />
    <xsl:value-of select="$basename" />
   <div class="fotogalleria"><a href="{url}.full" rel="lightbox[1]" title="{description}" class="noborder"><img src="{url}.mini" class="noborder" /></a></div>
</xsl:for-each>
</xsl:if>



		</div>
        <div>
        </div>

    </div>
</div>

<xsl:call-template name="bottom" />
</body>
</xsl:template>


</xsl:stylesheet>
<!--
  Copyright 2006 Alessandro Vincelli av@alessandro.vincelli.name
  
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
  xmlns:n="http://outerx.org/daisy/1.0#navigation" xmlns:i18n="http://apache.org/cocoon/i18n/2.1" xmlns:urlencoder="xalan://java.net.URLEncoder">

  <xsl:template match="web-page">
    <html>
      <head>
        <meta name="author" content="Wink Hosting (www.winkhosting.com)" />
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
            <title>CrystalX</title>

    <meta name="description" content="..." />
    <meta name="keywords" content="..." />
    
    <link rel="index" href="./" title="Home" />
        
        <link rel="stylesheet" href="resources/styles/main.css" type="text/css"  media="screen,projection"/>
        <link rel="stylesheet" href="resources/styles/print.css" type="text/css" media="print"/>
        <link rel="stylesheet" href="resources/styles/aural.css" type="text/css" media="aural"/>
        <title>
          <xsl:copy-of select="page-info/page-title/node()" />
        </title>
      </head>
      <xsl:call-template name="defaultLayout" />
    </html>
  </xsl:template>

  <xsl:template name="defaultLayout">
  
  
  <body id="www-url-cz">

<!-- Main -->

<div id="main" class="box">

    <!-- Header -->
    <div id="header">

        <!-- Logotyp -->
        <h1 id="logo"><a href="./" title="Deals CMS [Go to homepage]">Deals<strong>CMS</strong><span></span></a></h1>
        <hr class="noscreen" />          

        <!-- Quick links -->
        <div class="noscreen noprint">

            <p><em>Quick links: <a href="#content">content</a>, <a href="#tabs">navigation</a>, <a href="#search">search</a>.</em></p>
            <hr />
        </div>

        <!-- Search -->
        <div id="search" class="noprint">

            <form action="" method="get">
                <fieldset><legend>Search</legend>
                    <label><span class="noscreen">Find:</span>
                    <span id="search-input-out"><input type="text" name="" id="search-input" size="30" /></span></label>
                    <input type="image" src="design/search_submit.gif" id="search-submit" value="OK" />
                </fieldset>
            </form>
        </div> <!-- /search -->

    </div> <!-- /header -->

     <!-- Main menu (tabs) -->
     <div id="tabs" class="noprint">

            <h3 class="noscreen">Navigation</h3>
            <ul class="box">
				<xsl:for-each select="//web-page/navigation-menu/section">
					<li>
					<xsl:if test="//web-page/@id = @id">
					<xsl:attribute name="id">active</xsl:attribute> 
					</xsl:if>
					<a href="{href}"><xsl:value-of select="name"></xsl:value-of><span class="tab-l"></span><span class="tab-r"></span></a></li>
				</xsl:for-each>
             </ul>
        <hr class="noscreen" />
     </div> <!-- /tabs -->

    <!-- Page (2 columns) -->
    <div id="page" class="box">
    <div id="page-in" class="box">

        <div id="strip" class="box noprint">

            <!-- RSS feeds -->
            <p id="rss"><strong>RSS:</strong> <a href="#">articles</a> / <a href="#">comments</a></p>
            <hr class="noscreen" />

            <!-- Breadcrumbs -->
            <p id="breadcrumbs">You are here: <a href="/">Home</a> 
                        <xsl:choose>
            <!--  Faccio vedere la sezione solo se siamo in una sezione, altrimenti faccio vedere solo la categoria -->
                <xsl:when test="not(//web-page/bread-crumbs-menu/section/children/category)">
                    <xsl:for-each select="//web-page/bread-crumbs-menu/section">
                        &gt; <a href="{href}" title="{name}">
                        <xsl:value-of select="name" />
                        </a>
                    </xsl:for-each>
                </xsl:when>
                <xsl:otherwise>
                <xsl:for-each select="//web-page/bread-crumbs-menu/section/children/category">
                &gt; <a href="{href}" title="{name}">
                    <strong><xsl:value-of select="name" /></strong>
                </a>
                </xsl:for-each>                
                </xsl:otherwise>
            </xsl:choose>
            </p>

            <hr class="noscreen" />
            
        </div> <!-- /strip -->

        <!-- Content -->
        <div id="content">


			<xsl:when test="//search-results/@size > 0 ">
			<!-- Article -->
            <div class="article">
			
				<p class="info noprint">
                    <span class="date"><img src="images/page.gif" alt="Sono stati trovati {//search-results/@size} risultati"/>Sono stati trovati 
                                    <xsl:value-of select="//search-results/@size"/> risultati</span><span class="noscreen">,</span>
                    <span class="cat"><span>Pagina
                                        <xsl:value-of select="//page:page/@current"/> di
                                        <xsl:value-of select="//page:page/@total"/> </span></span><span class="noscreen">,</span>
                    <span class="user"><a href="#">My name</a></span><span class="noscreen">,</span>
                    <span class="comments"><a href="#">Comments</a></span>
                </p>
            
            </div> <!-- /article -->
			<!-- 
                            <div class="paginazione">
                                <div class="numero">
                                    <img src="images/page.gif" alt="Sono stati trovati {//search-results/@size} risultati"/>Sono stati trovati 
                                    <xsl:value-of select="//search-results/@size"/> risultati</div>
                                <div class="pagine">
                                    <span>
                                        <a href="{//page:link[@type='prev']/@uri}" title="indietro">
                                            <img src="images/indietro.gif" alt="indietro"/>
                                        </a>
                                    </span>
                                    <span>Pagina
                                        <xsl:value-of select="//page:page/@current"/> di
                                        <xsl:value-of select="//page:page/@total"/> </span>
                                    <span>
                                        <a href="{//page:link[@type='next']/@uri}" title="avanti">
                                            <img src="images/avanti.gif" alt="avanti"/>
                                        </a>
                                    </span>
                                </div>
                            </div>
                            </xsl:when>
                            <xsl:otherwise>
                                <div class="avviso">
                                    Nessun risultato trovato.
                                </div>
                            </xsl:otherwise>
                            </xsl:choose>
 -->


          <xsl:for-each select="//web-page/page-components/sub-pages/categories/child-category[1]/page-components/sub-pages/items/child-item[1]">
            <!-- Article -->
            <div class="article">
				<h2><span><a href="#"><xsl:value-of select="page-info/name" /></a></span></h2>
				<p class="info noprint">
                    <span class="date"><xsl:value-of select="page-info/published" /></span><span class="noscreen">,</span>
                    <span class="cat"><a href="#">Category</a></span><span class="noscreen">,</span>
                    <span class="user"><a href="#">My name</a></span><span class="noscreen">,</span>
                    <span class="comments"><a href="#">Comments</a></span>
                </p>
                <p>
				<xsl:value-of select="page-content/header" />
				</p>
                <p class="btn-more box noprint"><strong><a href="#">Continue</a></strong></p>
            </div> <!-- /article -->
          </xsl:for-each>


          <hr class="noscreen" />

          <xsl:for-each select="//web-page/page-components/sub-pages/categories/child-category[@id=14]/page-components/sub-pages/items/child-item[@id=16]">

            <!-- Article -->
            <div class="article">
				<h2><span><a href="#"><xsl:value-of select="page-info/name" /></a></span></h2>
				<p class="info noprint">
                    <span class="date"><xsl:value-of select="page-info/published" /></span><span class="noscreen">,</span>
                    <span class="cat"><a href="#">Category</a></span><span class="noscreen">,</span>
                    <span class="user"><a href="#">My name</a></span><span class="noscreen">,</span>
                    <span class="comments"><a href="#">Comments</a></span>
                </p>
                <p>
				<xsl:value-of select="page-content/header" />
				</p>
                <p class="btn-more box noprint"><strong><a href="#">Continue</a></strong></p>
            </div> <!-- /article -->
          </xsl:for-each>

            <hr class="noscreen" />
            
        </div> <!-- /content -->

        <!-- Right column -->
        <div id="col" class="noprint">
            <div id="col-in">

                <!-- About Me -->
                <h3><span><a href="#">About Me</a></span></h3>

                <div id="about-me">
                    <p><img src="design/tmp_photo.gif" id="me" alt="Yeah, it´s me!" />

                    <strong>Alessandro Vincelli</strong><br />
                    Age: 29<br />
                    Terni, Italy<br />
                    <a href="http://www.linkedin.com/pub/3/858/BA2">Profile on LinkedIn</a></p>
                </div> <!-- /about-me -->

                <hr class="noscreen" />

                <!-- Category -->
                <!-- 
                <h3 ><span>Category</span></h3>

                <ul id="category">
                    <li id="category-active"><a href="#">Selected category</a></li> 
                    <li><a href="#">Category</a></li>
                    <li><a href="#">Category</a></li>

                    <li><a href="#">Category</a></li>
                    <li><a href="#">Category</a></li>
                </ul>
 -->
                <hr class="noscreen" />

                <!-- Archive -->
                <!-- <h3><span>Archive</span></h3>

                <ul id="archive">
                   <li><a href="#">January 2007</a></li>
                    <li><a href="#">December 2006</a></li>
                    <li><a href="#">November 2006</a></li>
                    <li><a href="#">October 2006</a></li>
                    <li><a href="#">September 2006</a></li>

                    <li id="archive-active"><a href="#">August 2006</a></li> 
                    <li><a href="#">July 2006</a></li>
                    <li><a href="#">June 2006</a></li>
                    <li><a href="#">May 2006</a></li>
                    <li><a href="#">April 2006</a></li>
                    <li><a href="#">March 2006</a></li>

                    <li><a href="#">February 2006</a></li>
                    <li><a href="#">January 2006</a></li>
                </ul>
 -->
                <hr class="noscreen" />

                <!-- Links -->
                <h3><span>Links</span></h3>

                <ul id="links">
                    <li><a href="#">Something</a></li>
                    <li><a href="#">Something</a></li>
                    <li><a href="#">Something</a></li>
                    <li><a href="#">Something</a></li>
                    <li><a href="#">Something</a></li>

                </ul>

                <hr class="noscreen" />
            
            </div> <!-- /col-in -->
        </div> <!-- /col -->

    </div> <!-- /page-in -->
    </div> <!-- /page -->

    <!-- Footer -->
    <div id="footer">
        <div id="top" class="noprint"><p><span class="noscreen">Back on top</span> <a href="#header" title="Back on top ^">^<span></span></a></p></div>
        <hr class="noscreen" />
        
        <p id="createdby">Graphic by <a href="http://www.nuvio.cz">Nuvio | Webdesign</a> <!-- DON´T REMOVE, PLEASE! --></p>

        <p id="copyright"> 2008 <a href="mailto:av@alessandro.vincelli.name">Alessandro Vincelli</a></p>
    </div> <!-- /footer -->

</div> <!-- /main -->

</body>
  
  <!-- 
    <body>
      <div id="page" align="center">

        <div id="content" style="width:800px">

          <div id="logo">

            <div style="margin-top:70px" class="whitetitle">Deals CMS</div>

          </div>

          <div id="topheader">

            <div align="left" class="bodytext">
              <xsl:for-each select="//web-page/page-components/sub-pages/categories/child-category[@id=14]/page-components/sub-pages/items/child-item[@id=16]">
                <xsl:copy-of select="page-content/content" />
              </xsl:for-each>
            </div>

            <div id="toplinks" class="smallgraytext">

              <a href="/">Home</a>
              |
              <a href="#">Sitemap</a>
              |
              <a href="#">Contact</a>

            </div>

          </div>

          <div id="menu">

            <div align="right" class="smallwhitetext" style="padding:9px;">

              <a href="/">Home</a>
              |
              <a href="#">About me</a>
              |
              <a href="#">Deals CMS</a>
              |
              <a href="#">Cocoon 2.2</a>
              |
              <a href="#">Contact</a>
<form id="cerca" action="/search/(1).html" method="POST">
							
									<legend>Cerca nel sito</legend>
									<div>
										<label for="cerca1">Cerca nel sito</label>
										<input type="text" name="searchtext" title="Cerca nel sito" id="ricerca" class="input" />
										<input type="hidden" name="href" value="{//page-info/href}" />
										<input type="submit" value="Cerca" alt="Cerca" title="cerca" class="submit" />
									</div>
							
							</form>

            </div>

          </div>

          <div id="submenu">

            <div align="right" class="smallgraytext" style="padding:9px;">

              <a href="#">Submenu 1</a>
              |
              <a href="#">Submenu 2</a>
              |
              <a href="#">Submenu 3</a>
              |
              <a href="#">Submenu 4</a>
              |
              <a href="#">Submenu 5</a>
              |
              <a href="#">Submenu 6</a>

            </div>

          </div>
          
          <xsl:for-each select="//web-page/page-components/sub-pages/categories/child-category[1]/page-components/sub-pages/items/child-item[1]">
            <div id="contenttext">
              <div style="padding:10px">
                <span class="titletext">
                  <xsl:value-of select="page-info/name" />
                </span>
              </div>
              <div class="bodytext" style="padding:12px;" align="justify">
                <strong>
                  <xsl:value-of select="page-content/header" />
                </strong>
                <br />
                <br />
       
                <xsl:copy-of select="page-content/content" />
              </div>
            </div>
          </xsl:for-each>
          <div id="leftpanel">

            <div align="justify" class="graypanel">

              <span class="smalltitle">News</span>
              <br />
              <br />
              <xsl:for-each select="//web-page/navigation-menu/section[@id = 1]/children/category[@id=13]/children/item">
                <span class="smallredtext">
                  <xsl:value-of select="published" />
                </span>
                <br />
                <span class="bodytext">
                  <xsl:value-of select="header" />
                </span>
                <br />
                <a href="{href}" title="{name}" class="smallgraytext">More...</a>
                <br />
                <br />
              </xsl:for-each>
            </div>

          </div>

          <div id="footer" class="smallgraytext">

            <a href="#">Home</a>
            |
            <a href="#">About Us</a>
            |
            <a href="#">Products</a>
            |
            <a href="#">Our Services</a>
            |
            <a href="#">Contact Us</a>

            | Your Company Name

            2007 |
            <a href="http://www.winkhosting.com" target="_blank">Hosting Colombia</a>

          </div>
        </div>
      </div>
    </body>-->
  </xsl:template>
</xsl:stylesheet>

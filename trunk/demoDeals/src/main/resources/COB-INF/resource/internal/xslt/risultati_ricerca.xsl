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
  xmlns:n="http://outerx.org/daisy/1.0#navigation" xmlns:i18n="http://apache.org/cocoon/i18n/2.1" xmlns:urlencoder="xalan://java.net.URLEncoder" xmlns:page="http://apache.org/cocoon/paginate/1.0">
  <xsl:include href="common.xsl" />
  <xsl:template match="web-page">
    <html>
      <xsl:call-template name="header" />
      <xsl:call-template name="defaultLayout" />
    </html>
  </xsl:template>

  <xsl:template name="defaultLayout">
  
  
  <body id="www-url-cz">

<!-- Main -->

<div id="main" class="box">

    <xsl:call-template name="top" />

    <!-- Page (2 columns) -->
    <div id="page" class="box">
    <div id="page-in" class="box">

        <div id="strip" class="box noprint">

            <!-- RSS feeds -->
            <p id="rss"><a href="/feed"><strong>RSS</strong></a></p>
            <hr class="noscreen" />

            <!-- Breadcrumbs -->
            <p id="breadcrumbs">
            
            <xsl:choose>
			<xsl:when test="//search-results/@size > 0 ">
			
				<p class="info noprint">
                    <span class="date"><img src="images/page.gif" alt="Found {//search-results/@size} risults"/> Found 
                                    <xsl:value-of select="//search-results/@size"/> results</span><span class="noscreen">,</span>
                    <span class="cat"><span>, Page
                                        <xsl:value-of select="//page:page/@current"/> of
                                        <xsl:value-of select="//page:page/@total"/> </span></span><span class="noscreen">,</span>
                </p>
            </xsl:when>
                            <xsl:otherwise>
                                <div class="avviso">
                                    Your search did not match any documents
                                </div>
                            </xsl:otherwise>
                            </xsl:choose>
             </p>

            <hr class="noscreen" />
            
        </div> <!-- /strip -->

        <!-- Content -->
        <div id="content">
			<xsl:for-each select="//search-results/search-result">
				<div class="article">
				<h2><span><a href="{href}" title="{title}"><xsl:value-of select="title" /></a></span></h2>
				<p class="info noprint">
                    <span class="date"><xsl:value-of select="published" /></span><span class="noscreen">,</span>
                    <span class="cat"><a href="#"><xsl:value-of select="categories/category" /></a></span><span class="noscreen">,</span>
                    <!-- <span class="user"><a href="#">My name</a></span><span class="noscreen">,</span> -->
                    <!-- <span class="comments"><a href="#">Comments</a></span> -->
                    <span class="cat"><img src="images/score.gif" alt="Result score"/> Score: <strong><xsl:value-of select="score"/>%</strong></span>
                </p>
                <p><xsl:copy-of select="result"/></p>
                <p class="btn-more box noprint"><strong><a href="{href}">Continue</a></strong></p>
            </div> <!-- /article -->
            <hr class="noscreen" />
            </xsl:for-each>
        </div> <!-- /content -->

	<xsl:call-template name="right" />

    </div> <!-- /page-in -->
    </div> <!-- /page -->

    <xsl:call-template name="footer" />

</div> <!-- /main -->

</body>

  </xsl:template>
</xsl:stylesheet>

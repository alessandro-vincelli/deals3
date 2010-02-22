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
  <xsl:include href="common.xsl" />
  <xsl:template match="web-page">
    <html>
      <xsl:call-template name="header"/>
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
            <p id="breadcrumbs">You are here: <a href="/">Home</a> 
            </p>

            <hr class="noscreen" />
            
        </div> <!-- /strip -->

        <!-- Content -->
        <div id="content">

          <xsl:for-each select="//web-page/page-components/sub-pages/categories/child-category[1]/page-components/sub-pages/items/child-item[1]">
            <!-- Article -->
            <div class="article">
				<h2><span><a href="{page-info/href}"><xsl:value-of select="page-info/name" /></a></span></h2>
				<p class="info noprint">
                    <span class="date"><xsl:value-of select="page-info/published" /></span><span class="noscreen">,</span>
                    <span class="user"><a href="#"><xsl:value-of select="page-info/creator/user/firstname" />&#160;<xsl:value-of select="page-info/creator/user/surname" /></a></span><span class="noscreen">,</span>
                    <span class="comments"><a href="#">Comments</a></span>
                </p>
                <p>
				<xsl:value-of select="page-content/header" />
				</p>
				<p>
				<xsl:copy-of select="page-content/content" />
				</p>
            </div> <!-- /article -->
          </xsl:for-each>


          <hr class="noscreen" />

          <xsl:for-each select="//web-page/page-components/sub-pages/categories/child-category[@id=14]/page-components/sub-pages/items/child-item[@id=16]">

            <!-- Article -->
            <div class="article">
				<h2><span><a href="{page-info/href}"><xsl:value-of select="page-info/name" /></a></span></h2>
				<p class="info noprint">
                    <span class="date"><xsl:value-of select="page-info/published" /></span><span class="noscreen">,</span>
                    <span class="cat"><a href="{../../../../page-info/href}"><xsl:value-of select="../../../../page-info/name" /></a></span><span class="noscreen">,</span>
                    <span class="user"><a href="#"><xsl:value-of select="page-info/creator/user/firstname" />&#160;<xsl:value-of select="page-info/creator/user/surname" /></a></span><span class="noscreen">,</span>
                    <span class="comments"><a href="#">Comments</a></span>
                </p>
                <p>
				<xsl:value-of select="page-content/header" />
				</p>
                <p class="btn-more box noprint"><strong><a href="{page-info/href}">Continue</a></strong></p>
            </div> <!-- /article -->
          </xsl:for-each>

            <hr class="noscreen" />
            
        </div> <!-- /content -->

		<xsl:call-template name="right" />
       
    </div> <!-- /page-in -->
    </div> <!-- /page -->
	
	<xsl:call-template name="footer" />
    
</div> <!-- /main -->

</body>
  
  </xsl:template>
</xsl:stylesheet>

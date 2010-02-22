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

<xsl:template name="header">
  <head>
    <meta name="author" content="Wink Hosting (www.winkhosting.com)" />
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    
    <meta name="description" content="..." />
    <meta name="keywords" content="..." />
    
    <link rel="index" href="/" title="Home" />
    <link rel="stylesheet" href="resources/styles/main.css" type="text/css"  media="screen,projection"/>
    <link rel="stylesheet" href="resources/styles/print.css" type="text/css" media="print"/>
    <link rel="stylesheet" href="resources/styles/aural.css" type="text/css" media="aural"/>
    <title>:: Deals CMS ::</title>
    </head>
</xsl:template>

 <xsl:template name="top"> 
     <!-- Header -->
    <div id="header">

        <!-- Logotyp -->
        <h1 id="logo"><a href="/" title="Deals CMS [Go to homepage]">Deals<strong>CMS</strong><span></span></a></h1>
        <hr class="noscreen" />          

        <!-- Quick links -->
        <div class="noscreen noprint">

            <p><em>Quick links:
			<a href="#content">content</a>,
			<a href="#tabs">navigation</a>,
			<a href="#search">search</a>.</em></p>
            <hr />
        </div>

        <!-- Search -->
        <div id="search" class="noprint">
            <form action="/search/(1).html" method="POST">
                <fieldset><legend>Search</legend>
                    <label><span class="noscreen">Find:</span>
                    <span id="search-input-out"><input type="text" name="searchtext" id="search-input" size="30" /></span></label>
                    <input type="hidden" name="href" value="{//page-info/href}" />
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
					<xsl:if test="@selected = 'true'">
					<xsl:attribute name="id">active</xsl:attribute> 
					</xsl:if>
					<a href="{href}"><xsl:value-of select="name"></xsl:value-of><span class="tab-l"></span><span class="tab-r"></span></a></li>
				</xsl:for-each>
             </ul>
        <hr class="noscreen" />
     </div> <!-- /tabs -->
 </xsl:template>
 
 <xsl:template name="right">
 
  <!-- Right column -->
        <div id="col" class="noprint">
            <div id="col-in">

                <!-- Category -->
             	<xsl:if test="not(//web-page[@id=1]) and not(//search-results)">
                <h3 ><span>Categories</span></h3>

                <ul id="category">
                <xsl:for-each select="//web-page/navigation-menu/section[@selected='true']/children/category">
                	<li>
                	<xsl:if test="@selected='true'">
                	<xsl:attribute name="id">category-active</xsl:attribute>
                	</xsl:if>
                	<a href="{href}"><xsl:value-of select="name" /></a></li> 
				</xsl:for-each>                   
                </ul>
                <hr class="noscreen" />
				</xsl:if>

                <!-- About Me -->
                <h3><span><a href="#">About Me</a></span></h3>

                <div id="about-me">
                    <p><img src="design/tmp_photo.gif" id="me" alt="Yeah, it´s me!" />

                    <strong>Alessandro Vincelli</strong><br />
                    Terni, Italy<br />
                    <a href="http://nl.linkedin.com/in/alessandrovincelli">Profile on LinkedIn</a></p>
                </div> <!-- /about-me -->

                <hr class="noscreen" />

                <!-- Links -->
                <h3><span>Links</span></h3>

                <ul id="links">
                    <li><a href="http://cocoon.apache.org/">Apache Cocoon</a></li>
                    <li><a href="http://jackrabbit.apache.org/">Apache Jackrabbit</a></li>
                    <li><a href="http://lucene.apache.org/">Apache Lucene</a></li>
                    <li><a href="http://www.springframework.org/">Spring Framework</a></li>
                    <li><a href="http://www.hibernate.org/">Hibernate</a></li>
                    <li><a href="http://www.alessandro.vincelli.name/">My Blog</a></li>
                </ul>

                <hr class="noscreen" />
            
            </div> <!-- /col-in -->
        </div> <!-- /col -->
 
 </xsl:template>

<xsl:template name="footer">
<!-- Footer -->
    <div id="footer">
        <div id="top" class="noprint"><p><span class="noscreen">Back on top</span> <a href="#header" title="Back on top ^">^<span></span></a></p></div>
        <hr class="noscreen" />
        
        <p id="createdby">Graphic by <a href="http://www.nuvio.cz">Nuvio | Webdesign</a> <!-- DON´T REMOVE, PLEASE! --></p>

        <p id="copyright">&#169;2008 <a href="mailto:a.vincelli@gmail.com">Alessandro Vincelli</a></p>

<!-- Devel stats -->        
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
var pageTracker = _gat._getTracker("UA-5341719-1");
pageTracker._trackPageview();
</script>
        
<!-- Demo stats -->        
<!-- <script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
var pageTracker = _gat._getTracker("UA-5341747-1");
pageTracker._trackPageview();
</script> -->
    </div> <!-- /footer -->
</xsl:template> 

</xsl:stylesheet>

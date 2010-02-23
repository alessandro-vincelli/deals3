<?xml version="1.0"?>

<!--
	* Copyright 2005 Alessandro Vincelli
	*
	* @author <a href="mailto:a.vincelli@gmail.com">Alessandro Vincelli</a>
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
	
	<xsl:template match="/page">
		
			<head>
				<title>Deals CMS: Site builder</title>
			</head>
			<body>
				<div id="container">
					<div id="testata">
						<div id="logo"></div>
						</div>
					<div id="menu" class="clearfix">
					</div>
					<xsl:apply-templates />
					<div id="footer">
						Deals CMS Vers. 3.0 - Alessandro Vincelli -	<a href="mailto:a.vincelli@gmail.com">Send a feedback</a>
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
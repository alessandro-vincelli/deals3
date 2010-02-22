<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rewrite="http://hippo.nl/linkwriter/1.0" xmlns:webdav="http://hippo.nl/webdav/1.0"
	xmlns:D="DAV:"  xmlns:h="http://apache.org/cocoon/request/2.0" >
	<xsl:template match="/root">
		<rss version="2.0" xmlns:content="http://purl.org/rss/1.0/modules/content/" xmlns:wfw="http://wellformedweb.org/CommentAPI/" xmlns:dc="http://purl.org/dc/elements/1.1/"
			xmlns:atom="http://www.w3.org/2005/Atom">
			<channel>
				<title>Deals CMS</title>
				<atom:link href="http://devel.alessandro.vincelli.name/feed/" rel="self" type="application/rss+xml" />
				<link>http://devel.alessandro.vincelli.name/</link>
				<description>Deals CMS, software dev juice pages</description>
				<language>en</language>
				<pubDate></pubDate>
				<generator>http://devel.alessandro.vincelli.name/?v=1.0</generator>
				<xsl:variable name="host" select="h:request/h:requestHeaders/h:header[@name='Host']"></xsl:variable>
				<xsl:for-each select="//item">
				<item>
					<title><xsl:value-of select="name" /></title>
					<link>http://<xsl:value-of select="$host" /><xsl:value-of select="href" /></link>
					<description><xsl:value-of select="header" /></description>
					<pubDate><xsl:value-of select="published" /></pubDate>
					<guid>http://<xsl:value-of select="$host" /><xsl:value-of select="href" /></guid>
				</item>
			</xsl:for-each>			
			</channel>
		</rss>
	</xsl:template>
</xsl:stylesheet>
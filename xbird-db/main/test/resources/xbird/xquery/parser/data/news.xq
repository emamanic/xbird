(: Copyright (c) 2005. Makoto YUI. All rights reserved. :)
declare default element namespace "http://db-www.naist.jp/~makoto-y/";
declare namespace rss = "http://purl.org/rss/1.0/";
declare namespace rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
declare namespace atom = "http://purl.org/atom/ns#";
declare namespace dc = "http://purl.org/dc/elements/1.1/";
declare variable $mycom := doc("http://pcweb.mycom.co.jp/haishin/rss/index.rdf");
declare variable $gnews := doc("http://news.google.com/?output=atom");
declare variable $zdnet := doc("http://japan.zdnet.com/rss/news/index.rdf");

declare function local:list-rss($site)
{
  for $item in $site/rdf:RDF/rss:item
  return
    <res>
      <a href="{ $item/rss:link }">{ $item/rss:title/text() }</a> last modifiled: <modified>{ $item/dc:date/text() }</modified> source: { $site/rdf:RDF/rss:channel/rss:title/text() }<br/>
    </res>
};

declare function local:list-atom($site)
{
  for $entry in $site/atom:feed/atom:entry
  return
    <res>
      <a href="{ $entry/atom:link/@href }">{ $entry/atom:title/text() }</a>last modifiled: <modified>{ $entry/atom:modified/text() }</modified> source: { $site/atom:feed/atom:title/text() }<br/>
    </res>
};

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  </head>
  <body>
    <small>
      {
        for $i in (local:list-rss($mycom), local:list-rss($zdnet))
        return $i/node()
      }
    </small>
  </body>
</html>(: Stylus Studio meta-information - (c) 2004-2005. Progress Software Corporation. All rights reserved.
<metaInformation>
<scenarios ><scenario default="yes" name="xbird" userelativepaths="yes" externalpreview="no" useresolver="no" url="" outputurl="" processortype="custom" tcpport="1852599672" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="java -ea -Xmx255m -cp &quot;C:\workspace\xbird\lib\optional\nekohtml.jar;C:\workspace\xbird\lib\optional\xercesImpl.jar;C:\workspace\xbird\lib\relaxngDatatype.jar;C:\workspace\xbird\lib\xsdlib.jar;C:\workspace\xbird\lib\optional\args4j-2.0.4.jar;C:\workspace\xbird\target\classes&quot; xbird.client.InteractiveShell -o %3 -q %2 -encoding &quot;UTF-8&quot;" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" host="" port="46724056" user="" password="" validateoutput="no" validator="internal" customvalidator=""/><scenario default="no" name="saxon" userelativepaths="yes" externalpreview="no" useresolver="yes" url="" outputurl="" processortype="saxon" tcpport="14" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" host="" port="-233623102" user="" password="" validateoutput="no" validator="internal" customvalidator=""/><scenario default="no" name="built-in" userelativepaths="yes" externalpreview="no" useresolver="yes" url="" outputurl="" processortype="internal" tcpport="14" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" host="" port="-233623102" user="" password="" validateoutput="no" validator="internal" customvalidator=""/></scenarios><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/><MapperBlockPosition></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
:)
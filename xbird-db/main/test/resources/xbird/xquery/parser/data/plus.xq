(:
  -- Qizx
 $ java -Xmx255m -jar "D:\workspace\qizxopen-0.4\qizxopen.jar" -out %3 "D:\workspace\xbird-dev\docs\examples\plus.xq"
:)

declare namespace atom = "http://purl.org/atom/ns#";

declare variable $gnews := doc("http://news.google.co.jp/news?ned=us&amp;output=atom&amp;head=t");

declare function local:plus($left, $right) {
  $left + $right
};

local:plus(1, $gnews/atom:feed/@version/text()), local:plus(1, 1.5)(: Stylus Studio meta-information - (c) 2004-2005. Progress Software Corporation. All rights reserved.
<metaInformation>
<scenarios ><scenario default="no" name="built-in" userelativepaths="yes" externalpreview="no" useresolver="no" url="" outputurl="" processortype="internal" tcpport="0" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" host="" port="0" user="" password="" validateoutput="no" validator="internal" customvalidator=""/><scenario default="no" name="xbird" userelativepaths="yes" externalpreview="no" useresolver="no" url="" outputurl="" processortype="custom" tcpport="14" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="java -ea -Xmx255m -cp &quot;D:\workspace\xbird\lib\relaxngDatatype.jar;D:\workspace\xbird\lib\xsdlib.jar;D:\workspace\xbird\lib\optional\args4j-2.0.4.jar;D:\workspace\xbird\target\classes&quot; xbird.client.InteractiveShell -o %3 -q %2 -encoding &quot;UTF-8&quot;" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" host="" port="83886080" user="" password="" validateoutput="no" validator="internal" customvalidator=""/><scenario default="yes" name="saxon" userelativepaths="yes" externalpreview="no" useresolver="yes" url="" outputurl="" processortype="saxon" tcpport="0" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" host="" port="83886080" user="" password="" validateoutput="no" validator="internal" customvalidator=""/></scenarios><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/><MapperBlockPosition></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
:)
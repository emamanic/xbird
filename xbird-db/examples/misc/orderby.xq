declare variable $input-context := fn:doc("file:///C:/Software/XQTS_1_0_2/TestSources/bib.xml");

<results>
  {
    let $a := $input-context//author
    for $last in distinct-values($a/last),
        $first in distinct-values($a[last = $last]/first)
    order by $last, 
             $first 
    return
      <result>
        <author>
          <last>{ $last }</last>
          <first>{ $first }</first>
        </author>
        {
          for $b in $input-context/bib/book
          where some  $ba in $b/author satisfies ($ba/last = $last and $ba/first = $first)
          return $b/title
        }
      </result>
  }
</results>(: Stylus Studio meta-information - (c) 2004-2005. Progress Software Corporation. All rights reserved.
<metaInformation>
<scenarios ><scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" useresolver="yes" url="" outputurl="" processortype="custom" tcpport="0" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="java -ea -Xmx255m -cp &quot;D:\workspace\xbird\lib\commons-logging-1.0.4.jar;D:\workspace\xbird\lib\optional\args4j-2.0.4.jar;D:\workspace\xbird\build&quot; xbird.client.InteractiveShell -o %3 -q %2 -encoding &quot;UTF-8&quot;" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" host="" port="0" user="" password="" validateoutput="no" validator="internal" customvalidator=""/></scenarios><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/><MapperBlockPosition></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
:)
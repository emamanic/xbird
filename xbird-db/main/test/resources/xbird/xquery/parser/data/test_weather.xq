<html>
  <body>
    {
      for $d in doc("yahoo_weather.xml")//td[contains(a/small/text(), "New York, NY")]
      return
        for $row in $d/parent::tr/parent::table/tr
        where contains($d/a/small/text()[1], "New York")
        return
          <tr>
            <td>{ $row/td[1] }</td>
            <td>{ $row/td[2] }</td>
            <td>{ $row/td[3]//img }</td>
          </tr>
    }
  </body>
</html>(: Stylus Studio meta-information - (c) 2004-2005. Progress Software Corporation. All rights reserved.
<metaInformation>
<scenarios ><scenario default="no" name="xbird" userelativepaths="yes" externalpreview="no" useresolver="no" url="" outputurl="" processortype="custom" tcpport="14" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="java -ea -Xmx255m -cp &quot;C:\workspace\xbird\lib\optional\nekohtml.jar;C:\workspace\xbird\lib\optional\xercesImpl.jar;C:\workspace\xbird\lib\relaxngDatatype.jar;C:\workspace\xbird\lib\xsdlib.jar;C:\workspace\xbird\lib\optional\args4j-2.0.4.jar;C:\workspace\xbird\target\classes&quot; xbird.client.InteractiveShell -o %3 -q %2 -encoding &quot;UTF-8&quot;" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" host="" port="7471215" user="" password="" validateoutput="no" validator="internal" customvalidator=""/><scenario default="yes" name="built-in" userelativepaths="yes" externalpreview="no" useresolver="no" url="..\..\..\..\..\..\..\..\..\..\..\Documents and Settings\Administrator\デスクトップ\yahoo_weather.xml" outputurl="" processortype="internal" tcpport="14" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" host="" port="7471215" user="" password="" validateoutput="no" validator="internal" customvalidator=""/></scenarios><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/><MapperBlockPosition></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
:)
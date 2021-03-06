/*
 * @(#)$Id:XMarkTest.java 1951 2007-04-17 14:39:26Z yui $
 *
 * Copyright 2006-2008 Makoto YUI
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributors:
 *     Makoto YUI - initial implementation
 */
package benchmark.xmark;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.sf.saxon.Configuration;
import net.sf.saxon.event.SaxonOutputKeys;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;

import org.custommonkey.xmlunit.XMLAssert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import xbird.util.datetime.StopWatch;
import xbird.util.io.IOUtils;
import xbird.util.string.StringUtils;
import xbird.util.system.SystemUtils;
import xbird.util.xml.SAXWriter;
import xbird.xquery.XQueryException;
import xbird.xquery.XQueryModule;
import xbird.xquery.XQueryProcessor;
import xbird.xquery.dm.ser.SAXSerializer;
import xbird.xquery.dm.ser.Serializer;
import xbird.xquery.dm.value.Sequence;

public class XMarkTest extends TestCase {

    private static final boolean RUN_ONLY = System.getProperty("test.xmark.runonly") != null;
    private static final String DEFAULT_XMARK_HOME = "C:/Software/xmark";
    private static final String XMARK_HOME = System.getProperty("xmark_home", DEFAULT_XMARK_HOME);

    public static void main(String[] args) {
        junit.textui.TestRunner.run(XMarkTest.class);
    }

    private static String executeQueryWithXbird(String queryFile) throws XQueryException,
            IOException {
        System.gc();
        int gcBefore = SystemUtils.countGC();
        long free = SystemUtils.getHeapFreeMemory();
        StringBuilder stdbuf = new StringBuilder(256);
        stdbuf.append(" - free(init): " + StringUtils.displayBytesSize(free));
        final StopWatch sw = new StopWatch("[Xbird] " + queryFile);
        queryFile = XMARK_HOME + '/' + queryFile;
        final XQueryProcessor processor = new XQueryProcessor();
        XQueryModule mod = processor.parse(new FileInputStream(queryFile), new File(queryFile).toURI());
        Sequence result = processor.execute(mod);
        StringWriter res_sw = new StringWriter();
        final Serializer ser = new SAXSerializer(new SAXWriter(res_sw), res_sw);
        ser.emit(result);
        String swresult = sw.toString();
        long used = SystemUtils.getHeapUsedMemory();
        stdbuf.append(", used(before GC): " + StringUtils.displayBytesSize(used));
        if(RUN_ONLY) {
            res_sw.close();
            res_sw = null;
        }
        System.gc();
        used = SystemUtils.getHeapUsedMemory();
        stdbuf.append(", used(after GC): " + StringUtils.displayBytesSize(used));
        int gcCalled = SystemUtils.countGC() - gcBefore;
        stdbuf.append(", total gc count: " + gcCalled);
        System.out.println(swresult);
        System.out.println(stdbuf.toString());
        if(RUN_ONLY) {
            return "";// dummy
        }
        return res_sw.toString();
    }

    private static String executeQueryWithSaxon(String queryFile) throws XPathException,
            FileNotFoundException, IOException {
        System.gc();
        int gcBefore = SystemUtils.countGC();
        long free = SystemUtils.getHeapFreeMemory();
        StringBuilder stdbuf = new StringBuilder(256);
        stdbuf.append(" - free(init): " + StringUtils.displayBytesSize(free));
        final StopWatch sw = new StopWatch("[Saxon] " + queryFile);
        queryFile = XMARK_HOME + '/' + queryFile;
        final Configuration config = new Configuration();
        config.setHostLanguage(Configuration.XQUERY);
        final StaticQueryContext staticContext = new StaticQueryContext(config);
        staticContext.setBaseURI(new File(queryFile).toURI().toString());
        XQueryExpression exp = staticContext.compileQuery(IOUtils.toString(new FileInputStream(queryFile)));
        final DynamicQueryContext dynamicContext = new DynamicQueryContext(config);
        StringWriter res_sw = new StringWriter();
        Properties props = new Properties();
        //props.setProperty(OutputKeys.METHOD, "text");
        props.setProperty(SaxonOutputKeys.WRAP, "no");
        props.setProperty(OutputKeys.INDENT, "no");
        props.setProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        exp.run(dynamicContext, new StreamResult(res_sw), props);
        String swresult = sw.toString();
        long used = SystemUtils.getHeapUsedMemory();
        stdbuf.append(", used(before GC): " + StringUtils.displayBytesSize(used));
        if(RUN_ONLY) {
            res_sw.close();
            res_sw = null;
        }
        System.gc();
        used = SystemUtils.getHeapUsedMemory();
        stdbuf.append(", used(after GC): " + StringUtils.displayBytesSize(used));
        int gcCalled = SystemUtils.countGC() - gcBefore;
        stdbuf.append(", total gc count: " + gcCalled);
        System.out.println(swresult);
        System.out.println(stdbuf.toString());
        if(RUN_ONLY) {
            return "";//dummy
        }
        return res_sw.toString();
    }

    private void assertEqual(String expected, String actual) throws UnsupportedEncodingException {
        if(actual.length() == 0) {
            Assert.assertEquals(expected, actual);
        }
        actual = "<doc>" + actual + "</doc>";
        expected = "<doc>" + expected + "</doc>";
        Document actualDoc = buildDocument(new ByteArrayInputStream(actual.getBytes("UTF-8")));
        Document expectedDoc = buildDocument(new ByteArrayInputStream(expected.getBytes("UTF-8")));
        XMLAssert.assertXMLEqual(expectedDoc, actualDoc);
    }

    private static Document buildDocument(InputStream is) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        final Document doc;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(is);
        } catch (Exception e) {
            throw new IllegalStateException("buildDocument failed!", e);
        }
        return doc;
    }

    @Test
    public void testRun1() throws FileNotFoundException, XPathException, SAXException, IOException,
            ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q1.xq"), executeQueryWithXbird("q1.xq"));
    }

    @Test
    public void testRun2() throws FileNotFoundException, XPathException, SAXException, IOException,
            ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q2.xq"), executeQueryWithXbird("q2.xq"));
    }

    @Test
    public void testRun3() throws FileNotFoundException, XPathException, SAXException, IOException,
            ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q3.xq"), executeQueryWithXbird("q3.xq"));
    }

    @Test
    public void testRun4() throws FileNotFoundException, XPathException, SAXException, IOException,
            ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q4.xq"), executeQueryWithXbird("q4.xq"));
    }

    @Test
    public void testRun5() throws FileNotFoundException, XPathException, SAXException, IOException,
            ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q5.xq"), executeQueryWithXbird("q5.xq"));
    }

    @Test
    public void testRun6() throws FileNotFoundException, XPathException, SAXException, IOException,
            ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q6.xq"), executeQueryWithXbird("q6.xq"));
    }

    @Test
    public void testRun7() throws FileNotFoundException, XPathException, SAXException, IOException,
            ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q7.xq"), executeQueryWithXbird("q7.xq"));
    }

    @Test
    public void testRun8() throws FileNotFoundException, XPathException, SAXException, IOException,
            ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q8.xq"), executeQueryWithXbird("q8.xq"));
    }

    @Test
    public void testRun9() throws FileNotFoundException, XPathException, SAXException, IOException,
            ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q9.xq"), executeQueryWithXbird("q9.xq"));
    }

    @Test
    public void testRun10() throws FileNotFoundException, XPathException, SAXException,
            IOException, ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q10.xq"), executeQueryWithXbird("q10.xq"));
    }

    @Test
    public void testRun11() throws FileNotFoundException, XPathException, SAXException,
            IOException, ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q11.xq"), executeQueryWithXbird("q11.xq"));
    }

    @Test
    public void testRun12() throws FileNotFoundException, XPathException, SAXException,
            IOException, ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q12.xq"), executeQueryWithXbird("q12.xq"));
    }

    @Test
    public void testRun13() throws FileNotFoundException, XPathException, SAXException,
            IOException, ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q13.xq"), executeQueryWithXbird("q13.xq"));
    }

    @Test
    public void testRun14() throws FileNotFoundException, XPathException, SAXException,
            IOException, ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q14.xq"), executeQueryWithXbird("q14.xq"));
    }

    @Test
    public void testRun15() throws FileNotFoundException, XPathException, SAXException,
            IOException, ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q15.xq"), executeQueryWithXbird("q15.xq"));
    }

    @Test
    public void testRun16() throws FileNotFoundException, XPathException, SAXException,
            IOException, ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q16.xq"), executeQueryWithXbird("q16.xq"));
    }

    @Test
    public void testRun17() throws FileNotFoundException, XPathException, SAXException,
            IOException, ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q17.xq"), executeQueryWithXbird("q17.xq"));
    }

    @Test
    public void testRun18() throws FileNotFoundException, XPathException, SAXException,
            IOException, ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q18.xq"), executeQueryWithXbird("q18.xq"));
    }

    @Test
    public void testRun19() throws FileNotFoundException, XPathException, SAXException,
            IOException, ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q19.xq"), executeQueryWithXbird("q19.xq"));
    }

    @Test
    public void testRun20() throws FileNotFoundException, XPathException, SAXException,
            IOException, ParserConfigurationException, XQueryException {
        assertEqual(executeQueryWithSaxon("q20.xq"), executeQueryWithXbird("q20.xq"));
    }
}

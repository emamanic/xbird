/*
 * @(#)$Id: XQTSTest.template 946 2006-09-14 03:31:56Z yui $
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
package xqts.expressions.operators.arithexpr.numericopr.numericsubtract;

import junit.framework.TestCase;
import xqts.XQTSTestBase;

public class NumericSubtractTest extends TestCase {

    private static final String TEST_PATH = "((//ns:test-group[count(child::ns:test-group)=0])[21]//ns:test-case)";
    private static final String TARGET_XQTS_VERSION = "1.0.2";
    
    private final XQTSTestBase xqts;

    public NumericSubtractTest() {
    	super(NumericSubtractTest.class.getName());
        this.xqts = new XQTSTestBase(NumericSubtractTest.class.getName(), TARGET_XQTS_VERSION);
    }
    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractint2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[1]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractint2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[2]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractint2args3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[3]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractint2args4() throws Exception {
        xqts.invokeTest(TEST_PATH + "[4]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractint2args5() throws Exception {
        xqts.invokeTest(TEST_PATH + "[5]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractintg2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[6]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractintg2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[7]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractintg2args3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[8]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractintg2args4() throws Exception {
        xqts.invokeTest(TEST_PATH + "[9]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractintg2args5() throws Exception {
        xqts.invokeTest(TEST_PATH + "[10]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractdec2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[11]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractdec2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[12]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractdec2args3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[13]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractdec2args4() throws Exception {
        xqts.invokeTest(TEST_PATH + "[14]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractdec2args5() throws Exception {
        xqts.invokeTest(TEST_PATH + "[15]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractdbl2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[16]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractdbl2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[17]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractdbl2args3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[18]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractdbl2args4() throws Exception {
        xqts.invokeTest(TEST_PATH + "[19]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractdbl2args5() throws Exception {
        xqts.invokeTest(TEST_PATH + "[20]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractflt2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[21]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractflt2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[22]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractflt2args3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[23]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractflt2args4() throws Exception {
        xqts.invokeTest(TEST_PATH + "[24]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractflt2args5() throws Exception {
        xqts.invokeTest(TEST_PATH + "[25]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractlng2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[26]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractlng2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[27]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractlng2args3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[28]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractlng2args4() throws Exception {
        xqts.invokeTest(TEST_PATH + "[29]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractlng2args5() throws Exception {
        xqts.invokeTest(TEST_PATH + "[30]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractusht2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[31]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractusht2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[32]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractusht2args3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[33]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractusht2args4() throws Exception {
        xqts.invokeTest(TEST_PATH + "[34]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractusht2args5() throws Exception {
        xqts.invokeTest(TEST_PATH + "[35]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractnint2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[36]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractnint2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[37]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractpint2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[38]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractpint2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[39]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractpint2args3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[40]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractulng2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[41]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractulng2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[42]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractulng2args3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[43]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractulng2args4() throws Exception {
        xqts.invokeTest(TEST_PATH + "[44]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractulng2args5() throws Exception {
        xqts.invokeTest(TEST_PATH + "[45]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractnpi2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[46]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractnpi2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[47]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractnpi2args3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[48]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractnpi2args4() throws Exception {
        xqts.invokeTest(TEST_PATH + "[49]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractnpi2args5() throws Exception {
        xqts.invokeTest(TEST_PATH + "[50]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractnni2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[51]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractnni2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[52]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractnni2args3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[53]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractnni2args4() throws Exception {
        xqts.invokeTest(TEST_PATH + "[54]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractnni2args5() throws Exception {
        xqts.invokeTest(TEST_PATH + "[55]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractsht2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[56]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractsht2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[57]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractsht2args3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[58]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractsht2args4() throws Exception {
        xqts.invokeTest(TEST_PATH + "[59]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractsht2args5() throws Exception {
        xqts.invokeTest(TEST_PATH + "[60]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractmix2args1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[61]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractmix2args2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[62]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractmix2args3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[63]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractmix2args4() throws Exception {
        xqts.invokeTest(TEST_PATH + "[64]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractmix2args5() throws Exception {
        xqts.invokeTest(TEST_PATH + "[65]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractmix2args6() throws Exception {
        xqts.invokeTest(TEST_PATH + "[66]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractmix2args7() throws Exception {
        xqts.invokeTest(TEST_PATH + "[67]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractmix2args8() throws Exception {
        xqts.invokeTest(TEST_PATH + "[68]");
    }

    @org.junit.Test(timeout=300000)
    public void testOpNumericSubtractmix2args9() throws Exception {
        xqts.invokeTest(TEST_PATH + "[69]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract1() throws Exception {
        xqts.invokeTest(TEST_PATH + "[70]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract2() throws Exception {
        xqts.invokeTest(TEST_PATH + "[71]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract3() throws Exception {
        xqts.invokeTest(TEST_PATH + "[72]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract4() throws Exception {
        xqts.invokeTest(TEST_PATH + "[73]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract5() throws Exception {
        xqts.invokeTest(TEST_PATH + "[74]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract6() throws Exception {
        xqts.invokeTest(TEST_PATH + "[75]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract7() throws Exception {
        xqts.invokeTest(TEST_PATH + "[76]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract8() throws Exception {
        xqts.invokeTest(TEST_PATH + "[77]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract9() throws Exception {
        xqts.invokeTest(TEST_PATH + "[78]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract10() throws Exception {
        xqts.invokeTest(TEST_PATH + "[79]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract11() throws Exception {
        xqts.invokeTest(TEST_PATH + "[80]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract12() throws Exception {
        xqts.invokeTest(TEST_PATH + "[81]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract13() throws Exception {
        xqts.invokeTest(TEST_PATH + "[82]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract14() throws Exception {
        xqts.invokeTest(TEST_PATH + "[83]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract15() throws Exception {
        xqts.invokeTest(TEST_PATH + "[84]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract16() throws Exception {
        xqts.invokeTest(TEST_PATH + "[85]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract17() throws Exception {
        xqts.invokeTest(TEST_PATH + "[86]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract18() throws Exception {
        xqts.invokeTest(TEST_PATH + "[87]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract19() throws Exception {
        xqts.invokeTest(TEST_PATH + "[88]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract20() throws Exception {
        xqts.invokeTest(TEST_PATH + "[89]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract21() throws Exception {
        xqts.invokeTest(TEST_PATH + "[90]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract22() throws Exception {
        xqts.invokeTest(TEST_PATH + "[91]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract23() throws Exception {
        xqts.invokeTest(TEST_PATH + "[92]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract24() throws Exception {
        xqts.invokeTest(TEST_PATH + "[93]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract25() throws Exception {
        xqts.invokeTest(TEST_PATH + "[94]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract26() throws Exception {
        xqts.invokeTest(TEST_PATH + "[95]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract27() throws Exception {
        xqts.invokeTest(TEST_PATH + "[96]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract28() throws Exception {
        xqts.invokeTest(TEST_PATH + "[97]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract29() throws Exception {
        xqts.invokeTest(TEST_PATH + "[98]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract30() throws Exception {
        xqts.invokeTest(TEST_PATH + "[99]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract31() throws Exception {
        xqts.invokeTest(TEST_PATH + "[100]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract32() throws Exception {
        xqts.invokeTest(TEST_PATH + "[101]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract33() throws Exception {
        xqts.invokeTest(TEST_PATH + "[102]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract34() throws Exception {
        xqts.invokeTest(TEST_PATH + "[103]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract35() throws Exception {
        xqts.invokeTest(TEST_PATH + "[104]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract36() throws Exception {
        xqts.invokeTest(TEST_PATH + "[105]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract37() throws Exception {
        xqts.invokeTest(TEST_PATH + "[106]");
    }

    @org.junit.Test(timeout=300000)
    public void testKNumericSubtract38() throws Exception {
        xqts.invokeTest(TEST_PATH + "[107]");
    }

}
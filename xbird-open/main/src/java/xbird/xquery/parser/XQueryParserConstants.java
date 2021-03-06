/* Generated By:JavaCC: Do not edit this line. XQueryParserConstants.java */
/*
 * @(#)$Id: XQueryParser.jj 3527 2008-02-24 14:15:39Z yui $
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
package xbird.xquery.parser;

/** 
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface XQueryParserConstants {

    /** End of File. */
    int EOF = 0;
    /** RegularExpression Id. */
    int DecimalLiteral = 2;
    /** RegularExpression Id. */
    int DotDot = 3;
    /** RegularExpression Id. */
    int Dot = 4;
    /** RegularExpression Id. */
    int DoubleLiteral = 5;
    /** RegularExpression Id. */
    int IntegerLiteral = 6;
    /** RegularExpression Id. */
    int NotNumber = 7;
    /** RegularExpression Id. */
    int DeclareConstruction = 8;
    /** RegularExpression Id. */
    int DeclareDefaultOrder = 9;
    /** RegularExpression Id. */
    int DeclareCollation = 10;
    /** RegularExpression Id. */
    int DeclareNamespace = 11;
    /** RegularExpression Id. */
    int ModuleNamespace = 12;
    /** RegularExpression Id. */
    int DeclareBaseURI = 13;
    /** RegularExpression Id. */
    int DeclareDefaultElement = 14;
    /** RegularExpression Id. */
    int DeclareDefaultFunction = 15;
    /** RegularExpression Id. */
    int ImportSchemaToken = 16;
    /** RegularExpression Id. */
    int ImportModuleToken = 17;
    /** RegularExpression Id. */
    int DeclareCopyNamespace = 18;
    /** RegularExpression Id. */
    int Some = 19;
    /** RegularExpression Id. */
    int Every = 20;
    /** RegularExpression Id. */
    int DefineVariable = 21;
    /** RegularExpression Id. */
    int XmlCommentStart = 22;
    /** RegularExpression Id. */
    int ProcessingInstructionStart = 23;
    /** RegularExpression Id. */
    int CdataSectionStart = 24;
    /** RegularExpression Id. */
    int StartTagOpenRoot = 25;
    /** RegularExpression Id. */
    int DeclareXMLSpace = 26;
    /** RegularExpression Id. */
    int ValidateLbrace = 27;
    /** RegularExpression Id. */
    int ValidateSchemaMode = 28;
    /** RegularExpression Id. */
    int TypeswitchLpar = 29;
    /** RegularExpression Id. */
    int ElementLbrace = 30;
    /** RegularExpression Id. */
    int AttributeLbrace = 31;
    /** RegularExpression Id. */
    int AttributeQNameLbrace = 32;
    /** RegularExpression Id. */
    int ElementQNameLbrace = 33;
    /** RegularExpression Id. */
    int DocumentLbrace = 34;
    /** RegularExpression Id. */
    int TextLbrace = 35;
    /** RegularExpression Id. */
    int PILbrace = 36;
    /** RegularExpression Id. */
    int PINCNameLbrace = 37;
    /** RegularExpression Id. */
    int CommentLbrace = 38;
    /** RegularExpression Id. */
    int DefineFunction = 39;
    /** RegularExpression Id. */
    int OrderedOpen = 40;
    /** RegularExpression Id. */
    int UnorderedOpen = 41;
    /** RegularExpression Id. */
    int ExecuteAt = 42;
    /** RegularExpression Id. */
    int DeclareOrdering = 43;
    /** RegularExpression Id. */
    int XQueryVersion = 44;
    /** RegularExpression Id. */
    int DeclareOption = 45;
    /** RegularExpression Id. */
    int IfLpar = 46;
    /** RegularExpression Id. */
    int AxisAncestorOrSelf = 47;
    /** RegularExpression Id. */
    int AxisAncestor = 48;
    /** RegularExpression Id. */
    int AxisAttribute = 49;
    /** RegularExpression Id. */
    int AxisChild = 50;
    /** RegularExpression Id. */
    int AxisDescendantOrSelf = 51;
    /** RegularExpression Id. */
    int AxisDescendant = 52;
    /** RegularExpression Id. */
    int AxisFollowingSibling = 53;
    /** RegularExpression Id. */
    int AxisFollowing = 54;
    /** RegularExpression Id. */
    int AxisParent = 55;
    /** RegularExpression Id. */
    int AxisPrecedingSibling = 56;
    /** RegularExpression Id. */
    int AxisPreceding = 57;
    /** RegularExpression Id. */
    int AxisSelf = 58;
    /** RegularExpression Id. */
    int At = 59;
    /** RegularExpression Id. */
    int ElementType = 60;
    /** RegularExpression Id. */
    int AttributeType = 61;
    /** RegularExpression Id. */
    int SchemaElementType = 62;
    /** RegularExpression Id. */
    int SchemaAttributeType = 63;
    /** RegularExpression Id. */
    int CommentLparRpar = 64;
    /** RegularExpression Id. */
    int TextLparRpar = 65;
    /** RegularExpression Id. */
    int NodeLparRpar = 66;
    /** RegularExpression Id. */
    int DocumentLpar = 67;
    /** RegularExpression Id. */
    int ProcessingInstructionLpar = 68;
    /** RegularExpression Id. */
    int NCNameColonStar = 69;
    /** RegularExpression Id. */
    int StarColonNCName = 70;
    /** RegularExpression Id. */
    int QNameLpar = 71;
    /** RegularExpression Id. */
    int Rpar = 72;
    /** RegularExpression Id. */
    int ForVariable = 73;
    /** RegularExpression Id. */
    int LetVariable = 74;
    /** RegularExpression Id. */
    int Rbrace = 75;
    /** RegularExpression Id. */
    int Plus = 76;
    /** RegularExpression Id. */
    int SlashSlash = 77;
    /** RegularExpression Id. */
    int Slash = 78;
    /** RegularExpression Id. */
    int StringLiteral = 79;
    /** RegularExpression Id. */
    int Star = 80;
    /** RegularExpression Id. */
    int VariableIndicator = 81;
    /** RegularExpression Id. */
    int RparAs = 82;
    /** RegularExpression Id. */
    int Comma = 83;
    /** RegularExpression Id. */
    int Minus = 84;
    /** RegularExpression Id. */
    int LbraceExprEnclosure = 85;
    /** RegularExpression Id. */
    int PragmaOpen = 86;
    /** RegularExpression Id. */
    int Lpar = 87;
    /** RegularExpression Id. */
    int AtURILiteral = 88;
    /** RegularExpression Id. */
    int SemiColon = 89;
    /** RegularExpression Id. */
    int Ordered = 90;
    /** RegularExpression Id. */
    int Unordered = 91;
    /** RegularExpression Id. */
    int Then = 92;
    /** RegularExpression Id. */
    int Else = 93;
    /** RegularExpression Id. */
    int External = 94;
    /** RegularExpression Id. */
    int And = 95;
    /** RegularExpression Id. */
    int AtWord = 96;
    /** RegularExpression Id. */
    int ColonEquals = 97;
    /** RegularExpression Id. */
    int Div = 98;
    /** RegularExpression Id. */
    int Equals = 99;
    /** RegularExpression Id. */
    int Except = 100;
    /** RegularExpression Id. */
    int FortranEq = 101;
    /** RegularExpression Id. */
    int FortranGe = 102;
    /** RegularExpression Id. */
    int FortranGt = 103;
    /** RegularExpression Id. */
    int FortranLe = 104;
    /** RegularExpression Id. */
    int FortranLt = 105;
    /** RegularExpression Id. */
    int FortranNe = 106;
    /** RegularExpression Id. */
    int GtEquals = 107;
    /** RegularExpression Id. */
    int GtGt = 108;
    /** RegularExpression Id. */
    int Gt = 109;
    /** RegularExpression Id. */
    int Idiv = 110;
    /** RegularExpression Id. */
    int Intersect = 111;
    /** RegularExpression Id. */
    int In = 112;
    /** RegularExpression Id. */
    int Is = 113;
    /** RegularExpression Id. */
    int Lbrack = 114;
    /** RegularExpression Id. */
    int LtEquals = 115;
    /** RegularExpression Id. */
    int LtLt = 116;
    /** RegularExpression Id. */
    int Lt = 117;
    /** RegularExpression Id. */
    int Mod = 118;
    /** RegularExpression Id. */
    int NotEquals = 119;
    /** RegularExpression Id. */
    int GroupBy = 120;
    /** RegularExpression Id. */
    int OrderBy = 121;
    /** RegularExpression Id. */
    int OrderByStable = 122;
    /** RegularExpression Id. */
    int Or = 123;
    /** RegularExpression Id. */
    int Return = 124;
    /** RegularExpression Id. */
    int Satisfies = 125;
    /** RegularExpression Id. */
    int To = 126;
    /** RegularExpression Id. */
    int Union = 127;
    /** RegularExpression Id. */
    int Vbar = 128;
    /** RegularExpression Id. */
    int Where = 129;
    /** RegularExpression Id. */
    int Castable = 130;
    /** RegularExpression Id. */
    int CastAs = 131;
    /** RegularExpression Id. */
    int Instanceof = 132;
    /** RegularExpression Id. */
    int TreatAs = 133;
    /** RegularExpression Id. */
    int Case = 134;
    /** RegularExpression Id. */
    int As = 135;
    /** RegularExpression Id. */
    int Multiply = 136;
    /** RegularExpression Id. */
    int SchemaModeForDeclareConstruction = 137;
    /** RegularExpression Id. */
    int EmptyGreatest = 138;
    /** RegularExpression Id. */
    int EmptyLeast = 139;
    /** RegularExpression Id. */
    int Ascending = 140;
    /** RegularExpression Id. */
    int Descending = 141;
    /** RegularExpression Id. */
    int Default = 142;
    /** RegularExpression Id. */
    int Rbrack = 143;
    /** RegularExpression Id. */
    int Collation = 144;
    /** RegularExpression Id. */
    int NotOperatorKeyword = 145;
    /** RegularExpression Id. */
    int OccurrenceZeroOrOne = 146;
    /** RegularExpression Id. */
    int StringLiteralForVersion = 147;
    /** RegularExpression Id. */
    int XQueryEncoding = 148;
    /** RegularExpression Id. */
    int URILiteral = 149;
    /** RegularExpression Id. */
    int CommaForURITransition = 150;
    /** RegularExpression Id. */
    int AssignEquals = 151;
    /** RegularExpression Id. */
    int NCNameForPrefix = 152;
    /** RegularExpression Id. */
    int URILiteralToOperator = 153;
    /** RegularExpression Id. */
    int Inherit = 154;
    /** RegularExpression Id. */
    int NoInherit = 155;
    /** RegularExpression Id. */
    int Namespace = 156;
    /** RegularExpression Id. */
    int DefaultElement = 157;
    /** RegularExpression Id. */
    int Preserve = 158;
    /** RegularExpression Id. */
    int NoPreserve = 159;
    /** RegularExpression Id. */
    int CommaForCopyNamespace = 160;
    /** RegularExpression Id. */
    int XMLSpacePreserve = 161;
    /** RegularExpression Id. */
    int XMLSpaceStrip = 162;
    /** RegularExpression Id. */
    int EmptySequence = 163;
    /** RegularExpression Id. */
    int ElementTypeForKindTest = 164;
    /** RegularExpression Id. */
    int AttributeTypeForKindTest = 165;
    /** RegularExpression Id. */
    int SchemaElementTypeForKindTest = 166;
    /** RegularExpression Id. */
    int SchemaAttributeTypeForKindTest = 167;
    /** RegularExpression Id. */
    int CommentLparRparForKindTest = 168;
    /** RegularExpression Id. */
    int TextLparRparForKindTest = 169;
    /** RegularExpression Id. */
    int NodeLparRparForKindTest = 170;
    /** RegularExpression Id. */
    int DocumentLparForKindTest = 171;
    /** RegularExpression Id. */
    int ProcessingInstructionLparForKindTest = 172;
    /** RegularExpression Id. */
    int QNameForSequenceType = 173;
    /** RegularExpression Id. */
    int Item = 174;
    /** RegularExpression Id. */
    int QNameForAtomicType = 175;
    /** RegularExpression Id. */
    int RparForKindTest = 176;
    /** RegularExpression Id. */
    int AnyName = 177;
    /** RegularExpression Id. */
    int QNameForItemType = 178;
    /** RegularExpression Id. */
    int ElementTypeForDocumentTest = 179;
    /** RegularExpression Id. */
    int SchemaElementTypeForDocumentTest = 180;
    /** RegularExpression Id. */
    int NCNameForPI = 181;
    /** RegularExpression Id. */
    int StringLiteralForKindTest = 182;
    /** RegularExpression Id. */
    int CommaForKindTest = 183;
    /** RegularExpression Id. */
    int Nillable = 184;
    /** RegularExpression Id. */
    int OccurrenceOneOrMore = 185;
    /** RegularExpression Id. */
    int NotOccurrenceIndicator = 186;
    /** RegularExpression Id. */
    int QNameForOption = 187;
    /** RegularExpression Id. */
    int QNameForPragma = 188;
    /** RegularExpression Id. */
    int QName = 189;
    /** RegularExpression Id. */
    int S = 190;
    /** RegularExpression Id. */
    int PragmaContents = 191;
    /** RegularExpression Id. */
    int PragmaClose = 192;
    /** RegularExpression Id. */
    int VarName = 193;
    /** RegularExpression Id. */
    int EmptyTagClose = 194;
    /** RegularExpression Id. */
    int StartTagClose = 195;
    /** RegularExpression Id. */
    int OpenQuot = 196;
    /** RegularExpression Id. */
    int OpenApos = 197;
    /** RegularExpression Id. */
    int ValueIndicator = 198;
    /** RegularExpression Id. */
    int TagQName = 199;
    /** RegularExpression Id. */
    int EndTagOpen = 200;
    /** RegularExpression Id. */
    int XmlCommentStartForElementContent = 201;
    /** RegularExpression Id. */
    int ProcessingInstructionStartForElementContent = 202;
    /** RegularExpression Id. */
    int CdataSectionStartForElementContent = 203;
    /** RegularExpression Id. */
    int StartTagOpen = 204;
    /** RegularExpression Id. */
    int Lbrace = 205;
    /** RegularExpression Id. */
    int PredefinedEntityRef = 206;
    /** RegularExpression Id. */
    int CharRef = 207;
    /** RegularExpression Id. */
    int LCurlyBraceEscape = 208;
    /** RegularExpression Id. */
    int RCurlyBraceEscape = 209;
    /** RegularExpression Id. */
    int ElementContentChar = 210;
    /** RegularExpression Id. */
    int EndTagClose = 211;
    /** RegularExpression Id. */
    int XmlCommentEnd = 212;
    /** RegularExpression Id. */
    int CommentContentChar = 213;
    /** RegularExpression Id. */
    int CommentContentCharDash = 214;
    /** RegularExpression Id. */
    int SForPI = 215;
    /** RegularExpression Id. */
    int PITarget = 216;
    /** RegularExpression Id. */
    int ProcessingInstructionEnd = 217;
    /** RegularExpression Id. */
    int PIContentChar = 218;
    /** RegularExpression Id. */
    int CdataSectionEnd = 219;
    /** RegularExpression Id. */
    int CDataSectionChar = 220;
    /** RegularExpression Id. */
    int CloseQuot = 221;
    /** RegularExpression Id. */
    int EscapeQuot = 222;
    /** RegularExpression Id. */
    int QuotAttrContentChar = 223;
    /** RegularExpression Id. */
    int CloseApos = 224;
    /** RegularExpression Id. */
    int EscapeApos = 225;
    /** RegularExpression Id. */
    int AposAttrContentChar = 226;
    /** RegularExpression Id. */
    int Comment = 227;
    /** RegularExpression Id. */
    int CommentStart = 228;
    /** RegularExpression Id. */
    int CommentContent = 229;
    /** RegularExpression Id. */
    int CommentEnd = 230;
    /** RegularExpression Id. */
    int CommentContents = 231;
    /** RegularExpression Id. */
    int skip_ = 232;
    /** RegularExpression Id. */
    int Prefix = 233;
    /** RegularExpression Id. */
    int LocalPart = 234;
    /** RegularExpression Id. */
    int ValidationMode = 235;
    /** RegularExpression Id. */
    int Nmstart = 236;
    /** RegularExpression Id. */
    int Nmchar = 237;
    /** RegularExpression Id. */
    int Digits = 238;
    /** RegularExpression Id. */
    int HexDigits = 239;
    /** RegularExpression Id. */
    int NCName = 240;
    /** RegularExpression Id. */
    int Char = 241;
    /** RegularExpression Id. */
    int ExtensionContentChar = 242;
    /** RegularExpression Id. */
    int WhitespaceChar = 243;
    /** RegularExpression Id. */
    int Letter = 244;
    /** RegularExpression Id. */
    int BaseChar = 245;
    /** RegularExpression Id. */
    int Ideographic = 246;
    /** RegularExpression Id. */
    int CombiningChar = 247;
    /** RegularExpression Id. */
    int Digit = 248;
    /** RegularExpression Id. */
    int Extender = 249;
    /** RegularExpression Id. */
    int space_ = 250;

    /** Lexical state. */
    int DEFAULT = 0;
    /** Lexical state. */
    int DECLAREORDERING = 1;
    /** Lexical state. */
    int OPERATOR = 2;
    /** Lexical state. */
    int XQUERYVERSION = 3;
    /** Lexical state. */
    int NAMESPACEDECL = 4;
    /** Lexical state. */
    int URITOOPERATOR = 5;
    /** Lexical state. */
    int NAMESPACEKEYWORD = 6;
    /** Lexical state. */
    int XMLSPACE_DECL = 7;
    /** Lexical state. */
    int SINGLETYPE = 8;
    /** Lexical state. */
    int ITEMTYPE = 9;
    /** Lexical state. */
    int KINDTEST = 10;
    /** Lexical state. */
    int KINDTESTFORPI = 11;
    /** Lexical state. */
    int CLOSEKINDTEST = 12;
    /** Lexical state. */
    int OPTION = 13;
    /** Lexical state. */
    int PRAGMA = 14;
    /** Lexical state. */
    int PRAGMACONTENTS = 15;
    /** Lexical state. */
    int VARNAME = 16;
    /** Lexical state. */
    int EXPR_COMMENT = 17;
    /** Lexical state. */
    int XQUERYD = 18;
    /** Lexical state. */
    int NODETEST = 19;
    /** Lexical state. */
    int OCCURRENCEINDICATOR = 20;
    /** Lexical state. */
    int START_TAG = 21;
    /** Lexical state. */
    int END_TAG = 22;
    /** Lexical state. */
    int ELEMENT_CONTENT = 23;
    /** Lexical state. */
    int QUOT_ATTRIBUTE_CONTENT = 24;
    /** Lexical state. */
    int APOS_ATTRIBUTE_CONTENT = 25;
    /** Lexical state. */
    int XML_COMMENT = 26;
    /** Lexical state. */
    int PROCESSING_INSTRUCTION = 27;
    /** Lexical state. */
    int PROCESSING_INSTRUCTION_CONTENT = 28;
    /** Lexical state. */
    int CDATA_SECTION = 29;

    /** Literal token values. */
    String[] tokenImage = { "<EOF>", "<token of kind 1>", "<DecimalLiteral>", "\"..\"", "\".\"",
            "<DoubleLiteral>", "<IntegerLiteral>", "<NotNumber>", "<DeclareConstruction>",
            "<DeclareDefaultOrder>", "<DeclareCollation>", "<DeclareNamespace>",
            "<ModuleNamespace>", "<DeclareBaseURI>", "<DeclareDefaultElement>",
            "<DeclareDefaultFunction>", "<ImportSchemaToken>", "<ImportModuleToken>",
            "<DeclareCopyNamespace>", "<Some>", "<Every>", "<DefineVariable>", "\"<!--\"",
            "\"<?\"", "\"<![CDATA[\"", "\"<\"", "<DeclareXMLSpace>", "<ValidateLbrace>",
            "<ValidateSchemaMode>", "<TypeswitchLpar>", "<ElementLbrace>", "<AttributeLbrace>",
            "<AttributeQNameLbrace>", "<ElementQNameLbrace>", "<DocumentLbrace>", "<TextLbrace>",
            "<PILbrace>", "<PINCNameLbrace>", "<CommentLbrace>", "<DefineFunction>",
            "<OrderedOpen>", "<UnorderedOpen>", "<ExecuteAt>", "<DeclareOrdering>",
            "<XQueryVersion>", "<DeclareOption>", "<IfLpar>", "\"ancestor-or-self::\"",
            "\"ancestor::\"", "\"attribute::\"", "\"child::\"", "\"descendant-or-self::\"",
            "\"descendant::\"", "\"following-sibling::\"", "\"following::\"", "\"parent::\"",
            "\"preceding-sibling::\"", "\"preceding::\"", "\"self::\"", "\"@\"", "<ElementType>",
            "<AttributeType>", "<SchemaElementType>", "<SchemaAttributeType>", "<CommentLparRpar>",
            "<TextLparRpar>", "<NodeLparRpar>", "<DocumentLpar>", "<ProcessingInstructionLpar>",
            "<NCNameColonStar>", "<StarColonNCName>", "<QNameLpar>", "\")\"", "<ForVariable>",
            "<LetVariable>", "\"}\"", "\"+\"", "\"//\"", "\"/\"", "<StringLiteral>", "\"*\"",
            "\"$\"", "<RparAs>", "\",\"", "\"-\"", "\"{\"", "\"(#\"", "\"(\"", "<AtURILiteral>",
            "\";\"", "\"ordered\"", "\"unordered\"", "\"then\"", "\"else\"", "\"external\"",
            "\"and\"", "\"at\"", "\":=\"", "\"div\"", "\"=\"", "\"except\"", "\"eq\"", "\"ge\"",
            "\"gt\"", "\"le\"", "\"lt\"", "\"ne\"", "\">=\"", "\">>\"", "\">\"", "\"idiv\"",
            "\"intersect\"", "\"in\"", "\"is\"", "\"[\"", "\"<=\"", "\"<<\"", "\"<\"", "\"mod\"",
            "\"!=\"", "<GroupBy>", "<OrderBy>", "<OrderByStable>", "\"or\"", "\"return\"",
            "\"satisfies\"", "\"to\"", "\"union\"", "\"|\"", "\"where\"", "<Castable>", "<CastAs>",
            "<Instanceof>", "<TreatAs>", "\"case\"", "\"as\"", "\"*\"",
            "<SchemaModeForDeclareConstruction>", "<EmptyGreatest>", "<EmptyLeast>",
            "\"ascending\"", "\"descending\"", "\"default\"", "\"]\"", "\"collation\"",
            "<NotOperatorKeyword>", "\"?\"", "<StringLiteralForVersion>", "\"encoding\"",
            "<URILiteral>", "\",\"", "\"=\"", "<NCNameForPrefix>", "<URILiteralToOperator>",
            "\"inherit\"", "\"no-inherit\"", "\"namespace\"", "<DefaultElement>", "\"preserve\"",
            "\"no-preserve\"", "\",\"", "\"preserve\"", "\"strip\"", "<EmptySequence>",
            "<ElementTypeForKindTest>", "<AttributeTypeForKindTest>",
            "<SchemaElementTypeForKindTest>", "<SchemaAttributeTypeForKindTest>",
            "<CommentLparRparForKindTest>", "<TextLparRparForKindTest>",
            "<NodeLparRparForKindTest>", "<DocumentLparForKindTest>",
            "<ProcessingInstructionLparForKindTest>", "<QNameForSequenceType>", "<Item>",
            "<QNameForAtomicType>", "\")\"", "\"*\"", "<QNameForItemType>",
            "<ElementTypeForDocumentTest>", "<SchemaElementTypeForDocumentTest>", "<NCNameForPI>",
            "<StringLiteralForKindTest>", "\",\"", "\"?\"", "\"+\"", "<NotOccurrenceIndicator>",
            "<QNameForOption>", "<QNameForPragma>", "<QName>", "<S>", "<PragmaContents>", "\"#)\"",
            "<VarName>", "\"/>\"", "\">\"", "\"\\\"\"", "\"\\\'\"", "\"=\"", "<TagQName>",
            "\"</\"", "\"<!--\"", "\"<?\"", "\"<![CDATA[\"", "\"<\"", "\"{\"",
            "<PredefinedEntityRef>", "<CharRef>", "\"{{\"", "\"}}\"", "<ElementContentChar>",
            "\">\"", "\"-->\"", "<CommentContentChar>", "<CommentContentCharDash>", "<SForPI>",
            "<PITarget>", "\"?>\"", "<PIContentChar>", "<CdataSectionEnd>", "<CDataSectionChar>",
            "\"\\\"\"", "\"\\\"\\\"\"", "<QuotAttrContentChar>", "\"\\\'\"", "\"\\\'\\\'\"",
            "<AposAttrContentChar>", "<Comment>", "\"(:\"", "<CommentContent>", "\":)\"",
            "<CommentContents>", "<skip_>", "<Prefix>", "<LocalPart>", "<ValidationMode>",
            "<Nmstart>", "<Nmchar>", "<Digits>", "<HexDigits>", "<NCName>", "<Char>",
            "<ExtensionContentChar>", "<WhitespaceChar>", "<Letter>", "<BaseChar>",
            "<Ideographic>", "<CombiningChar>", "<Digit>", "<Extender>", "<space_>", };

}

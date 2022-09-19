/*
 * Copyright 2017 PingCAP, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pingcap.tikv.meta;

import java.util.Map;
import org.tikv.common.util.Pair;
import org.tikv.shade.com.google.common.collect.ImmutableMap;

public class Collation {
  public static final int DEF_COLLATION_CODE = 83;
  private static final Map<String, Integer> collationMap;
  private static final Map<Integer, String> collationCodeMap;
  private static boolean newCollationEnabled = false;

  static {
    collationMap =
        ImmutableMap.<String, Integer>builder()
            .put("big5_chinese_ci", 1)
            .put("latin2_czech_cs", 2)
            .put("dec8_swedish_ci", 3)
            .put("cp850_general_ci", 4)
            .put("latin1_german1_ci", 5)
            .put("hp8_english_ci", 6)
            .put("koi8r_general_ci", 7)
            .put("latin1_swedish_ci", 8)
            .put("latin2_general_ci", 9)
            .put("swe7_swedish_ci", 10)
            .put("ascii_general_ci", 11)
            .put("ujis_japanese_ci", 12)
            .put("sjis_japanese_ci", 13)
            .put("cp1251_bulgarian_ci", 14)
            .put("latin1_danish_ci", 15)
            .put("hebrew_general_ci", 16)
            .put("tis620_thai_ci", 18)
            .put("euckr_korean_ci", 19)
            .put("latin7_estonian_cs", 20)
            .put("latin2_hungarian_ci", 21)
            .put("koi8u_general_ci", 22)
            .put("cp1251_ukrainian_ci", 23)
            .put("gb2312_chinese_ci", 24)
            .put("greek_general_ci", 25)
            .put("cp1250_general_ci", 26)
            .put("latin2_croatian_ci", 27)
            .put("gbk_chinese_ci", 28)
            .put("cp1257_lithuanian_ci", 29)
            .put("latin5_turkish_ci", 30)
            .put("latin1_german2_ci", 31)
            .put("armscii8_general_ci", 32)
            .put("utf8_general_ci", 33)
            .put("cp1250_czech_cs", 34)
            .put("ucs2_general_ci", 35)
            .put("cp866_general_ci", 36)
            .put("keybcs2_general_ci", 37)
            .put("macce_general_ci", 38)
            .put("macroman_general_ci", 39)
            .put("cp852_general_ci", 40)
            .put("latin7_general_ci", 41)
            .put("latin7_general_cs", 42)
            .put("macce_bin", 43)
            .put("cp1250_croatian_ci", 44)
            .put("utf8mb4_general_ci", 45)
            .put("utf8mb4_bin", 46)
            .put("latin1_bin", 47)
            .put("latin1_general_ci", 48)
            .put("latin1_general_cs", 49)
            .put("cp1251_bin", 50)
            .put("cp1251_general_ci", 51)
            .put("cp1251_general_cs", 52)
            .put("macroman_bin", 53)
            .put("utf16_general_ci", 54)
            .put("utf16_bin", 55)
            .put("utf16le_general_ci", 56)
            .put("cp1256_general_ci", 57)
            .put("cp1257_bin", 58)
            .put("cp1257_general_ci", 59)
            .put("utf32_general_ci", 60)
            .put("utf32_bin", 61)
            .put("utf16le_bin", 62)
            .put("binary", 63)
            .put("armscii8_bin", 64)
            .put("ascii_bin", 65)
            .put("cp1250_bin", 66)
            .put("cp1256_bin", 67)
            .put("cp866_bin", 68)
            .put("dec8_bin", 69)
            .put("greek_bin", 70)
            .put("hebrew_bin", 71)
            .put("hp8_bin", 72)
            .put("keybcs2_bin", 73)
            .put("koi8r_bin", 74)
            .put("koi8u_bin", 75)
            .put("latin2_bin", 77)
            .put("latin5_bin", 78)
            .put("latin7_bin", 79)
            .put("cp850_bin", 80)
            .put("cp852_bin", 81)
            .put("swe7_bin", 82)
            .put("utf8_bin", 83)
            .put("big5_bin", 84)
            .put("euckr_bin", 85)
            .put("gb2312_bin", 86)
            .put("gbk_bin", 87)
            .put("sjis_bin", 88)
            .put("tis620_bin", 89)
            .put("ucs2_bin", 90)
            .put("ujis_bin", 91)
            .put("geostd8_general_ci", 92)
            .put("geostd8_bin", 93)
            .put("latin1_spanish_ci", 94)
            .put("cp932_japanese_ci", 95)
            .put("cp932_bin", 96)
            .put("eucjpms_japanese_ci", 97)
            .put("eucjpms_bin", 98)
            .put("cp1250_polish_ci", 99)
            .put("utf16_unicode_ci", 101)
            .put("utf16_icelandic_ci", 102)
            .put("utf16_latvian_ci", 103)
            .put("utf16_romanian_ci", 104)
            .put("utf16_slovenian_ci", 105)
            .put("utf16_polish_ci", 106)
            .put("utf16_estonian_ci", 107)
            .put("utf16_spanish_ci", 108)
            .put("utf16_swedish_ci", 109)
            .put("utf16_turkish_ci", 110)
            .put("utf16_czech_ci", 111)
            .put("utf16_danish_ci", 112)
            .put("utf16_lithuanian_ci", 113)
            .put("utf16_slovak_ci", 114)
            .put("utf16_spanish2_ci", 115)
            .put("utf16_roman_ci", 116)
            .put("utf16_persian_ci", 117)
            .put("utf16_esperanto_ci", 118)
            .put("utf16_hungarian_ci", 119)
            .put("utf16_sinhala_ci", 120)
            .put("utf16_german2_ci", 121)
            .put("utf16_croatian_ci", 122)
            .put("utf16_unicode_520_ci", 123)
            .put("utf16_vietnamese_ci", 124)
            .put("ucs2_unicode_ci", 128)
            .put("ucs2_icelandic_ci", 129)
            .put("ucs2_latvian_ci", 130)
            .put("ucs2_romanian_ci", 131)
            .put("ucs2_slovenian_ci", 132)
            .put("ucs2_polish_ci", 133)
            .put("ucs2_estonian_ci", 134)
            .put("ucs2_spanish_ci", 135)
            .put("ucs2_swedish_ci", 136)
            .put("ucs2_turkish_ci", 137)
            .put("ucs2_czech_ci", 138)
            .put("ucs2_danish_ci", 139)
            .put("ucs2_lithuanian_ci", 140)
            .put("ucs2_slovak_ci", 141)
            .put("ucs2_spanish2_ci", 142)
            .put("ucs2_roman_ci", 143)
            .put("ucs2_persian_ci", 144)
            .put("ucs2_esperanto_ci", 145)
            .put("ucs2_hungarian_ci", 146)
            .put("ucs2_sinhala_ci", 147)
            .put("ucs2_german2_ci", 148)
            .put("ucs2_croatian_ci", 149)
            .put("ucs2_unicode_520_ci", 150)
            .put("ucs2_vietnamese_ci", 151)
            .put("ucs2_general_mysql500_ci", 159)
            .put("utf32_unicode_ci", 160)
            .put("utf32_icelandic_ci", 161)
            .put("utf32_latvian_ci", 162)
            .put("utf32_romanian_ci", 163)
            .put("utf32_slovenian_ci", 164)
            .put("utf32_polish_ci", 165)
            .put("utf32_estonian_ci", 166)
            .put("utf32_spanish_ci", 167)
            .put("utf32_swedish_ci", 168)
            .put("utf32_turkish_ci", 169)
            .put("utf32_czech_ci", 170)
            .put("utf32_danish_ci", 171)
            .put("utf32_lithuanian_ci", 172)
            .put("utf32_slovak_ci", 173)
            .put("utf32_spanish2_ci", 174)
            .put("utf32_roman_ci", 175)
            .put("utf32_persian_ci", 176)
            .put("utf32_esperanto_ci", 177)
            .put("utf32_hungarian_ci", 178)
            .put("utf32_sinhala_ci", 179)
            .put("utf32_german2_ci", 180)
            .put("utf32_croatian_ci", 181)
            .put("utf32_unicode_520_ci", 182)
            .put("utf32_vietnamese_ci", 183)
            .put("utf8_unicode_ci", 192)
            .put("utf8_icelandic_ci", 193)
            .put("utf8_latvian_ci", 194)
            .put("utf8_romanian_ci", 195)
            .put("utf8_slovenian_ci", 196)
            .put("utf8_polish_ci", 197)
            .put("utf8_estonian_ci", 198)
            .put("utf8_spanish_ci", 199)
            .put("utf8_swedish_ci", 200)
            .put("utf8_turkish_ci", 201)
            .put("utf8_czech_ci", 202)
            .put("utf8_danish_ci", 203)
            .put("utf8_lithuanian_ci", 204)
            .put("utf8_slovak_ci", 205)
            .put("utf8_spanish2_ci", 206)
            .put("utf8_roman_ci", 207)
            .put("utf8_persian_ci", 208)
            .put("utf8_esperanto_ci", 209)
            .put("utf8_hungarian_ci", 210)
            .put("utf8_sinhala_ci", 211)
            .put("utf8_german2_ci", 212)
            .put("utf8_croatian_ci", 213)
            .put("utf8_unicode_520_ci", 214)
            .put("utf8_vietnamese_ci", 215)
            .put("utf8_general_mysql500_ci", 223)
            .put("utf8mb4_unicode_ci", 224)
            .put("utf8mb4_icelandic_ci", 225)
            .put("utf8mb4_latvian_ci", 226)
            .put("utf8mb4_romanian_ci", 227)
            .put("utf8mb4_slovenian_ci", 228)
            .put("utf8mb4_polish_ci", 229)
            .put("utf8mb4_estonian_ci", 230)
            .put("utf8mb4_spanish_ci", 231)
            .put("utf8mb4_swedish_ci", 232)
            .put("utf8mb4_turkish_ci", 233)
            .put("utf8mb4_czech_ci", 234)
            .put("utf8mb4_danish_ci", 235)
            .put("utf8mb4_lithuanian_ci", 236)
            .put("utf8mb4_slovak_ci", 237)
            .put("utf8mb4_spanish2_ci", 238)
            .put("utf8mb4_roman_ci", 239)
            .put("utf8mb4_persian_ci", 240)
            .put("utf8mb4_esperanto_ci", 241)
            .put("utf8mb4_hungarian_ci", 242)
            .put("utf8mb4_sinhala_ci", 243)
            .put("utf8mb4_german2_ci", 244)
            .put("utf8mb4_croatian_ci", 245)
            .put("utf8mb4_unicode_520_ci", 246)
            .put("utf8mb4_vietnamese_ci", 247)
            .build();

    ImmutableMap.Builder<Integer, String> builder = ImmutableMap.builder();
    for (String collation : collationMap.keySet()) {
      builder.put(collationMap.get(collation), collation);
    }
    collationCodeMap = builder.build();
  }

  public static int translate(String collation) {
    Integer code = collationMap.get(collation);
    if (code == null) {
      return DEF_COLLATION_CODE;
    }
    return code;
  }

  public static String translate(int code) {
    if (code < 0 && isNewCollationEnabled()) {
      code = -code;
    }
    String collation = collationCodeMap.get(code);
    if (collation == null) {
      return "";
    }
    return collation;
  }

  public static void setNewCollationEnabled(boolean flag) {
    newCollationEnabled = flag;
  }

  public static Boolean isNewCollationEnabled() {
    return newCollationEnabled;
  }

  // RewriteNewCollationIDIfNeeded rewrites a collation id if the new collations are enabled.
  // When new collations are enabled, we turn the collation id to negative so that other the
  // components of the cluster(for example, TiKV) is able to aware of it without any change to
  // the protocol definition.
  // When new collations are not enabled, collation id remains the same.
  public static int rewriteNewCollationIDIfNeeded(int id) {
    if (isNewCollationEnabled()) {
      if (id >= 0) {
        return -id;
      }
    }
    return id;
  }

  public static String truncateTailingSpace(String str) {
    int i = str.length() - 1;
    for (; i >= 0; i--) {
      if (str.charAt(i) != ' ') {
        break;
      }
    }
    return str.substring(0, i + 1);
  }

  public static int sign(int i) {
    return i > 0 ? 1 : i < 0 ? -1 : 0;
  }

  public static int runeLen(int b) {
    if (b < 0x80) {
      return 1;
    } else if (b < 0xE0) {
      return 2;
    } else if (b < 0xF0) {
      return 3;
    }
    return 4;
  }

  private static final int DEFAULT_LEN = 0;
  private static final byte b2Mask = 0x1F;
  private static final byte b3Mask = 0x0F;
  private static final byte b4Mask = 0x07;
  private static final byte mbMask = 0x3F;

  public static Pair<Integer, Integer> decodeRune(String s, int si) {
    int r = 0;
    int newIndex = 0;
    byte[] sb = s.getBytes();
    int b = sb[si] & 0xFF;
    switch (runeLen(b)) {
      case 1:
        r = b;
        newIndex = si + 1;
        break;
      case 2:
        r = (b & b2Mask) << 6 | (sb[1 + si] & 0xFF & mbMask);
        newIndex = si + 2;
        break;
      case 3:
        r = (b & b3Mask) << 12 | (sb[1 + si] & 0xFF & mbMask) << 6 | (sb[2 + si] & 0xFF & mbMask);
        newIndex = si + 3;
        break;
      default:
        r =
            (b & b4Mask) << 18
                | (sb[1 + si] & 0xFF & mbMask) << 12
                | (sb[2 + si] & 0xFF & mbMask) << 6
                | (sb[3 + si] & 0xFF & mbMask);
        newIndex = si + 4;
    }
    return new Pair<>(r, newIndex);
  }

  public static boolean isBinCollation(int collation) {
    return translate(collation).equals("binary") || translate(collation).endsWith("_bin");
  }

  public static boolean isUTF8BinCollation(int collation) {
    return collation == translate("utf8_bin") || collation == translate("utf8mb4_bin");
  }

  public static boolean isUTF8GeneralCICollation(int collation) {
    return collation == translate("utf8_general_ci")
        || collation == translate("utf8mb4_general_ci");
  }

  public static boolean isUTF8UnicodeCICollation(int collation) {
    return collation == translate("utf8_unicode_ci")
        || collation == translate("utf8mb4_unicode_ci");
  }
}

package org.wind57.mp3;

import java.util.HashMap;
import java.util.Map;

import static org.wind57.mp3.AudioVersionIDProvider.AudioVersion;
import static org.wind57.mp3.LayerProvider.LayerVersion;

final class BitrateProvider {

    // 15 and 12 bits are set 0000_0000_0000_0000_1111_0000_0000_0000
    private static final int BITRATE_MASK = 61440;

    // 0000_0000_0000_0000_0000_0000_0001_0000
    private static final int V1_MASK = 16;

    // 0000_0000_0000_0000_0000_0000_0000_1000
    private static final int V2_MASK = 8;

    // 0000_0000_0000_0000_0000_0000_0000_0100
    private static final int L1_MASK = 4;

    // 0000_0000_0000_0000_0000_0000_0000_0010
    private static final int L2_MASK = 2;

    // 0000_0000_0000_0000_0000_0000_0000_0001
    private static final int L3_MASK = 1;

    private static final Map<Integer, String> BITRATE_MAPPINGS = new HashMap<>();

    static {
        BITRATE_MAPPINGS.put(20,                       "free");      // 0000_10100 ( 0000 and V1,L1 )
        BITRATE_MAPPINGS.put(18,                       "free");      // 0000_10010 ( 0000 and V1,L2 )
        BITRATE_MAPPINGS.put(17,                       "free");      // 0000_10001 ( 0000 and V1,L3 )
        BITRATE_MAPPINGS.put(12,                       "free");      // 0000_01100 ( 0000 and V2,L1 )
        BITRATE_MAPPINGS.put(10,                       "free");      // 0000_01010 ( 0000 and V2,L2 )
        BITRATE_MAPPINGS.put(9,                        "free");      // 0000_01001 ( 0000 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 32,                    "32");      // 0001_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 32,                    "32");      // 0001_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 32,                    "32");      // 0001_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 32,                    "32");      // 0001_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 32,                     "8");      // 0001_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 32,                     "8");      // 0001_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 64,                    "64");      // 0010_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 64,                    "48");      // 0010_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 64,                    "40");      // 0010_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 64,                    "48");      // 0010_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 64,                    "16");      // 0010_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 64,                    "16");      // 0010_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 32 + 64,               "96");      // 0011_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 32 + 64,               "56");      // 0011_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 32 + 64,               "48");      // 0011_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 32 + 64,               "56");      // 0011_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 32 + 64,               "24");      // 0011_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 32 + 64,               "24");      // 0011_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 128,                  "128");      // 0100_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 128,                   "64");      // 0100_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 128,                   "56");      // 0100_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 128,                   "64");      // 0100_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 128,                   "32");      // 0100_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 128,                   "32");      // 0100_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 32 + 128,             "160");      // 0101_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 32 + 128,              "80");      // 0101_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 32 + 128,              "64");      // 0101_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 32 + 128,              "80");      // 0101_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 32 + 128,              "40");      // 0101_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 32 + 128,              "40");      // 0101_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 64 + 128,             "192");      // 0110_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 64 + 128,              "96");      // 0110_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 64 + 128,              "80");      // 0110_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 64 + 128,              "96");      // 0110_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 64 + 128,              "48");      // 0110_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 64 + 128,              "48");      // 0110_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 32 + 64 + 128,        "224");      // 0111_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 32 + 64 + 128,        "112");      // 0111_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 32 + 64 + 128,         "96");      // 0111_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 32 + 64 + 128,        "112");      // 0111_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 32 + 64 + 128,         "56");      // 0111_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 32 + 64 + 128,         "56");      // 0111_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 256,                  "256");      // 1000_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 256,                  "128");      // 1000_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 256,                  "112");      // 1000_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 256,                  "128");      // 1000_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 256,                   "64");      // 1000_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 256,                   "64");      // 1000_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 32 + 256,             "288");      // 1001_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 32 + 256,             "160");      // 1001_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 32 + 256,             "128");      // 1001_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 32 + 256,             "144");      // 1001_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 32 + 256,              "80");      // 1001_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 32 + 256,              "80");      // 1001_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 64 + 256,             "320");      // 1010_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 64 + 256,             "192");      // 1010_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 64 + 256,             "160");      // 1010_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 64 + 256,             "160");      // 1010_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 64 + 256,              "96");      // 1010_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 64 + 256,              "96");      // 1010_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 32 + 64 + 256,        "352");      // 1011_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 32 + 64 + 256,        "224");      // 1011_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 32 + 64 + 256,        "192");      // 1011_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 32 + 64 + 256,        "176");      // 1011_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 32 + 64 + 256,        "112");      // 1011_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 32 + 64 + 256,        "112");      // 1011_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 128 + 256,            "384");      // 1100_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 128 + 256,            "256");      // 1100_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 128 + 256,            "224");      // 1100_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 128 + 256,            "192");      // 1100_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 128 + 256,            "128");      // 1100_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 128 + 256,            "128");      // 1100_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 32 + 128 + 256,       "416");      // 1101_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 32 + 128 + 256,       "320");      // 1101_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 32 + 128 + 256,       "256");      // 1101_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 32 + 128 + 256,       "224");      // 1101_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 32 + 128 + 256,       "144");      // 1101_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 32 + 128 + 256,       "144");      // 1101_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 64 + 128 + 256,       "448");      // 1110_10100 ( 0001 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 64 + 128 + 256,       "384");      // 1110_10010 ( 0001 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 64 + 128 + 256,       "320");      // 1110_10001 ( 0001 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 64 + 128 + 256,       "256");      // 1110_01100 ( 0001 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 64 + 128 + 256,       "160");      // 1110_01010 ( 0001 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 64 + 128 + 256,       "160");      // 1110_01001 ( 0001 and V2,L3 )

        BITRATE_MAPPINGS.put(20 + 32 + 64 + 128 + 256,  "bad");      // 1111_10100 ( 0000 and V1,L1 )
        BITRATE_MAPPINGS.put(18 + 32 + 64 + 128 + 256,  "bad");      // 1111_10010 ( 0000 and V1,L2 )
        BITRATE_MAPPINGS.put(17 + 32 + 64 + 128 + 256,  "bad");      // 1111_10001 ( 0000 and V1,L3 )
        BITRATE_MAPPINGS.put(12 + 32 + 64 + 128 + 256,  "bad");      // 1111_01100 ( 0000 and V2,L1 )
        BITRATE_MAPPINGS.put(10 + 32 + 64 + 128 + 256,  "bad");      // 1111_01010 ( 0000 and V2,L2 )
        BITRATE_MAPPINGS.put(9  + 32 + 64 + 128 + 256,  "bad");      // 1111_01001 ( 0000 and V2,L3 )

    }

    /**
     * shortVersion = true, if v1, else v2
     */
    static String bitrate(int frame, AudioVersion audioVersion, LayerVersion layerVersion) {

        if (audioVersion == AudioVersion.RESERVED) {
            return "Bitrate : NONE ('reserved' audio version)";
        }

        if (layerVersion == LayerVersion.RESERVED) {
            return "Bitrate : NONE ('reserved' layer)";
        }

        int bitRateIndex = (frame & BITRATE_MASK) >> 12;

        // make space, to the right for : V1 / V2 / L1 / L2 / L3
        bitRateIndex = bitRateIndex << 5;
        bitRateIndex = withAudioVersion(bitRateIndex, audioVersion);
        bitRateIndex = withLayerVersion(bitRateIndex, layerVersion);

        return BITRATE_MAPPINGS.get(bitRateIndex);

    }

    private static int withAudioVersion(int bitRateIndex, AudioVersion audioVersion) {
        switch (audioVersion) {
            case V1  -> bitRateIndex = bitRateIndex | V1_MASK;
            case V2  -> bitRateIndex = bitRateIndex | V2_MASK;
            case V25 -> bitRateIndex = bitRateIndex | V2_MASK;
        }
        return bitRateIndex;
    }

    private static int withLayerVersion(int bitRateIndex, LayerVersion layerVersion) {
        switch (layerVersion) {
            case L1 -> bitRateIndex = bitRateIndex | L1_MASK;
            case L2 -> bitRateIndex = bitRateIndex | L2_MASK;
            case L3 -> bitRateIndex = bitRateIndex | L3_MASK;
        }
        return bitRateIndex;
    }

}

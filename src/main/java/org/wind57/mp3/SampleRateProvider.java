package org.wind57.mp3;

import java.util.HashMap;
import java.util.Map;

import static org.wind57.mp3.AudioVersionIDProvider.AudioVersion;

final class SampleRateProvider {

    private static final Map<Integer, String> SAMPLE_RATE_MAPPINGS = new HashMap<>();

    static {
        SAMPLE_RATE_MAPPINGS.put(4,            "44100 Hz");  // 00_100 (00 V1)
        SAMPLE_RATE_MAPPINGS.put(2,            "22050 Hz");  // 00_010 (00 V2)
        SAMPLE_RATE_MAPPINGS.put(1,            "11025 Hz");  // 00_001 (00 V2.5)

        SAMPLE_RATE_MAPPINGS.put(4 + 8,        "48000 Hz");  // 01_100 (01 V1)
        SAMPLE_RATE_MAPPINGS.put(2 + 8,        "24000 Hz");  // 01_010 (01 V2)
        SAMPLE_RATE_MAPPINGS.put(1 + 8,        "12000 Hz");  // 01_001 (01 V2.5)

        SAMPLE_RATE_MAPPINGS.put(4 + 16,       "32000 Hz");  // 10_100 (10 V1)
        SAMPLE_RATE_MAPPINGS.put(2 + 16,       "16000 Hz");  // 10_010 (10 V2)
        SAMPLE_RATE_MAPPINGS.put(1 + 16,       "8000 Hz");   // 10_001 (10 V2.5)

        SAMPLE_RATE_MAPPINGS.put(4 + 8 + 16,   "reserve");   // 11_100 (11 V1)
        SAMPLE_RATE_MAPPINGS.put(2 + 8 + 16,   "reserve");   // 11_010 (11 V2)
        SAMPLE_RATE_MAPPINGS.put(1 + 8 + 16,   "reserve");   // 11_001 (11 V2.5)
    }

    // 11 and 10 bits are set 0000_0000_0000_0000_0000_1100_0000_0000
    private static final int SAMPLE_RATE_MASK = 3072;

    // 0000_0000_0000_0000_0000_0000_0000_0100
    private static final int V1_MASK = 4;

    // 0000_0000_0000_0000_0000_0000_0000_0010
    private static final int V2_MASK = 2;

    // 0000_0000_0000_0000_0000_0000_0000_0001
    private static final int V25_MASK = 1;

    static String rate(int frame, AudioVersion audioVersion) {

        if (audioVersion == AudioVersion.RESERVED) {
            return "Sample Rate : NONE ('reserved' audio version)";
        }

        int rateIndex = (frame & SAMPLE_RATE_MASK) >> 10;
        // make space, to the right, for : V1 / V2 / V2.5
        rateIndex = rateIndex << 3;
        rateIndex = withAudioVersion(rateIndex, audioVersion);

        return SAMPLE_RATE_MAPPINGS.get(rateIndex);
    }

    private static int withAudioVersion(int rateIndex, AudioVersion audioVersion) {
        switch (audioVersion) {
            case V1  -> rateIndex = rateIndex | V1_MASK;
            case V2  -> rateIndex = rateIndex | V2_MASK;
            case V25 -> rateIndex = rateIndex | V25_MASK;
        }
        return rateIndex;
    }


}

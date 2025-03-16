package org.wind57.mp3;

final class AudioVersionIDProvider {

    // 20 and 19 bits are set 0000_0000_0001_1000_0000_0000_0000_0000
    private static final int MPEG_AUDIO_VERSION_ID_MASK = 1572864;

    static AudioIdAndVersion audio(int frame) {

        int audioVersionID = (( frame & MPEG_AUDIO_VERSION_ID_MASK) >> 19);

        return switch (audioVersionID) {
            case 0 -> new AudioIdAndVersion("MPEG Version 2.5 (later extension of MPEG 2)", AudioVersion.V25);
            case 1 -> new AudioIdAndVersion("reserved", AudioVersion.RESERVED);
            case 2 -> new AudioIdAndVersion("MPEG Version 2 (ISO/IEC 13818-3)", AudioVersion.V2);
            case 3 -> new AudioIdAndVersion("MPEG Version 1 (ISO/IEC 11172-3)", AudioVersion.V1);
            default -> throw new IllegalStateException("Unexpected value: " + audioVersionID);
        };

    }

    enum AudioVersion {
        V1,
        V2,
        V25,
        RESERVED
    }

    record AudioIdAndVersion(String audioId, AudioVersion audioVersion) {}

}

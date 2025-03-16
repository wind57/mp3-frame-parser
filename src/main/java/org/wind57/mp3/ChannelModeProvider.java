package org.wind57.mp3;

final class ChannelModeProvider {

    // 7 and 6 bits are set 0000_0000_0000_0000_0000_0000_1100_0000
    private static final int CHANNEL_MODE_MASK = 192;

    static String channel(int frame) {

        int channelIndex = (frame & CHANNEL_MODE_MASK) >> 6;

        return switch (channelIndex) {
            case 0 -> "Stereo";
            case 1 -> "Joint stereo (Stereo)";
            case 2 -> "Dual channel (2 mono channels)";
            case 3 -> "Single channel (Mono)";
            default -> throw new IllegalStateException("Unexpected value: " + channelIndex);
        };

    }

}

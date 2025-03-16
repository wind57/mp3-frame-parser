package org.wind57.mp3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

final class FrameProvider {

    // 31 to 21 bits are set, the rest are zeros,
    // unsigned: 1111_1111_1110_0000_0000_0000_0000_0000
    private static final int FRAME_SYNC_MASK = -2097152;

    static int frame(String path) {
        // get the file
        try (FileInputStream stream = new FileInputStream(path)) {
            FileChannel channel = stream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(4);
            int bytesRead = channel.read(buffer);
            if (bytesRead != 4) {
                throw new RuntimeException("corrupt mp3? cant read 4 bytes frame header");
            }

            // these are the 4 bytes of the frame header
            byte[] frameBytes = buffer.array();

            // this will keep each byte shifted into its position, stored as an int
            // next comment will explain this more
            int[] frameBytesAsInt = new int[4];

            // take each byte unsigned and shift it into its position.
            // for example if we read:
            // 255 = 1111_1111
            // 215 = 1111_1011
            // 208 = 1101_0000
            // 196 = 1100_0100
            // we need to shift each of them in a correct position (this is frameBytesAsInt), so
            // 1111_1111_0000_0000_0000_0000_0000_0000 (255 shifted 24 times)
            // 0000_0000_1111_1011_0000_0000_0000_0000 (215 shifted 16 times)
            // 0000_0000_0000_0000_1101_0000_0000_0000 (208 shifted  8 times)
            // 0000_0000_0000_0000_0000_0000_1100_0100 (196 shifted  0 times)

            for (int i = 0; i < bytesRead; i++) {
                int x = Byte.toUnsignedInt(frameBytes[i]);
                System.out.println();
                frameBytesAsInt[i] = x << (24 - i * 8);
            }

            // do an OR operation on all parts, and this will get us the frame header as bits
            int frame = 0;
            for (int j : frameBytesAsInt) {
                frame |= j;
            }

            System.out.println("frame header as bits : " + Integer.toBinaryString(frame));
            return validateFrameSync(frame);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    // 31 - 21 bits of a frame header must be set
    private static int validateFrameSync(int frame) {
        if ((frame & FRAME_SYNC_MASK) != FRAME_SYNC_MASK) {
            throw new RuntimeException("Invalid mp3, 'frame sync mask not set correctly'");
        }
        return frame;
    }

}

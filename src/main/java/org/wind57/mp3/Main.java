package org.wind57.mp3;

import static org.wind57.mp3.FrameProvider.frame;
import static org.wind57.mp3.LayerProvider.layer;
import static org.wind57.mp3.AudioVersionIDProvider.audio;
import static org.wind57.mp3.BitrateProvider.bitrate;
import static org.wind57.mp3.SampleRateProvider.rate;
import static org.wind57.mp3.ChannelModeProvider.channel;

import static org.wind57.mp3.AudioVersionIDProvider.AudioIdAndVersion;
import static org.wind57.mp3.LayerProvider.LayerIdAndVersion;

public class Main {

    public static void main(String[] args) {
        int frame = frame(args[0]);

        AudioIdAndVersion audioVersionId = audio(frame);
        LayerIdAndVersion layerIdAndVersion = layer(frame);
        String bitrate = bitrate(frame, audioVersionId.audioVersion(), layerIdAndVersion.layerVersion());

        System.out.println("Audio Version ID : " + audioVersionId.audioId());
        System.out.println("Layer            : " + layerIdAndVersion.layerId());
        System.out.println("Bitrate          : " + bitrate);
        System.out.println("Sample Rate      : " + rate(frame, audioVersionId.audioVersion()));
        System.out.println("Channel          : " + channel(frame));

    }

}

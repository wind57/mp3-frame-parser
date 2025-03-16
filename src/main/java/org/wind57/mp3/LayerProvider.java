package org.wind57.mp3;

final class LayerProvider {

    // 18 and 17 bits are set 0000_0000_0000_0110_0000_0000_0000_0000
    private static final int LAYER_MASK = 393216;

    static LayerIdAndVersion layer(int frame) {

        int layer = (( frame & LAYER_MASK) >> 17);

        return switch (layer) {
            case 0 -> new LayerIdAndVersion("reserved", LayerVersion.RESERVED);
            case 1 -> new LayerIdAndVersion("Layer III", LayerVersion.L3);
            case 2 -> new LayerIdAndVersion("Layer II", LayerVersion.L2);
            case 3 -> new LayerIdAndVersion("Layer I", LayerVersion.L1);
            default -> throw new IllegalStateException("Unexpected value: " + layer);
        };

    }

    enum LayerVersion {
        L1,
        L2,
        L3,
        RESERVED
    }

    record LayerIdAndVersion(String layerId, LayerVersion layerVersion) {}

}

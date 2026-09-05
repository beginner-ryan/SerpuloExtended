package serpuloex;

public class RoofMode {
    private static boolean roofModeEnabled = false;

    public static void toggleRoofMode() {
        roofModeEnabled = !roofModeEnabled;
    }

    public static void setRoofMode(boolean state) {
        roofModeEnabled = state;
    }

    public static boolean isRoofMode() {
        return roofModeEnabled;
    }
}

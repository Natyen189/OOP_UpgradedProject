package TowerDefense;

public final class Config {
    /**
     * Game name. Change it if you want.
     */
    public static final String GAME_NAME = "Tower Defender";
    /**
     * Ticks per second
     */
    public static final int GAME_TPS = 30;
    /**
     * Size of the tile, in pixel.
     * 1.0f field unit == TILE_SIZE pixel on the screen.
     * Change it base on your texture size.
     */
    public static final int TILE_SIZE = 63;
    /**
     * Num of tiles the screen can display if fieldZoom is TILE_SIZE,
     * in other words, the texture will be display as it without scaling.
     */
    public static final int TILE_HORIZONTAL = 19;
    /**
     * Num of tiles the screen can display if fieldZoom is TILE_SIZE,
     * in other words, the texture will be display as it without scaling.
     */
    public static final int TILE_VERTICAL = 11;
    /**
     * Size of the menu screen.
     */
    public static final int MENU_WIDTH = 300;
    /**
     * Size of the menu screen.
     */
    public static final int MENU_HEIGHT = 100;
    /**
     * Size of the screen.
     */
    public static final int SCREEN_WIDTH = TILE_SIZE * TILE_HORIZONTAL + MENU_WIDTH;
    /**
     * Size of the screen.
     */
    public static final int SCREEN_HEIGHT = TILE_SIZE * TILE_VERTICAL + MENU_HEIGHT;
}

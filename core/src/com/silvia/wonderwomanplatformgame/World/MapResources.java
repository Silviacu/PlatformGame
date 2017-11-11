package com.silvia.wonderwomanplatformgame.World;

public class MapResources {
    private String[] mapOneLayers = {
        "N/A",
        "BG_SKY",
        "BG_CLOUDS",
        "BG_OTHER",
        "BG_ENVIRONMENT",
        "FG_GROUND",
        "FG_GROUND_DECOR",
        "BG_OBJECTS",
        "BG_DESTRUCTIBLES",
        "OBJ_TREASURE_1",
        "OBJ_TREASURE_2",
        "OBJ_BREAKABLES",
        "OBJ_SPIKES",
        "OBJ_POWERUPS",
        "OBJ_ZONE_FALL",
        "OBJ_MONEY",
        "OBJ_HEALTH",
        "OBJ_ZONE_START",
        "OBJ_ZONE_END",
        "OBJ_GROUND"
    };

    public int getIndexOf(String q) {
        return java.util.Arrays.asList(mapOneLayers).indexOf(q);
    }

    public static int bg_sky = 0;
    public static int bg_clouds = 1;
    public static int bg_other = 2;
    public static int bg_environment = 3;
    public static int fg_ground = 4;
    public static int fg_ground_decor = 5;
    public static int bg_objects = 6;
    public static int fg_destructibles = 7;
    public static int obj_treasure_sm = 8;
    public static int obj_treasure_lg = 9;
    public static int obj_breakables = 10;
    public static int obj_spikes = 11;
    public static int obj_powerups = 12;
    public static int obj_zone_fall = 13;
    public static int obj_money = 14;
    public static int obj_health = 15;
    public static int obj_zone_start = 16;
    public static int obj_zone_end = 17;
    public static int obj_ground = 18;
}

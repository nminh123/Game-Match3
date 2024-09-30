package net.nminh.match3game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class Assets
{
    static HashMap<String, TextureRegion> texturesMap = new HashMap<>();
    static HashMap<String, Animation> animationsMap = new HashMap<>();
    static TextureAtlas atlas;

    public Assets() {}

    public static void Load()
    {
        //LOGO
        texturesMap.put(Consts.LOGO_BG, new TextureRegion(new Texture(Utils.getInternalPath(Consts.LOGO_BG))));
        texturesMap.put(Consts.LOGO_CAKE, new TextureRegion(new Texture(Utils.getInternalPath(Consts.LOGO_CAKE))));
        texturesMap.put(Consts.LOGO_MATCH3, new TextureRegion(new Texture(Utils.getInternalPath(Consts.LOGO_MATCH3))));
        texturesMap.put(Consts.LOGO_SWEET, new TextureRegion(new Texture(Utils.getInternalPath(Consts.LOGO_SWEET))));
        texturesMap.put(Consts.LOGO_PURPLE_DONUT, new TextureRegion(new Texture(Utils.getInternalPath(Consts.LOGO_PURPLE_DONUT))));
        texturesMap.put(Consts.LOGO_GREEN_DONUT, new TextureRegion(new Texture(Utils.getInternalPath(Consts.LOGO_GREEN_DONUT))));

        //HOMESCREEN
        texturesMap.put(Consts.HOMESCREEN_BG, new TextureRegion(new Texture(Utils.getInternalPath(Consts.HOMESCREEN_BG))));
        texturesMap.put(Consts.HOMESCREEN_PLAY_BTN, new TextureRegion(new Texture(Utils.getInternalPath(Consts.HOMESCREEN_PLAY_BTN))));
        texturesMap.put(Consts.HOMESCREEN_RATE_BTN, new TextureRegion(new Texture(Utils.getInternalPath(Consts.HOMESCREEN_RATE_BTN))));
        texturesMap.put(Consts.HOMESCREEN_SETTING_BTN, new TextureRegion(new Texture(Utils.getInternalPath(Consts.HOMESCREEN_SETTING_BTN))));
        texturesMap.put(Consts.HOMESCREEN_CHAR_LAYOUT, new TextureRegion(new Texture(Utils.getInternalPath(Consts.HOMESCREEN_CHAR_LAYOUT))));

        //GameScreen
        texturesMap.put(Consts.GAMESCREEN_BG, new TextureRegion(new Texture(Utils.getInternalPath(Consts.GAMESCREEN_BG))));
        texturesMap.put(Consts.GAMESCREEN_BACKGROUNDMOVEREMAIN, new TextureRegion(new Texture(Utils.getInternalPath(Consts.GAMESCREEN_BACKGROUNDMOVEREMAIN))));
        texturesMap.put(Consts.GAMESCREEN_PROCESS, new TextureRegion(new Texture(Utils.getInternalPath(Consts.GAMESCREEN_PROCESS))));
        texturesMap.put(Consts.BLOCK_1, new TextureRegion(new Texture(Utils.getInternalPath(Consts.BLOCK_1))));
        texturesMap.put(Consts.BLOCK_2, new TextureRegion(new Texture(Utils.getInternalPath(Consts.BLOCK_2))));

        //Candy piece
        texturesMap.put(Consts.COLOR1, new TextureRegion(new Texture(Utils.getInternalPath(Consts.COLOR1))));
        texturesMap.put(Consts.COLOR2, new TextureRegion(new Texture(Utils.getInternalPath(Consts.COLOR2))));
        texturesMap.put(Consts.COLOR3, new TextureRegion(new Texture(Utils.getInternalPath(Consts.COLOR3))));
        texturesMap.put(Consts.COLOR4, new TextureRegion(new Texture(Utils.getInternalPath(Consts.COLOR4))));
        texturesMap.put(Consts.COLOR5, new TextureRegion(new Texture(Utils.getInternalPath(Consts.COLOR5))));

        atlas = new TextureAtlas(Utils.getInternalPath(Consts.TEXTURES_ATLAS));
    }

    public static TextureRegion getRegion(String key) {return texturesMap.get(key);}

    public static TextureAtlas getTexture() {return atlas;}

    public static void dispose()
    {
        atlas.dispose();
    }
}
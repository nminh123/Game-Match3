package net.nminh.match3game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.nminh.match3game.Match3Game;
import net.nminh.match3game.actors.FramesPerSecond;
import net.nminh.match3game.utils.Assets;
import net.nminh.match3game.utils.Consts;

public class HomeScreen extends ParentScreen
{
    Match3Game game;
    FramesPerSecond fps;
    Image[] images = new Image[11];
    TextureRegion HomeScreen_BG,
            HomeScreen_Playbtn,
            HomeScreen_Ratebtn,
            HomeScreen_Settingbtn,
            HomeScreen_Charlayout,
            Logo_BG,
            Logo_Cake,
            Logo_Match3,
            Logo_Sweet,
            Logo_Purple_Donut,
            Logo_Green_Donut;

    public HomeScreen(Match3Game game)
    {
        super(game);
        this.game = game;
        cam = new OrthographicCamera(Consts.VIEWPORT_WIDTH, Consts.VIEWPORT_HEIGHT);
        Gdx.input.setInputProcessor(this);

        init();
        setUpImages();
        setupFPS();

        for(Image image : images)
            addActor(image);
        addActor(fps);
    }

    private void init()
    {
        HomeScreen_BG = Assets.getRegion(Consts.HOMESCREEN_BG);
        HomeScreen_Ratebtn = Assets.getRegion(Consts.HOMESCREEN_RATE_BTN);
        HomeScreen_Playbtn = Assets.getRegion(Consts.HOMESCREEN_PLAY_BTN);
        HomeScreen_Settingbtn = Assets.getRegion(Consts.HOMESCREEN_SETTING_BTN);
        HomeScreen_Charlayout = Assets.getRegion(Consts.HOMESCREEN_CHAR_LAYOUT);

        Logo_BG = Assets.getRegion(Consts.LOGO_BG);
        Logo_Cake = Assets.getRegion(Consts.LOGO_CAKE);
        Logo_Sweet = Assets.getRegion(Consts.LOGO_SWEET);
        Logo_Green_Donut = Assets.getRegion(Consts.LOGO_GREEN_DONUT);
        Logo_Purple_Donut = Assets.getRegion(Consts.LOGO_PURPLE_DONUT);
        Logo_Match3 = Assets.getRegion(Consts.LOGO_MATCH3);
    }

    private void setUpImages()
    {
        images[0] = new Image(HomeScreen_BG);
        images[0].setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        images[0].setPosition(0,0);

        images[1] = new Image(Logo_BG);
        images[1].setSize(Gdx.graphics.getWidth()/1.29f,Gdx.graphics.getHeight()/3.45f);
        images[1].setPosition(36.8f, 433);

        images[2] = new Image(HomeScreen_Charlayout);
        images[2].setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/1.3f);
        images[2].setPosition(0,0);

        images[3] = new Image(HomeScreen_Settingbtn);
        images[3].setSize(Gdx.graphics.getWidth()/8.3f,Gdx.graphics.getHeight()/13.5f);
        images[3].setPosition(5,580);
        images[3].addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                System.out.println("Setting clicked!!");
                Gdx.net.openURI(Consts.Video_Url);
            }
        });

        images[4] = new Image(HomeScreen_Playbtn);
        images[4].setSize(Gdx.graphics.getWidth()/1.9f,Gdx.graphics.getHeight()/6.3f);
        images[4].setPosition(74,70);
        images[4].addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                System.out.println("Go to next screen!!");
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game));
            }
        });

        images[5] = new Image(HomeScreen_Ratebtn);
        images[5].setSize(Gdx.graphics.getWidth()/2.5f, Gdx.graphics.getHeight()/7.8f);
        images[5].setPosition(101,6);
        images[5].addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                System.out.println("Rate Clicked!!");
                Gdx.net.openURI(Consts.VIDEO_URL);
            }
        });

        images[6] = new Image(Logo_Sweet);
        images[6].setSize(Gdx.graphics.getWidth()/1.46f,Gdx.graphics.getHeight()/6f);
        images[6].setPosition(53,498);

        images[7] = new Image(Logo_Cake);
        images[7].setSize(Gdx.graphics.getWidth()/2.3f, Gdx.graphics.getHeight()/11.2f);
        images[7].setPosition(96,470);

        images[8] = new Image(Logo_Green_Donut);
        images[8].setSize(36.3f,32.3f);
        images[8].setPosition(228,459);

        images[9] = new Image(Logo_Purple_Donut);
        images[9].setSize(38.3f,38.3f);
        images[9].setPosition(253,463);

        images[10] = new Image(Logo_Match3);
        images[10].setSize(110.7f,31.7f);
        images[10].setPosition(124,443);
    }

    private void setupFPS()
    {
        Rectangle fpsBounds = new Rectangle(getCamera().viewportWidth * -320 / -64,
                getCamera().viewportHeight * 1.04f, getCamera().viewportWidth,
                getCamera().viewportHeight);
        fps = new FramesPerSecond(fpsBounds);
    }

    @Override
    public void show()
    {
        super.show();
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
    }

    @Override
    public void dispose()
    {
        super.dispose();
        HomeScreen_BG.getTexture().dispose();
        HomeScreen_Playbtn.getTexture().dispose();
        HomeScreen_Ratebtn.getTexture().dispose();
        HomeScreen_Charlayout.getTexture().dispose();
        HomeScreen_Settingbtn.getTexture().dispose();

        Logo_Match3.getTexture().dispose();
        Logo_Sweet.getTexture().dispose();
        Logo_Cake.getTexture().dispose();
        Logo_BG.getTexture().dispose();
        Logo_Purple_Donut.getTexture().dispose();
        Logo_Green_Donut.getTexture().dispose();

        for(Image image : images)
        {
            image.clear();
        }
    }
}
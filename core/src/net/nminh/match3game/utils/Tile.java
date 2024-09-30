package net.nminh.match3game.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Tile extends Image
{
    public int row;
    public int col;
    public int type = -1;

    public Tile(int col, int row){
        this.col = col;
        this.row = row;
    }

    public void init(TextureRegion sprite, int index)
    {
        setBounds(getX(), getY(), getWidth(), getHeight());
        setDrawable(new TextureRegionDrawable(sprite));
        this.type = index;
    }

    public void setRowCol(int row, int col){
        this.row = row;
        this.col = col;
    }
}
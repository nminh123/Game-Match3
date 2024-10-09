package net.nminh.match3game.actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import net.nminh.match3game.utils.Consts;
import net.nminh.match3game.utils.Tile;

public class Grid extends Group
{
    Tile[][] tiles = new Tile[8][8];

    public Grid(int rows, int cols, float size, Vector2 position, TextureRegion block1, TextureRegion block2) {
        // Tạo Image và add vào Group
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                TextureRegion currentTexture = (row + col) % 2 == 0 ? block1 : block2;
                Image cell = new Image(new TextureRegion(currentTexture));
                float x = position.x + col * size;
                float y = position.y + row * size;
                cell.setSize(size, size);
                cell.setPosition(x,y);

                this.addActor(cell);  // Thêm Image vào Group
            }
        }
    }

    public Grid(Array<TextureAtlas.AtlasRegion> textures)
    {
        Vector2 position = Consts.POSITION;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile tile = new Tile(i, j);
                int num = (i + j) % 2 == 0 ? 0 : 1;
                float x = position.x + j * Consts.SIZE;
                float y = position.y + i * Consts.SIZE;
                tile.init(textures.get(num), num);
                tile.setPosition(x, y);
                tile.setSize(Consts.SIZE, Consts.SIZE);
                tiles[i][j] = tile;
                this.addActor(tile);
            }
        }
    }
}
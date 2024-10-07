package net.nminh.match3game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import net.nminh.match3game.Match3Game;

public class Grid extends Group
{
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
}
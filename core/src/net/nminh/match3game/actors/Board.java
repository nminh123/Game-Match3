package net.nminh.match3game.actors;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.nminh.match3game.utils.Consts;
import net.nminh.match3game.utils.Tile;

public class Board extends Group implements Disposable {
    Array<TextureAtlas.AtlasRegion> entities;
    Tile[][] tiles = new Tile[8][8];
    Vector2 position;

    public Board(Array<TextureAtlas.AtlasRegion> sprites, int row, int col, int size, Vector2 position) {
        this.entities = sprites;

        initialize(row, col, size, position);
    }

    public Board(Array<TextureAtlas.AtlasRegion> sprites) {
        this.entities = sprites;

        initialize();
    }

    private void initialize() {
        position = Consts.POSITION;
        setBounds(0, 0, 640, 640);
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile tile = new Tile(i, j);
                tile.addListener(clickListener);
                int num = MathUtils.random(1, 4);
                float x = position.x + j * Consts.SIZE;
                float y = position.y + i * Consts.SIZE;
                tile.init(this.entities.get(num), num);
                tile.setPosition(x, y);
                tile.setSize(Consts.SIZE, Consts.SIZE);
                tiles[i][j] = tile;
                this.addActor(tile);

                if (tiles[i][j] == null)
                    Gdx.app.log("Board", "Could not found Board Texture");

                // Tạo Label
                Label label = new Label("(" + i + "," + j + ")",
                        new Label.LabelStyle(new BitmapFont(), Color.WHITE));
                label.setPosition(x, y);
                label.setFontScale(.8f);
                this.addActor(label);
            }
        }
        findMatches();

    }

    private void initialize(int row, int col, int size, Vector2 position) {
        setBounds(0, 0, 640, 640);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Tile tile = new Tile(i, j);
                tile.addListener(clickListener);
                int num = MathUtils.random(1, 4);
                float x = position.x + col * size;
                float y = position.y + row * size;
                tile.init(this.entities.get(num), num);
                tile.setSize(size, size);
                tile.setPosition(x, y);
                tiles[i][j] = tile;

                this.addActor(tile);
            }
        }
    }

    ClickListener clickListener = new ClickListener() {
        Tile firstClick;
        int count = 0;

        @Override
        public void clicked(InputEvent event, float x, float y) {
            Tile target = (Tile) event.getTarget();

            swapTile(firstClick, target);

            count++;
            firstClick = target;

            if (count == 2) {
                firstClick = null;
                count = 0;
            } else {
                for (Tile[] tile : tiles) {
                    for (Tile t : tile) {
                        t.setTouchable(Touchable.disabled);
                    }
                }
                target.setOrigin(Align.center);
                target.addAction(sequence(parallel(moveTo(target.getX(), target.getY() + 10, 0.125f, Interpolation.swingOut),
                                Actions.scaleBy(0.2f, 0.2f, 0.125f)),
                        parallel(Actions.scaleBy(-0.2f, -0.2f, 0.125f),
                                moveTo(target.getX(), target.getY(), 0.125f, Interpolation.swingIn)), afterClick));
            }
        }

        final Action afterClick = new Action() {
            @Override
            public boolean act(float delta) {
                for (Tile[] tile : tiles) {
                    for (Tile t : tile) {
                        t.setTouchable(Touchable.enabled);
                    }
                }
                return true;
            }
        };
    };

    private void swapTile(Tile firstClick, Tile target) {
        if (firstClick != null) {
            target.clearActions();
            firstClick.clearActions();

            if (target.row == firstClick.row) {
                if (target.col == firstClick.col + 1 || target.col == firstClick.col - 1) {
                    System.out.println("Swap col target <> firstclick");
                    int row = target.row;
                    int col = target.col;
                    tiles[target.col][target.row] = firstClick;
                    tiles[firstClick.col][firstClick.row] = target;

                    target.setRowCol(firstClick.row, firstClick.col);
                    firstClick.setRowCol(row, col);

                    firstClick.addAction(Actions.moveTo(position.x + firstClick.row * Consts.SIZE,
                            position.y + firstClick.col * Consts.SIZE, .15f));
                    target.addAction(Actions.moveTo(position.x + target.row * Consts.SIZE,
                            position.y + target.col * Consts.SIZE, .15f));
                    findMatches();
                    fallingTiles();
                }
            } else if (target.col == firstClick.col) {
                if (target.row == firstClick.row + 1 || target.row == firstClick.row - 1) {
                    System.out.println("Swap row target <> firstclick");
                    int row = target.row;
                    int col = target.col;
                    tiles[target.col][target.row] = firstClick;
                    tiles[firstClick.col][firstClick.row] = target;

                    target.setRowCol(firstClick.row, firstClick.col);
                    firstClick.setRowCol(row, col);

                    firstClick.addAction(Actions.moveTo(position.x + firstClick.row * Consts.SIZE,
                            position.y + firstClick.col * Consts.SIZE, .15f));
                    target.addAction(Actions.moveTo(position.x + target.row * Consts.SIZE,
                            position.y + target.col * Consts.SIZE, .15f));
                    findMatches();
                    fallingTiles();
                }
            }
        }
    }

    //When the player swaps two tiles, the game checks for matches and removes them.
    //What the fvck is this???
    private void findMatches() {
        boolean removed = false;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != null) {
                    int type = tiles[i][j].getType();

                    // Kiểm tra theo hàng dọc
                    if (i + 2 < tiles.length &&
                            tiles[i + 1][j] != null && tiles[i + 2][j] != null &&
                            tiles[i + 1][j].getType() == type && tiles[i + 2][j].getType() == type) {
                        for (int k = 0; k < 3 && i + k < tiles.length; k++) {
                            removed = true;
                            removeTile(tiles[i + k][j]);
                        }
                    } else if (i - 2 >= 0 &&
                            tiles[i - 1][j] != null && tiles[i - 2][j] != null &&
                            tiles[i - 1][j].getType() == type && tiles[i - 2][j].getType() == type) {
                        for (int k = 0; k < 3; k++) {
                            removed = true;
                            removeTile(tiles[i - k][j]);
                        }
                    }

                    // Kiểm tra theo hàng ngang
                    if (j + 2 < tiles[i].length &&
                            tiles[i][j + 1] != null && tiles[i][j + 2] != null &&
                            tiles[i][j + 1].getType() == type && tiles[i][j + 2].getType() == type) {
                        for (int k = 0; k < 3 && j + k < tiles[i].length; k++) {
                            removed = true;
                            removeTile(tiles[i][j + k]);
                        }
                    } else if (j - 2 >= 0 &&
                            tiles[i][j - 1] != null && tiles[i][j - 2] != null &&
                            tiles[i][j - 1].getType() == type && tiles[i][j - 2].getType() == type) {
                        for (int k = 0; k < 3; k++) {
                            removed = true;
                            removeTile(tiles[i][j - k]);
                        }
                    }
                }
            }
        }
        if (removed)
            fallingTiles();
    }

//TODO: COI LẠI LOGIC ROW COL TRONG DUYỆT MẢNG, IN RA MÀN HÌNH CHƠI CÁC SỐ TRONG MẢNG.
    private void removeTile(Tile tile) {
        if (tile == null) return;
        tile.remove();
        tile.clear();
        tile.addAction(sequence(Actions.alpha(0, .5f), Actions.fadeOut(.5f)));
    }

    private void fallingTiles() {
        boolean check = true;
        while (check) {
            check = false;
//            for (int i = 0; i < tiles.length; i++) { //Đổi
//                for (int j = 0; j < tiles[i].length; j++) { //Đổi
//                    Tile tile = tiles[i][j]; //Đổi
//                    Tile under = tiles[i + 1][j]; //Đổi
//                    if (tile != null && under == null) {
//                        tile.debug();
//                        float x = position.x + j * Consts.SIZE;
//                        float y = position.y + (i - 1) * Consts.SIZE;
//                        tile.addAction(Actions.delay(1, moveTo(x, y)));
//                        tiles[i - 1][j] = tile;
//                        tiles[i][j] = null;
//                        check = true;
//                    }
//                }
//            }
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 1; j < tiles[i].length - 1; j++) {
                    Tile tile = tiles[i][j];
                    Tile under = tiles[i][j + 1];
                    if (tile != null && under == null) {
                        tile.debug();
                        float x = position.x + (j + 1) * Consts.SIZE;
                        float y = position.y + i * Consts.SIZE;
                        tile.addAction(Actions.delay(1, moveTo(x, y)));
                        tiles[i][j + 1] = tile;
                        tiles[i][j] = null;
                        check = true;
                    }
                }
            }

//            for (int i = tiles.length; i > 0; i--) { //Đổi
//                for (int j = tiles[i - 1].length - 1; j > 0; j--) { //Đổi
//                    Tile tile = tiles[i-1][j]; //Đổi
//                    Tile above = tiles[i - 1][j]; //Đổi
//                    if (tile != null && above == null) {
//                        tile.debug();
//                        float x = position.x + j * Consts.SIZE;
//                        float y = position.y + (i - 1) * Consts.SIZE;
//                        tile.addAction(Actions.delay(1, moveTo(x, y)));
//                        tiles[i - 1][j] = tile;
//                        tiles[i][j] = null;
//                        check = true;
//                    }
//                }
//            }
        }
    }

    private void fallingTiles2() {
        boolean check = true;
        while (check) {
            check = false;
            for (int i = 1; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    Tile tile = tiles[i][j];
                    Tile above = tiles[i - 1][j];
                    if (tile != null && above == null) {
                        tile.debug();
                        float x = position.x + j * Consts.SIZE;
                        float y = position.y + (i - 1) * Consts.SIZE;
                        tile.addAction(Actions.delay(1, moveTo(x, y)));
                        tiles[i - 1][j] = tile;
                        tiles[i][j] = null;
                        check = true;
                    }
                }
            }
        }
    }

    private void fallingTiles1() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == null) {
                    for (int k = i; k >= 0; k--) {
                        Tile src = tiles[k][j];
                        if (src != null) {
                            float x = position.x + j * Consts.SIZE;
                            float y = position.y + k * Consts.SIZE;
                            src.addAction(Actions.delay(2, moveTo(x, y, .15f)));
                            tiles[i][j] = src;
                            tiles[k][j] = null;
                        }
                    }
                }
            }
        }
    }

    private void createNewTiles() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == null) {
                    Tile tile = new Tile(i, j);
                    tile.addListener(clickListener);
                    int num = MathUtils.random(1, 4);
                    float x = position.x + j * Consts.SIZE;
                    float y = position.y + i * Consts.SIZE;
                    tile.init(this.entities.get(num), num);
                    tile.setPosition(x, y);
                    tile.setSize(Consts.SIZE, Consts.SIZE);
                    tiles[i][j] = tile;
                    findMatches();
                    this.addActor(tile);
                }
            }
        }
    }

    @Override
    public void dispose() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j].clear();
            }
        }
    }
}
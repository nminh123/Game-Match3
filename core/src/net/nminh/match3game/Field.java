package net.nminh.match3game;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import net.nminh.match3game.utils.Tile;

public class Field extends Group implements Disposable {
    //todo: abandon

    Match3Game game;
    int size = 8;
    Array<TextureAtlas.AtlasRegion> entities;
    //    Array<Tile> activeTiles = new Array<>(64);
    Tile[][] tiles = new Tile[8][8];

    public Field(Match3Game game, Array<TextureAtlas.AtlasRegion> sprites) {
        this.game = game;
        this.entities = sprites;

//        defaults().width(80).height(80);
        setBounds(0, 0, 640, 640);
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile tile = new Tile(i, j);
                tile.addListener(clickListener);
                int num = MathUtils.random(0, 4);
                tile.init(this.entities.get(num), num);
                tile.setPosition(j * tile.getWidth(), i * tile.getHeight());
//                activeTiles.add(tile);
                tiles[i][j] = tile;
                this.addActor(tile);
//                if (i % size == 0)
//                    row();
//                add(tile);
            }
        }
        this.debugAll();
//        findMatches();
    }

    ClickListener clickListener = new ClickListener() {
        Tile firstClick;
        int count = 0;

        final Action afterSwap = new Action() {
            @Override
            public boolean act(float delta) {
//                findMatches();
                return true;
            }
        };

        final Action afterClick = new Action() {
            @Override
            public boolean act(float delta) {
                for (Tile[] tile : tiles) {
                    for (Tile t : tile) {
                        t.setTouchable(Touchable.enabled);
                    }
                }
//                for (Tile tile : activeTiles) {
//                    tile.setTouchable(Touchable.enabled);
//                }
                return true;
            }
        };

        @Override
        public void clicked(InputEvent event, float x, float y) {
            Tile target = (Tile) event.getTarget();

            if (firstClick != null) {
                target.clearActions();
                firstClick.clearActions();

                if (target.row == firstClick.row) {
                    if (target.col == firstClick.col + 1 || target.col == firstClick.col - 1) {

                        System.out.println("swap row target <> firstclick");
                        int row = target.row;
                        int col = target.col;
                        tiles[target.col][target.row] = firstClick;
                        tiles[firstClick.col][firstClick.row] = target;
                        target.setRowCol(firstClick.row, firstClick.col);
                        firstClick.setRowCol(row, col);
                        firstClick.addAction(Actions.moveTo(firstClick.row * firstClick.getWidth(), firstClick.col * firstClick.getHeight(), 2));
                        target.addAction(Actions.moveTo(target.row * target.getWidth(), target.col * target.getHeight(), 2));
                    }
                } else if (target.col == firstClick.col) {
                    if (target.row == firstClick.row + 1 || target.row == firstClick.row - 1) {
                        System.out.println("swap col target <> firstclick");
                        int row = target.row;
                        int col = target.col;
                        tiles[target.col][target.row] = firstClick;
                        tiles[firstClick.col][firstClick.row] = target;
                        target.setRowCol(firstClick.row, firstClick.col);
                        firstClick.setRowCol(row, col);
                        firstClick.addAction(Actions.moveTo(firstClick.row * firstClick.getWidth(), firstClick.col * firstClick.getHeight(), 2));
                        target.addAction(Actions.moveTo(target.row * target.getWidth(), target.col * target.getHeight(), 2));
                    }
                }

//                int tileIndex1 = activeTiles.indexOf(firstClick, true);
//                int tileIndex2 = activeTiles.indexOf(target, true);
//
////                activeTiles.swap(tileIndex1, tileIndex2);
//                if ((tileIndex1 == tileIndex2 - 1 || tileIndex1 == tileIndex2 + 1 ||
//                        tileIndex1 == tileIndex2 + size || tileIndex1 == tileIndex2 - size) && hasMatches()) {
//                    swapActor(tileIndex1, tileIndex2);
//                    target.addAction(moveTo(firstClick.getX(), firstClick.getY(), 0.2f));
//                    firstClick.addAction(sequence(moveTo(target.getX(), target.getY(), 0.2f), afterSwap));
//                } else {
//                    activeTiles.swap(tileIndex1, tileIndex2);
//                    target.addAction(sequence(moveTo(firstClick.getX(), firstClick.getY(), 0.1f),
//                            moveTo(target.getX(), target.getY(), 0.1f)));
//                    firstClick.addAction(sequence(moveTo(target.getX(), target.getY(), 0.1f),
//                            moveTo(firstClick.getX(), firstClick.getY(), 0.1f), afterSwap));
//                }
            }

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
//                for (Tile tile : activeTiles) {
//                    tile.setTouchable(Touchable.disabled);
//                }
                target.setOrigin(Align.center);
                target.addAction(sequence(parallel(moveTo(target.getX(), target.getY() + 10, 0.125f, Interpolation.swingOut),
                                Actions.scaleBy(0.2f, 0.2f, 0.125f)),
                        parallel(Actions.scaleBy(-0.2f, -0.2f, 0.125f),
                                moveTo(target.getX(), target.getY(), 0.125f, Interpolation.swingIn)), afterClick));
            }
        }
    };
//
//    private void swap(int index1, int index2) {
//        activeTiles.swap(index1, index2);
//        swapActor(index1, index2);
//    }
//
//    private void update() {
//        clearChildren();
//        int i = 0;
//        for (Tile tile : activeTiles) {
//            if (tile.type == -1) {
//                int num = MathUtils.random(0, 6);
//                tile.init(entities.get(num), num);
//                tile.addAction(sequence(fadeIn(0.25f)));
//            }
//            if (i % size == 0)
//                row();
//            add(tile);
//            i++;
//        }
//    }
//
//    private void moveDown() {
//        for (int i = size - 1; i >= 0; i--)
//            for (int j = 0; j < size; j++)
//                if (activeTiles.get(j + i * size).type == -1)
//                    for (int n = i; n >= 0; n--)
//                        if (activeTiles.get(j + n * size).type != -1) {
//                            swap(j + n * size, j + i * size);
//                            break;
//                        }
//
//    }
//
//    private void findMatches() {
//        //checking rows
//        int matches;
//        int colorToMatch;
//        boolean hasMatch = false;
//        for (int i = 0; i < size; i++) {
//            colorToMatch = activeTiles.get(i * size).type;
//            matches = 1;
//            for (int j = 1; j < size; j++) {
//                if (activeTiles.get(j + i * size).type == colorToMatch && colorToMatch != -1) {
//                    matches++;
//                } else {
//                    colorToMatch = activeTiles.get(j + i * size).type;
//                    if (matches >= 3) {
//                        hasMatch = true;
//                        for (int j2 = j - 1; j2 >= j - matches; j2--) {
//                            activeTiles.get(j2 + i * size).type = -1;
//                        }
//                    }
//                    matches = 1;
//                }
//            }
//            if (matches >= 3) {
//                hasMatch = true;
//                for (int j = size - 1; j >= size - matches; j--) {
//                    activeTiles.get(j + i * size).type = -1;
//                }
//            }
//        }
//        //checking columns
//        for (int j = 0; j < size; j++) {
//            colorToMatch = activeTiles.get(j).type;
//            matches = 1;
//            for (int i = 1; i < size; i++) {
//                if (activeTiles.get(j + i * size).type == colorToMatch && colorToMatch != -1) {
//                    matches++;
//                } else {
//                    colorToMatch = activeTiles.get(j + i * size).type;
//                    if (matches >= 3) {
//                        hasMatch = true;
//                        for (int i2 = i - 1; i2 >= i - matches; i2--) {
//                            activeTiles.get(j + i2 * size).type = -1;
//                        }
//                    }
//                    matches = 1;
//                }
//            }
//            if (matches >= 3) {
//                hasMatch = true;
//                for (int i = size - 1; i >= size - matches; i--) {
//                    activeTiles.get(j + i * size).type = -1;
//                }
//            }
//        }
//        if (hasMatch) {
//            int count = 1;
//            for (Tile tile : activeTiles.select((tile) -> tile.type == -1)) {
//                if (count % 3 == 0)
//                    tile.addAction(sequence(fadeOut(0.25f), afterMatch));
//                else
//                    tile.addAction(fadeOut(0.25f));
//                count++;
//            }
//        }
//    }
//
//    private final Action afterMatch = new Action() {
//        @Override
//        public boolean act(float delta) {
//            moveDown();
//            update();
//            findMatches();
//            return true;
//        }
//    };
//
//    private boolean hasMatches() {
//        int matches;
//        int colorToMatch;
//        boolean hasMatch = false;
//        for (int i = 0; i < size; i++) {
//            colorToMatch = activeTiles.get(i * size).type;
//            matches = 1;
//            for (int j = 1; j < size; j++) {
//                if (activeTiles.get(j + i * size).type == colorToMatch) {
//                    matches++;
//                } else {
//                    colorToMatch = activeTiles.get(j + i * size).type;
//                    if (matches >= 3) {
//                        hasMatch = true;
//                    }
//                    matches = 1;
//                }
//            }
//            if (matches >= 3) {
//                hasMatch = true;
//            }
//        }
//        for (int j = 0; j < size; j++) {
//            colorToMatch = activeTiles.get(j).type;
//            matches = 1;
//            for (int i = 1; i < size; i++) {
//                if (activeTiles.get(j + i * size).type == colorToMatch) {
//                    matches++;
//                } else {
//                    colorToMatch = activeTiles.get(j + i * size).type;
//                    if (matches >= 3) {
//                        hasMatch = true;
//                    }
//                    matches = 1;
//                }
//            }
//            if (matches >= 3) {
//                hasMatch = true;
//            }
//        }
//        return hasMatch;
//    }

    @Override
    public void dispose() {

    }
}
package net.nminh.match3game.actors.spine;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;

import net.nminh.match3game.utils.Consts;
import net.nminh.match3game.utils.Utils;

/**
 * Created by nminh123 on 9/10/24
 */
public class FarmGirl extends Actor implements Disposable
{
    TextureAtlas atlas;//
    Skeleton skeleton;
    AnimationStateData stateData;
    AnimationState state;
    SkeletonRenderer renderer;

    public FarmGirl() {
        init();
    }

    private void init()
    {
        atlas = new TextureAtlas(Utils.getInternalPath(Consts.FARMERGIRL_ATLAS));
        renderer = new SkeletonRenderer();
        renderer.setPremultipliedAlpha(false);
        SkeletonJson json = new SkeletonJson(atlas);
        SkeletonData skeletonData = json.readSkeletonData(Utils.getInternalPath(Consts.FARMERGIRL_SKEL));

        skeleton = new Skeleton(skeletonData);
        stateData = new AnimationStateData(skeletonData);
        state = new AnimationState(stateData);
        state.setAnimation(0, "animation", true);

        this.setSize(skeleton.getData().getWidth(), skeleton.getData().getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        state.update(delta);
        state.apply(skeleton);
        skeleton.setPosition(getX(), getY());
        skeleton.updateWorldTransform();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.begin();
        renderer.draw(batch, skeleton);
        batch.end();
    }

    @Override
    public void dispose() {
        atlas.dispose();
    }
}
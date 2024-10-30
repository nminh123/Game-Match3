package net.nminh.match3game.actors.spine;

import com.badlogic.gdx.Gdx;
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

import net.nminh.match3game.utils.Assets;
import net.nminh.match3game.utils.Consts;
import net.nminh.match3game.utils.Utils;

/**
 * Created by nminh123 on 9/10/24
 */
public class FarmGirl extends Actor implements Disposable
{
    private SkeletonRenderer renderer;
    private Skeleton skeleton;
    private AnimationState state;
    private SkeletonData skeletonData;
    private AnimationStateData stateData;

    public FarmGirl()
    {
        renderer = new SkeletonRenderer();
        TextureAtlas atlas = Assets.getSpineAtlas(Consts.FARMERGIRL_ATLAS);
        SkeletonJson json = new SkeletonJson(atlas);
        skeletonData = json.readSkeletonData(Utils.getInternalPath(Consts.FARMERGIRL_SKEL));
        stateData = new AnimationStateData(skeletonData);
        state = new AnimationState(stateData);
        state.setAnimation(0, "idle", true);
        skeleton = new Skeleton(skeletonData);
        skeleton.setPosition(0, 0);
        skeleton.updateWorldTransform();
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        state.update(Gdx.graphics.getDeltaTime());
        state.apply(skeleton);
        skeleton.updateWorldTransform();
        renderer.draw(batch, skeleton);
    }

    @Override
    public void dispose()
    {
        renderer = null;
        skeleton = null;
        state = null;
        skeletonData = null;
        stateData = null;
    }
}
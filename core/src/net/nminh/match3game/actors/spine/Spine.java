package net.nminh.match3game.actors.spine;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;

import net.nminh.match3game.utils.Utils;

/**
 * Created by nminh123 on 15/10/24
 */
public abstract class Spine extends Actor
{
    Skeleton skeleton;
    public AnimationState animationState;
    SkeletonRenderer renderer;
    TextureAtlas atlas;

    public Spine(){}

    public Spine(String atlasPath, String skelPath)
    {
        atlas = new TextureAtlas(Utils.getInternalPath(atlasPath));
        renderer = new SkeletonRenderer();
        renderer.setPremultipliedAlpha(false);
        SkeletonJson json = new SkeletonJson(atlas);
        SkeletonData skeletonData = json.readSkeletonData(Utils.getInternalPath(skelPath));

        skeleton = new Skeleton(skeletonData);
        AnimationStateData animationStateData = new AnimationStateData(skeletonData);
        animationState = new AnimationState(animationStateData);
        animationState.setAnimation(0,"animation", true);

        this.setSize(skeleton.getData().getWidth(), skeleton.getData().getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        animationState.update(delta);
        animationState.apply(skeleton);
        skeleton.setPosition(getX(), getY());
        skeleton.updateWorldTransform();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        drawSpine(batch);
    }

    private void drawSpine(Batch batch)
    {
        batch.begin();
        renderer.draw(batch, skeleton);
        batch.end();
    }

    public Skeleton getSkeleton()
    {
        return skeleton;
    }
}
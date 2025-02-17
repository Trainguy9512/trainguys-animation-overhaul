package com.trainguy9512.animationoverhaul.animation.animator;

import com.google.common.collect.Maps;
import com.trainguy9512.animationoverhaul.animation.animator.entity.EntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.animator.entity.LivingEntityJointAnimator;
import com.trainguy9512.animationoverhaul.animation.joint.JointSkeleton;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Optional;

public class JointAnimatorRegistry {

    private static final HashMap<EntityType<?>, EntityJointAnimator<?, ?>> THIRD_PERSON_ENTITY_JOINT_ANIMATORS = Maps.newHashMap();
    private static final HashMap<EntityType<?>, JointSkeleton> THIRD_PERSON_ENTITY_JOINT_SKELETONS = Maps.newHashMap();

    private static LivingEntityJointAnimator<LocalPlayer, PlayerRenderState> FIRST_PERSON_PLAYER_JOINT_ANIMATOR = null;
    private static JointSkeleton FIRST_PERSON_PLAYER_JOINT_SKELETON = null;

    /**
     * Registers a joint animator for use on third person living entities.
     * @param entityType                Type of entity associated with the living entity
     * @param entityJointAnimator       Newly constructed entity joint animator object
     */
    public static <T extends Entity> void registerEntityJointAnimator(EntityType<T> entityType, EntityJointAnimator<T, ?> entityJointAnimator){
        THIRD_PERSON_ENTITY_JOINT_ANIMATORS.put(entityType, entityJointAnimator);
        THIRD_PERSON_ENTITY_JOINT_SKELETONS.put(entityType, entityJointAnimator.buildSkeleton());
    }


    public static void registerFirstPersonPlayerJointAnimator(LivingEntityJointAnimator<LocalPlayer, PlayerRenderState> firstPersonPlayerJointAnimator){
        FIRST_PERSON_PLAYER_JOINT_ANIMATOR = firstPersonPlayerJointAnimator;
        FIRST_PERSON_PLAYER_JOINT_SKELETON = firstPersonPlayerJointAnimator.buildSkeleton();
    }

    /**
     * Returns whether the provided entity type has a registered joint animator.
     * @param entityType                Entity type
     */
    public static boolean entityTypeRegisteredWithJointAnimator(EntityType<?> entityType){
        return THIRD_PERSON_ENTITY_JOINT_ANIMATORS.containsKey(entityType);
    }

    /**
     * Returns the entity joint animator for the provided entity type. If the entity type does not have a joint animator registered, null is returned.
     * @param entityType                Entity type
     * @return                          Entity joint animator
     */
    @SuppressWarnings("unchecked")
    public static <T extends Entity> Optional<EntityJointAnimator<T, ?>> getThirdPersonJointAnimator(EntityType<T> entityType){
        return Optional.ofNullable((EntityJointAnimator<T, ?>) THIRD_PERSON_ENTITY_JOINT_ANIMATORS.get(entityType));
    }

    /**
     * Returns a joint skeleton for the provided entity type. If the entity type does not have a joint animator registered, null is returned.
     * @param entityType                Entity type
     * @return Joint skeleton
     */
    public static Optional<JointSkeleton> getThirdPersonJointSkeleton(EntityType<?> entityType){
        return Optional.ofNullable(THIRD_PERSON_ENTITY_JOINT_SKELETONS.get(entityType));
    }

    /**
     * Returns the first person player joint animator, if it has been registered. If not, it returns null.
     */
    public static Optional<LivingEntityJointAnimator<LocalPlayer, PlayerRenderState>> getFirstPersonPlayerJointAnimator(){
        return Optional.ofNullable(FIRST_PERSON_PLAYER_JOINT_ANIMATOR);
    }

    /**
     * Returns the first person player joint skeleton, if it has been registered. If not, it returns null.
     */
    public static Optional<JointSkeleton> getFirstPersonPlayerJointSkeleton(){
        return Optional.ofNullable(FIRST_PERSON_PLAYER_JOINT_SKELETON);
    }
}

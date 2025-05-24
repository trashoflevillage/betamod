package io.github.trashoflevillage.trashsbetamod.blocks.custom.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;

public class MonsterBoxBlockEntity extends BlockEntity {
    public int spawnDelay = -1;
    private String spawnedEntityId;
    public double rotation;
    public double lastRotation = 0.0;

    public MonsterBoxBlockEntity() {
        this.spawnedEntityId = "Pig";
        this.spawnDelay = 20;
    }

    @Environment(EnvType.CLIENT)
    public String getSpawnedEntityId() {
        return this.spawnedEntityId;
    }

    public void setSpawnedEntityId(String spawnedEntityId) {
        this.spawnedEntityId = spawnedEntityId;
    }

    public boolean isPlayerInRange() {
        return this.world.getClosestPlayer((double)this.x + 0.5, (double)this.y + 0.5, (double)this.z + 0.5, 24.0) != null;
    }

    @Override
    public void tick() {
        this.lastRotation = this.rotation;
        if (this.isPlayerInRange()) {
            double var1 = (double)((float)this.x + this.world.random.nextFloat());
            double var3 = (double)((float)this.y + this.world.random.nextFloat());
            double var5 = (double)((float)this.z + this.world.random.nextFloat());
            this.world.addParticle("smoke", var1, var3, var5, 0.0, 0.0, 0.0);
            this.world.addParticle("flame", var1, var3, var5, 0.0, 0.0, 0.0);

            for (this.rotation = this.rotation + (double)(1000.0F / ((float)this.spawnDelay + 200.0F)); this.rotation > 360.0; this.lastRotation -= 360.0) {
                this.rotation -= 360.0;
            }

            if (!this.world.isRemote) {
                if (this.spawnDelay == -1) {
                    this.resetDelay();
                }

                if (this.spawnDelay > 0) {
                    this.spawnDelay--;
                    return;
                }

                byte var7 = 8;

                for (int var8 = 0; var8 < var7; var8++) {
                    LivingEntity entity = (LivingEntity) EntityRegistry.create(this.spawnedEntityId, this.world);
                    if (entity == null) {
                        return;
                    }

                    int nearbyEntities = this.world
                            .collectEntitiesByClass(
                                    entity.getClass(),
                                    Box.createCached((double)this.x, (double)this.y, (double)this.z, (double)(this.x + 1), (double)(this.y + 1), (double)(this.z + 1)).expand(8.0, 4.0, 8.0)
                            )
                            .size();
                    if (nearbyEntities >= 6) {
                        this.resetDelay();
                        return;
                    }

                    if (entity != null) {
                        double var11 = (double)this.x + (this.world.random.nextDouble() - this.world.random.nextDouble()) * 4.0;
                        double var13 = (double)(this.y + this.world.random.nextInt(3) - 1);
                        double var15 = (double)this.z + (this.world.random.nextDouble() - this.world.random.nextDouble()) * 4.0;
                        int entitiesSpawned = 0;
                        for (int i = 0; i < 10; i++) {
                            entity.setPositionAndAnglesKeepPrevAngles(var11, var13, var15, this.world.random.nextFloat() * 360.0F, 0.0F);
                            if (entity.canSpawn()) {
                                this.world.spawnEntity(entity);
                                entity.isPersistent = true;
                                entitiesSpawned++;

                                for (int var17 = 0; var17 < 2; var17++) {
                                    var1 = (double) this.x + 0.5 + ((double) this.world.random.nextFloat() - 0.5) * 2.0;
                                    var3 = (double) this.y + 0.5 + ((double) this.world.random.nextFloat() - 0.5) * 2.0;
                                    var5 = (double) this.z + 0.5 + ((double) this.world.random.nextFloat() - 0.5) * 2.0;
                                    this.world.addParticle("smoke", var1, var3, var5, 0.0, 0.0, 0.0);
                                    this.world.addParticle("flame", var1, var3, var5, 0.0, 0.0, 0.0);
                                }

                                entity.animateSpawn();
                            }
                        }
                        if (entitiesSpawned > 0) world.setBlock(this.x, this.y, this.z, 0);
                    }
                }
            }

            super.tick();
        }
    }

    private void resetDelay() {
        this.spawnDelay = 200 + this.world.random.nextInt(600);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.spawnedEntityId = nbt.getString("EntityId");
        this.spawnDelay = nbt.getShort("Delay");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString("EntityId", this.spawnedEntityId);
        nbt.putShort("Delay", (short)this.spawnDelay);
    }
}

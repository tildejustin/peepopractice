package me.falu.peepopractice.core.category.properties;

import me.falu.peepopractice.core.exception.NotInitializedException;
import me.falu.peepopractice.core.category.PracticeCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Random;

@SuppressWarnings("UnusedDeclaration")
public class StructureProperties extends BaseProperties {
    private ConfiguredStructureFeature<?, ?> structure;
    private ChunkPos chunkPos;
    private PracticeCategory.ExecuteReturnTask<ChunkPos> chunkPosTask;
    private Direction orientation;
    private BlockRotation rotation;
    private Integer structureTopY;
    private PracticeCategory.ExecuteReturnTask<Integer> structureTopYTask;
    private boolean generatable = false;
    private PracticeCategory.ExecuteReturnTask<Boolean> generatableTask;
    private boolean generated = false;

    public ConfiguredStructureFeature<?, ?> getStructure() {
        return this.structure;
    }

    public boolean isSameStructure(ConfiguredStructureFeature<?, ?> feature) {
        return this.hasStructure() && feature.field_24835.getName().equals(this.structure.field_24835.getName());
    }

    public boolean isSameStructure(StructureFeature<?> feature) {
        return this.hasStructure() && feature.getName().equals(this.structure.field_24835.getName());
    }

    public boolean hasStructure() {
        return this.structure != null;
    }

    public StructureProperties setStructure(ConfiguredStructureFeature<?, ?> structure) {
        this.structure = structure;
        return this;
    }

    public ChunkPos getChunkPos() {
        return this.chunkPos;
    }

    public boolean hasChunkPos() {
        return this.chunkPos != null;
    }

    public StructureProperties setChunkPos(ChunkPos structureChunkPos) {
        this.chunkPos = structureChunkPos;
        return this;
    }

    public StructureProperties setChunkPos(PracticeCategory.ExecuteReturnTask<ChunkPos> task) {
        this.chunkPosTask = task;
        return this;
    }

    public Direction getOrientation() {
        return this.orientation;
    }

    public boolean hasOrientation() {
        return this.orientation != null;
    }

    public StructureProperties setOrientation(Direction orientation) {
        this.orientation = orientation;
        return this;
    }

    public BlockRotation getRotation() {
        return this.rotation;
    }

    public boolean hasRotation() {
        return this.rotation != null;
    }

    public StructureProperties setRotation(BlockRotation rotation) {
        this.rotation = rotation;
        return this;
    }

    public Integer getStructureTopY() {
        return this.structureTopY;
    }

    public boolean hasStructureTopY() {
        return this.structureTopY != null;
    }

    public StructureProperties setStructureTopY(int structureTopY) {
        this.structureTopY = structureTopY;
        return this;
    }

    public StructureProperties setStructureTopY(PracticeCategory.ExecuteReturnTask<Integer> task) {
        this.structureTopYTask = task;
        return this;
    }

    public boolean isGeneratable() {
        return this.generatable;
    }

    public StructureProperties setGeneratable(boolean generatable) {
        this.generatable = generatable;
        return this;
    }

    public StructureProperties setGeneratable(PracticeCategory.ExecuteReturnTask<Boolean> task) {
        this.generatableTask = task;
        return this;
    }

    public boolean hasGenerated() {
        return this.generated;
    }

    public void setGenerated() {
        this.generated = true;
    }

    public void reset(Random random, ServerWorld world) throws NotInitializedException {
        this.generated = false;
        if (this.chunkPosTask != null) {
            this.setChunkPos(this.chunkPosTask.execute(this.getCategory(), random, world));
        }
        if (this.structureTopYTask != null) {
            Integer value = this.structureTopYTask.execute(this.getCategory(), random, world);
            if (value != null) {
                this.setStructureTopY(value);
            }
        }
        if (this.generatableTask != null) {
            Boolean value = this.generatableTask.execute(this.getCategory(), random, world);
            if (value != null) {
                this.setGeneratable(value);
            }
        }
    }
}

package honeyandhell.init;

import com.google.common.collect.Lists;
import honeyandhell.common.world.gen.feature.BeeNestFeature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ModGeneration
{
    public static final Feature<NoFeatureConfig> BEE_NEST = new BeeNestFeature(NoFeatureConfig::deserialize);

    public static List<String> beeNestBiomes = Lists.newArrayList("minecraft:plains", "minecraft:sunflower_plains", "minecraft:forest", "minecraft:wooded_hills",
            "minecraft:flower_forest", "minecraft:savanna", "minecraft:savanna_plateau", "minecraft:shattered_savanna", "minecraft:shattered_savanna_plateau", "minecraft:jungle",
            "minecraft:jungle_hills", "minecraft:jungle_edge", "minecraft:modified_jungle", "minecraft:modified_jungle_hills", "minecraft:bamboo_jungle", "minecraft:bamboo_jungle_hills");

    public static void setup()
    {
        for (String biomeName : beeNestBiomes)
        {
            ResourceLocation loc = new ResourceLocation(biomeName);

            if (ForgeRegistries.BIOMES.containsKey(loc))
            {
                Biome biome = ForgeRegistries.BIOMES.getValue(loc);
                addFeature(biome, GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(BEE_NEST, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(8)));
            }
        }
    }

    public static void addFeature(Biome biome, GenerationStage.Decoration decorationStage, ConfiguredFeature<?> featureIn)
    {
        biome.addFeature(decorationStage, featureIn);
    }
}

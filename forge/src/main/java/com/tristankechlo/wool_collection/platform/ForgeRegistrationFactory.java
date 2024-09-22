package com.tristankechlo.wool_collection.platform;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@AutoService(RegistrationProvider.Factory.class)
public class ForgeRegistrationFactory implements RegistrationProvider.Factory {

    // very bad way to do this
    private static final Map<ResourceLocation, IForgeRegistry<?>> REGISTRIES = ImmutableMap.of(
            key("block"), ForgeRegistries.BLOCKS,
            key("item"), ForgeRegistries.ITEMS,
            key("menu"), ForgeRegistries.CONTAINERS,
            key("recipe_serializer"), ForgeRegistries.RECIPE_SERIALIZERS
    );

    private static ResourceLocation key(String key) {
        return new ResourceLocation(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> RegistrationProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId) {
        final var containerOpt = ModList.get().getModContainerById(modId);
        if (containerOpt.isEmpty())
            throw new NullPointerException("Cannot find mod container for id " + modId);
        final var cont = containerOpt.get();
        if (cont instanceof FMLModContainer fmlModContainer) {
            IForgeRegistry<?> reg = REGISTRIES.get(resourceKey.location());
            final var register = DeferredRegister.create(reg, modId);
            register.register(fmlModContainer.getEventBus());
            return (RegistrationProvider<T>) new Provider<>(modId, register);
        } else {
            throw new ClassCastException("The container of the mod " + modId + " is not a FML one!");
        }
    }

    private static class Provider<T extends IForgeRegistryEntry<T>> implements RegistrationProvider<T> {
        private final String modId;
        private final DeferredRegister<T> registry;

        private final Set<RegistryObject<T>> entries = new HashSet<>();
        private final Set<RegistryObject<T>> entriesView = Collections.unmodifiableSet(entries);

        private Provider(String modId, DeferredRegister<T> registry) {
            this.modId = modId;
            this.registry = registry;
        }

        @Override
        public String getModId() {
            return modId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <I extends T> RegistryObject<I> register(String name, Supplier<? extends I> supplier) {
            final var obj = registry.<I>register(name, supplier);
            final var ro = new RegistryObject<I>() {

                @Override
                public ResourceLocation getId() {
                    return obj.getId();
                }

                @Override
                public I get() {
                    return obj.get();
                }

            };
            entries.add((RegistryObject<T>) ro);
            return ro;
        }

        @Override
        public Set<RegistryObject<T>> getEntries() {
            return entriesView;
        }
    }
}
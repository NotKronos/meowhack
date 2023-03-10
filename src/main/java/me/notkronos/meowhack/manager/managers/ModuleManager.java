package me.notkronos.meowhack.manager.managers;

import me.notkronos.meowhack.manager.Manager;
import me.notkronos.meowhack.module.Module;
import me.notkronos.meowhack.module.client.ClickGUIModule;
import me.notkronos.meowhack.module.client.Colors;
import me.notkronos.meowhack.module.client.CustomFont;
import me.notkronos.meowhack.module.client.HUD;
import me.notkronos.meowhack.module.combat.*;
import me.notkronos.meowhack.module.misc.*;
import me.notkronos.meowhack.module.movement.Sprint;
import me.notkronos.meowhack.module.render.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ModuleManager extends Manager {

    private final List<Module> modules;

    public ModuleManager() {
        super("ModuleManager");
        modules = Arrays.asList(
            new Announcer(),
            new Ambience(),
            new AutoMeow(),
            new ChatSuffix(),
            new ChatTimestamp(),
            new ChestplateSwap(),
            new ClickGUIModule(),
            new Colors(),
            new CrystalChams(),
            new CustomFont(),
            new HoleESP(),
            new HUD(),
            new NoBob(),
            new NoEnchantingTableLag(),
            new PopLag(),
            new RPC(),
            new Shader(),
            new Sprint(),
            new StrengthDetect(),
            new SwingSpeed(),
            new TotemPopCounter(),
            new VisualRange(),
            new Weather()
        );
    }

    @Override
    public void onThread() {
        for (Module module : modules) {
            if (module.getBind().getValue().isPressed()) {
                module.toggle();
            }
        }
    }

    public List<Module> getAllModules() {
        return modules;
    }

    public Module getModule(Predicate<? super Module> predicate) {
        return modules.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }
}

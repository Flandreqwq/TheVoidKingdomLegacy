package flandre.scarlet.thevoidkingdom.utils.framework;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import com.molean.isletopia.framework.annotations.AutoInject;
import com.molean.isletopia.framework.annotations.BeanHandler;
import com.molean.isletopia.framework.annotations.IBeanHandler;

@BeanHandler
public class PaperCommandHandler implements IBeanHandler {


    @AutoInject
    private PaperCommandManager paperCommandManager;

    @Override
    public void handle(Object o) {
        if (o instanceof PaperCommand) {
            if (o instanceof BaseCommand baseCommand) {
                paperCommandManager.registerCommand(baseCommand);
            }
        }
    }
}
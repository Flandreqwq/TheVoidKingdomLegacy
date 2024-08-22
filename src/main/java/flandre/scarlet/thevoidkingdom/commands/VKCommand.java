package flandre.scarlet.thevoidkingdom.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.framework.PaperCommand;

@Bean
@CommandAlias("vk|flandre")
@CommandPermission("vkcommand.admin")
@Description("[虚空之国]插件命令")
public class VKCommand extends BaseCommand implements PaperCommand {

}

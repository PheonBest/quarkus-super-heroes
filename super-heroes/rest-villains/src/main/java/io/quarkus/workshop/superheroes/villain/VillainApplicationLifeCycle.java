package io.quarkus.workshop.superheroes.villain;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import io.smallrye.config.SmallRyeConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import java.util.List;
import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.logging.Logger;

@ApplicationScoped
public class VillainApplicationLifeCycle {

  private static final Logger LOGGER = Logger.getLogger(VillainApplicationLifeCycle.class);

  public static List<String> getProfiles() {
    return ConfigProvider.getConfig().unwrap(SmallRyeConfig.class).getProfiles();
  }

  void onStart(@Observes StartupEvent ev) {
    LOGGER.info("The application VILLAIN is starting with profile " + getProfiles().toString());
    LOGGER.info(" __     ___ _ _       _             _    ____ ___ ");
    LOGGER.info(" \\ \\   / (_) | | __ _(_)_ __       / \\  |  _ \\_ _|");
    LOGGER.info("  \\ \\ / /| | | |/ _` | | '_ \\     / _ \\ | |_) | | ");
    LOGGER.info("   \\ V / | | | | (_| | | | | |   / ___ \\|  __/| | ");
    LOGGER.info("    \\_/  |_|_|_|\\__,_|_|_| |_|  /_/   \\_\\_|  |___|");
  }

  void onStop(@Observes ShutdownEvent ev) {
    LOGGER.info("The application VILLAIN is stopping...");
  }
}

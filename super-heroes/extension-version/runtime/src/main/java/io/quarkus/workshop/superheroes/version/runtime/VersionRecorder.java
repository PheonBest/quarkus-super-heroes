package io.quarkus.workshop.superheroes.version.runtime;

import io.quarkus.runtime.annotations.Recorder;
import org.jboss.logging.Logger;
// this class is a recorder used to record actions executed later at runtime. Indeed, these actions
// are replayed at runtime.
@Recorder
public class VersionRecorder {

  public void printVersion(String version) {
    Logger.getLogger(VersionRecorder.class.getName()).infof("Version: %s", version);
  }
}

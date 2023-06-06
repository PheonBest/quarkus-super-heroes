package io.quarkus.workshop.superheroes.statistics;

import io.quarkus.runtime.annotations.RegisterForReflection;

// The @RegisterForReflection annotation instructs the native compilation to allow reflection access
// to the class. Without, the serialization/deserialization would not work when running the native
// executable.
@RegisterForReflection
public class Score {
  public String name;
  public int score;

  public Score() {
    this.score = 0;
  }
}

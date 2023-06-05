package io.quarkus.workshop.superheroes.hero;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Random;

@Entity
public class Hero extends PanacheEntityBase {

  @Id
  @GeneratedValue(generator = "hibernate_sequence", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(
      name = "hibernate_sequence",
      sequenceName = "hibernate_sequence",
      allocationSize = 1)
  public Long id;

  @NotNull
  @Size(min = 3, max = 50)
  public String name;

  public String otherName;

  @NotNull
  @Min(1)
  public Integer level;

  public String picture;

  @Column(columnDefinition = "TEXT")
  public String powers;

  public static Uni<Hero> findRandom() {
    Random random = new Random();
    return count()
        .map(count -> random.nextInt(count.intValue()))
        .chain(randomHero -> findAll().page(randomHero, 1).firstResult());
  }

  @Override
  public String toString() {
    return "Hero{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", otherName='"
        + otherName
        + '\''
        + ", level="
        + level
        + ", picture='"
        + picture
        + '\''
        + ", powers='"
        + powers
        + '\''
        + '}';
  }
}

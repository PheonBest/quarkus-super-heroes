package io.quarkus.workshop.superheroes.villain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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
public class Villain extends PanacheEntityBase {

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

  @Override
  public String toString() {
    return "Villain{"
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

  public static Villain findRandom() {
    long countVillains = count();
    Random random = new Random();
    int randomVillain = random.nextInt((int) countVillains);
    return findAll().page(randomVillain, 1).firstResult();
  }
}

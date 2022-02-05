package dev.georgetech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DANCE")
@Entity
public class Dance {

  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "LONG_GENERATOR")
  @SequenceGenerator(
      name = "LONG_GENERATOR",
      sequenceName = "dance_sequence",
      allocationSize = 1)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Version
  int version;

  public Dance(String name) {
    this.name = name;
  }
}

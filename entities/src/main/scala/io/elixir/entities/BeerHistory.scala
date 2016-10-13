package io.elixir.entities

import java.util.Date
import javax.persistence.{Column, _}

/**
  * Created by sbelkin on 6/15/2016.
  */
@Entity
@Table(name="beer_history")
class BeerHistory(val idc: Long, val beerc: Beer, val userc: User, val creationTimec: Date) {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = idc

  @Column(name="beer_id")
  @ManyToOne
  val beer: Beer = beerc

  @Column(name="user_id")
  @ManyToOne
  val user: User = userc

  @Column(name="creation_time")
  @ManyToOne
  val creationTime: Date = creationTimec

}

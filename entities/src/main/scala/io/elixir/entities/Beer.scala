package io.elixir.entities

import javax.persistence._

/**
  * Created by sbelkin on 6/15/2016.
  */
@Entity
@Table(name="beer")
class Beer(val idc: Long, val namec: String, val extrac: String) {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = idc

  @Column(name="name")
  val name: String = namec

  @Column(name="extra")
  val extra: String = extrac
}

package io.elixir.entities

import javax.persistence._

@Entity
@Table(name="user")
class User(val idc: Long, val namec: String, val emailc: String) {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = idc

  @Column(name="name")
  val name: String = namec

  @Column(name="email")
  val email: String = emailc


}

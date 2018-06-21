package com.example.app

import org.scalatra._
import scalikejdbc._
import org.joda.time._
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

class MyScalatraServlet extends ScalatraServlet {

  get("/") {
    views.html.hello()
  }

  get("/sample") {
    "おすしだよ"
  }

  // おすしを見る
  // {"name":"user","sushi":[{"name":"toro","num":1},{"name":"tamago","num":1}]}
  get("/:user_id/osushi/all") {
    case class Sushi(name: String, num: Integer)
    case class User(name: String, sushi: List[Sushi])

    val sushi = List(Sushi("toro", 1),Sushi("tamago", 1),Sushi("ikura", 2)) //ほんとはDBから取ってくる

    val user = User(params("user_id"), sushi)
    val json = user.asJson.noSpaces
    json
  }

   // おすしを贈る
   // {"name":"user","sushi":{"name":"tamago","num":1}}
  post("/:user_id/:neta") {
    case class Sushi(name: String, num: Integer)
    case class User(name: String, sushi: Sushi)

    val sushi = Sushi(params("neta"), 1)
    val user = User(params("user_id"), sushi)

    // dbに永続化

    val json = user.asJson.noSpaces
    json

  }

  // debug 送ったおすしを見る
  get("/debug/:user_id/osushi/:neta") {
    case class Sushi(name: String, num: Integer)
    case class User(name: String, sushi: Sushi)

    val sushi = Sushi(params("neta"), 1)
    val user = User(params("user_id"), sushi)
    val json = user.asJson.noSpaces
    json

  }

  get("/db") {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:mem:db", "username", "password")

    // テーブル作成
    // DB autoCommit { implicit session =>
    //   SQL("""
    //     create table members (
    //       id bigint primary key auto_increment,
    //       name varchar(30) not null,
    //       description varchar(1000),
    //       birthday date,
    //       created_at timestamp not null
    //     )
    //   """).execute.apply()
    // }

    // insert
    // DB localTx { implicit session =>
    //   val insertSql = SQL("insert into members (name, birthday, created_at) values (?, ?, ?)")
    //   val createdAt = DateTime.now
    //
    //   insertSql.bind("Alice", Option(new LocalDate("1980-01-01")), createdAt).update.apply()
    //   insertSql.bind("Bob", None, createdAt).update.apply()
    // }

    // select
    val names: List[String] = DB readOnly { implicit session =>
      sql"select name from members".map(rs => rs.string("name")).list.apply()
    }

    names
  }


}

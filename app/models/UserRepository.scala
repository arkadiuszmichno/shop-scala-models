package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class UserTable(tag: Tag) extends Table[User](tag, "user") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def firstName = column[String]("firstName")

    def lastName = column[String]("lastName")

    def login = column[String]("login")

    def gender = column[String]("gender")

    def * = (id, firstName, lastName, login, gender) <> ((User.apply _).tupled, User.unapply)
  }

  val user = TableQuery[UserTable]

  def list(): Future[Seq[User]] = db.run {
    user.result
  }

  def create(firstName: String, lastName: String, login: String, gender: String): Future[User] = db.run {
    (user.map(c => (c.firstName, c.lastName, c.login, c.gender))
      returning user.map(_.id)
      into { case ((firstName, lastName, login, gender), id) => User(id, firstName, lastName, login, gender) }
      ) += (firstName, lastName, login, gender)
  }

  def getById(id: Long): Future[User] = db.run {
    user.filter(_.id === id).result.head
  }

  def delete(id: Long): Future[Unit] = db.run(user.filter(_.id === id).delete).map(_ => ())

  def update(id: Long, new_user: User): Future[Unit] = {
    val userToUpdate: User = new_user.copy(id)
    db.run(user.filter(_.id === id).update(userToUpdate)).map(_ => ())
  }
}
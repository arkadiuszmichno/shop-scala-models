package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CareerRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class CareerTable(tag: Tag) extends Table[Career](tag, "career") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def position = column[String]("position")

    def description = column[String]("description")

    def * = (id, position, description) <> ((Career.apply _).tupled, Career.unapply)
  }

  val career = TableQuery[CareerTable]

  def list(): Future[Seq[Career]] = db.run {
    career.result
  }

  def create(position: String, description: String): Future[Career] = db.run {
    (career.map(c => (c.position, c.description))
      returning career.map(_.id)
      into { case ((position, description), id) => Career(id, position, description) }
      ) += (position, description)
  }

  def getById(id: Long): Future[Career] = db.run {
    career.filter(_.id === id).result.head
  }

  def delete(id: Long): Future[Unit] = db.run(career.filter(_.id === id).delete).map(_ => ())

  def update(id: Long, new_career: Career): Future[Unit] = {
    val careerToUpdate: Career = new_career.copy(id)
    db.run(career.filter(_.id === id).update(careerToUpdate)).map(_ => ())
  }
}

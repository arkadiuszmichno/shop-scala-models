package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class OrderRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, userRepository: UserRepository)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
  import userRepository.UserTable

  class OrderTable(tag: Tag) extends Table[Order](tag, "order") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def user = column[Long]("user")

    def user_fk = foreignKey("user_fk", user, usr)(_.id)

    def price = column[Int]("price")

    def * = (id, user, price) <> ((Order.apply _).tupled, Order.unapply)
  }

  val order = TableQuery[OrderTable]
  private val usr = TableQuery[UserTable]


  def create(user: Long, price: Int): Future[Order] = db.run {
    (order.map(b => (b.user, b.price))
      returning order.map(_.id)
      into { case ((user, price), id) => Order(id, user, price) }
      ) += (user, price)
  }

  def list(): Future[Seq[Order]] = db.run {
    order.result
  }

  def getById(id: Long): Future[Order] = db.run {
    order.filter(_.id === id).result.head
  }

  def getByUser(user_id: Long): Future[Seq[Order]] = db.run {
    order.filter(_.user === user_id).result
  }

  def delete(id: Long): Future[Unit] = db.run(order.filter(_.id === id).delete).map(_ => ())

  def update(id: Long, new_bookReview: Order): Future[Unit] = {
    val bookReviewToUpdate: Order = new_bookReview.copy(id)
    db.run(order.filter(_.id === id).update(bookReviewToUpdate)).map(_ => ())
  }
}

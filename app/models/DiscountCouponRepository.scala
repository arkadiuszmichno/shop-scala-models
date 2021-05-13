package models


import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class DiscountCouponRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class DiscountCouponTable(tag: Tag) extends Table[DiscountCoupon](tag, "discountCoupon") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def value = column[Int]("value")

    def * = (id, name, value) <> ((DiscountCoupon.apply _).tupled, DiscountCoupon.unapply)
  }

  val discountCoupon = TableQuery[DiscountCouponTable]

  def create(name: String,value: Int): Future[DiscountCoupon] = db.run {
    (discountCoupon.map(b => (b.name, b.value))
      returning discountCoupon.map(_.id)
      into { case ((name, value), id) => DiscountCoupon(id, name, value) }
      ) += (name, value)
  }

  def list(): Future[Seq[DiscountCoupon]] = db.run {
    discountCoupon.result
  }

  def getById(id: Long): Future[DiscountCoupon] = db.run {
    discountCoupon.filter(_.id === id).result.head
  }

  def delete(id: Long): Future[Unit] = db.run(discountCoupon.filter(_.id === id).delete).map(_ => ())
}
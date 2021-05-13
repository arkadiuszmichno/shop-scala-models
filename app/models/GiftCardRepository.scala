package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GiftCardRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, categoryRepository: CategoryRepository)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import categoryRepository.CategoryTable
  import dbConfig._
  import profile.api._

  class GiftCardTable(tag: Tag) extends Table[GiftCard](tag, "giftCard") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def value = column[Int]("value")

    def category = column[Int]("category")

    def category_fk = foreignKey("cat_fk", category, cat)(_.id)

    def * = (id, name, value, category) <> ((GiftCard.apply _).tupled, GiftCard.unapply)
  }

  val book = TableQuery[GiftCardTable]
  private val cat = TableQuery[CategoryTable]


  def create(name: String, value: Int, category: Int): Future[GiftCard] = db.run {
    (book.map(b => (b.name, b.value, b.category))
      returning book.map(_.id)
      into { case ((name, value, category), id) => GiftCard(id, name, value, category) }
      ) += (name, value, category)
  }

  def list(): Future[Seq[GiftCard]] = db.run {
    book.result
  }

  def getByCategory(category_id: Int): Future[Seq[GiftCard]] = db.run {
    book.filter(_.category === category_id).result
  }

  def getById(id: Long): Future[GiftCard] = db.run {
    book.filter(_.id === id).result.head
  }

  def delete(id: Long): Future[Unit] = db.run(book.filter(_.id === id).delete).map(_ => ())

}
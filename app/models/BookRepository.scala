package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class BookRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, categoryRepository: CategoryRepository)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import categoryRepository.CategoryTable
  import dbConfig._
  import profile.api._

  class BookTable(tag: Tag) extends Table[Book](tag, "book") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def description = column[String]("description")

    def category = column[Int]("category")

    def category_fk = foreignKey("cat_fk", category, cat)(_.id)

    def * = (id, name, description, category) <> ((Book.apply _).tupled, Book.unapply)
  }

  val book = TableQuery[BookTable]
  private val cat = TableQuery[CategoryTable]


  def create(name: String, description: String, category: Int): Future[Book] = db.run {
    (book.map(b => (b.name, b.description, b.category))
      returning book.map(_.id)
      into { case ((name, description, category), id) => Book(id, name, description, category) }
      ) += (name, description, category)
  }

  def list(): Future[Seq[Book]] = db.run {
    book.result
  }

  def getByCategory(category_id: Int): Future[Seq[Book]] = db.run {
    book.filter(_.category === category_id).result
  }

  def getById(id: Long): Future[Book] = db.run {
    book.filter(_.id === id).result.head
  }

  def getByIdOption(id: Long): Future[Option[Book]] = db.run {
    book.filter(_.id === id).result.headOption
  }

  def getByCategories(category_ids: List[Int]): Future[Seq[Book]] = db.run {
    book.filter(_.category inSet category_ids).result
  }

  def delete(id: Long): Future[Unit] = db.run(book.filter(_.id === id).delete).map(_ => ())

  def update(id: Long, new_book: Book): Future[Unit] = {
    val productToUpdate: Book = new_book.copy(id)
    db.run(book.filter(_.id === id).update(productToUpdate)).map(_ => ())
  }
}

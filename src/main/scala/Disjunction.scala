sealed trait Disjunction[A, B] {
  def fold[X](l: A => X)(r: B => X): X =
    this match {
      case DisA(a) => l(a)
      case DisB(b) => r(b)
    }

  def flatMap[C](f: B => Disjunction[A, C]): Disjunction[A, C] =
    fold[Disjunction[A, C]](DisA(_))(f)

  def map[C](f: B => C): Disjunction[A, C] =
    flatMap { f andThen DisB.apply }
}

final case class DisA[A, B](a: A) extends Disjunction[A, B]
final case class DisB[A, B](b: B) extends Disjunction[A, B]

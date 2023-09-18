//import zio.{Chunk, Config}
//import zio.Config.Primitive
//
//import scala.concurrent.duration.Duration
//
//trait Config[+A]
//
//object Config {
//
//  sealed trait Primitive[+A] extends Config[A] {
//    self =>
//    final def description: String =
//      (self: Primitive[_]) match {
//        case FiniteDuration => "a boolean property"
//      }
//
//    final def missingError(name: String): Config.Error =
//      Config.Error.MissingData(Chunk.empty, s"Expected ${description} with name ${name}")
//
//    def parse(text: String): Either[Config.Error, A]
//  }
//
//  case object FiniteDuration extends Primitive[scala.concurrent.duration.FiniteDuration] {
//    final def parse(text: String): Either[Config.Error, zio.Duration] =
//        zio.Config.Duration.parse(text).flatMap{
//          case zio.Duration.Infinity => Left(Config.Error.InvalidData(Chunk.empty, s"Expected a finite duration value, but found ${text}"))
//          case zio.Duration.Zero => Right(scala.concurrent.duration.Duration.Zero)
//          case duration => Right(scala.concurrent.duration.FiniteDuration(duration.toNanos, TimeUnit.NANOSECONDS))
//        }
//  }
//
//}

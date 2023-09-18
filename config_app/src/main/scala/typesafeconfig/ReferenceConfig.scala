package typesafeconfig

import zio.Config.*
import zio.Runtime.default
import zio.config.*
import zio.config.typesafe.*
import zio.{Config, Unsafe, ZIO}

case class ConnectionPool(initialSize: Int, validationQuery: String)

object ConnectionPool:
  val config: Config[ConnectionPool] =
    Config.int("initial-size").zip(
        Config.string("validation-query")
      )
      .nested("default-connection-pool")
      .nested("persistence")
      .to[ConnectionPool]

end ConnectionPool

object ReferenceConfig extends App {


  val io: ZIO[Any, Error, ConnectionPool] =
    ZIO.config(ConnectionPool.config)

  private val app =
    for {
      config <- ZIO.config(ConnectionPool.config)
        .withConfigProvider(
          TypesafeConfigProvider.fromResourcePath(false)
        )


    } yield config

  private def run =
    app

  println(
    Unsafe.unsafe(implicit u => default.unsafe.run(run).getOrThrowFiberFailure())

  )
}


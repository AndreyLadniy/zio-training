package typesafeconfig

import zio.Config.*
import zio.Runtime.default
import zio.config.*
import zio.config.typesafe.*
import zio.{Config, Unsafe, ZIO}

case class ConnectionPool(initialSize: Int, validationQuery: String)

object ReferenceConfig extends App {

  val connectionPoolConfig: Config[ConnectionPool] =
    (Config.int("initial-size").zip(Config.string("validation-query")))
      .nested("default-connection-pool")
      .nested("persistence")
      .to[ConnectionPool]

  val io: ZIO[Any, Error, ConnectionPool] =
    ZIO.config(connectionPoolConfig)

  val app =
    for {
      config <- ZIO.config(connectionPoolConfig)
        .withConfigProvider(
          TypesafeConfigProvider.fromResourcePath(false)
        )


    } yield config

  def run =
    app

  println(
    Unsafe.unsafe(implicit u => default.unsafe.run(run).getOrThrowFiberFailure())

  )
}


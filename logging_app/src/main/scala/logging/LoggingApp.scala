package logging

import typesafeconfig.{ConnectionPool, ReferenceConfig}
import zio.config.typesafe.TypesafeConfigProvider
import zio.logging.backend.SLF4J
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault, ZLayer}

object LoggingApp extends ZIOAppDefault {

  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] = SLF4J.slf4j

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    for {
      config <- ZIO.config(ConnectionPool.config)
        .withConfigProvider(
          TypesafeConfigProvider.fromResourcePath(false)
        )
      _ <- ZIO.logInfo(config.initialSize.toString)
    } yield ()

}

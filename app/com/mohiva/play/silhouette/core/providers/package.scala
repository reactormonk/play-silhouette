/**
 * Copyright 2014 Mohiva Organisation (license at mohiva dot com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mohiva.play.silhouette.core

import scala.util.{ Failure, Success, Try }
import scala.concurrent.Future

/**
 * Contains [[com.mohiva.play.silhouette.core.Provider]] implementations that provide authentication
 * for different schemes and services.
 */
package object providers {

  /**
   * Provides an `asFuture` method on a [[scala.util.Try]] which maps a [[scala.util.Try]] to
   * a [[scala.concurrent.Future]].
   *
   * @see https://groups.google.com/forum/#!topic/scala-user/Mu4_lZAWxz0/discussion
   * @see http://stackoverflow.com/questions/17907772/scala-chaining-futures-try-blocks
   */
  implicit class TryAsFuture[T](val attempt: Try[T]) extends AnyVal {
    def asFuture: Future[T] = attempt match {
      case Success(v) => Future.successful(v)
      case Failure(f) => Future.failed(f)
    }
  }
}

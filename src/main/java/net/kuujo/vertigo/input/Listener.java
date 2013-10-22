/*
* Copyright 2013 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package net.kuujo.vertigo.input;

import net.kuujo.vertigo.messaging.JsonMessage;

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;

/**
 * A component listener.
 *
 * @author Jordan Halterman
 */
public interface Listener {

  /**
   * Sets a message handler on the listener.
   *
   * @param handler
   *   The message handler to set.
   * @return
   *   The called listener instance.
   */
  public Listener messageHandler(Handler<JsonMessage> handler);

  /**
   * Starts the listener.
   *
   * @return
   *   The called listener instance.
   */
  public Listener start();

  /**
   * Starts the listener.
   *
   * @param doneHandler
   *   An asynchronous handler to be invoked once the listener is started.
   * @return
   *   The called listener instance.
   */
  public Listener start(Handler<AsyncResult<Void>> doneHandler);

  /**
   * Stops the listener.
   */
  public void stop();

  /**
   * Stops the listener.
   *
   * @param doneHandler
   *   An asynchronous handler to be invoked once the listener is stopped.
   */
  public void stop(Handler<AsyncResult<Void>> doneHandler);

}

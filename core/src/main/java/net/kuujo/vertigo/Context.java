/*
 * Copyright 2014 the original author or authors.
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
package net.kuujo.vertigo;

import net.kuujo.vertigo.util.Json;

/**
 * Immutable configuration information for Vertigo types.
 * 
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
public interface Context<T extends Context<T>> extends Json.Serializable {

  /**
   * Returns the type ID.
   *
   * @return The type ID.
   */
  String id();

  /**
   * Returns a formatted representation of the type context.
   *
   * @param formatted Whether to format the type context.
   * @return A formatted representation of the type context.
   */
  String toString(boolean formatted);

  /**
   * Creates a copy of the type context.
   *
   * @return A copy of the type context and its children.
   */
  T copy();

  /**
   * Type context builder.
   *
   * @param <T> The type built by the builder.
   */
  public static interface Builder<T extends Builder<T, U>, U extends Context<U>> {

    /**
     * Sets the unique context ID.
     *
     * @param id The unique context ID.
     * @return The context builder.
     */
    T setId(String id);

    /**
     * Builds the instance.
     *
     * @return The built type context instance.
     */
    U build();

  }

}
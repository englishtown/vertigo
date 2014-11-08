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
package net.kuujo.vertigo.network;

import io.vertx.codegen.annotations.VertxGen;
import net.kuujo.vertigo.Context;
import net.kuujo.vertigo.component.ComponentContext;
import net.kuujo.vertigo.network.impl.NetworkContextImpl;

import java.util.Collection;

/**
 * A network context which contains information regarding the complete structure of a
 * deployed network.<p>
 *
 * Network contexts are immutable as they are constructed after a network
 * is deployed.
 * 
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
@VertxGen
public interface NetworkContext extends Context<NetworkContext> {

  /**
   * Returns a network context builder.
   *
   * @return A new network context builder.
   */
  static Builder builder() {
    return new NetworkContextImpl.Builder();
  }

  /**
   * Returns a new network context builder.
   *
   * @param network An existing network context object to wrap.
   * @return A network context builder wrapper
   */
  static Builder builder(NetworkContext network) {
    return new NetworkContextImpl.Builder((NetworkContextImpl) network);
  }

  /**
   * Returns the network name.<p>
   *
   * The network name is unique to the network within a Vertigo cluster.
   *
   * @return The network name.
   */
  String name();

  /**
   * Returns the network version. Contexts may be updated through network
   * configuration changes, so the version can be used to track updates to
   * the network.
   *
   * @return The network version.
   */
  String version();

  /**
   * Returns the network configuration from which the context was built.
   *
   * @return The network configuration.
   */
  NetworkBuilder config();

  /**
   * Returns a collection of network component contexts.
   * 
   * @return A collection of network component contexts.
   */
  Collection<ComponentContext> components();

  /**
   * Returns a boolean indicating whether the component exists.
   *
   * @param name The name of the component to check.
   * @return Indicates whether a component with that name exists.
   */
  boolean hasComponent(String name);

  /**
   * Returns a component context by name.
   * 
   * @param name The component name.
   * @return A component context.
   * @throws IllegalArgumentException If a component does not exist at the given name.
   */
  ComponentContext component(String name);

  /**
   * Network context builder.
   */
  public static interface Builder extends Context.Builder<Builder, NetworkContext> {

    /**
     * Sets the network name.
     *
     * @param name The unique network name.
     * @return The network context builder.
     */
    Builder setName(String name);

    /**
     * Sets the network version.
     *
     * @param version The network version.
     * @return The network context builder.
     */
    Builder setVersion(String version);

    /**
     * Sets the network configuration.
     *
     * @param config The network configuration.
     * @return The network context builder.
     */
    Builder setConfig(NetworkBuilder config);

    /**
     * Adds a component to the network context.
     *
     * @param component The component context to add.
     * @return The network context builder.
     */
    Builder addComponent(ComponentContext component);

    /**
     * Removes a component from the network context.
     *
     * @param component The component context to remove.
     * @return The network context builder.
     */
    Builder removeComponent(ComponentContext component);

    /**
     * Sets the network components.
     *
     * @param components A collection of network component context.
     * @return The network context builder.
     */
    Builder setComponents(ComponentContext... components);

    /**
     * Sets the network components.
     *
     * @param components A collection of network component context.
     * @return The network context builder.
     */
    Builder setComponents(Collection<ComponentContext> components);
  }

}
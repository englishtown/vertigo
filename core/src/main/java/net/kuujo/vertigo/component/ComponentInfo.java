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
package net.kuujo.vertigo.component;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.json.JsonObject;
import net.kuujo.vertigo.io.InputInfo;
import net.kuujo.vertigo.io.OutputInfo;
import net.kuujo.vertigo.network.Network;

import java.util.Collection;
import java.util.Set;

/**
 * Component info.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
@VertxGen
public interface ComponentInfo {

  /**
   * <code>name</code> is a string indicating the network unique component name. This
   * name is used as the basis for generating unique event bus addresses.
   */
  public static final String COMPONENT_NAME = "name";

  /**
   * <code>identifier</code> is a string indicating the verticle identifier. This field is required
   * for all components.
   */
  public static final String COMPONENT_IDENTIFIER = "identifier";

  /**
   * <code>config</code> is an object defining the configuration to pass to each instance
   * of the component. If no configuration is provided then an empty configuration will be
   * passed to component instances.
   */
  public static final String COMPONENT_CONFIG = "config";

  /**
   * <code>worker</code> is a boolean indicating whether this verticle should be deployed
   * as a worker verticle. Defaults to <code>false</code>
   */
  public static final String COMPONENT_WORKER = "worker";

  /**
   * <code>multi-threaded</code> is a boolean indicating whether a worker verticle is
   * multi-threaded. This option only applies to verticles where <code>worker</code> is
   * <code>true</code>. Defaults to <code>false</code>
   */
  public static final String COMPONENT_MULTI_THREADED = "multi-threaded";

  /**
   * <code>resources</code> is a list of resources that should be distributed with this
   * component when clustering.
   */
  public static final String COMPONENT_RESOURCES = "resources";

  /**
   * Returns the component name.
   *
   * @return The component name.
   */
  String getName();

  /**
   * Sets the component name.
   *
   * @param name The component name.
   * @return The component info.
   */
  @Fluent
  ComponentInfo setName(String name);

  /**
   * Returns the component verticle identifier.
   *
   * @return The component verticle identifier.
   */
  String getIdentifier();

  /**
   * Sets the component verticle identifier.
   *
   * @param identifier The component verticle identifier.
   * @return The component info.
   */
  @Fluent
  ComponentInfo setIdentifier(String identifier);

  /**
   * Returns the component configuration.
   *
   * @return The component configuration.
   */
  JsonObject getConfig();

  /**
   * Sets the component configuration.
   *
   * @param config The component configuration.
   * @return The component info.
   */
  @Fluent
  ComponentInfo setConfig(JsonObject config);

  /**
   * Returns whether the component is a worker.
   *
   * @return Indicates whether the component is a worker.
   */
  boolean isWorker();

  /**
   * Sets whether the component is a worker.
   *
   * @param worker Whether the component is a worker.
   * @return The component info.
   */
  @Fluent
  ComponentInfo setWorker(boolean worker);

  /**
   * Returns whether the component is multi-threaded.
   *
   * @return Indicates whether the component is multi-threaded.
   */
  boolean isMultiThreaded();

  /**
   * Sets whether the component is multi-threaded.
   *
   * @param multiThreaded Whether the component is multi-threaded.
   * @return The component info.
   */
  @Fluent
  ComponentInfo setMultiThreaded(boolean multiThreaded);

  /**
   * Returns the component input info.
   *
   * @return The component input info.
   */
  InputInfo getInput();

  /**
   * Sets the component input info.
   *
   * @param input The component input info.
   * @return The component info.
   */
  @Fluent
  ComponentInfo setInput(InputInfo input);

  /**
   * Returns the component output info.
   *
   * @return The component output info.
   */
  OutputInfo getOutput();

  /**
   * Sets the component output info.
   *
   * @param output The component output info.
   * @return The component info.
   */
  @Fluent
  ComponentInfo setOutput(OutputInfo output);

  /**
   * Adds a resource to the component.
   *
   * @param resource The resource to add.
   * @return The component options.
   */
  @Fluent
  ComponentInfo addResource(String resource);

  /**
   * Removes a resource from the component.
   *
   * @param resource The resource to remove.
   * @return The component options.
   */
  @Fluent
  ComponentInfo removeResource(String resource);

  /**
   * Sets the component resources.
   *
   * @param resources The component resources.
   * @return The component options.
   */
  @Fluent
  ComponentInfo setResources(String... resources);

  /**
   * Sets the component resources.
   *
   * @param resources The component resources.
   * @return The component options.
   */
  @Fluent
  ComponentInfo setResources(Collection<String> resources);

  /**
   * Returns the component resources.
   *
   * @return The component resources.
   */
  Set<String> getResources();

  /**
   * Sets the parent network.
   *
   * @param network The parent network.
   * @return The component info.
   */
  ComponentInfo setNetwork(Network network);

  /**
   * Returns the parent network.
   *
   * @return The parent network.
   */
  Network getNetwork();

}
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
package net.kuujo.vertigo.io.connection.impl;

import net.kuujo.vertigo.TypeInfo;
import net.kuujo.vertigo.impl.BaseTypeInfoImpl;
import net.kuujo.vertigo.io.connection.TargetInfo;

/**
 * Connection source info implementation.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
public class TargetInfoImpl extends BaseTypeInfoImpl<TargetInfo> implements TargetInfo {
  private String component;
  private String port;
  private int instance;

  @Override
  public String component() {
    return component;
  }

  @Override
  public String port() {
    return port;
  }

  @Override
  public int instance() {
    return instance;
  }

  /**
   * Target info builder.
   */
  public static class Builder implements TypeInfo.Builder<TargetInfo> {
    private final TargetInfoImpl target;

    public Builder() {
      target = new TargetInfoImpl();
    }

    public Builder(TargetInfoImpl source) {
      this.target = source;
    }

    /**
     * Sets the target component.
     *
     * @param component The target component name.
     * @return The target info builder.
     */
    public Builder setComponent(String component) {
      target.component = component;
      return this;
    }

    /**
     * Sets the target port.
     *
     * @param port The target port name.
     * @return The target info builder.
     */
    public Builder setPort(String port) {
      target.port = port;
      return this;
    }

    /**
     * Sets the target instance.
     *
     * @param instance The target instance number.
     * @return The target info builder.
     */
    public Builder setInstance(int instance) {
      target.instance = instance;
      return this;
    }

    @Override
    public TargetInfoImpl build() {
      return target;
    }
  }

}
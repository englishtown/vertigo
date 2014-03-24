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
package net.kuujo.vertigo.context;

/**
 * Input connection context.
 *
 * @author Jordan Halterman
 */
public class InputConnectionContext extends ConnectionContext<InputConnectionContext> {
  private String address;
  private String source;

  @Override
  public String address() {
    return address;
  }

  /**
   * Returns the connection source.
   *
   * @return The connection source.
   */
  public String source() {
    return source;
  }

  /**
   * Input connection context builder.
   *
   * @author Jordan Halterman
   */
  public static class Builder extends net.kuujo.vertigo.context.Context.Builder<InputConnectionContext> {

    private Builder() {
      super(new InputConnectionContext());
    }

    private Builder(InputConnectionContext context) {
      super(context);
    }

    /**
     * Creates a new context builder.
     *
     * @return A new context builder.
     */
    public static Builder newBuilder() {
      return new Builder();
    }

    /**
     * Creates a new context builder.
     *
     * @param context A starting connection context.
     * @return A new context builder.
     */
    public static Builder newBuilder(InputConnectionContext context) {
      return new Builder(context);
    }

    /**
     * Sets the stream address.
     *
     * @param address The stream address.
     * @return The context builder.
     */
    public Builder setAddress(String address) {
      context.address = address;
      return this;
    }

    /**
     * Sets the connection source.
     *
     * @param source The connection source.
     * @return The context builder.
     */
    public Builder setSource(String source) {
      context.source = source;
      return this;
    }
  }

}
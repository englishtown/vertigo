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
package net.kuujo.vertigo.output.selector;

import java.util.List;

import net.kuujo.vertigo.messaging.JsonMessage;
import net.kuujo.vertigo.output.Connection;

import org.vertx.java.core.json.JsonObject;

/**
 * A fields selector.
 *
 * The *fields* selector is a consistent-hashing based grouping. Given
 * a field on which to hash, this grouping guarantees that workers will
 * always receive messages with the same field values.
 *
 * @author Jordan Halterman
 */
public class FieldsSelector extends AbstractSelector {

  private String fieldName;

  public FieldsSelector() {
    super();
  }

  public FieldsSelector(String fieldName, String grouping) {
    super(grouping);
    this.fieldName = fieldName;
  }

  /**
   * Sets the grouping field.
   *
   * @param fieldName
   *   The grouping field name.
   * @return
   *   The called grouping instance.
   */
  public FieldsSelector setField(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

  /**
   * Gets the grouping field.
   *
   * @return
   *   The grouping field.
   */
  public String getField() {
    return fieldName;
  }

  @Override
  public JsonObject getState() {
    return super.getState().putString("field", fieldName);
  }

  @Override
  public void setState(JsonObject state) {
    super.setState(state);
    fieldName = state.getString("field");
  }

  @Override
  public List<Connection> select(JsonMessage message, List<Connection> connections) {
    String value = message.body().getString(fieldName);
    if (value != null) {
      int index = value.length() % connections.size();
      connections.subList(index, index+1);
    }
    return null;
  }

}
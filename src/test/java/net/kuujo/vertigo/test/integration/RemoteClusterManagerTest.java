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
package net.kuujo.vertigo.test.integration;

import static org.vertx.testtools.VertxAssert.assertTrue;
import static org.vertx.testtools.VertxAssert.testComplete;
import net.kuujo.vertigo.cluster.LocalClusterManager;
import net.kuujo.vertigo.cluster.RemoteClusterManager;
import net.kuujo.vertigo.cluster.VertigoClusterManager;
import net.kuujo.vertigo.context.NetworkContext;
import net.kuujo.vertigo.java.ComponentVerticle;
import net.kuujo.vertigo.network.Network;
import net.kuujo.xync.test.integration.XyncTestVerticle;

import org.junit.Test;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;

/**
 * A remote cluster test.
 *
 * @author Jordan Halterman
 */
public class RemoteClusterManagerTest extends XyncTestVerticle {

  @Test
  public void testLocalDeploy() {
    Network network = new Network("test");
    network.setNumAuditors(2);
    network.addVerticle("test.feeder", TestFeeder.class.getName());
    network.addVerticle("test.worker1", TestWorker.class.getName(), 2).addInput("test.feeder", "stream1");
    network.addVerticle("test.worker2", TestWorker.class.getName(), 2).addInput("test.feeder", "stream2");

    VertigoClusterManager cluster = new RemoteClusterManager(this);
    cluster.deployNetwork(network, new Handler<AsyncResult<NetworkContext>>() {
      @Override
      public void handle(AsyncResult<NetworkContext> result) {
        assertTrue(result.succeeded());
        testComplete();
      }
    });
  }

  @Test
  public void testLocalShutdown() {
    Network network = new Network("test");
    network.setNumAuditors(2);
    network.addVerticle("test.feeder", TestFeeder.class.getName());
    network.addVerticle("test.worker1", TestWorker.class.getName(), 2).addInput("test.feeder", "stream1");
    network.addVerticle("test.worker2", TestWorker.class.getName(), 2).addInput("test.feeder", "stream2");

    final VertigoClusterManager cluster = new LocalClusterManager(this);
    cluster.deployNetwork(network, new Handler<AsyncResult<NetworkContext>>() {
      @Override
      public void handle(AsyncResult<NetworkContext> result) {
        assertTrue(result.succeeded());
        vertx.setTimer(2000, new Handler<Long>() {
          @Override
          public void handle(Long timerID) {
            cluster.undeployNetwork("test", new Handler<AsyncResult<Void>>() {
              @Override
              public void handle(AsyncResult<Void> result) {
                assertTrue(result.succeeded());
                testComplete();
              }
            });
          }
        });
      }
    });
  }

  public static class TestFeeder extends ComponentVerticle {
  }

  public static class TestWorker extends ComponentVerticle {
  }

}
// (c) Copyright 2010 Odiago, Inc.

package com.odiago.rtengine.exec;

import java.io.IOException;

import com.odiago.rtengine.plan.FlowSpecification;

/**
 * Specification of an environment in which FlowElements
 * can be executed. Supports a Flume-based implementation, a local implementation, and
 * potentially others.
 */
public abstract class ExecEnvironment {

  public ExecEnvironment() {
  }

  /** @return the name of this execution environment. e.g. "local",
   * or perhaps the server you're connected to, etc.
   */
  public String getEnvName() {
    return this.getClass().getName();
  }

  /**
   * Submit a query statement to the planner.
   * @return a response containing any text for the user, as well as any
   * flow ids spawned, etc.
   */
  public abstract QuerySubmitResponse submitQuery(String query);

  /**
   * Deploys a specified flow in the environment. Operates at a lower level
   * than submitQuery(), which allows the environment itself to plan the flow
   * based on the query.
   * @return the FlowId of this flow.
   */
  public abstract FlowId addFlow(FlowSpecification spec);

  /**
   * Terminates a running flow.
   */
  public abstract void cancelFlow(FlowId id) throws InterruptedException, IOException;

  /**
   * Disconnects this client from the environment.
   */
  public abstract void disconnect() throws InterruptedException, IOException;
}

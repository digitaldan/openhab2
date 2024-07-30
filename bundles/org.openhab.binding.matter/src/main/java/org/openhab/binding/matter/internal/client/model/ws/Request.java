/**
 * Copyright (c) 2010-2024 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.matter.internal.client.model.ws;

/**
 * @author Dan Cunningham
 *
 */
public class Request {

    public String id;
    public String namespace;
    public String function;
    public Object args[];

    public Request(String requestId, String namespace, String function, Object args[]) {
        this.id = requestId;
        this.namespace = namespace;
        this.function = function;
        this.args = args;
    }
}
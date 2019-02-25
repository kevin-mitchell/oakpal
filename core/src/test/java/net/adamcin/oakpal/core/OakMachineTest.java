/*
 * Copyright 2019 Mark Adamcin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.adamcin.oakpal.core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OakMachineTest {

    @Test
    public void testJcrSession() throws Exception {
        OakMachine machine = new OakMachine.Builder().build();
        machine.initAndInspect(session -> {
            assertTrue("Root node should be same as / node",
                    session.getRootNode().isSame(session.getNode("/")));
        });
    }

    @Test
    public void testHomePaths() throws Exception {
        OakMachine machine = new OakMachine.Builder().build();
        machine.initAndInspect(session -> {
            assertTrue("/home/users/system should exist", session.nodeExists("/home/users/system"));
            assertTrue("/home/groups should exist", session.nodeExists("/home/groups"));
        });
    }
}

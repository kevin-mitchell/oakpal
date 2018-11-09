/*
 * Copyright 2018 Mark Adamcin
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

package net.adamcin.oakpal.core.checks;

import static org.junit.Assert.assertEquals;

import net.adamcin.commons.testing.junit.TestBody;
import net.adamcin.oakpal.core.CheckReport;
import net.adamcin.oakpal.core.ProgressCheck;
import org.json.JSONObject;
import org.junit.Test;

public class OverlapsTest extends ProgressCheckTestBase {
    @Test
    public void testOverlaps() {
        TestBody.test(new TestBody() {
            @Override
            protected void execute() throws Exception {
                ProgressCheck check = new Overlaps().newInstance( new JSONObject());
                CheckReport report = scanWithCheck(check, "test_a-1.0.zip", "test_b-1.0.zip");
                logViolations("testOverlaps:none", report);
                assertEquals("no violations", 0, report.getViolations().size());
            }
        });
        TestBody.test(new TestBody() {
            @Override
            protected void execute() throws Exception {
                ProgressCheck check = new Overlaps().newInstance( new JSONObject());
                CheckReport report = scanWithCheck(check, "tmp_foo.zip", "tmp_foo_bar.zip", "tmp_foo_bar_test.zip");
                logViolations("testOverlaps:[foo, foo_bar, foo_bar_test]", report);
                assertEquals("two violations", 2, report.getViolations().size());
            }
        });
        TestBody.test(new TestBody() {
            @Override
            protected void execute() throws Exception {
                ProgressCheck check = new Overlaps().newInstance( new JSONObject());
                CheckReport report = scanWithCheck(check, "tmp_foo_bar_test.zip", "tmp_foo_bar.zip", "tmp_foo.zip");
                logViolations("testOverlaps:[foo_bar_test, foo_bar, foo]", report);
                assertEquals("two violations", 2, report.getViolations().size());
            }
        });
    }
}
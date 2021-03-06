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

package net.adamcin.oakpal.core;

import javax.jcr.Node;
import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Stream;

import static net.adamcin.oakpal.api.Fun.uncheck1;

/**
 * Represents an error that causes a package scan to abort without notifying the {@link ErrorListener}.
 */
public class AbortedScanException extends Exception {

    private final URL currentPackageUrl;
    private final File currentPackageFile;
    private final Node currentPackageNode;

    public AbortedScanException(final Throwable cause, final File currentPackageFile) {
        super(cause);
        this.currentPackageFile = currentPackageFile;
        this.currentPackageNode = null;
        this.currentPackageUrl = null;
    }

    public AbortedScanException(final Throwable cause, final URL currentPackageUrl) {
        super(cause);
        this.currentPackageFile = null;
        this.currentPackageNode = null;
        this.currentPackageUrl = currentPackageUrl;
    }

    public AbortedScanException(final Throwable cause, final Node currentPackageNode) {
        super(cause);
        this.currentPackageFile = null;
        this.currentPackageNode = currentPackageNode;
        this.currentPackageUrl = null;
    }

    public AbortedScanException(Throwable cause) {
        super(cause);
        this.currentPackageFile = null;
        this.currentPackageNode = null;
        this.currentPackageUrl = null;
    }

    @SuppressWarnings("WeakerAccess")
    public Optional<URL> getCurrentPackageUrl() {
        return Optional.ofNullable(currentPackageUrl);
    }

    public Optional<File> getCurrentPackageFile() {
        return Optional.ofNullable(currentPackageFile);
    }

    @SuppressWarnings("WeakerAccess")
    public Optional<Node> getCurrentPackageNode() {
        return Optional.ofNullable(currentPackageNode);
    }

    @Override
    public String getMessage() {
        return getFailedPackageMessage() + super.getMessage();
    }

    @SuppressWarnings({"WeakerAccess", "OptionalGetWithoutIsPresent"})
    public String getFailedPackageMessage() {
        return Stream.of(
                getCurrentPackageNode().map(uncheck1(Node::getPath)),
                getCurrentPackageFile().map(File::getAbsolutePath),
                getCurrentPackageUrl().map(URL::toString))
                .filter(Optional::isPresent)
                .findFirst()
                .map(location -> "(Failed package: " + location.get() + ") ").orElse("");
    }
}

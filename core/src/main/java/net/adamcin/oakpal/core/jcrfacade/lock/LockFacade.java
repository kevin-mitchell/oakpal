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

package net.adamcin.oakpal.core.jcrfacade.lock;

import net.adamcin.oakpal.core.ListenerReadOnlyException;
import net.adamcin.oakpal.core.jcrfacade.NodeFacade;
import net.adamcin.oakpal.core.jcrfacade.SessionFacade;
import org.jetbrains.annotations.NotNull;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.lock.Lock;

/**
 * Wraps a {@link Lock} to block the associated {@link Node}.
 */
public final class LockFacade<S extends Session> implements Lock {

    private final @NotNull Lock delegate;
    private final @NotNull SessionFacade<S> session;

    public LockFacade(final @NotNull Lock delegate, final @NotNull SessionFacade<S> session) {
        this.delegate = delegate;
        this.session = session;
    }

    @Override
    public String getLockOwner() {
        return delegate.getLockOwner();
    }

    @Override
    public boolean isDeep() {
        return delegate.isDeep();
    }

    @Override
    public Node getNode() {
        return NodeFacade.wrap(delegate.getNode(), session);
    }

    @Override
    public String getLockToken() {
        return delegate.getLockToken();
    }

    @Override
    public long getSecondsRemaining() throws RepositoryException {
        return delegate.getSecondsRemaining();
    }

    @Override
    public boolean isLive() throws RepositoryException {
        return delegate.isLive();
    }

    @Override
    public boolean isSessionScoped() {
        return delegate.isSessionScoped();
    }

    @Override
    public boolean isLockOwningSession() {
        return delegate.isLockOwningSession();
    }

    @Override
    public void refresh() throws RepositoryException {
        throw new ListenerReadOnlyException();
    }
}

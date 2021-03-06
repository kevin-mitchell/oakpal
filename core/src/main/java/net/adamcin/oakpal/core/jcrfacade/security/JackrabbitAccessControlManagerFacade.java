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

package net.adamcin.oakpal.core.jcrfacade.security;

import org.apache.jackrabbit.api.security.JackrabbitAccessControlManager;
import org.apache.jackrabbit.api.security.JackrabbitAccessControlPolicy;
import org.jetbrains.annotations.NotNull;

import javax.jcr.RepositoryException;
import javax.jcr.security.AccessControlPolicy;
import javax.jcr.security.Privilege;
import java.security.Principal;
import java.util.Set;

/**
 * Wraps {@link JackrabbitAccessControlManager} to prevent writes by handlers.
 */
public final class JackrabbitAccessControlManagerFacade extends AccessControlManagerFacade<JackrabbitAccessControlManager> implements JackrabbitAccessControlManager {

    @SuppressWarnings("WeakerAccess")
    public JackrabbitAccessControlManagerFacade(final @NotNull JackrabbitAccessControlManager delegate) {
        super(delegate);
    }

    @Override
    public JackrabbitAccessControlPolicy[] getApplicablePolicies(Principal principal) throws RepositoryException {
        return delegate.getApplicablePolicies(principal);
    }

    @Override
    public JackrabbitAccessControlPolicy[] getPolicies(Principal principal) throws RepositoryException {
        return delegate.getPolicies(principal);
    }

    @Override
    public AccessControlPolicy[] getEffectivePolicies(Set<Principal> principals) throws RepositoryException {
        return delegate.getEffectivePolicies(principals);
    }

    @Override
    public boolean hasPrivileges(String absPath, Set<Principal> principals, Privilege[] privileges) throws RepositoryException {
        return delegate.hasPrivileges(absPath, principals, privileges);
    }

    @Override
    public Privilege[] getPrivileges(String absPath, Set<Principal> principals) throws RepositoryException {
        return delegate.getPrivileges(absPath, principals);
    }
}

package one.microstream.serializer;

/*-
 * #%L
 * MicroStream Serializer
 * %%
 * Copyright (C) 2019 - 2023 MicroStream Software
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is
 * available at https://www.gnu.org/software/classpath/license.html.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 * #L%
 */

import one.microstream.persistence.types.PersistenceCustomTypeHandlerRegistry;
import one.microstream.persistence.types.PersistenceObjectRegistry;
import one.microstream.persistence.types.PersistenceRoots;
import one.microstream.persistence.types.PersistenceRootsProvider;

public class EmptyPersistenceRootsProvider<D> implements PersistenceRootsProvider<D>
{

    EmptyPersistenceRootsProvider()
    {
        super();
    }

    @Override
    public PersistenceRoots provideRoots()
    {
        //no-op
        return null;
    }

    @Override
    public PersistenceRoots peekRoots()
    {
        //no-op
        return null;
    }

    @Override
    public void updateRuntimeRoots(final PersistenceRoots runtimeRoots)
    {
        //no-op
    }

    @Override
    public void registerRootsTypeHandlerCreator(
            final PersistenceCustomTypeHandlerRegistry<D> typeHandlerRegistry,
            final PersistenceObjectRegistry objectRegistry
    )
    {
        //no-op
    }
}

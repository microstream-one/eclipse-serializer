package org.eclipse.microstream.serializer.persistence.binary.types;

/*-
 * #%L
 * microstream-persistence-binary
 * %%
 * Copyright (C) 2019 - 2022 MicroStream Software
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

import static org.eclipse.microstream.serializer.util.X.notNull;

import org.eclipse.microstream.serializer.persistence.binary.one.microstream.persistence.types.BinaryHandlerPersistenceRootsDefault;
import org.eclipse.microstream.serializer.persistence.types.PersistenceCustomTypeHandlerRegistry;
import org.eclipse.microstream.serializer.persistence.types.PersistenceObjectRegistry;
import org.eclipse.microstream.serializer.persistence.types.PersistenceRootReference;
import org.eclipse.microstream.serializer.persistence.types.PersistenceRootReferenceProvider;
import org.eclipse.microstream.serializer.persistence.types.PersistenceRootResolver;
import org.eclipse.microstream.serializer.persistence.types.PersistenceRootResolverProvider;
import org.eclipse.microstream.serializer.persistence.types.PersistenceRoots;
import org.eclipse.microstream.serializer.persistence.types.PersistenceRootsProvider;
import org.eclipse.microstream.serializer.persistence.types.PersistenceTypeHandler;


public interface BinaryPersistenceRootsProvider extends PersistenceRootsProvider<Binary>
{
	public static BinaryPersistenceRootsProvider New(
		final PersistenceRootResolverProvider          rootResolverProvider ,
		final PersistenceRootReferenceProvider<Binary> rootReferenceProvider
	)
	{
		return new BinaryPersistenceRootsProvider.Default(
			notNull(rootResolverProvider) ,
			notNull(rootReferenceProvider)
		);
	}
	
	public final class Default implements BinaryPersistenceRootsProvider
	{
		///////////////////////////////////////////////////////////////////////////
		// instance fields //
		////////////////////

		final PersistenceRootResolverProvider          rootResolverProvider ;
		final PersistenceRootReferenceProvider<Binary> rootReferenceProvider;
		
		transient PersistenceRootResolver rootResolver;
		transient PersistenceRoots        roots       ;
		
		
		
		///////////////////////////////////////////////////////////////////////////
		// constructors //
		/////////////////
		
		Default(
			final PersistenceRootResolverProvider          rootResolverProvider ,
			final PersistenceRootReferenceProvider<Binary> rootReferenceProvider
		)
		{
			super();
			this.rootResolverProvider  = rootResolverProvider ;
			this.rootReferenceProvider = rootReferenceProvider;
		}
		
		
		
		///////////////////////////////////////////////////////////////////////////
		// methods //
		////////////
		
		private PersistenceRootResolver ensureRootResolver()
		{
			if(this.rootResolver == null)
			{
				this.rootResolver = this.rootResolverProvider.provideRootResolver();
			}
			
			return this.rootResolver;
		}

		@Override
		public final PersistenceRoots provideRoots()
		{
			if(this.roots == null)
			{
				this.roots = PersistenceRoots.New(this.ensureRootResolver());
			}
			
			return this.roots;
		}
		
		@Override
		public final PersistenceRoots peekRoots()
		{
			return this.roots;
		}
		
		@Override
		public final void updateRuntimeRoots(final PersistenceRoots runtimeRoots)
		{
			this.roots = runtimeRoots;
		}
		
		@Override
		public final void registerRootsTypeHandlerCreator(
			final PersistenceCustomTypeHandlerRegistry<Binary> typeHandlerRegistry,
			final PersistenceObjectRegistry                    objectRegistry
		)
		{
			final BinaryHandlerPersistenceRootsDefault rootsHandler = BinaryHandlerPersistenceRootsDefault.New(
				this.rootResolverProvider,
				objectRegistry
			);
			
			final PersistenceTypeHandler<Binary, ? extends PersistenceRootReference> rootReferenceHandler =
				this.rootReferenceProvider.provideTypeHandler(objectRegistry)
			;
			
			typeHandlerRegistry.registerTypeHandler(rootsHandler);
			typeHandlerRegistry.registerTypeHandler(rootReferenceHandler);
		}

	}

}

package org.eclipse.microstream.serializer.persistence.binary.android.java.time;

/*-
 * #%L
 * MicroStream Serializer Persistence Android
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

import java.time.Duration;

import org.eclipse.microstream.serializer.persistence.binary.internal.AbstractBinaryHandlerCustomNonReferentialFixedLength;
import org.eclipse.microstream.serializer.persistence.binary.types.Binary;
import org.eclipse.microstream.serializer.persistence.types.PersistenceLoadHandler;
import org.eclipse.microstream.serializer.persistence.types.PersistenceStoreHandler;

public final class BinaryHandlerDuration extends AbstractBinaryHandlerCustomNonReferentialFixedLength<Duration>
{
	///////////////////////////////////////////////////////////////////////////
	// static methods //
	///////////////////
	
	public static BinaryHandlerDuration New()
	{
		return new BinaryHandlerDuration();
	}

	
	
	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	BinaryHandlerDuration()
	{
		super(
			Duration.class,
			CustomFields(
				CustomField(long.class, "nanos")
			)
		);
	}



	///////////////////////////////////////////////////////////////////////////
	// methods //
	////////////
	

	@Override
	public final void store(
		final Binary                          data    ,
		final Duration                        instance,
		final long                            objectId,
		final PersistenceStoreHandler<Binary> handler
	)
	{
		data.storeEntityHeader(Long.BYTES, this.typeId(), objectId);
		
		data.store_long(instance.toNanos());
	}

	@Override
	public final Duration create(final Binary data, final PersistenceLoadHandler handler)
	{
		return Duration.ofNanos(data.read_long(0L));
	}

	@Override
	public final void updateState(final Binary data, final Duration instance, final PersistenceLoadHandler handler)
	{
		// no-op
	}

}
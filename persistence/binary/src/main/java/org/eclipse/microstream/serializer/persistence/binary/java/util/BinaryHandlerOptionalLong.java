package org.eclipse.microstream.serializer.persistence.binary.java.util;

/*-
 * #%L
 * MicroStream Serializer Persistence Binary
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

import java.util.OptionalLong;

import org.eclipse.microstream.serializer.memory.XMemory;
import org.eclipse.microstream.serializer.persistence.binary.internal.AbstractBinaryHandlerCustomValueFixedLength;
import org.eclipse.microstream.serializer.persistence.binary.types.Binary;
import org.eclipse.microstream.serializer.persistence.types.PersistenceLoadHandler;
import org.eclipse.microstream.serializer.persistence.types.PersistenceStoreHandler;

public final class BinaryHandlerOptionalLong
extends AbstractBinaryHandlerCustomValueFixedLength<OptionalLong, Long>
{
	///////////////////////////////////////////////////////////////////////////
	// constants //
	//////////////

	static final long
		BINARY_OFFSET_IS_PRESENT =                                     0,
		BINARY_OFFSET_VALUE      = BINARY_OFFSET_IS_PRESENT + Byte.BYTES, // Boolean.BYTES does not exist
		BINARY_LENGTH            = BINARY_OFFSET_VALUE      + Long.BYTES
	;
	
	

	///////////////////////////////////////////////////////////////////////////
	// static methods //
	///////////////////
	
	public static BinaryHandlerOptionalLong New()
	{
		return new BinaryHandlerOptionalLong();
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	BinaryHandlerOptionalLong()
	{
		/*
		 * Note on fields:
		 * These are not tied to JDK-specific internals. It's merely a sufficient state
		 * (even properly queryable in the implementation, fancy that) to represent an instance.
		 * The identical naming is "by coincidence" and will continue to work even if they change theirs internally.
		 */
		super(
			OptionalLong.class,
			CustomFields(
				CustomField(boolean.class, "isPresent"),
				CustomField(long   .class, "value"    )
			)
		);
	}



	///////////////////////////////////////////////////////////////////////////
	// methods //
	////////////
	
	private static long instanceState(final OptionalLong instance)
	{
		// or ELSE!!!
		return instance.orElse(0L);
	}
	
	private static long binaryState(final Binary data)
	{
		return data.read_long(BINARY_OFFSET_VALUE);
	}

	@Override
	public void store(
		final Binary                          data    ,
		final OptionalLong                    instance,
		final long                            objectId,
		final PersistenceStoreHandler<Binary> handler
	)
	{
		data.storeEntityHeader(BINARY_LENGTH, this.typeId(), objectId);
		data.store_boolean(
			BINARY_OFFSET_IS_PRESENT,
			instance.isPresent()
		);

		data.store_long(
			BINARY_OFFSET_VALUE,
			instanceState(instance)
		);
	}

	@Override
	public OptionalLong create(final Binary data, final PersistenceLoadHandler handler)
	{
		final boolean isPresent = data.read_boolean(BINARY_OFFSET_IS_PRESENT);
		
		// luckily, an uninitialized instance (all-zeroes, meaning isPresent == false) is all that is required.
		return isPresent
			? OptionalLong.of(
				data.read_long(BINARY_OFFSET_VALUE)
			)
			: XMemory.instantiateBlank(OptionalLong.class)
		;
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// validation //
	///////////////
	
	// actually never called, just to satisfy the interface
	@Override
	public Long getValidationStateFromInstance(final OptionalLong instance)
	{
		return instanceState(instance);
	}

	// actually never called, just to satisfy the interface
	@Override
	public Long getValidationStateFromBinary(final Binary data)
	{
		return binaryState(data);
	}
	
	@Override
	public void validateState(
		final Binary                 data    ,
		final OptionalLong           instance,
		final PersistenceLoadHandler handler
	)
	{
		final long instanceState = instanceState(instance);
		final long binaryState   = binaryState(data);
				
		if(instanceState == binaryState)
		{
			return;
		}
		
		this.throwInconsistentStateException(instance, instanceState, binaryState);
	}
	
}

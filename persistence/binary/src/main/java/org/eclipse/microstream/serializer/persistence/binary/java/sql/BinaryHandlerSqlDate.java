package org.eclipse.microstream.serializer.persistence.binary.java.sql;

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

import java.sql.Date;

import org.eclipse.microstream.serializer.persistence.binary.internal.AbstractBinaryHandlerCustomNonReferentialFixedLength;
import org.eclipse.microstream.serializer.persistence.binary.java.util.BinaryHandlerDate;
import org.eclipse.microstream.serializer.persistence.binary.types.Binary;
import org.eclipse.microstream.serializer.persistence.types.PersistenceLoadHandler;
import org.eclipse.microstream.serializer.persistence.types.PersistenceStoreHandler;

/**
 * Blunt copy of {@link BinaryHandlerDate} for the as good as superfluous type {@link java.sql.Date}.
 *
 */
@SuppressWarnings("exports")
public final class BinaryHandlerSqlDate extends AbstractBinaryHandlerCustomNonReferentialFixedLength<Date>
{
	///////////////////////////////////////////////////////////////////////////
	// static methods //
	///////////////////
	
	public static BinaryHandlerSqlDate New()
	{
		return new BinaryHandlerSqlDate();
	}

	
	
	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	BinaryHandlerSqlDate()
	{
		super(
			Date.class,
			CustomFields(
				CustomField(long.class, "timestamp")
			)
		);
	}



	///////////////////////////////////////////////////////////////////////////
	// methods //
	////////////
	
	private static long instanceState(final Date instance)
	{
		return instance.getTime();
	}
	
	private static long binaryState(final Binary data)
	{
		return data.read_long(0);
	}

	@Override
	public final void store(
		final Binary                          data    ,
		final Date                            instance,
		final long                            objectId,
		final PersistenceStoreHandler<Binary> handler
	)
	{
		data.storeEntityHeader(Long.BYTES, this.typeId(), objectId);
		
		// the data content of a date is simple the timestamp long, nothing else
		data.store_long(instanceState(instance));
	}

	@Override
	public final Date create(final Binary data, final PersistenceLoadHandler handler)
	{
		return new Date(binaryState(data));
	}

	@Override
	public final void updateState(final Binary data, final Date instance, final PersistenceLoadHandler handler)
	{
		instance.setTime(binaryState(data));
	}

}

package org.eclipse.microstream.serializer.persistence.binary.android.types;

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

import org.eclipse.microstream.serializer.util.X;
import org.eclipse.microstream.serializer.persistence.binary.android.java.time.BinaryHandlerDuration;
import org.eclipse.microstream.serializer.persistence.binary.android.java.time.BinaryHandlerInstant;
import org.eclipse.microstream.serializer.persistence.binary.android.java.time.BinaryHandlerLocalDate;
import org.eclipse.microstream.serializer.persistence.binary.android.java.time.BinaryHandlerLocalDateTime;
import org.eclipse.microstream.serializer.persistence.binary.android.java.time.BinaryHandlerLocalTime;
import org.eclipse.microstream.serializer.persistence.binary.android.java.time.BinaryHandlerMonthDay;
import org.eclipse.microstream.serializer.persistence.binary.android.java.time.BinaryHandlerOffsetDateTime;
import org.eclipse.microstream.serializer.persistence.binary.android.java.time.BinaryHandlerOffsetTime;
import org.eclipse.microstream.serializer.persistence.binary.android.java.time.BinaryHandlerPeriod;
import org.eclipse.microstream.serializer.persistence.binary.android.java.time.BinaryHandlerYear;
import org.eclipse.microstream.serializer.persistence.binary.android.java.time.BinaryHandlerYearMonth;
import org.eclipse.microstream.serializer.persistence.binary.android.java.time.BinaryHandlerZonedDateTime;
import org.eclipse.microstream.serializer.persistence.binary.types.Binary;
import org.eclipse.microstream.serializer.persistence.types.PersistenceTypeHandlerRegistration;

/**
 * Registeres special type handlers written for Android.
 * Some of them have to sacrifice referencial integrity for funcionality.
 * <p>
 * See <a href="https://github.com/microstream-one/microstream/issues/245#issuecomment-921660371">Issue</a> for further details.
 */
public final class BinaryHandlersAndroid
{
	public static <F extends PersistenceTypeHandlerRegistration.Executor<Binary>> F registerAndroidTypeHandlers(final F executor)
	{
		executor.executeTypeHandlerRegistration((r, c) ->
			r.registerTypeHandlers(X.List(
				BinaryHandlerDuration.New(),
				BinaryHandlerInstant.New(),
				BinaryHandlerLocalDate.New(),
				BinaryHandlerLocalTime.New(),
				BinaryHandlerLocalDateTime.New(),
				BinaryHandlerMonthDay.New(),
				BinaryHandlerOffsetTime.New(),
				BinaryHandlerOffsetDateTime.New(),
				BinaryHandlerPeriod.New(),
				BinaryHandlerYear.New(),
				BinaryHandlerYearMonth.New(),
				BinaryHandlerZonedDateTime.New()
			))
		);
		
		return executor;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////
	
	/**
	 * Dummy constructor to prevent instantiation of this static-only utility class.
	 *
	 * @throws UnsupportedOperationException when called
	 */
	protected BinaryHandlersAndroid()
	{
		// static only
		throw new UnsupportedOperationException();
	}
	
}

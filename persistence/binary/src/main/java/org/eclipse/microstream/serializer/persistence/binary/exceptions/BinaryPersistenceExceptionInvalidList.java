package org.eclipse.microstream.serializer.persistence.binary.exceptions;

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

public class BinaryPersistenceExceptionInvalidList extends BinaryPersistenceException
{
	///////////////////////////////////////////////////////////////////////////
	// static methods //
	///////////////////
	
	public static String messageBody()
	{
		return "Invalid list data";
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// instance fields //
	////////////////////
	
	private final long entityLength   ;
	private final long objectId       ;
	private final long typeId         ;
	private final long listStartOffset;
	private final long listTotalLength;
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	public BinaryPersistenceExceptionInvalidList(
		final long entityLength   ,
		final long objectId       ,
		final long typeId         ,
		final long listStartOffset,
		final long listTotalLength
	)
	{
		super();
		this.entityLength    = entityLength   ;
		this.objectId        = objectId       ;
		this.typeId          = typeId         ;
		this.listStartOffset = listStartOffset;
		this.listTotalLength = listTotalLength;
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// methods //
	////////////
	
	protected String assembleDetailStringBody()
	{
		return messageBody() + ": " +
			"entityLength = "     + this.entityLength     + ", " +
			"objectId = "         + this.objectId         + ", " +
			"typeId = "           + this.typeId           + ", " +
			"listStartOffset = "  + this.listStartOffset  + ", " +
			"listTotalLength = "  + this.listTotalLength
		;
	}
	
	@Override
	public String assembleDetailString()
	{
		return this.assembleDetailStringBody() + ".";
	}
	
}

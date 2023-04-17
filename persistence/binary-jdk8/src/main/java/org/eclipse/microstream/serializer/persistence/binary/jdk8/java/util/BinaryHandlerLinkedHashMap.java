package org.eclipse.microstream.serializer.persistence.binary.jdk8.java.util;

/*-
 * #%L
 * MicroStream Serializer Persistence JDK8
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

import java.util.LinkedHashMap;

import org.eclipse.microstream.serializer.util.X;
import org.eclipse.microstream.serializer.collections.old.KeyValueFlatCollector;
import org.eclipse.microstream.serializer.collections.old.OldCollections;
import org.eclipse.microstream.serializer.persistence.binary.internal.AbstractBinaryHandlerCustomCollection;
import org.eclipse.microstream.serializer.persistence.binary.jdk8.types.SunJdk8Internals;
import org.eclipse.microstream.serializer.persistence.binary.types.Binary;
import org.eclipse.microstream.serializer.persistence.types.Persistence;
import org.eclipse.microstream.serializer.persistence.types.PersistenceFunction;
import org.eclipse.microstream.serializer.persistence.types.PersistenceLoadHandler;
import org.eclipse.microstream.serializer.persistence.types.PersistenceReferenceLoader;
import org.eclipse.microstream.serializer.persistence.types.PersistenceStoreHandler;


public final class BinaryHandlerLinkedHashMap extends AbstractBinaryHandlerCustomCollection<LinkedHashMap<?, ?>>
{
	///////////////////////////////////////////////////////////////////////////
	// constants //
	//////////////

	static final long BINARY_OFFSET_LOAD_FACTOR  =                                        0;
	static final long BINARY_OFFSET_ACCESS_ORDER = BINARY_OFFSET_LOAD_FACTOR  + Float.BYTES;
	static final long BINARY_OFFSET_ELEMENTS     = BINARY_OFFSET_ACCESS_ORDER + Byte.BYTES ; // actually Boolean.BYTES



	///////////////////////////////////////////////////////////////////////////
	// static methods //
	///////////////////

	@SuppressWarnings({"unchecked", "rawtypes"})
	private static Class<LinkedHashMap<?, ?>> handledType()
	{
		return (Class)LinkedHashMap.class; // no idea how to get ".class" to work otherwise
	}

	static final float getLoadFactor(final Binary bytes)
	{
		return bytes.read_float(BINARY_OFFSET_LOAD_FACTOR);
	}

	static final boolean getAccessOrder(final Binary bytes)
	{
		return bytes.read_boolean(BINARY_OFFSET_ACCESS_ORDER);
	}

	static final int getElementCount(final Binary bytes)
	{
		return X.checkArrayRange(bytes.getListElementCountKeyValue(BINARY_OFFSET_ELEMENTS));
	}
	
	public static BinaryHandlerLinkedHashMap New()
	{
		return new BinaryHandlerLinkedHashMap();
	}



	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	BinaryHandlerLinkedHashMap()
	{
		super(
			handledType(),
			keyValuesFields(
				CustomField(float.class,   "loadFactor"),
				CustomField(boolean.class, "accessOrder")
			)
		);
	}



	///////////////////////////////////////////////////////////////////////////
	// methods //
	////////////

	@Override
	public final void store(
		final Binary                          bytes   ,
		final LinkedHashMap<?, ?>             instance,
		final long                            objectId,
		final PersistenceStoreHandler<Binary> handler
	)
	{
		// store elements simply as array binary form
		bytes.storeMapEntrySet(
			this.typeId()         ,
			objectId              ,
			BINARY_OFFSET_ELEMENTS,
			instance.entrySet()   ,
			handler
		);
		bytes.store_float(
			BINARY_OFFSET_LOAD_FACTOR,
			SunJdk8Internals.getLoadFactor(instance)
		);
		bytes.store_boolean(
			BINARY_OFFSET_ACCESS_ORDER,
			SunJdk8Internals.getAccessOrder(instance)
		);
	}

	@Override
	public final LinkedHashMap<?, ?> create(final Binary bytes, final PersistenceLoadHandler idResolver)
	{
		return new LinkedHashMap<>(
			getElementCount(bytes),
			getLoadFactor(bytes),
			getAccessOrder(bytes)
		);
	}

	@Override
	public final void updateState(
		final Binary                 data      ,
		final LinkedHashMap<?, ?>    instance  ,
		final PersistenceLoadHandler idResolver
	)
	{
		instance.clear();
		final int elementCount = getElementCount(data);
		final KeyValueFlatCollector<Object, Object> collector = KeyValueFlatCollector.New(elementCount);
		data.collectKeyValueReferences(BINARY_OFFSET_ELEMENTS, elementCount, idResolver, collector);
		data.registerHelper(instance, collector.yield());
	}

	@Override
	public void complete(final Binary bytes, final LinkedHashMap<?, ?> instance, final PersistenceLoadHandler idResolver)
	{
		OldCollections.populateMapFromHelperArray(instance, bytes.getHelper(instance));
	}

	@Override
	public final void iterateInstanceReferences(final LinkedHashMap<?, ?> instance, final PersistenceFunction iterator)
	{
		Persistence.iterateReferencesMap(iterator, instance);
	}

	@Override
	public final void iterateLoadableReferences(final Binary bytes, final PersistenceReferenceLoader iterator)
	{
		bytes.iterateKeyValueEntriesReferences(BINARY_OFFSET_ELEMENTS, iterator);
	}
	
}
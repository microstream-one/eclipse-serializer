package org.eclipse.microstream.serializer.collections;

/*-
 * #%L
 * MicroStream Serializer Base
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

import org.eclipse.microstream.serializer.typing.KeyValue;

public abstract class AbstractChainKeyValueCollection<K, V, EN extends AbstractChainEntry<KeyValue<K, V>, K, V, EN>>
extends AbstractChainCollection<KeyValue<K, V>, K, V, EN>
{
	@Override
	protected abstract long size();

	@Override
	protected abstract void internalRemoveEntry(EN chainEntry);

	@Override
	protected abstract int internalRemoveNullEntries();

	@Override
	protected abstract int internalClear();

}

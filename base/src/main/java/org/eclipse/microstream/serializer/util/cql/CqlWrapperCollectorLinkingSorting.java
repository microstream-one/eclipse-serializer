package org.eclipse.microstream.serializer.util.cql;

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

import java.util.Comparator;
import java.util.function.BiConsumer;

import org.eclipse.microstream.serializer.collections.sorting.Sortable;
import org.eclipse.microstream.serializer.functional.Aggregator;

public final class CqlWrapperCollectorLinkingSorting<O, R extends Sortable<O>> implements Aggregator<O, R>
{
	final R                     target;
	final BiConsumer<O, R>     linker;
	final Comparator<? super O> order ;

	CqlWrapperCollectorLinkingSorting(final R target, final BiConsumer<O, R> linker, final Comparator<? super O> order)
	{
		super();
		this.target = target;
		this.linker = linker;
		this.order  = order ;
	}

	@Override
	public final void accept(final O element)
	{
		this.linker.accept(element, this.target);
	}

	@Override
	public final R yield()
	{
		this.target.sort(this.order);
		return this.target;
	}

}

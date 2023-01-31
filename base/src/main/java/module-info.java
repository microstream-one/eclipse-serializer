/*-
 * #%L
 * microstream-base
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
module org.eclipse.microstream.serializer.base
{
	exports org.eclipse.microstream.serializer.memory.android;
	exports org.eclipse.microstream.serializer.hashing;
	exports org.eclipse.microstream.serializer.util.xcsv;
	exports org.eclipse.microstream.serializer.reference;
	exports org.eclipse.microstream.serializer.reflect;
	exports org.eclipse.microstream.serializer.typing;
	exports org.eclipse.microstream.serializer.concurrency;
	exports org.eclipse.microstream.serializer.functional;
	exports org.eclipse.microstream.serializer.chars;
	exports org.eclipse.microstream.serializer.collections;
	exports org.eclipse.microstream.serializer.branching;
	exports org.eclipse.microstream.serializer.equality;
	exports org.eclipse.microstream.serializer.entity;
	exports org.eclipse.microstream.serializer.util.similarity;
	exports org.eclipse.microstream.serializer.util.logging;
	exports org.eclipse.microstream.serializer.util.iterables;
	exports org.eclipse.microstream.serializer.collections.types;
	exports org.eclipse.microstream.serializer.memory;
	exports org.eclipse.microstream.serializer.io;
	exports org.eclipse.microstream.serializer.util;
	exports org.eclipse.microstream.serializer.collections.interfaces;
	exports org.eclipse.microstream.serializer.collections.sorting;
	exports org.eclipse.microstream.serializer.memory.sun;
	exports org.eclipse.microstream.serializer.collections.old;
	exports org.eclipse.microstream.serializer.meta;
	exports org.eclipse.microstream.serializer.exceptions;
	exports org.eclipse.microstream.serializer.math;
	exports org.eclipse.microstream.serializer.util.cql;
	exports org.eclipse.microstream.serializer.time;

	requires java.compiler;
	requires transitive java.management;
	requires transitive jdk.unsupported;
	requires transitive org.slf4j;
}

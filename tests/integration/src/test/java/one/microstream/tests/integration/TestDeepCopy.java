package one.microstream.tests.integration;

/*-
 * #%L
 * integration
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

import one.microstream.persistence.binary.serializer.ObjectCopier;
import one.microstream.tests.integration.data.TestSerializationData;
import one.microstream.tests.integration.data.TestSerializationDataProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class TestDeepCopy {

    @ParameterizedTest
    @ArgumentsSource(TestSerializationDataProvider.class)
    void testCopy(TestSerializationData serializationData) {
        try (ObjectCopier objectCopier = ObjectCopier.New()) {

            Object cloned = objectCopier.copy(serializationData.getInstance());

            serializationData.getCompareInstances().doCheckEquality(cloned, serializationData.getInstance());
        }
    }
}

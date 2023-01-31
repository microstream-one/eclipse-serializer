package org.eclipse.microstream.serializer.tests.integration;

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

import org.eclipse.microstream.serializer.Serializer;
import org.eclipse.microstream.serializer.SerializerFoundation;
import org.eclipse.microstream.serializer.TypedSerializer;
import org.eclipse.microstream.serializer.tests.model.Address;
import org.eclipse.microstream.serializer.tests.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TypedSerializerTest {

    @Test
    void basic_usage() throws Exception {

        SerializerFoundation<?> foundation = SerializerFoundation.New();

        byte[] bytesEmployee;
        byte[] bytesAddress;

        try (Serializer<byte[]> serializer = TypedSerializer.Bytes(foundation)) {

            bytesEmployee = serializer.serialize(new Employee(123L, "John"));

            // TypedSerializer makes that TypeDictionary is included
            bytesAddress = serializer.serialize(testAddress());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        foundation = SerializerFoundation.New();
        Address address;
        // We can now use the TypedSerializer as usual without worrying about ids.
        try (Serializer<byte[]> serializer = TypedSerializer.Bytes(foundation)) {
            address = serializer.deserialize(bytesAddress);
        }

        Assertions.assertThat(address.toString()).isEqualTo("Address[id=123, street='to nowhere', city='somewhere', postalCode='666']");

    }


    private static Address testAddress() {
        return new Address(123, "to nowhere", "somewhere", "666");
    }
}

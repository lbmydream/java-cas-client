/**
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apereo.cas.client.validation;

import org.apereo.cas.client.authentication.AttributePrincipal;
import org.apereo.cas.client.authentication.AttributePrincipalImpl;

import junit.framework.TestCase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Test cases for the {@link AssertionImpl}.
 *
 * @author Scott Battaglia
 * @since 3.0
 */
public final class AssertionImplTests extends TestCase {

    private static final AttributePrincipal CONST_PRINCIPAL = new AttributePrincipalImpl("test");

    private static final Map<String, Object> CONST_ATTRIBUTES = new HashMap<String, Object>();

    static {
        CONST_ATTRIBUTES.put("test", "test");
    }

    public void testPrincipalConstructor() {
        final Assertion assertion = new AssertionImpl(CONST_PRINCIPAL);

        assertEquals(CONST_PRINCIPAL, assertion.getPrincipal());
        assertTrue(assertion.getAttributes().isEmpty());
        assertNull(assertion.getPrincipal().getProxyTicketFor("test"));
    }

    public void testAssertionValidity() throws Exception {
        final Assertion assertion = new AssertionImpl(CONST_PRINCIPAL, new Date(), new Date(), new Date(), CONST_ATTRIBUTES);
        assertTrue(assertion.isValid());
    }

    public void testCompleteConstructor() {
        final Assertion assertion = new AssertionImpl(CONST_PRINCIPAL, CONST_ATTRIBUTES);

        assertEquals(CONST_PRINCIPAL, assertion.getPrincipal());
        assertEquals(CONST_ATTRIBUTES, assertion.getAttributes());
    }
}

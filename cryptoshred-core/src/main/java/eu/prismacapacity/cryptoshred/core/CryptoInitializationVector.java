/*
 * Copyright © 2020 PRISMA European Capacity Platform GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.prismacapacity.cryptoshred.core;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class CryptoInitializationVector {
	@NonNull
	String initVector;

	public byte[] getBytes() {
		try {
			// make sure, we have 16 bytes there
			StringBuffer sb = new StringBuffer(initVector);
			while (sb.length() < 16)
				sb.append(initVector);

			byte[] bytes = sb.toString().getBytes("UTF-8");
			// take the first 16 bytes
			return Arrays.copyOf(bytes, 16);
		} catch (UnsupportedEncodingException e) {
			// must not happen
			throw new IllegalStateException("UTF-8 not a valid charset!?");
		}
	}
}

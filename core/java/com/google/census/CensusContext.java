/*
 * Copyright 2016, Google Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *    * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *
 *    * Neither the name of Google Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.google.census;

import java.nio.ByteBuffer;

/**
 * An immutable Census-specific context for an operation.
 */
public interface CensusContext {
  /** Returns a builder based on this {@link CensusContext} */
  Builder builder();

  /** Shorthand for builder().set(k1, v1).build() */
  CensusContext with(TagKey k1, TagValue v1);

  /** Shorthand for builder().set(k1, v1).set(k2, v2).build() */
  CensusContext with(TagKey k1, TagValue v1, TagKey k2, TagValue v2);

  /** Shorthand for builder().set(k1, v1).set(k2, v2).set(k3, v3).build() */
  CensusContext with(TagKey k1, TagValue v1, TagKey k2, TagValue v2, TagKey k3, TagValue v3);

  /**
   * Records the given metrics against this {@link CensusContext}.
   *
   * @param metrics the metrics to record against the saved {@link CensusContext}
   * @return this
   */
  CensusContext record(MetricMap metrics);

  /**
   * Serializes the {@link CensusContext} into the on-the-wire representation.
   *
   * @return serialized bytes.
   */
  ByteBuffer serialize();

  /** Sets the current thread-local {@link CensusContext}. */
  void setCurrent();

  /** Builder for {@link Context}. */
  interface Builder {
    /**
     * Associates the given tag key with the given tag value.
     *
     * @param key the key
     * @param value the value to be associated with {@code key}
     * @return {@code this}
     */
    Builder set(TagKey key, TagValue value);

    /**
     * Builds a {@link CensusContext} from the specified keys and values.
     */
    CensusContext build();
  }
}
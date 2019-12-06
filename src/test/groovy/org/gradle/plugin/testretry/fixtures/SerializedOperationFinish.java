/**
 * Copyright 2019 Gradle, Inc.
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
package org.gradle.plugin.testretry.fixtures;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

class SerializedOperationFinish implements SerializedOperation {

    final long id;

    final long endTime;

    final Object result;
    final String resultClassName;

    final String failureMsg;

    SerializedOperationFinish(Map<String, ?> map) {
        this.id = ((Integer) map.get("id")).longValue();
        this.endTime = (Long) map.get("endTime");
        this.result = map.get("result");
        this.resultClassName = (String) map.get("resultClassName");
        this.failureMsg = (String) map.get("failure");
    }

    @Override
    public Map<String, ?> toMap() {
        ImmutableMap.Builder<String, Object> map = ImmutableMap.builder();

        // Order is optimised for humans looking at the log.

        map.put("id", id);

        if (result != null) {
            map.put("result", result);
            map.put("resultClassName", resultClassName);
        }

        if (failureMsg != null) {
            map.put("failure", failureMsg);
        }

        map.put("endTime", endTime);

        return map.build();
    }

}

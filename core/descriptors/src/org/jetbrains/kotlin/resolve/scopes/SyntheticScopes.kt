/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.resolve.scopes

import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.incremental.components.LookupLocation
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.types.KotlinType


interface SyntheticScope {
    fun getSyntheticExtensionProperties(receiverTypes: Collection<KotlinType>, name: Name, location: LookupLocation): Collection<PropertyDescriptor>
    fun getSyntheticMemberFunctions(receiverTypes: Collection<KotlinType>, name: Name, location: LookupLocation): Collection<FunctionDescriptor>
    fun getSyntheticStaticFunctions(scope: ResolutionScope, name: Name, location: LookupLocation): Collection<FunctionDescriptor>

    fun getSyntheticExtensionProperties(receiverTypes: Collection<KotlinType>): Collection<PropertyDescriptor>
    fun getSyntheticMemberFunctions(receiverTypes: Collection<KotlinType>): Collection<FunctionDescriptor>
    fun getSyntheticStaticFunctions(scope: ResolutionScope): Collection<FunctionDescriptor>
}

interface SyntheticScopes {
    val scopes: Collection<SyntheticScope>

    object Empty : SyntheticScopes {
        override val scopes: Collection<SyntheticScope> = emptyList()
    }
}


fun SyntheticScopes.collectSyntheticExtensionProperties(receiverTypes: Collection<KotlinType>, name: Name, location: LookupLocation)
        = scopes.flatMap { it.getSyntheticExtensionProperties(receiverTypes, name, location) }

fun SyntheticScopes.collectSyntheticMemberFunctions(receiverTypes: Collection<KotlinType>, name: Name, location: LookupLocation)
        = scopes.flatMap { it.getSyntheticMemberFunctions(receiverTypes, name, location) }

fun SyntheticScopes.collectSyntheticStaticFunctions(scope: ResolutionScope, name: Name, location: LookupLocation)
        = scopes.flatMap { it.getSyntheticStaticFunctions(scope, name, location) }

fun SyntheticScopes.collectSyntheticExtensionProperties(receiverTypes: Collection<KotlinType>)
        = scopes.flatMap { it.getSyntheticExtensionProperties(receiverTypes) }

fun SyntheticScopes.collectSyntheticMemberFunctions(receiverTypes: Collection<KotlinType>)
        = scopes.flatMap { it.getSyntheticMemberFunctions(receiverTypes) }

fun SyntheticScopes.collectSyntheticStaticFunctions(scope: ResolutionScope)
        = scopes.flatMap { it.getSyntheticStaticFunctions(scope) }

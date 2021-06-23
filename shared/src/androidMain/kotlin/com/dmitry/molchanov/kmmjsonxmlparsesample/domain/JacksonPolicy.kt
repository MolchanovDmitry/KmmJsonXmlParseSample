package com.dmitry.molchanov.kmmjsonxmlparsesample.domain

import nl.adaptivity.xmlutil.QName
import nl.adaptivity.xmlutil.serialization.DefaultXmlSerializationPolicy
import nl.adaptivity.xmlutil.serialization.OutputKind
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerializationPolicy
import nl.adaptivity.xmlutil.serialization.structure.SafeParentInfo

/**
 * Example policy that (very crudely) mimicks the way that Jackson serializes xml. It starts by eliding defaults.
 * Note that this version doesn't handle the jackson annotations.
 */
object JacksonPolicy :
    DefaultXmlSerializationPolicy(false, encodeDefault = XmlSerializationPolicy.XmlEncodeDefault.NEVER) {
    /*
     * Rather than replacing the method wholesale, just make attributes into elements unless the [XmlElement] annotation
     * is present with a `false` value on the value attribute.
     */
    override fun effectiveOutputKind(serializerParent: SafeParentInfo, tagParent: SafeParentInfo): OutputKind {
        val r = super.effectiveOutputKind(serializerParent, tagParent)
        return when {
            // Do take into account the XmlElement annotation
            r == OutputKind.Attribute &&
                    serializerParent.elementUseAnnotations.mapNotNull { it as? XmlElement }
                        .firstOrNull()?.value != false
            -> OutputKind.Element

            else -> r
        }
    }

    /**
     * Jackson naming policy is based upon use name only. However, for this policy we do take the type annotation
     * if it is available. If there is no annotation for the name, we get the name out of the useName in all cases
     * (the default policy is dependent on member kind and the output used (attribute vs element)).
     */
    override fun effectiveName(
        serializerParent: SafeParentInfo,
        tagParent: SafeParentInfo,
        outputKind: OutputKind,
        useName: XmlSerializationPolicy.DeclaredNameInfo
    ): QName {
        return useName.annotatedName
            ?: serializerParent.elemenTypeDescriptor.typeQname
            ?: serialNameToQName(useName.serialName, tagParent.namespace)
    }

}
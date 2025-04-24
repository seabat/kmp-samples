package dev.seabat.kmp.appsync.model

import com.amplifyframework.core.model.Model
import com.amplifyframework.core.model.annotations.ModelConfig
import com.amplifyframework.core.model.annotations.ModelField
import com.amplifyframework.core.model.temporal.Temporal

@ModelConfig(pluralName = "UserInfos")
data class UserInfo(
    @ModelField(targetType = "ID", isRequired = true)
    val userId: String,

    @ModelField(targetType = "Int", isRequired = true)
    val points: Int,

    @ModelField(targetType = "AWSDateTime", isRequired = true)
    val createdAt: Temporal.DateTime? = null,

    @ModelField(targetType = "AWSDateTime", isRequired = true)
    val updatedAt: Temporal.DateTime? = null
) : Model {
    override fun resolveIdentifier(): String = userId
} 
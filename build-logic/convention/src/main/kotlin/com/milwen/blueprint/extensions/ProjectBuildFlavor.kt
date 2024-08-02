package com.milwen.blueprint.extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.Project


enum class FlavorDimension {
    contentType
}

data class ApiEndpoint(
    val apiName: String,
    val url: String,
    val apiKey: String,
)

enum class AppFlavor(
    val dimension: FlavorDimension,
    val applicationIdSuffix: String? = null,
    val core: ApiEndpoint,
    val search: ApiEndpoint,
    val payment: ApiEndpoint,
) {
    dev(
        dimension = FlavorDimension.contentType,
        applicationIdSuffix = ".blueprint.dev",
        core = ApiEndpoint(apiName = "core_api", url = "https://blueprintbe-dev.milwen.sk", apiKey = "79c5a14d-3f44-4820-9373-56c59fdb962f"),
        search = ApiEndpoint(apiName = "search_api", url = "https://blueprintbe-search-dev.milwen.sk", apiKey = "d1c9e37f-5c92-4f4e-93b4-2a7bf30a6e9b"),
        payment = ApiEndpoint(apiName = "payment_api", url = "https://blueprintbe-payment-dev.milwen.sk", apiKey = "eaf6c91e-c93b-4d60-91de-13bb94d7fc95")
    ),
    prod_test(
        dimension = FlavorDimension.contentType,
        applicationIdSuffix = ".blueprint.test",
        core = ApiEndpoint(apiName = "core_api", url = "https://blueprintbe-test.milwen.sk", apiKey = "9df9cf63-bb17-4558-98dc-a5a8a26a94c4"),
        search = ApiEndpoint(apiName = "search_api", url = "https://blueprintbe-search-test.milwen.sk", apiKey = "c18d8dc1-65f0-4fed-aa27-63d589ab1b40"),
        payment = ApiEndpoint(apiName = "payment_api", url = "https://blueprintbe-payment-test.milwen.sk", apiKey = "3e1c5662-9b0e-45d6-8833-440dd55018b5")
    ),
    prod(
        dimension = FlavorDimension.contentType,
        applicationIdSuffix = ".blueprint",
        core = ApiEndpoint(apiName = "core_api", url = "https://blueprintbe.milwen.sk", apiKey = "9b5110f4-c4ac-4f73-8a45-929d38f70ad2"),
        search = ApiEndpoint(apiName = "search_api", url = "https://blueprintbe-search.milwen.sk", apiKey = "7eb38901-fb06-4780-87f0-94ce24e8443c"),
        payment = ApiEndpoint(apiName = "payment_api", url = "https://blueprintbe-payment.milwen.sk", apiKey = "ad0827ca-25f4-4229-bd55-cb3997b5d453")
    )
}

fun Project.configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: AppFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.contentType.name
        productFlavors {
            AppFlavor.values().forEach { flavor ->
                create(flavor.name) {
                    dimension = flavor.dimension.name
                    flavorConfigurationBlock(this, flavor)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (flavor.applicationIdSuffix != null) {
                            this.applicationIdSuffix = flavor.applicationIdSuffix
                        }

                        this.apply {
                            buildConfigField("String", "${flavor.core.apiName}_${flavor.core::url.name}", "\"${flavor.core.url}\"")
                            buildConfigField("String", "${flavor.core.apiName}_${flavor.core::apiKey.name}", "\"${flavor.core.apiKey}\"")

                            buildConfigField("String", "${flavor.search.apiName}_${flavor.search::url.name}", "\"${flavor.search.url}\"")
                            buildConfigField("String", "${flavor.search.apiName}_${flavor.search::apiKey.name}", "\"${flavor.search.apiKey}\"")

                            buildConfigField("String", "${flavor.payment.apiName}_${flavor.payment::url.name}", "\"${flavor.payment.url}\"")
                            buildConfigField("String", "${flavor.payment.apiName}_${flavor.payment::apiKey.name}", "\"${flavor.payment.apiKey}\"")
                        }
                    }
                }
            }
        }
    }
}
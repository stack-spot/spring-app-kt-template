package {{project_group_id}}

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import software.amazon.awscdk.App
import software.amazon.awscdk.Environment
import software.amazon.awscdk.StackProps
import java.nio.file.Paths

const val APP_NAME = "{{project_name}}"
const val APP_PORT = 8080

fun main() {
    val app = App()
    lateinit var stage: Any
    try {
        stage = app.node.tryGetContext("stage")
    } catch (ex: Exception) {
        throw Error("Stage parameter is required")
    }
    val stageObject =
        jacksonObjectMapper().readValue(Paths.get("..", "stages", "$stage.json").toFile(), Stage::class.java)
    val environment = Environment.builder()
        .account(stageObject.cloud.account.id)
        .region(stageObject.cloud.account.region)
        .build()
    val stackProps = StackProps.builder().env(environment).build()
    {{computed_inputs.camel_project_name}}CdkStack(app, "$APP_NAME-stack", stackProps, stageObject)
    app.synth()
}

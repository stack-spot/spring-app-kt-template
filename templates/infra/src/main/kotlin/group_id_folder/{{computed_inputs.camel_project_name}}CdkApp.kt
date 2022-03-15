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

    val stage = app.node.tryGetContext("stage")

    val stageObject =
        jacksonObjectMapper().readValue(Paths.get("..", "stages", "$stage.json").toFile(), Stage::class.java)

    val environment = Environment.builder()
        .account(System.getenv(stageObject.cloud.account.id))
        .region(System.getenv(stageObject.cloud.account.region))
        .build()

    val stackProps = StackProps.builder().env(environment).build()

    {{computed_inputs.camel_project_name}}CdkStack(app, "$APP_NAME-stack", stackProps, stageObject)

    app.synth()
}

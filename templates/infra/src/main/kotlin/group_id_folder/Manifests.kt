package {{project_group_id}}

import org.cdk8s.Chart
import org.cdk8s.ChartProps
import org.cdk8s.Duration
import org.cdk8s.Size
import org.cdk8s.plus22.ContainerProps
import org.cdk8s.plus22.Cpu
import org.cdk8s.plus22.CpuResources
import org.cdk8s.plus22.Deployment
import org.cdk8s.plus22.EnvValue
import org.cdk8s.plus22.HttpGetProbeOptions
import org.cdk8s.plus22.MemoryResources
import org.cdk8s.plus22.Probe
import org.cdk8s.plus22.Resources
import org.cdk8s.plus22.ServiceAccount
import software.constructs.Construct

class Manifests(scope: Construct, id: String, chartProps: ChartProps) : Chart(scope, id, chartProps) {

    companion object {
        val env = mutableMapOf<String, EnvValue>()
    }

    init {
        val probe = Probe.fromHttpGet(
            "/actuator/health",
            HttpGetProbeOptions.builder()
                .initialDelaySeconds(Duration.minutes(1))
                .periodSeconds(Duration.seconds(30))
                .build()
        )

        val resources = Resources.builder()
            .cpu(CpuResources.builder().request(Cpu.units(1)).limit(Cpu.units(2)).build())
            .memory(MemoryResources.builder().request(Size.gibibytes(2)).limit(Size.gibibytes(4)).build())
            .build()

        val containerProps = ContainerProps.builder()
            .name(APP_NAME)
            .image("{{project_image_name}}")
            .port(APP_PORT)
            .liveness(probe)
            .readiness(probe)
            .env(env)
            .resources(resources)
            .build()

        val deployment = Deployment.Builder.create(this, "deployment")
            .containers(listOf(containerProps))
            .serviceAccount(ServiceAccount.fromServiceAccountName(APP_NAME))
            .build()

        deployment.exposeViaService().exposeViaIngress("/")
    }
}

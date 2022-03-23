package {{project_group_id}}

import org.cdk8s.App
import org.cdk8s.ChartProps
import software.amazon.awscdk.Fn
import software.amazon.awscdk.Stack
import software.amazon.awscdk.StackProps
import software.amazon.awscdk.services.eks.Cluster
import software.amazon.awscdk.services.eks.ClusterAttributes
import software.amazon.awscdk.services.eks.OpenIdConnectProvider
import software.amazon.awscdk.services.eks.ServiceAccountOptions
import software.constructs.Construct

class {{computed_inputs.camel_project_name}}CdkStack(scope: Construct?, id: String?, props: StackProps?, stageObject: Stage) : Stack(scope, id, props) {

    init {
        val openIdOutput = stageObject.outputs?.get("openId")
            ?: throw IllegalStateException("The attribute outputs.openId is not present in the stage")

        val clusterNameOutput = stageObject.outputs["clusterName"]
            ?: throw IllegalStateException("The attribute outputs.clusterName is not present in the stage")

        val kubectlRoleOutput = stageObject.outputs["kubectlRole"]
            ?: throw IllegalStateException("The attribute outputs.kubectlRole is not present in the stage")

        val openId = Fn.importValue(openIdOutput)
        val clusterName = Fn.importValue(clusterNameOutput)
        val kubectlRole = Fn.importValue(kubectlRoleOutput)
        val namespace = stageObject.cloud.namespace ?: throw IllegalStateException("The attribute cloud.namespace is not present in the stage")
        val provider = OpenIdConnectProvider.fromOpenIdConnectProviderArn(this, "open-id", openId)

        val attributes = ClusterAttributes.builder()
            .clusterName(clusterName)
            .kubectlRoleArn(kubectlRole)
            .openIdConnectProvider(provider)
            .build()

        val chartProps = ChartProps.builder()
            .namespace(namespace)
            .build()

        val cluster = Cluster.fromClusterAttributes(this, "cluster", attributes)
        cluster.addCdk8sChart("chart", Manifests(App(), APP_NAME, chartProps))

        val accountOptions = ServiceAccountOptions.builder().name(APP_NAME.lowercase()).namespace(namespace).build()
        val accountRole = cluster.addServiceAccount("account", accountOptions).role
    }
}

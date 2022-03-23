package {{project_group_id}}

data class Stage(val cloud: Cloud, val stackName: String?, val outputs: Map<String, String>?) {
    data class Cloud(val account: Account, val vpcId: String?, val namespace: String?) {
        data class Account (val id: String, val region: String)
    }
}
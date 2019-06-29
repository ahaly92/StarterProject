import org.gradle.api.Project
import java.util.Properties

private var localProperties: Properties? = null

fun getLocalProperty(propertyName: String, project: Project): String {
    if (localProperties == null) {
        localProperties = Properties().apply {
            val localPropertiesFile = project.rootProject.file("local.properties")
            if (localPropertiesFile.exists()) {
                load(localPropertiesFile.inputStream())
            }
        }
    }

    val property = localProperties?.getProperty(propertyName)
    return property ?: ""
}

fun Project.getLocalProperty(propertyName: String): String {
    return getLocalProperty(propertyName, this)
}
